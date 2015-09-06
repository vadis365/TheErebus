package erebus.block.plants;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.item.ItemErebusFood.FoodType;
import erebus.item.ItemMaterials.DATA;
import erebus.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockBerryBush extends Block {

	private String type;

	public BlockBerryBush(String bushType) {
		super(Material.plants);
		type = bushType;
		setUnlocalizedName(Reference.MOD_ID + "." + type + "BerryBush");
		setTickRandomly(true);
		setHardness(0.2F);
		setLightOpacity(1);
		setCreativeTab(ModTabs.plants);
		setStepSound(Block.soundTypeGrass);
	}

	@Override
	public int tickRate(World world) {
		return 10;
	}

	@Override
	public int getRenderType() {
		return 0;
	}

	@Override
	public String getLocalizedName() {
		return String.format(StatCollector.translateToLocal("tile." + Reference.MOD_ID + "." + type + "BerryBush.name"));
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
		int meta = access.getBlockState(pos).getBlock().getMetaFromState(access.getBlockState(pos));
		float widthReduced = 0, heightReduced = 0;

		switch (meta) {
			case 0:
				widthReduced = 0.25F;
				heightReduced = 0.5F;
				break;
			case 1:
				widthReduced = 0.125F;
				heightReduced = 0.25F;
				break;
			case 2:
				widthReduced = 0F;
				heightReduced = 0F;
				break;
			case 3:
				widthReduced = 0F;
				heightReduced = 0F;
				break;
		}
		setBlockBounds(widthReduced, 0F, widthReduced, 1F - widthReduced, 1F - heightReduced, 1F - widthReduced);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		int meta = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
		if (rand.nextInt(25) == 0)
			switch (meta) {
				case 0:
					world.setBlockState(pos, this.getStateFromMeta(1), 2);
					break;
				case 1:
					world.setBlockState(pos, this.getStateFromMeta(2), 2);
					break;
			}
		if (meta == 2 && rand.nextInt(50) == 0)
			world.setBlockState(pos, this.getStateFromMeta(3), 2);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
		int meta = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
		if (meta == 3) {
			world.playSoundAtEntity(player, "random.pop", 0.5F, 2.0F);
			if (!player.inventory.addItemStackToInventory(getBerry()))
				Utils.dropStack(world, (int) (pos.getX() + 0.5D), (int) (pos.getY() + 0.5D), (int) (pos.getZ() + 0.5D), getBerry());
			world.setBlockState(pos, this.getStateFromMeta(2), 2);
			return true;
		}
		return true;
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		int meta = getMetaFromState(state);
		if (meta == 3)
			Utils.dropStack(world, (int) (pos.getX() + 0.5D), (int) (pos.getY() + 0.5D), (int) (pos.getZ() + 0.5D), getBerry());
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return isValidBlock(world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock());
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighbour) {
		int meta = getMetaFromState(state);
		if (world.isAirBlock(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()))) {
			if (meta == 3)
				Utils.dropStack(world, (int) (pos.getX() + 0.5D), (int) (pos.getY() + 0.5D), (int) (pos.getZ() + 0.5D), getBerry());
			Utils.dropStack(world, (int) (pos.getX() + 0.5D), (int) (pos.getY() + 0.5D), (int) (pos.getZ() + 0.5D), new ItemStack(Item.getItemFromBlock(this)));
			world.setBlockToAir(pos);
		}
	}

	private boolean isValidBlock(Block block) {
		return block != null && block == ModBlocks.mud || block == Blocks.farmland;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return 1;
	}

	public ItemStack getBerry() {
		ItemStack item = null;
		if (type == "jade")
			item = new ItemStack(ModItems.materials, 1, DATA.jadeBerries.ordinal());
		if (type == "heart")
			item = new ItemStack(ModItems.heartBerries, 1);
		if (type == "swamp")
			item = new ItemStack(ModItems.food, 1, FoodType.swampBerries.ordinal());
		return item;
	}
}