package io.github.moderngamingworlds_mods.mods.woodenutilities;

import io.github.moderngamingworlds_mods.mods.woodenutilities.common.config.ModConfig;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.init.ModInit;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.utility.ModConstants;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModConstants.MOD_ID)
public class WoodenUtilities {

    public static ModConfig config;

    public WoodenUtilities() {
        ModInit.init(FMLJavaModLoadingContext.get().getModEventBus());
        // TODO re-add config
//        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
//        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
//        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, ModUtils::createConfigScreen);
    }
}
