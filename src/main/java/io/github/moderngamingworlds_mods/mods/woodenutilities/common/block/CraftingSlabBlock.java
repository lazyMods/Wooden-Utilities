package io.github.moderngamingworlds_mods.mods.woodenutilities.common.block;

import com.google.common.collect.ImmutableSet;
import io.github.moderngamingworlds_mods.mods.woodenutilities.WoodenUtilities;
import io.github.noeppi_noeppi.libx.mod.registration.Registerable;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;

public class CraftingSlabBlock extends SlabBlock implements Registerable {

    private final Item item;

    public CraftingSlabBlock() {
        super(Properties.copy(Blocks.OAK_SLAB));
        //noinspection ConstantConditions
        this.item = new BlockItem(this, new Item.Properties().tab(WoodenUtilities.getInstance().tab));
    }

    @Override
    @ParametersAreNonnullByDefault
    @Nonnull
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(level, pos));
            player.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    @ParametersAreNonnullByDefault
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos blockPos) {
        return new MenuProvider() {
            @Override
            @Nonnull
            public Component getDisplayName() {
                return new TranslatableComponent("container.crafting");
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

    @Override
    public Set<Object> getAdditionalRegisters(ResourceLocation id) {
        return ImmutableSet.of(this.item);
    }
}
