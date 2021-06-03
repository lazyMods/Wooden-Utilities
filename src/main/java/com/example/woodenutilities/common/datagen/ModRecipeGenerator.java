package com.example.woodenutilities.common.datagen;

import com.example.woodenutilities.common.init.ModItems;
import com.example.woodenutilities.common.item.buckets.EnumWoodenBucket;
import com.example.woodenutilities.common.item.plates.EnumWoodenPlate;
import net.minecraft.data.*;
import net.minecraft.tags.ItemTags;

import java.util.Objects;
import java.util.function.Consumer;

public class ModRecipeGenerator extends RecipeProvider {

    public ModRecipeGenerator(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModItems.WOODEN_SHEARS.get())
                .define('#', ItemTags.PLANKS)
                .pattern(" #")
                .pattern("# ")
                .unlockedBy("has_planks", has(ItemTags.PLANKS)).save(consumer);

        EnumWoodenPlate.asList().forEach(plate -> plateRecipe(plate, consumer));
        this.bucketRecipe(EnumWoodenBucket.EMPTY, consumer);
    }

    private void plateRecipe(EnumWoodenPlate plate, Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(plate.getRegistryObject().get())
                .requires(plate.getFromMaterial())
                .requires(ModItems.WOODEN_SHEARS.get())
                .unlockedBy("has_" + Objects.requireNonNull(plate.getFromMaterial().getRegistryName()).getPath()
                        , has(plate.getFromMaterial()))
                .save(consumer);
    }

    private void bucketRecipe(EnumWoodenBucket bucket, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(bucket.getItem())
                .define('#', ItemTags.PLANKS)
                .pattern("# #")
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy("has_planks", has(ItemTags.PLANKS)).save(consumer);
    }
}
