package com.example.woodenutilities.common.utility;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class ModUtils {

    public static ItemGroup createTab(String id, Supplier<Item> icon){
        return new ItemGroup(id) {
            @Override
            @Nonnull
            @OnlyIn(Dist.CLIENT)
            public ItemStack makeIcon() {
                return new ItemStack(icon.get());
            }
        };
    }
}
