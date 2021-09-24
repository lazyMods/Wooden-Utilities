package io.github.moderngamingworlds_mods.mods.woodenutilities.common.init;

import io.github.moderngamingworlds_mods.mods.woodenutilities.common.block.CraftingSlabBlock;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.utility.ModConstants;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.utility.ModLogger;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;

public class ModBlocks {

    public static final ModLogger logger = new ModLogger(LogManager.getLogger());

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModConstants.MOD_ID);

    public static final RegistryObject<Block> CRAFTING_SLAB = BLOCKS.register(ModConstants.Blocks.CRAFTING_SLAB, CraftingSlabBlock::new);

    public static void init(IEventBus bus){
        logger.debug("Registry: Blocks");
        BLOCKS.register(bus);
    }
}
