package io.github.moderngamingworlds_mods.mods.woodenutilities.common.datagen;

import io.github.moderngamingworlds_mods.mods.woodenutilities.common.item.buckets.EnumWoodenBucket;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.utility.ModConstants;
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

        this.addConfigScreenTranslations();

//        EnumWoodenPlate.asList().forEach(plate -> this.add(plate.getRegistryObject().get(), plate.getDisplayName()));
        EnumWoodenBucket.asList().forEach(bucket -> this.add(bucket.getRegistryObject().get(), bucket.getDisplayName()));

//        this.add(ModItems.WOODEN_SHEARS.get(), "Wooden Shears");
//        this.add(ModItems.WOODEN_DIAMOND.get(), "Wooden Diamond");
    }

    private void addConfigScreenTranslations(){
        String mainKey = "text.autoconfig.woodenutilities.";
        this.add(mainKey.concat("title"), "Wooden Utilities Configs");

        String wbKey = mainKey.concat("option.woodenBucket.");
        this.add(wbKey.substring(0, wbKey.length() - 1), "Wooden Bucket Configs.");
        this.add(wbKey.concat("destroyTime"), "Destroy bucket time.");
        this.add(wbKey.concat("destroyTime.@Tooltip"), "The time in ticks that the Wooden Bucket will take to self burn when picking a hot fluid.");
        this.add(wbKey.concat("maxTemperature"), "Max temperature.");
        this.add(wbKey.concat("maxTemperature.@Tooltip"), "The max temperature that the Wooden Bucket can hold until burns itself.");
        this.add(wbKey.concat("fireTime"), "Time to set on fire.");
        this.add(wbKey.concat("fireTime.@Tooltip"), "Number of seconds that the player will be set on fire when the bucket burn itself.");
    }
}
