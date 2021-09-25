package io.github.moderngamingworlds_mods.mods.woodenutilities.common.item.buckets;

import io.github.moderngamingworlds_mods.mods.woodenutilities.common.utility.ModConstants;
import io.github.moderngamingworlds_mods.mods.woodenutilities.common.utility.ModLogger;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.List;

public enum EnumWoodenBucket {

    EMPTY(ModConstants.Items.WOODEN_BUCKET, Fluids.EMPTY),
    LAVA(ModConstants.Items.WOODEN_BUCKET_LAVA, Fluids.LAVA),
    WATER(ModConstants.Items.WOODEN_BUCKET_WATER, Fluids.WATER);

    private final String regName;
    private final String displayName;
    private final Fluid withFluid;

    private RegistryObject<Item> registryObject;

    private static final ModLogger logger = new ModLogger(LogManager.getLogger());

    EnumWoodenBucket(String regName, Fluid withFluid) {
        this.regName = regName;
        this.displayName = "Fix Me"; // TODO
        this.withFluid = withFluid;
    }

    public static void registerAll(DeferredRegister<Item> deferredRegister) {
        logger.debug("Registering wooden plates...");
        asList().forEach(bucket -> bucket.setRegistryObject(deferredRegister.register(bucket.getRegistryName(), () -> new WoodenBucketItem(() -> bucket.withFluid))));
    }

    public static List<EnumWoodenBucket> asList() {
        return Arrays.asList(values());
    }

    public static ItemStack getBucket(Fluid fluid) {
        return asList().stream()
                .filter(bucket -> bucket.getFluid().isSame(fluid))
                .map(bucket -> new ItemStack(bucket.getItem()))
                .findFirst().orElseThrow(RuntimeException::new);
    }

    public Item getItem() {
        return this.getRegistryObject().get();
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

    public Fluid getFluid() {
        return this.withFluid;
    }
}
