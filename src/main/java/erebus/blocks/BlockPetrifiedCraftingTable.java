package erebus.blocks;

import erebus.Erebus;
import erebus.ModTabs;
import erebus.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPetrifiedCraftingTable extends Block {

	public BlockPetrifiedCraftingTable() {
		super(Material.ROCK);
		setHardness(2.5F);
		setSoundType(SoundType.STONE);
		setCreativeTab(ModTabs.BLOCKS);
	}

	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		else {
			player.openGui(Erebus.INSTANCE, CommonProxy.GuiID.PETRIFIED_CRAFT.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
	}
}