package com.example.woodenutilities.common.item.buckets;

import com.example.woodenutilities.WoodenUtilities;
import com.example.woodenutilities.common.init.ModInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Supplier;

import net.minecraft.world.item.Item.Properties;

import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.ItemUtils;

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
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected) {
        if(!level.isClientSide) {
            //TODO: Readd config maxTemperature
            if(this.content.getAttributes().getTemperature() >= 1300) {
                if(entity instanceof Player) {
                    Player playerEntity = (Player) entity;
                    if(!hasCooldown){
                        //TODO: Readd config destroyTime
                        playerEntity.getCooldowns().addCooldown(this, 50);
                        hasCooldown = true;
                    }
                    if(!playerEntity.getCooldowns().isOnCooldown(this)) {
                        playerEntity.getCooldowns().removeCooldown(this);
                        stack.shrink(1);
                        //TODO: Readd config fireTime
                        playerEntity.setSecondsOnFire(5);
                        playerEntity.broadcastBreakEvent(InteractionHand.MAIN_HAND);
                        hasCooldown = false;
                    }
                }
            }
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    @Nonnull
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        HitResult raytraceresult = getPlayerPOVHitResult(level, player, this.content == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(player, level, itemstack, raytraceresult);
        if(ret != null) return ret;
        if(raytraceresult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else if(raytraceresult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            BlockHitResult blockraytraceresult = (BlockHitResult) raytraceresult;
            BlockPos blockpos = blockraytraceresult.getBlockPos();
            Direction direction = blockraytraceresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if(level.mayInteract(player, blockpos) && player.mayUseItemAt(blockpos1, direction, itemstack)) {
                if(this.content == Fluids.EMPTY) {
                    BlockState blockstate1 = level.getBlockState(blockpos);
                    if(blockstate1.getBlock() instanceof BucketPickup) {
                        BucketPickup bucketPickup = (BucketPickup) blockstate1.getBlock();
                        ItemStack fluidStack = bucketPickup.pickupBlock(level, blockpos, blockstate1);
                        if(!fluidStack.isEmpty()) {
                            player.awardStat(Stats.ITEM_USED.get(this));

                            bucketPickup.getPickupSound().ifPresent((p_150709_) -> {
                                player.playSound(p_150709_, 1.0F, 1.0F);
                            });

                            ItemStack bucket = EnumWoodenBucket.getBucket(((BucketItem)fluidStack.getItem()).getFluid());

                            ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, bucket);
                            if(!level.isClientSide) {
                                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, bucket);
                            }

                            return InteractionResultHolder.sidedSuccess(itemstack1, level.isClientSide());
                        }
                    }

                    return InteractionResultHolder.fail(itemstack);
                } else {
                    BlockState blockstate = level.getBlockState(blockpos);
                    BlockPos blockpos2 = canBlockContainFluid(level, blockpos, blockstate) ? blockpos : blockpos1;
                    if(this.emptyContents(player, level, blockpos2, blockraytraceresult)) {
                        this.checkExtraContent(player, level, itemstack, blockpos2);
                        if(player instanceof ServerPlayer) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, blockpos2, itemstack);
                        }

                        player.awardStat(Stats.ITEM_USED.get(this));
                        return InteractionResultHolder.sidedSuccess(WoodenBucketItem.getEmptySuccessItem(itemstack, player), level.isClientSide());
                    } else {
                        return InteractionResultHolder.fail(itemstack);
                    }
                }
            } else {
                return InteractionResultHolder.fail(itemstack);
            }
        }
    }

    @ParametersAreNonnullByDefault
    @Nonnull
    public static ItemStack getEmptySuccessItem(ItemStack stack, Player player) {
        return !player.getAbilities().instabuild ? new ItemStack(EnumWoodenBucket.EMPTY.getItem()) : stack;
    }

    private boolean canBlockContainFluid(Level levelIn, BlockPos posIn, BlockState blockstate) {
        return blockstate.getBlock() instanceof LiquidBlockContainer && ((LiquidBlockContainer) blockstate.getBlock()).canPlaceLiquid(levelIn, posIn, blockstate, this.content);
    }
}
