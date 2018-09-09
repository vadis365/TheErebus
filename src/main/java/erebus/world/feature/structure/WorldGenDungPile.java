package erebus.world.feature.structure;

import java.util.List;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModFluids;
import erebus.ModItems;
import erebus.items.ItemErebusFood.EnumFoodType;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.loot.IPostProcess;
import erebus.world.loot.LootItemStack;
import erebus.world.loot.LootUtil;
import erebus.world.loot.WeightedLootList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class WorldGenDungPile extends WorldGenerator {
	
	public int height = 5;
	public int radius = 5;
	public IBlockState DUNG = ModBlocks.DUNG.getDefaultState();
	
	public static final WeightedLootList CHEST_LOOT = new WeightedLootList(new LootItemStack[] {
			new LootItemStack(ModItems.MATERIALS).setAmount(1, 3).setDamage(EnumErebusMaterialsType.JADE.ordinal()).setWeight(10),
			new LootItemStack(ModItems.MATERIALS).setAmount(4, 8).setDamage(EnumErebusMaterialsType.PLATE_EXO.ordinal()).setWeight(9),
			new LootItemStack(Items.ENCHANTED_BOOK).setWeight(8),
			new LootItemStack(Items.IRON_PICKAXE).setWeight(2),
			new LootItemStack(ModItems.JADE_PICKAXE).setWeight(1),
			new LootItemStack(Items.IRON_SHOVEL).setWeight(2),
			new LootItemStack(ModItems.JADE_SHOVEL).setWeight(1),
			new LootItemStack(Items.IRON_AXE).setWeight(2),
			new LootItemStack(ModItems.JADE_AXE).setWeight(1),
			new LootItemStack(Items.IRON_SWORD).setWeight(2),
			new LootItemStack(ModItems.JADE_SWORD).setWeight(1),
			new LootItemStack(Items.IRON_CHESTPLATE).setWeight(2), new LootItemStack(ModItems.JADE_CHESTPLATE).setWeight(1),
			new LootItemStack(Items.IRON_HELMET).setWeight(2),
			new LootItemStack(ModItems.JADE_HELMET).setWeight(1),
			new LootItemStack(Items.IRON_LEGGINGS).setWeight(2), new LootItemStack(ModItems.JADE_LEGGINGS).setWeight(1),
			new LootItemStack(Items.IRON_BOOTS).setWeight(2),
			new LootItemStack(ModItems.JADE_BOOTS).setWeight(1),
			new LootItemStack(ModItems.EXOSKELETON_SHIELD).setAmount(1).setWeight(3),
			new LootItemStack(ModItems.BAMBOO_SHIELD).setAmount(1).setWeight(3),
			new LootItemStack(ModItems.ANTI_VENOM_BOTTLE).setAmount(1, 3).setWeight(8),
			new LootItemStack(ModItems.SPRAY_CAN).setAmount(3, 6).setWeight(5),
			new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(EnumErebusMaterialsType.SCORPION_PINCER.ordinal()).setWeight(1),
			new LootItemStack(ModItems.MATERIALS).setAmount(1, 3).setDamage(EnumErebusMaterialsType.REINFORCED_PLATE_EXO.ordinal()).setWeight(4),
			new LootItemStack(ModItems.MATERIALS).setAmount(3, 9).setDamage(EnumErebusMaterialsType.AMBER_STAR.ordinal()).setWeight(6),
			new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(EnumErebusMaterialsType.BEETLE_RIDING_KIT.ordinal()).setWeight(1),
			new LootItemStack(ModItems.EREBUS_FOOD).setAmount(1, 3).setDamage(EnumFoodType.TITAN_CHOP_RAW.ordinal()).setWeight(6),
			new LootItemStack(ModItems.EREBUS_FOOD).setAmount(1, 2).setDamage(EnumFoodType.TITAN_CHOP_COOKED.ordinal()).setWeight(4),
			new LootItemStack(ModItems.STAG_HEART_RAW).setAmount(1).setWeight(1),
			new LootItemStack(ModItems.BAMBUCKET).setAmount(1).setWeight(3),
			new LootItemStack(ModItems.CABBAGE_SEEDS).setAmount(3).setWeight(5),
			new LootItemStack(ModItems.EREBUS_FOOD).setAmount(1, 3).setDamage(EnumFoodType.CABBAGE.ordinal()).setWeight(10)
		}).setPostProcessor(new IPostProcess() {
			@SuppressWarnings("rawtypes")
			@Override
			public ItemStack postProcessItem(ItemStack is, Random rand) {
				if (is.getItem() == Items.ENCHANTED_BOOK || rand.nextBoolean() && (is.getItem() instanceof ItemTool || is.getItem() instanceof ItemArmor || is.getItem() instanceof ItemSword)) {
					boolean enchBook = is.getItem() == Items.ENCHANTED_BOOK;
					if (enchBook)
						is = new ItemStack(Items.BOOK);
					List enchList = EnchantmentHelper.buildEnchantmentList(rand, is, 7 + rand.nextInt(10), true);
					if (enchBook)
						is = new ItemStack(Items.ENCHANTED_BOOK);

					if (enchList != null && enchList.size() > 0)
						for (int a = 0; a < enchList.size(); ++a) {
							EnchantmentData data = (EnchantmentData) enchList.get(a);
							is.addEnchantment(data.enchantment, data.enchantmentLevel);
						}
				}
				if (is.getItem() == ModItems.BAMBUCKET) {
					int randomBucket = rand.nextInt(2);
					switch (randomBucket) {
					case 0:
						is = ModFluids.getFilledBambucket(new FluidStack(FluidRegistry.getFluid("beetle_juice"), Fluid.BUCKET_VOLUME));
						break;
					case 1:
						is = ModFluids.getFilledBambucket(new FluidStack(FluidRegistry.getFluid("honey"), Fluid.BUCKET_VOLUME));
						break;
					}
				}
				return is;
			}
		});

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		if(!aircheck(world, rand, pos))
			return false;

	for (int xx = - radius; xx <= radius; xx++) {
			for (int zz = - radius; zz <= radius; zz++) {
				for (int yy = 0; yy < height; yy++) {
					double dSqDome = Math.pow(xx, 2.0D) + Math.pow(zz, 2.0D) + Math.pow(yy, 2.0D);
					if (yy == 0 && rand.nextBoolean() && Math.round(Math.sqrt(dSqDome)) == 5)
						world.setBlockState(pos.add(xx, -1, zz), DUNG, 2);

					if (Math.round(Math.sqrt(dSqDome)) < 5)
						world.setBlockState(pos.add(xx, yy, zz), DUNG, 2);
				}
				if (rand.nextInt(5) == 0)
					world.setBlockState(pos.add(xx, 0, zz), DUNG, 2);
			}
		}
		world.setBlockState(pos, Blocks.CHEST.getStateFromMeta(0), 2);
		TileEntityChest chest = (TileEntityChest) world.getTileEntity(pos);
		if (chest != null)
			LootUtil.generateLoot(chest, rand, CHEST_LOOT, 8, 14);
		
		world.setBlockState(pos.add(-1, 1, 1), ModBlocks.DUNG_SPAWNER_BOT_FLY.getDefaultState(), 2);
		world.setBlockState(pos.add(1, 1, 1), ModBlocks.DUNG_SPAWNER_FLY.getDefaultState(), 2);
		world.setBlockState(pos.add(1, 1, -1), ModBlocks.DUNG_SPAWNER_BOT_FLY.getDefaultState(), 2);
		world.setBlockState(pos.add(-1, 1, -1), ModBlocks.DUNG_SPAWNER_FLY.getDefaultState(), 2);
		world.setBlockState(pos.add(0, height, 0), ModBlocks.DUNG_SPAWNER_FLY.getDefaultState(), 2);

		return true;
	}

	private boolean aircheck(World world, Random rand, BlockPos pos) {
		for (int xx = -radius; xx <= radius; xx++)
			for (int zz = -radius; zz <= radius; zz++) {
				if (!checkSurface(world, SurfaceType.MIXED, pos.add(xx, -1, zz)))
					return false;
				for (int yy = 0; yy < height; yy++)
					if (!world.isAirBlock(pos.add(xx, yy, zz)))
						return false;
			}
		return true;
	}

	protected boolean checkSurface(World world, SurfaceType surfaceType, BlockPos pos) {
		return surfaceType.matchBlock(world.getBlockState(pos));
	}
}
