package com.example.woodenutilities.common.builder;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.ToolType;

import java.util.function.Supplier;

public class BlockBuilder {

    public static final BlockBehaviour.Properties WOODEN_BLOCK = BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD);
    private int harvestLevel = 0;
    private BlockBehaviour.Properties properties = WOODEN_BLOCK;
    private Supplier<BlockEntity> tileEntitySupplier;
    private ToolType toolType = ToolType.PICKAXE;

    public int getHarvestLevel() {
        return harvestLevel;
    }

    public BlockBehaviour.Properties getProperties() {
        return properties;
    }

    public Supplier<BlockEntity> getTileEntitySupplier() {
        return tileEntitySupplier;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public BlockBuilder harvestLevel(ToolType type, int level) {
        this.toolType = type;
        this.harvestLevel = level;
        return this;
    }

    public BlockBuilder properties(BlockBehaviour.Properties properties) {
        this.properties = properties;
        return this;
    }

    public BlockBuilder tileEntitySupplier(Supplier<BlockEntity> supplier) {
        this.tileEntitySupplier = supplier;
        return this;
    }
}
