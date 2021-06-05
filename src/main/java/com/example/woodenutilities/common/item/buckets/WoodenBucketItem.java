package com.example.woodenutilities.common.item.buckets;

import com.example.woodenutilities.WoodenUtilities;
import com.example.woodenutilities.common.init.ModInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Supplier;

public class WoodenBucketItem extends BucketItem {

    private final Fluid content;
    private boolean hasCooldown = false;

    public WoodenBucketItem(Supplier<Fluid> inFluid) {
        super(inFluid, new Properties().tab(ModInit.CREATIVE_TAB).stacksTo(inFluid == Fluids.EMPTY ? 16 : 1));
        this.content = inFluid.get();
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(EnumWoodenBucket.EMPTY.getItem());
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void inventoryTick(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        if(!world.isClientSide) {
            if(this.content.getAttributes().getTemperature() >= WoodenUtilities.config.woodenBucket.maxTemperature) {
                if(entity instanceof PlayerEntity) {
                    PlayerEntity playerEntity = (PlayerEntity) entity;
                    if(!hasCooldown){
                        playerEntity.getCooldowns().addCooldown(this, WoodenUtilities.config.woodenBucket.destroyTime);
                        hasCooldown = true;
                    }
                    if(!playerEntity.getCooldowns().isOnCooldown(this)) {
                        playerEntity.getCooldowns().removeCooldown(this);
                        stack.shrink(1);
                        playerEntity.setSecondsOnFire(WoodenUtilities.config.woodenBucket.fireTime);
                        playerEntity.broadcastBreakEvent(Hand.MAIN_HAND);
                        hasCooldown = false;
                    }
                }
            }
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemstack = playerEntity.getItemInHand(hand);
        RayTraceResult raytraceresult = getPlayerPOVHitResult(world, playerEntity, this.content == Fluids.EMPTY ? RayTraceContext.FluidMode.SOURCE_ONLY : RayTraceContext.FluidMode.NONE);
        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerEntity, world, itemstack, raytraceresult);
        if(ret != null) return ret;
        if(raytraceresult.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.pass(itemstack);
        } else if(raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
            return ActionResult.pass(itemstack);
        } else {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
            BlockPos blockpos = blockraytraceresult.getBlockPos();
            Direction direction = blockraytraceresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if(world.mayInteract(playerEntity, blockpos) && playerEntity.mayUseItemAt(blockpos1, direction, itemstack)) {
                if(this.content == Fluids.EMPTY) {
                    BlockState blockstate1 = world.getBlockState(blockpos);
                    if(blockstate1.getBlock() instanceof IBucketPickupHandler) {
                        Fluid fluid = ((IBucketPickupHandler) blockstate1.getBlock()).takeLiquid(world, blockpos, blockstate1);
                        if(fluid != Fluids.EMPTY) {
                            playerEntity.awardStat(Stats.ITEM_USED.get(this));

                            SoundEvent soundevent = this.content.getAttributes().getFillSound();
                            if(soundevent == null)
                                soundevent = fluid.is(FluidTags.LAVA) ? SoundEvents.BUCKET_FILL_LAVA : SoundEvents.BUCKET_FILL;
                            playerEntity.playSound(soundevent, 1.0F, 1.0F);

                            ItemStack bucket = EnumWoodenBucket.getBucket(fluid);

                            ItemStack itemstack1 = DrinkHelper.createFilledResult(itemstack, playerEntity, bucket);
                            if(!world.isClientSide) {
                                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity) playerEntity, bucket);
                            }

                            return ActionResult.sidedSuccess(itemstack1, world.isClientSide());
                        }
                    }

                    return ActionResult.fail(itemstack);
                } else {
                    BlockState blockstate = world.getBlockState(blockpos);
                    BlockPos blockpos2 = canBlockContainFluid(world, blockpos, blockstate) ? blockpos : blockpos1;
                    if(this.emptyBucket(playerEntity, world, blockpos2, blockraytraceresult)) {
                        this.checkExtraContent(world, itemstack, blockpos2);
                        if(playerEntity instanceof ServerPlayerEntity) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) playerEntity, blockpos2, itemstack);
                        }

                        playerEntity.awardStat(Stats.ITEM_USED.get(this));
                        return ActionResult.sidedSuccess(this.getEmptySuccessItem(itemstack, playerEntity), world.isClientSide());
                    } else {
                        return ActionResult.fail(itemstack);
                    }
                }
            } else {
                return ActionResult.fail(itemstack);
            }
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    @Nonnull
    protected ItemStack getEmptySuccessItem(ItemStack stack, PlayerEntity playerEntity) {
        return !playerEntity.abilities.instabuild ? new ItemStack(EnumWoodenBucket.EMPTY.getItem()) : stack;
    }

    private boolean canBlockContainFluid(World worldIn, BlockPos posIn, BlockState blockstate) {
        return blockstate.getBlock() instanceof ILiquidContainer && ((ILiquidContainer) blockstate.getBlock()).canPlaceLiquid(worldIn, posIn, blockstate, this.content);
    }
}
