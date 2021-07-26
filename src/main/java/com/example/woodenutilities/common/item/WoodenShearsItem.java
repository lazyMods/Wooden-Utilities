package com.example.woodenutilities.common.item;

import com.example.woodenutilities.common.init.ModInit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;

import java.util.Random;

public class WoodenShearsItem extends ShearsItem {

    public WoodenShearsItem() {
        super(new Properties().durability(119).tab(ModInit.CREATIVE_TAB));
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        var inContainer = itemStack.copy();
        if (inContainer.hurt(1, new Random(), null)) return ItemStack.EMPTY;
        else return inContainer;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
