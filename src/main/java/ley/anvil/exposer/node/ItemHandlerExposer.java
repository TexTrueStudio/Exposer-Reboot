package ley.anvil.exposer.node;

import com.raoulvdberge.refinedstorage.api.network.INetwork;
import com.raoulvdberge.refinedstorage.api.storage.IStorageCache;
import com.raoulvdberge.refinedstorage.api.storage.IStorageCacheListener;
import com.raoulvdberge.refinedstorage.api.util.Action;
import com.raoulvdberge.refinedstorage.api.util.IComparer;
import com.raoulvdberge.refinedstorage.util.StackUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class ItemHandlerExposer implements IItemHandler, IStorageCacheListener<ItemStack> {

    private INetwork network;
    private ItemStack[] storageCacheData;

    public ItemHandlerExposer(INetwork network) {
        this.network = network;
        invalidate();
    }

    @Override
    public int getSlots() {
        return storageCacheData.length + 1;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot < storageCacheData.length) {
            return storageCacheData[slot];
        }

        return ItemStack.EMPTY;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return StackUtils.nullToEmpty(network.insertItem(stack, stack.getCount(), simulate ? Action.SIMULATE : Action.PERFORM));
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (slot < storageCacheData.length) {
            return StackUtils.nullToEmpty(network.extractItem(storageCacheData[slot], amount, IComparer.COMPARE_DAMAGE | IComparer.COMPARE_NBT, simulate ? Action.SIMULATE : Action.PERFORM));
        }
        return ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 64;
    }

    private void invalidate() {
        this.storageCacheData = network.getItemStorageCache().getList().getStacks().toArray(new ItemStack[0]);
    }

    @Override
    public void onAttached() {

    }

    @Override
    public void onInvalidated() {
        invalidate();
    }

    @Override
    public void onChanged(@Nonnull ItemStack stack, int size) {
        invalidate();
    }

}
