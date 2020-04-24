package ley.anvil.exposer;

import ley.anvil.exposer.block.BlockExposer;
import ley.anvil.exposer.tile.TileExposer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(
        modid = Exposer.MOD_ID,
        name = Exposer.MOD_NAME,
        version = Exposer.VERSION,
        dependencies = "required-after:refinedstorage",
        acceptableRemoteVersions = "*"
)
public class Exposer {

    public static final String MOD_ID = "exposer";
    public static final String MOD_NAME = "Exposer";
    public static final String VERSION = "${version}";

    @Mod.Instance(MOD_ID)
    public static Exposer INSTANCE;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        GameRegistry.registerTileEntity(TileExposer.class, new ResourceLocation(MOD_ID, "exposer"));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }





}
