package io.github.moderngamingworlds_mods.mods.woodenutilities.common.init;

import io.github.moderngamingworlds_mods.mods.woodenutilities.common.item.plates.EnumWoodenPlate;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.utility.ModConstants;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.utility.ModLogger;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.utility.ModUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = ModConstants.MOD_ID)
public class ModInit {

    public static final CreativeModeTab CREATIVE_TAB = ModUtils.createTab(ModConstants.MOD_ID, EnumWoodenPlate.OAK::getItem);

    public static final ModLogger logger = new ModLogger(LogManager.getLogger());

    public static void init(IEventBus bus){
        logger.debug("Initializing mod registries.");
        ModBlocks.init(bus);
        ModItems.init(bus);
    }
}
