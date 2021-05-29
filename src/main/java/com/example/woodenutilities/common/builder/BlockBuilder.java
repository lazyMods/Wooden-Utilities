package com.example.woodenutilities.common.builder;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ToolType;

import java.util.function.Supplier;

public class BlockBuilder {

    public static final AbstractBlock.Properties WOODEN_BLOCK = AbstractBlock.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD);
    private int harvestLevel = 0;
    private AbstractBlock.Properties properties = WOODEN_BLOCK;
    private Supplier<TileEntity> tileEntitySupplier;
    private ToolType toolType = ToolType.PICKAXE;

    public int getHarvestLevel() {
        return harvestLevel;
    }

    public AbstractBlock.Properties getProperties() {
        return properties;
    }

    public Supplier<TileEntity> getTileEntitySupplier() {
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

    public BlockBuilder properties(AbstractBlock.Properties properties) {
        this.properties = properties;
        return this;
    }

    public BlockBuilder tileEntitySupplier(Supplier<TileEntity> supplier) {
        this.tileEntitySupplier = supplier;
        return this;
    }
}