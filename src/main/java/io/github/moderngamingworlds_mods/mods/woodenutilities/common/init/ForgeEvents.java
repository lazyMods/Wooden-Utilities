package io.github.moderngamingworlds_mods.mods.woodenutilities.common.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SubscribeEvent
    public static void onBreakBlock(BlockEvent.BreakEvent event) {
        if (!event.getWorld().isClientSide()) {
            if (event.getPlayer().getMainHandItem() == ItemStack.EMPTY) {
                if (event.getState().is(BlockTags.LOGS)) {
                    //TODO: Config to change the chance of getting mining fatigue and maybe add duration of the effect.
                    if (event.getWorld().getRandom().nextFloat() <= 0.2f) {
                        event.getPlayer().addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 2000, 2));
                    }
                }
            }
        }
    }
}
