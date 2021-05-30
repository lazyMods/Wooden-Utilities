package com.example.woodenutilities.common.init;

import com.example.woodenutilities.common.item.WoodenShearsItem;
import com.example.woodenutilities.common.item.plates.EnumWoodenPlate;
import com.example.woodenutilities.common.utility.ModConstants;
import com.example.woodenutilities.common.utility.ModLogger;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;

public class ModItems {

    public static final ModLogger logger = new ModLogger(LogManager.getLogger());

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModConstants.MOD_ID);

    public static final RegistryObject<Item> WOODEN_SHEARS = ITEMS.register(
            ModConstants.Items.WOODEN_SHEARS, WoodenShearsItem::new
    );

    public static void init(IEventBus bus) {
        logger.debug("Registry: Items");

        EnumWoodenPlate.registerAll(ITEMS);

        ITEMS.register(bus);
    }
}
