package erebus.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.lib.EnumWood;
import erebus.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockWoodFence extends BlockFence {

	private final EnumWood wood;

	public BlockWoodFence(EnumWood wood) {
		super(null, Material.wood);
		this.wood = wood;
		setHardness(2.0F);
		setResistance(5.0F);
		setStepSound(soundTypeWood);
		setCreativeTab(ModTabs.blocks);
		setBlockName(Reference.MOD_ID + ".fence" + wood.name());
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return side == ForgeDirection.UP;
	}

	@Override
	public boolean canConnectFenceTo(IBlockAccess world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		return super.canConnectFenceTo(world, x, y, z) || block instanceof BlockWoodFence || block instanceof BlockWoodFenceGate;
	}

	@Override
	public String getLocalizedName() {
		return StatCollector.translateToLocal("tile." + Reference.MOD_ID + ".fence_" + wood.getUnlocalisedName() + ".name");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return ModBlocks.planks.getIcon(side, wood.ordinal());
	}
}