package com.example.woodenutilities;

import com.example.woodenutilities.common.init.ModInit;
import com.example.woodenutilities.common.utility.ModConstants;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModConstants.MOD_ID)
public class WoodenUtilities {

    public WoodenUtilities() {
        ModInit.init(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
