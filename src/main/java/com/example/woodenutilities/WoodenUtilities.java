package com.example.woodenutilities;

import com.example.woodenutilities.common.config.ModConfig;
import com.example.woodenutilities.common.init.ModInit;
import com.example.woodenutilities.common.utility.ModConstants;
import com.example.woodenutilities.common.utility.ModUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModConstants.MOD_ID)
public class WoodenUtilities {

    public static ModConfig config;

    public WoodenUtilities() {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        ModInit.init(FMLJavaModLoadingContext.get().getModEventBus());
        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, ModUtils::createConfigScreen);
    }
}
