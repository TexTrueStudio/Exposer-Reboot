package ley.anvil.exposer;

import ley.anvil.exposer.block.BlockExposer;
import ley.anvil.exposer.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class Registry {

    @GameRegistry.ObjectHolder(Exposer.MOD_ID)
    public static class Blocks {
        public static final BlockExposer exposer = null;
    }

    @GameRegistry.ObjectHolder(Exposer.MOD_ID)
    public static class Items {
        public static final ItemBlock exposer = null;
    }

    @SubscribeEvent
    public static void addItems(RegistryEvent.Register<Item> event) {
        if (ModConfig.exposer)
            event.getRegistry().register(new ItemBlock(Blocks.exposer).setRegistryName(new ResourceLocation(Exposer.MOD_ID, BlockExposer.ID)));
    }


    @SubscribeEvent
    public static void addBlocks(RegistryEvent.Register<Block> event) {
        if (ModConfig.exposer)
            event.getRegistry().register(new BlockExposer());
    }

}
