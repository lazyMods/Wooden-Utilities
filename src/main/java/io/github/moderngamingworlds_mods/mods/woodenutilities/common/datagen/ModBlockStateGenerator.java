package io.github.moderngamingworlds_mods.mods.woodenutilities.common.datagen;

import io.github.moderngamingworlds_mods.mods.woodenutilities.WoodenUtilities;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.init.ModBlocks;
import io.github.noeppi_noeppi.libx.data.provider.BlockStateProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateGenerator extends BlockStateProviderBase {

    public ModBlockStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(WoodenUtilities.getInstance(), gen, exFileHelper);
    }

    @Override
    protected void setup() {

    }

    @Override
    protected void defaultState(ResourceLocation id, Block block, ModelFile model) {
        if (block == ModBlocks.craftingSlab) {
            this.slabLol(id, block, this.mcLoc("block/crafting_table_top"), this.mcLoc("block/crafting_table_side"), this.mcLoc("block/oak_planks"));
        } else {
            super.defaultState(id, block, model);
        }
    }

    @Override
    protected ModelFile defaultModel(ResourceLocation id, Block block) {
        if (block == ModBlocks.craftingSlab) {
            return null;
        }

        return super.defaultModel(id, block);
    }

    private void slabLol(ResourceLocation id, Block block, ResourceLocation top, ResourceLocation side, ResourceLocation bottom) {
        ModelFile modelBottom = this.models().slab(id.getPath(), side, bottom, top);
//        this.models().withExistingParent(id.getPath() + "_bottom", this.mcLoc("block/slab"))
//                .texture("top", top)
//                .texture("side", side)
//                .texture("bottom", bottom);
        ModelFile modelTop = this.models().slabTop(id.getPath() + "_top", side, bottom, top);
//                this.models().withExistingParent(id.getPath() + "_top", this.mcLoc("block/slab_top"))
//                .texture("top", top)
//                .texture("side", side)
//                .texture("bottom", bottom);
        ModelFile modelFull = this.models().cubeBottomTop(id.getPath() + "_full", side, bottom, top);
        VariantBlockStateBuilder builder = this.getVariantBuilder(block);

        for (SlabType type : BlockStateProperties.SLAB_TYPE.getPossibleValues()) {
            builder.partialState()
                    .with(BlockStateProperties.SLAB_TYPE, type)
                    .addModels(new ConfiguredModel(type == SlabType.TOP ? modelTop : type == SlabType.BOTTOM ? modelBottom : modelFull));
        }
    }

    @Override
    public void simpleBlock(Block block) {
        super.simpleBlock(block);
    }

    //    public ResourceLocation modLoc(String id) {
//        return new ResourceLocation(ModConstants.MOD_ID, id);
//    }
//
//    protected void basicBlock(Block block) {
//        simpleItemBlock(block, cubeAll(block));
//    }
//
//    protected void simpleItemBlock(Block block, ModelFile modelFile) {
//        simpleBlock(block, modelFile);
//        simpleBlockItem(block, modelFile);
//    }
}
