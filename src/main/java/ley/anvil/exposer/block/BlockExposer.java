package ley.anvil.exposer.block;

import com.raoulvdberge.refinedstorage.RS;
import ley.anvil.exposer.Exposer;
import ley.anvil.exposer.tile.TileExposer;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockExposer extends Block implements ITileEntityProvider {

    public static final String ID = "exposer";

    public BlockExposer() {
        super(Material.IRON);
        setHardness(1);
        setRegistryName(new ResourceLocation(Exposer.MOD_ID, ID));
        setTranslationKey(Exposer.MOD_ID + "." + ID);
        setCreativeTab(RS.INSTANCE.tab);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileExposer();
    }

}
