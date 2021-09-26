package io.github.moderngamingworlds_mods.woodenutilities.common.item.buckets;

import io.github.moderngamingworlds_mods.woodenutilities.common.init.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import java.util.Arrays;
import java.util.List;

public enum EnumWoodenBucket {

    EMPTY(Fluids.EMPTY),
    LAVA(Fluids.LAVA),
    WATER(Fluids.WATER);

    private final Fluid withFluid;

    EnumWoodenBucket(Fluid withFluid) {
        this.withFluid = withFluid;
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
        switch (this) {
            case WATER -> {
                return ModItems.waterWoodenBucket;
            }
            case LAVA -> {
                return ModItems.lavaWoodenBucket;
            }
            default -> {
                return ModItems.woodenBucket;
            }
        }
    }

    public Fluid getFluid() {
        return this.withFluid;
    }
}
