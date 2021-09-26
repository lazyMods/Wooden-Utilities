package io.github.moderngamingworlds_mods.woodenutilities.common.item;

import io.github.moderngamingworlds_mods.woodenutilities.WoodenUtilities;
import io.github.noeppi_noeppi.libx.base.ItemBase;
import net.minecraft.world.item.Item;

public class BaseItem extends ItemBase {

    public BaseItem() {
        super(WoodenUtilities.getInstance(), new Item.Properties());
    }
}
