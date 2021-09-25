package io.github.moderngamingworlds_mods.mods.woodenutilities.common.init;

import io.github.moderngamingworlds_mods.mods.woodenutilities.common.block.CraftingSlabBlock;
import io.github.noeppi_noeppi.libx.annotation.registration.RegisterClass;
import net.minecraft.world.level.block.Block;

@RegisterClass
public class ModBlocks {

    public static final Block craftingSlab = new CraftingSlabBlock();
}
