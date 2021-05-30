package com.example.woodenutilities.common.init;

import com.example.woodenutilities.common.utility.ModConstants;
import com.example.woodenutilities.common.utility.ModLogger;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = ModConstants.MOD_ID)
public class ModInit {

    public static final ItemGroup CREATIVE_TAB = new ItemGroup(ModConstants.MOD_ID) {
        @Override
        @Nonnull
        public ItemStack makeIcon() {
            return new ItemStack(Items.APPLE);
        }
    };

    public static final ModLogger logger = new ModLogger(LogManager.getLogger());

    public static void init(IEventBus bus){
        logger.debug("Initializing mod registries.");
        ModBlocks.init(bus);
        ModItems.init(bus);
    }
}
