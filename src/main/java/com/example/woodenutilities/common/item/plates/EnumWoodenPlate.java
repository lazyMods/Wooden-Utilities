package com.example.woodenutilities.common.item.plates;

import com.example.woodenutilities.common.item.WoodenPlateItem;
import com.example.woodenutilities.common.utility.ModConstants;
import com.example.woodenutilities.common.utility.ModLogger;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.List;

public enum EnumWoodenPlate {

    OAK(ModConstants.Items.OAK_PLATE, "Oak Plate", Items.OAK_PLANKS),
    BIRCH(ModConstants.Items.BIRCH_PLATE, "Birch Plate", Items.BIRCH_PLANKS),
    JUNGLE(ModConstants.Items.JUNGLE_PLATE, "Jungle Plate", Items.JUNGLE_PLANKS),
    ACACIA(ModConstants.Items.ACACIA_PLATE, "Acacia Plate", Items.ACACIA_PLANKS),
    DARK_OAK(ModConstants.Items.DARK_OAK_PLATE, "Dark Oak Plate", Items.DARK_OAK_PLANKS),
    SPRUCE(ModConstants.Items.SPRUCE_PLATE, "Spruce Plate", Items.SPRUCE_PLANKS),
    CRIMSON(ModConstants.Items.CRIMSON_PLATE, "Crimson Plate", Items.CRIMSON_PLANKS),
    WARPED(ModConstants.Items.WARPED_PLATE, "Warped Plate", Items.WARPED_PLANKS);

    private final String regName;
    private final String displayName;
    private final Item fromMaterial;

    private RegistryObject<Item> registryObject;

    private static final ModLogger logger = new ModLogger(LogManager.getLogger());

    EnumWoodenPlate(String regName, String displayName, Item fromMaterial) {
        this.regName = regName;
        this.displayName = displayName;
        this.fromMaterial = fromMaterial;
    }

    public static List<EnumWoodenPlate> asList() {
        return Arrays.asList(values());
    }

    public static void registerAll(DeferredRegister<Item> deferredRegister) {
        logger.debug("Registering wooden plates...");
        asList().forEach(plate -> plate.setRegistryObject(deferredRegister.register(plate.getRegistryName(), WoodenPlateItem::new)));
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }

    public RegistryObject<Item> getRegistryObject() {
        return this.registryObject;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getRegistryName() {
        return this.regName;
    }

    public Item getFromMaterial() {
        return this.fromMaterial;
    }
}
