package ley.anvil.exposer.config;

import ley.anvil.exposer.Exposer;
import net.minecraftforge.common.config.Config;

@Config(modid = Exposer.MOD_ID)
public class ModConfig {
    
    @Config.Name("Grid Expose")
    @Config.Comment("Should the Grid expose the networks contents? (requires Enable Exposing to be true)")
    public static boolean gridExpose = true;

    @Config.Name("Enable Exposer")
    @Config.Comment("Enables the Exposer block. Needs also to be on the client if true.")
    public static boolean exposer = true;

    @Config.Name("Enable Exposing")
    @Config.Comment("Enables the Controller to expose the network.")
    public static boolean expose = true;
    
}
