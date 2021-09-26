package io.github.moderngamingworlds_mods.woodenutilities.common.datagen;

import io.github.moderngamingworlds_mods.woodenutilities.WoodenUtilities;
import io.github.moderngamingworlds_mods.woodenutilities.common.init.ModItems;
import io.github.noeppi_noeppi.libx.data.provider.ItemModelProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.loaders.DynamicBucketModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemGenerator extends ItemModelProviderBase {
    public static final ResourceLocation DRIPPING_BUCKET = new ResourceLocation("forge", "bucket_drip");

    public ModItemGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(WoodenUtilities.getInstance(), generator, existingFileHelper);
    }

    @Override
    protected void setup() {
        this.handheld(ModItems.woodenDiamond);
        this.handheld(ModItems.woodenShears);
    }

    @Override
    protected void defaultItem(ResourceLocation id, Item item) {
        if (item instanceof BucketItem bucket) {
            this.bucket(id, bucket);
        } else {
            super.defaultItem(id, item);
        }
    }

    private void bucket(ResourceLocation id, BucketItem bucket) {
        this.withExistingParent(id.getPath(), DRIPPING_BUCKET)
                .texture("base", this.modLoc("item/wooden_bucket"))
                .customLoader(DynamicBucketModelBuilder::begin)
                .fluid(bucket.getFluid());
    }
}
