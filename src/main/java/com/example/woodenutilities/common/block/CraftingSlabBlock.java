package com.example.woodenutilities.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class CraftingSlabBlock extends SlabBlock {

    public CraftingSlabBlock() {
        super(Properties.copy(Blocks.OAK_SLAB));
    }

    @Override
    @ParametersAreNonnullByDefault
    @Nonnull
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
        if (!level.isClientSide) {
            player.openMenu(state.getMenuProvider(level, pos));
            return InteractionResult.SUCCESS;
        }
        return super.use(state, level, pos, player, hand, hitRes);
    }

    @Nullable
    @Override
    @ParametersAreNonnullByDefault
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos blockPos) {
        return new MenuProvider() {
            @Override
            @Nonnull
            public Component getDisplayName() {
                return new TranslatableComponent("container.crafting_slab.title");
            }

            @Override
            @ParametersAreNonnullByDefault
            public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
                return new CraftingMenu(id, playerInv, ContainerLevelAccess.create(level, blockPos)) {
                    @Override
                    public boolean stillValid(Player player1) {
                        return true;
                    }
                };
            }
        };
    }
}
