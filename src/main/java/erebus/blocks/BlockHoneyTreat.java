package erebus.blocks;

import erebus.ModBlocks;
import erebus.ModTabs;
import net.minecraft.block.BlockCake;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockHoneyTreat extends BlockCake {

	public BlockHoneyTreat() {
		setHardness(0.5F);
		setSoundType(SoundType.CLOTH);
		setCreativeTab(ModTabs.BLOCKS);
	}

	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote)
			return this.eatTreatSlice(world, pos, state, player);
		else {
			ItemStack itemstack = player.getHeldItem(hand);
			return this.eatTreatSlice(world, pos, state, player) || itemstack.isEmpty();
		}
	}

	private boolean eatTreatSlice(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (player.canEat(false)) {
			player.getFoodStats().addStats(2, 0.1F);
			player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 5 * 20, 1));
			int i = ((Integer) state.getValue(BITES)).intValue();
			if (i < 6)
				world.setBlockState(pos, state.withProperty(BITES, Integer.valueOf(i + 1)), 3);
			else
				world.setBlockToAir(pos);
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(ModBlocks.HONEY_TREAT));
	}

}