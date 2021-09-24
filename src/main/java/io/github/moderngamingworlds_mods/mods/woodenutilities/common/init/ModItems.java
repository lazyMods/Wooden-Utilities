package io.github.moderngamingworlds_mods.mods.woodenutilities.common.init;

import io.github.moderngamingworlds_mods.mods.woodenutilities.common.item.WoodenShearsItem;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.item.buckets.EnumWoodenBucket;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.item.plates.EnumWoodenPlate;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.utility.ModConstants;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.utility.ModLogger;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.utility.ModUtils;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;

public class ModItems {

    public static final ModLogger logger = new ModLogger(LogManager.getLogger());

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModConstants.MOD_ID);

    public static final RegistryObject<Item> WOODEN_SHEARS = ITEMS.register(
            ModConstants.Items.WOODEN_SHEARS, WoodenShearsItem::new
    );

    public static final RegistryObject<Item> WOODEN_DIAMOND = ITEMS.register(
            ModConstants.Items.WOODEN_DIAMOND, ModUtils::createBasicItem
    );

    public static final RegistryObject<BlockItem> CRAFTING_SLAB = ITEMS.register(
            ModConstants.Blocks.CRAFTING_SLAB, () -> ModUtils.createBasicBlockItem(ModBlocks.CRAFTING_SLAB.get())
    );

    public static void init(IEventBus bus) {
        logger.debug("Registry: Items");

        EnumWoodenPlate.registerAll(ITEMS);
        EnumWoodenBucket.registerAll(ITEMS);

        ITEMS.register(bus);
    }
}
