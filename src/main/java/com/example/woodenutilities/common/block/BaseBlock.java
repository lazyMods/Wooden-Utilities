package com.example.woodenutilities.common.block;

import com.example.woodenutilities.common.builder.BlockBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BaseBlock extends Block {

    private final Supplier<BlockEntity> tileEntitySupplier;
    private final ToolType toolType;

    public BaseBlock(BlockBuilder builder) {
        super(builder.getProperties());
        this.tileEntitySupplier = builder.getTileEntitySupplier();
        this.toolType = builder.getToolType();
    }

    @Nullable
//    @Override FIXME
    public BlockEntity createTileEntity(BlockState state, BlockGetter level) {
        if(tileEntitySupplier == null) {
            return null;
        } else {
            return tileEntitySupplier.get();
        }
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(@Nonnull BlockState state) {
        return toolType;
    }

//    @Override FIXME
    public boolean hasTileEntity(BlockState state) {
        return tileEntitySupplier != null;
    }

    protected static Boolean never(BlockState state, BlockGetter blockReader, BlockPos pos, EntityType<?> entityType) {
        return false;
    }
}
