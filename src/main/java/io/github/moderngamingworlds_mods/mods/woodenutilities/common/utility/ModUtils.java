package io.github.moderngamingworlds_mods.mods.woodenutilities.common.utility;

import io.github.moderngamingworlds_mods.mods.woodenutilities.common.init.ModInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
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

    public static Item createBasicItem() {
        return new Item(new Item.Properties().tab(ModInit.CREATIVE_TAB));
    }

    public static BlockItem createBasicBlockItem(Block block){
        return new BlockItem(block, new Item.Properties().tab(ModInit.CREATIVE_TAB));
    }

    /*public static BiFunction<Minecraft, Screen, Screen> createConfigScreen() {
        return (client, parent) -> AutoConfig.getConfigScreen(ModConfig.class, parent).get();
    }*/
}
