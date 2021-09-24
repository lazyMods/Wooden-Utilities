package io.github.moderngamingworlds_mods.mods.woodenutilities.common.item.buckets;

import io.github.moderngamingworlds_mods.mods.woodenutilities.common.init.ModInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

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
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected) {
        if (!level.isClientSide) {
            //TODO: Readd config maxTemperature
            if (this.content.getAttributes().getTemperature() >= 1300) {
                if (entity instanceof Player playerEntity) {
                    if (!hasCooldown) {
                        //TODO: Readd config destroyTime
                        playerEntity.getCooldowns().addCooldown(this, 50);
                        hasCooldown = true;
                    }
                    if (!playerEntity.getCooldowns().isOnCooldown(this)) {
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
        var itemInHand = player.getItemInHand(hand);
        var raytraceresult = getPlayerPOVHitResult(level, player, this.content == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(player, level, itemInHand, raytraceresult);
        if (ret != null) return ret;
        if (raytraceresult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemInHand);
        } else if (raytraceresult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemInHand);
        } else {
            var blockraytraceresult = (BlockHitResult) raytraceresult;
            var blockpos = blockraytraceresult.getBlockPos();
            Direction direction = blockraytraceresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if (level.mayInteract(player, blockpos) && player.mayUseItemAt(blockpos1, direction, itemInHand)) {
                if (this.content == Fluids.EMPTY) {
                    var blockstate1 = level.getBlockState(blockpos);
                    if (blockstate1.getBlock() instanceof BucketPickup bucketPickup) {
                        var fluidStack = bucketPickup.pickupBlock(level, blockpos, blockstate1);
                        if (!fluidStack.isEmpty()) {
                            player.awardStat(Stats.ITEM_USED.get(this));

                            bucketPickup.getPickupSound().ifPresent((p_150709_) -> {
                                player.playSound(p_150709_, 1.0F, 1.0F);
                            });

                            var bucket = EnumWoodenBucket.getBucket(((BucketItem) fluidStack.getItem()).getFluid());

                            var itemstack1 = ItemUtils.createFilledResult(itemInHand, player, bucket);
                            if (!level.isClientSide) {
                                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, bucket);
                            }

                            return InteractionResultHolder.sidedSuccess(itemstack1, level.isClientSide());
                        }
                    }

                    return InteractionResultHolder.fail(itemInHand);
                } else {
                    var blockstate = level.getBlockState(blockpos);
                    var blockpos2 = canBlockContainFluid(level, blockpos, blockstate) ? blockpos : blockpos1;
                    if (this.emptyContents(player, level, blockpos2, blockraytraceresult)) {
                        this.checkExtraContent(player, level, itemInHand, blockpos2);
                        if (player instanceof ServerPlayer serverPlayer) {
                            CriteriaTriggers.PLACED_BLOCK.trigger(serverPlayer, blockpos2, itemInHand);
                        }

                        player.awardStat(Stats.ITEM_USED.get(this));
                        return InteractionResultHolder.sidedSuccess(WoodenBucketItem.getEmptySuccessItem(itemInHand, player), level.isClientSide());
                    } else {
                        return InteractionResultHolder.fail(itemInHand);
                    }
                }
            } else {
                return InteractionResultHolder.fail(itemInHand);
            }
        }
    }

    @ParametersAreNonnullByDefault
    @Nonnull
    public static ItemStack getEmptySuccessItem(ItemStack stack, Player player) {
        return !player.getAbilities().instabuild ? new ItemStack(EnumWoodenBucket.EMPTY.getItem()) : stack;
    }

    private boolean canBlockContainFluid(Level levelIn, BlockPos posIn, BlockState blockstate) {
        return blockstate.getBlock() instanceof LiquidBlockContainer container && container.canPlaceLiquid(levelIn, posIn, blockstate, this.content);
    }
}
