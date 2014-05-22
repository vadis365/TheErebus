package erebus.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockErebusGrass extends BlockTallGrass {

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int blockID) {
		return blockID == Block.grass.blockID || blockID == Block.dirt.blockID || blockID == Block.tilledField.blockID || blockID == Block.sand.blockID;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block soil = world.getBlock(x, y - 1, z);
		return soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		return ColorizerFoliage.getFoliageColorBasic();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess access, int x, int y, int z) {
		return access.getBiomeGenForCoords(x, z).getBiomeGrassColor();
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return 1;
	}
}