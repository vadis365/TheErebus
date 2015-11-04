package erebus.block.plants;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.item.ItemErebusFood.FoodType;
import erebus.item.ItemMaterials.DATA;
import erebus.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBerryBush extends Block {

	private String type;
	@SideOnly(Side.CLIENT)
	private IIcon fastIcon, fastFruitIcon, fruitIcon;

	public BlockBerryBush(String bushType) {
		super(Material.plants);
		type = bushType;
		setBlockName(Reference.MOD_ID + "." + type + "BerryBush");
		setBlockTextureName(Reference.MOD_ID + ":" + type + "BerryBush");
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
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta > 2)
			return Minecraft.getMinecraft().gameSettings.fancyGraphics ? fruitIcon : fastFruitIcon;

		return Minecraft.getMinecraft().gameSettings.fancyGraphics ? blockIcon : fastIcon;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon(getTextureName() + "Fancy");
		fastIcon = reg.registerIcon(getTextureName() + "Fast");
		fruitIcon = reg.registerIcon(getTextureName() + "FruitedFancy");
		fastFruitIcon = reg.registerIcon(getTextureName() + "FruitedFast");
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
		int meta = access.getBlockMetadata(x, y, z);
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
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);
		if (rand.nextInt(25) == 0)
			switch (meta) {
				case 0:
					world.setBlock(x, y, z, this, 1, 2);
					break;
				case 1:
					world.setBlock(x, y, z, this, 2, 2);
					break;
			}
		if (meta == 2 && rand.nextInt(50) == 0)
			world.setBlock(x, y, z, this, 3, 2);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 3) {
			world.playSoundAtEntity(player, "random.pop", 0.5F, 2.0F);
			if (!player.inventory.addItemStackToInventory(getBerry()))
				Utils.dropStack(world, (int) (x + 0.5D), (int) (y + 0.5D), (int) (z + 0.5D), getBerry());
			world.setBlock(x, y, z, this, 2, 2);
			return true;
		}
		return true;
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int id, EntityPlayer player) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 3)
			Utils.dropStack(world, (int) (x + 0.5D), (int) (y + 0.5D), (int) (z + 0.5D), getBerry());
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x, y - 1, z)) && canBlockStay(world, x, y, z);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x, y - 1, z));
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		int meta = world.getBlockMetadata(x, y, z);
		if (world.isAirBlock(x, y - 1, z)) {
			if (meta == 3)
				Utils.dropStack(world, (int) (x + 0.5D), (int) (y + 0.5D), (int) (z + 0.5D), getBerry());
			Utils.dropStack(world, (int) (x + 0.5D), (int) (y + 0.5D), (int) (z + 0.5D), new ItemStack(Item.getItemFromBlock(this)));
			world.setBlockToAir(x, y, z);
		}
		canBlockStay(world, x, y, z);
	}

	private boolean isValidBlock(Block block) {
		return block != null && block == ModBlocks.mud || block == Blocks.farmland;
	}

	@Override
	public int damageDropped(int meta) {
		return 0;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		return 1;
	}

	public ItemStack getBerry() {
		ItemStack item = null;
		if (type == "jade")
			item = new ItemStack(ModItems.materials, 1, DATA.jadeBerries.ordinal());
		if (type == "heart")
			item = new ItemStack(ModItems.heartBerries, 1);
		if (type == "swamp")
			item = new ItemStack(ModItems.food, 1, FoodType.SWAMPBERRIES.ordinal());
		return item;
	}
}