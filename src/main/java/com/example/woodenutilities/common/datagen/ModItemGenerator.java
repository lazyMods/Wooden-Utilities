package com.example.woodenutilities.common.datagen;
import com.example.woodenutilities.common.utility.ModConstants;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemGenerator extends ItemModelProvider {

    public ModItemGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ModConstants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }
}
