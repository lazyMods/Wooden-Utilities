package com.example.woodenutilities.common.item;

import com.example.woodenutilities.common.init.ModInit;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;

import java.util.Random;

public class WoodenShearsItem extends ShearsItem {

    //TODO: Should the shears drop the wool

    public WoodenShearsItem() {
        super(new Properties().durability(119).tab(ModInit.CREATIVE_TAB));
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack inContainer = itemStack.copy();
        if(inContainer.hurt(1, new Random(), null)) return ItemStack.EMPTY;
        else return inContainer;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
