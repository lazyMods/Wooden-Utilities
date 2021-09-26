package io.github.moderngamingworlds_mods.woodenutilities.common.item;

import io.github.moderngamingworlds_mods.woodenutilities.WoodenUtilities;
import io.github.noeppi_noeppi.libx.base.ItemBase;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class WoodenShears extends ItemBase {

    public WoodenShears() {
        super(WoodenUtilities.getInstance(), new Item.Properties().durability(119));
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        int damage = stack.getDamageValue() + 1;
        if (damage >= stack.getMaxDamage()) {
            return ItemStack.EMPTY;
        }

        stack.setDamageValue(damage);
        return stack.copy();
    }
}
