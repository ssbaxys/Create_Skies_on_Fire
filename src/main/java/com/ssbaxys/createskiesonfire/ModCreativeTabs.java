package com.ssbaxys.createskiesonfire;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, CreateSkiesOnFire.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SKIES_ON_FIRE_TAB = CREATIVE_MODE_TABS.register(
            "skies_on_fire_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.create_skies_on_fire"))
                    .icon(() -> new ItemStack(ModBlocks.ROCKET_BASE.get()))
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
