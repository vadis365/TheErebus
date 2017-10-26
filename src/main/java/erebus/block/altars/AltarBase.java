package erebus.block.altars;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModSounds;
import erebus.items.ItemMaterials;
import erebus.tileentity.TileEntityErebusAltarEmpty;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AltarBase extends AltarAbstract {

	private final Map<ItemMaterials.EnumErebusMaterialsType, Block> ALTAR_TYPES = new HashMap<ItemMaterials.EnumErebusMaterialsType, Block>();

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityErebusAltarEmpty();
	}

	@Override
	 public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		initMap();
		if (world.isRemote)
			return true;

		ItemStack stack = player.getHeldItem(hand);
		if (!stack.isEmpty() && stack.getItem() == ModItems.MATERIALS) {
			Block altar = ALTAR_TYPES.get(ItemMaterials.EnumErebusMaterialsType.values()[stack.getItemDamage()]);
			if (altar != null) {
				if (!world.isRemote) {
					world.setBlockState(pos, altar.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
					world.playSound((EntityPlayer)null, pos, ModSounds.ALTAR_OFFERING, SoundCategory.BLOCKS, 0.2F, 1.0F);
					if (!player.capabilities.isCreativeMode && stack.getCount() <= 0)
						player.setHeldItem(hand, ItemStack.EMPTY);
				} else
					for (int i = 0; i < 10; i++) {
						Random rand = world.rand;
						world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + rand.nextDouble(), pos.getY() + 1.1, pos.getZ() + rand.nextDouble(), 0D, 0D, 0D);
						world.spawnParticle(EnumParticleTypes.CLOUD, pos.getX() + rand.nextDouble(), pos.getY() + 1.1, pos.getZ() + rand.nextDouble(), 0D, 0D, 0D);
					}
				return true;
			}
		}

		return false;
	}

	private void initMap() {
		if (ALTAR_TYPES.isEmpty()) {
			ALTAR_TYPES.put(ItemMaterials.EnumErebusMaterialsType.BIO_VELOCITY, ModBlocks.ALTAR_XP);
			ALTAR_TYPES.put(ItemMaterials.EnumErebusMaterialsType.ELASTIC_FIBRE, ModBlocks.ALTAR_REPAIR);
			ALTAR_TYPES.put(ItemMaterials.EnumErebusMaterialsType.RED_GEM, ModBlocks.ALTAR_LIGHTNING);
			ALTAR_TYPES.put(ItemMaterials.EnumErebusMaterialsType.BIO_LUMINESCENCE, ModBlocks.ALTAR_HEALING);
		}
	}
}