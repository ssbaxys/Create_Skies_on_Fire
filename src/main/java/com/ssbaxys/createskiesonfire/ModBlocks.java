package com.ssbaxys.createskiesonfire;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
                        CreateSkiesOnFire.MOD_ID);

        public static final RegistryObject<Block> ROCKET_BASE = registerBlock("rocket_base",
                        () -> new RocketBaseBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
        public static final RegistryObject<Block> ROCKET_THRUSTER = registerBlock("rocket_thruster",
                        () -> new RocketThrusterBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
        public static final RegistryObject<Block> ROCKET_HULL = registerBlock("rocket_hull",
                        () -> new RocketHullBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
        public static final RegistryObject<Block> ROCKET_NOSE_CONE = registerBlock("rocket_nose_cone",
                        () -> new RocketNoseConeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));

        private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
                RegistryObject<T> toReturn = BLOCKS.register(name, block);
                registerBlockItem(name, toReturn);
                return toReturn;
        }

        private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
                return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        }

        public static void register(IEventBus eventBus) {
                BLOCKS.register(eventBus);
        }
}
