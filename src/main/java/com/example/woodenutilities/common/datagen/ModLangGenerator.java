package com.example.woodenutilities.common.datagen;

import com.example.woodenutilities.common.init.ModItems;
import com.example.woodenutilities.common.item.plates.EnumWoodenPlate;
import com.example.woodenutilities.common.utility.ModConstants;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLangGenerator extends LanguageProvider {

    public ModLangGenerator(DataGenerator gen) {
        super(gen, ModConstants.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add("itemGroup.woodenutilities", "Wooden Utilities");

        this.add("book.name", "Wooden Utilities: Guide");
        this.add("book.landing_text", "This is the text that appears on the first page.");

        EnumWoodenPlate.asList().forEach(plate -> this.add(plate.getRegistryObject().get(), plate.getDisplayName()));

        this.add(ModItems.WOODEN_SHEARS.get(), "Wooden Shears");
    }
}
