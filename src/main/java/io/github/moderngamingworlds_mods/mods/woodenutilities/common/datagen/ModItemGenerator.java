package io.github.moderngamingworlds_mods.mods.woodenutilities.common.datagen;

import io.github.moderngamingworlds_mods.mods.woodenutilities.WoodenUtilities;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.init.ModItems;
import io.github.noeppi_noeppi.libx.data.provider.ItemModelProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemGenerator extends ItemModelProviderBase {

    public ModItemGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(WoodenUtilities.getInstance(), generator, existingFileHelper);
    }

    @Override
    protected void setup() {
        this.handheld(ModItems.woodenDiamond);
        this.handheld(ModItems.woodenShears);
    }
}
