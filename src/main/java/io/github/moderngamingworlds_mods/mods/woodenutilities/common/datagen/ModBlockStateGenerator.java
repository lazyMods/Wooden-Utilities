package io.github.moderngamingworlds_mods.mods.woodenutilities.common.datagen;

import io.github.moderngamingworlds_mods.mods.woodenutilities.common.utility.ModConstants;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateGenerator extends BlockStateProvider {

    public ModBlockStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ModConstants.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }

    public ResourceLocation modLoc(String id) {
        return new ResourceLocation(ModConstants.MOD_ID, id);
    }

    protected void basicBlock(Block block) {
        simpleItemBlock(block, cubeAll(block));
    }

    protected void simpleItemBlock(Block block, ModelFile modelFile) {
        simpleBlock(block, modelFile);
        simpleBlockItem(block, modelFile);
    }

}
