package com.example.woodenutilities.common.config;

import com.example.woodenutilities.common.utility.ModConstants;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import static me.shedaniel.autoconfig.annotation.ConfigEntry.*;

@Config(name = ModConstants.MOD_ID)
public class ModConfig implements ConfigData {

    @Gui.CollapsibleObject
    public WoodenBucket woodenBucket = new WoodenBucket();

    public static class WoodenBucket {

        @Gui.Tooltip
        public int destroyTime = 50;
        @Gui.Tooltip
        public int maxTemperature = 1300;
        @Gui.Tooltip
        public int fireTime = 5;
    }
}
