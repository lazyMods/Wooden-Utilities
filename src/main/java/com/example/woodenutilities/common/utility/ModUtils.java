package com.example.woodenutilities.common.utility;

//import com.example.woodenutilities.common.config.ModConfig;
//import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ModUtils {

    public static CreativeModeTab createTab(String id, Supplier<Item> icon) {
        return new CreativeModeTab(id) {
            @Override
            @Nonnull
            @OnlyIn(Dist.CLIENT)
            public ItemStack makeIcon() {
                return new ItemStack(icon.get());
            }
        };
    }

//    public static BiFunction<Minecraft, Screen, Screen> createConfigScreen() {
//        return (client, parent) -> AutoConfig.getConfigScreen(ModConfig.class, parent).get();
//    }
}
