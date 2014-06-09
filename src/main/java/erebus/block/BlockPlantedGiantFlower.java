package erebus.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.ModItems;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import erebus.item.block.ItemBlockFlowerPlanted;
import erebus.world.feature.plant.WorldGenGiantFlowers;

public class BlockPlantedGiantFlower extends BlockSapling implements ISubBlocksBlock {

	public enum FLOWER_TYPE {
		BLACK, RED, BROWN, BLUE, PURPLE, CYAN, LIGHT_GRAY, GRAY, PINK, YELLOW, LIGHT_BLUE, MAGENTA, ORANGE, WHITE, RAINBOW
	}

	@SideOnly(Side.CLIENT)
	private IIcon main, petals, rainbowPetals;

	public BlockPlantedGiantFlower() {
		setCreativeTab(null);
		float var3 = 0.4F;
		setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 1F, 0.5F + var3);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block soil = world.getBlock(x, y - 1, z);
		return soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote) {
			super.updateTick(world, x, y, z, rand);
			if (rand.nextInt(13 - (world.getBlockLightValue(x, y + 1, z) >> 1)) == 0)
				func_149878_d(world, x, y, z, rand);
		}
	}

	@Override
	public void func_149879_c(World world, int x, int y, int z, Random rand) {
	}

	@Override
	public void func_149878_d(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);
		WorldGenerator worldGen = new WorldGenGiantFlowers();
		if (meta >= 0 && meta <= 13)
			((WorldGenGiantFlowers) worldGen).setFlowerColor(meta + 2);
		world.setBlockToAir(x, y, z);
		if (!worldGen.generate(world, rand, x, y, z))
			world.setBlock(x, y, z, ModBlocks.flowerPlanted, meta, 3);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 0 ? main : meta == FLOWER_TYPE.RAINBOW.ordinal() ? rainbowPetals : petals;
	}

	@Override
	public int getRenderType() {
		return BlockRenderIDs.PLANTED_FLOWER.id();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return ModItems.flowerSeeds;
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(ModItems.flowerSeeds, 1, meta));
		return ret;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < FLOWER_TYPE.values().length; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		main = reg.registerIcon("erebus:flowerPlanted0");
		petals = reg.registerIcon("erebus:flowerPlanted1");
		rainbowPetals = reg.registerIcon("erebus:flowerPlanted2");
	}

	@SubscribeEvent
	public void onBonemeal(BonemealEvent event) {
		if (!event.world.isRemote && event.block == this) {
			if (event.world.rand.nextFloat() < 0.45D)
				func_149878_d(event.world, event.x, event.y, event.z, event.world.rand);
			event.setResult(Result.ALLOW);
		}
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockFlowerPlanted.class;
	}
}