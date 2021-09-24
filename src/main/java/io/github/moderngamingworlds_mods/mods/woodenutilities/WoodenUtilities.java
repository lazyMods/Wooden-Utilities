package io.github.moderngamingworlds_mods.mods.woodenutilities;

import io.github.moderngamingworlds_mods.mods.woodenutilities.common.init.ModInit;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.item.plates.EnumWoodenPlate;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.utility.ModUtils;
import io.github.noeppi_noeppi.libx.mod.registration.ModXRegistration;
import io.github.noeppi_noeppi.libx.mod.registration.RegistrationBuilder;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("woodenutilities")
public class WoodenUtilities extends ModXRegistration {

    public WoodenUtilities() {
        super("woodenutilities", ModUtils.createTab("woodenutilities", EnumWoodenPlate.OAK::getItem));
        ModInit.init(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @Override
    protected void setup(FMLCommonSetupEvent event) {

    }

    @Override
    protected void clientSetup(FMLClientSetupEvent event) {

    }

    @Override
    protected void initRegistration(RegistrationBuilder builder) {
        builder.setVersion(1);
    }
}
