package ley.anvil.exposer.event;

import com.raoulvdberge.refinedstorage.tile.TileController;
import com.raoulvdberge.refinedstorage.tile.grid.TileGrid;
import ley.anvil.exposer.Exposer;
import ley.anvil.exposer.cap.CoreCapProvider;
import ley.anvil.exposer.cap.GridCapProvider;
import ley.anvil.exposer.config.ModConfig;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Exposer.MOD_ID)
public class EventHandler {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<TileEntity> event) {
        if (event.getObject() instanceof TileController && ModConfig.expose) {
            event.addCapability(new ResourceLocation(Exposer.MOD_ID, "coreexpose"), new CoreCapProvider((TileController) event.getObject()));
        } else if (event.getObject() instanceof TileGrid && ModConfig.gridExpose && ModConfig.expose) {
            event.addCapability(new ResourceLocation(Exposer.MOD_ID, "gridexpose"), new GridCapProvider((TileGrid) event.getObject()));
        }

    }

}
