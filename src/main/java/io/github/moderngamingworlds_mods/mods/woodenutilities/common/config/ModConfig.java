package io.github.moderngamingworlds_mods.mods.woodenutilities.common.config;

import io.github.noeppi_noeppi.libx.annotation.config.RegisterConfig;
import io.github.noeppi_noeppi.libx.config.Config;
import io.github.noeppi_noeppi.libx.config.Group;

@RegisterConfig
public class ModConfig {

    @Group
    public static class WoodenBucket {

        @Config
        public static int destroyTime = 50;

        @Config
        public static int maxTemperature = 1300;

        @Config
        public static int fireTime = 5;
    }
}
