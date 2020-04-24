package ley.anvil.exposer.cap;

import com.raoulvdberge.refinedstorage.tile.TileController;
import com.raoulvdberge.refinedstorage.tile.TileExternalStorage;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CoreCapProvider implements ICapabilityProvider {

    TileController core;
    ItemHandlerExposer handler;

    public CoreCapProvider(TileController core) {
        this.core = core;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing != null) {
                BlockPos pos = core.getPos().offset(facing);
                if (core.getWorld().isBlockLoaded(pos)) {
                    TileEntity te = core.getWorld().getTileEntity(pos);
                    if (te instanceof TileExternalStorage) {
                        return false;
                    }
                }
            }

            if (handler != null) {
                return true;
            }
            else if (core.getItemStorageCache() != null){
                handler = new ItemHandlerExposer(core);
                core.getItemStorageCache().addListener(handler);
                return true;
            }
        }
        return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (hasCapability(capability, facing)) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(handler);
        }
        return null;
    }


}
