package com.example.woodenutilities.common.datagen;

import com.example.woodenutilities.common.item.plates.EnumWoodenPlate;
import com.example.woodenutilities.common.utility.ModConstants;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemGenerator extends ItemModelProvider {

    private static final String ITEMS_TAG = "items/";
    private static final String ITEM_HANDHELD_TAG = "item/handheld";
    private static final String LAYER_0_TAG = "layer0";

    public ModItemGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ModConstants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        EnumWoodenPlate.asList().forEach(plate -> this.handheldZeroLayeredModel(plate.getRegistryName()));

        this.handheldZeroLayeredModel(ModConstants.Items.WOODEN_SHEARS);
    }

    private void handheldZeroLayeredModel(String regName) {
        this.singleTexture(regName, new ResourceLocation(ITEM_HANDHELD_TAG), LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + regName));
    }
}
