package ley.anvil.exposer.cap;

import com.raoulvdberge.refinedstorage.tile.TileExternalStorage;
import com.raoulvdberge.refinedstorage.tile.grid.TileGrid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GridCapProvider implements ICapabilityProvider {

    TileGrid grid;
    TileEntity core;

    public GridCapProvider(TileGrid grid) {
        this.grid = grid;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && grid.getNode().getNetwork() instanceof TileEntity) {
            if (facing != null) {
                BlockPos pos = grid.getPos().offset(facing);
                if (grid.getWorld().isBlockLoaded(pos)) {
                    TileEntity te = grid.getWorld().getTileEntity(pos);
                    if (te instanceof TileExternalStorage) {
                        return false;
                    }
                }
            }
            if (core == null || !grid.getNode().isActive()) {
                core = (TileEntity) grid.getNode().getNetwork();
            }
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (hasCapability(capability, facing)) {
            return core.getCapability(capability, null);
        }
        return null;
    }

}
