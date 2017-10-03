package erebus.world.feature.plant;

import java.util.List;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.blocks.BlockLogErebus;
import erebus.blocks.EnumWood;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
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

public class WorldGenRottenTreeStump extends WorldGenerator {

	private int height = -1;
	private int baseRadius = -1;
	private int maxRadius = 6;
	private int maxHeight = 16;
	protected IBlockState log;

	public WorldGenRottenTreeStump(int height, int baseRadius) {
		this.height = height;
		this.baseRadius = baseRadius;
		log = EnumWood.ROTTEN.getLog().getDefaultState();
	}

	public static final WeightedLootList chestLoot = new WeightedLootList(new LootItemStack[] { new LootItemStack(Items.BOOK).setAmount(1, 4).setWeight(18), new LootItemStack(Items.PAPER).setAmount(2, 6).setWeight(16), new LootItemStack(Blocks.WEB).setAmount(2, 7).setWeight(13), new LootItemStack(ModItems.MATERIALS).setAmount(1, 3).setDamage(EnumErebusMaterialsType.JADE.ordinal()).setWeight(10), new LootItemStack(ModItems.MATERIALS).setAmount(4, 8).setDamage(EnumErebusMaterialsType.PLATE_EXO.ordinal()).setWeight(9), new LootItemStack(Items.ENCHANTED_BOOK).setWeight(8),/* new LootItemStack(ModBlocks.umberGolemStatue).setAmount(1).setWeight(1), new LootItemStack(ModItems.webSlinger).setAmount(1).setWeight(1),*/ new LootItemStack(Items.GOLDEN_PICKAXE).setWeight(3), new LootItemStack(Items.IRON_PICKAXE).setWeight(2),
			new LootItemStack(ModItems.JADE_PICKAXE).setWeight(1), new LootItemStack(Items.STONE_PICKAXE).setWeight(1), new LootItemStack(Items.GOLDEN_SHOVEL).setWeight(3), new LootItemStack(Items.IRON_SHOVEL).setWeight(2), new LootItemStack(ModItems.JADE_SHOVEL).setWeight(1), new LootItemStack(Items.STONE_SHOVEL).setWeight(1), new LootItemStack(Items.GOLDEN_AXE).setWeight(3), new LootItemStack(Items.IRON_AXE).setWeight(2), new LootItemStack(ModItems.JADE_AXE).setWeight(1), new LootItemStack(Items.STONE_AXE).setWeight(1), new LootItemStack(Items.GOLDEN_SWORD).setWeight(3), new LootItemStack(Items.IRON_SWORD).setWeight(2), new LootItemStack(ModItems.JADE_SWORD).setWeight(1), new LootItemStack(Items.STONE_SWORD).setWeight(1), new LootItemStack(Items.IRON_CHESTPLATE).setWeight(2),
			new LootItemStack(ModItems.JADE_CHESTPLATE).setWeight(1), new LootItemStack(Items.GOLDEN_CHESTPLATE).setWeight(1), new LootItemStack(Items.IRON_HELMET).setWeight(2), new LootItemStack(ModItems.JADE_HELMET).setWeight(1), new LootItemStack(Items.GOLDEN_HELMET).setWeight(1), new LootItemStack(Items.IRON_LEGGINGS).setWeight(2), new LootItemStack(ModItems.JADE_LEGGINGS).setWeight(1), new LootItemStack(Items.GOLDEN_LEGGINGS).setWeight(1), new LootItemStack(Items.IRON_BOOTS).setWeight(2), new LootItemStack(ModItems.JADE_BOOTS).setWeight(1), new LootItemStack(Items.GOLDEN_BOOTS).setWeight(1) }).setPostProcessor(new IPostProcess() {

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
					return is;
				}
			});

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		for (int x1 = x - baseRadius; x1 <= x + baseRadius; x1++)
			for (int z1 = z - baseRadius; z1 <= z + baseRadius; z1++)
				for (int y1 = y + 1; y1 < y + height; y1++)
					if (!world.isAirBlock(new BlockPos(x1, y1, z1)))
						return false;

		int radius = baseRadius - 1;

		// Trunk
		for (int yy = y; height + y >= yy; yy++)
			for (int i = radius * -1; i <= radius; ++i)
				for (int j = radius * -1; j <= radius; ++j) {
					double dSq = i * i + j * j;
					if (Math.round(Math.sqrt(dSq)) == radius) {
						world.setBlockState(new BlockPos(x + i, yy, z + j), log);
						if (yy >= y + 5 && rand.nextInt(20) == 0)
							world.setBlockState(new BlockPos(x + i, yy, z + j), Blocks.AIR.getDefaultState());
						if (yy == y + height && rand.nextInt(2) == 0)
							world.setBlockState(new BlockPos(x + i, yy, z + j), Blocks.AIR.getDefaultState());
					} else
						world.setBlockState(new BlockPos(x + i, yy, z + j), Blocks.AIR.getDefaultState());
					// floor at 10 high
					if (Math.round(Math.sqrt(dSq)) <= radius - 1 && baseRadius >= maxRadius && height >= maxHeight)
						if (yy == y + 10)
							world.setBlockState(new BlockPos(x + i, yy, z + j), log);
				}

		// Randomised root Base
		for (int i = baseRadius * -1; i <= baseRadius; ++i)
			for (int j = baseRadius * -1; j <= baseRadius; ++j) {
				double dSq = i * i + j * j;
				if (Math.round(Math.sqrt(dSq)) <= baseRadius) {
					world.setBlockState(new BlockPos(x + i, y, z + j), log.withProperty(BlockLogErebus.LOG_AXIS, BlockLogErebus.EnumAxis.NONE), 2);
					world.setBlockState(new BlockPos(x + i + rand.nextInt(2) - 1, y, z + j + rand.nextInt(2) - 1), log.withProperty(BlockLogErebus.LOG_AXIS, BlockLogErebus.EnumAxis.NONE), 2);
					world.setBlockState(new BlockPos(x + i, y + rand.nextInt(2), z + j), log.withProperty(BlockLogErebus.LOG_AXIS, BlockLogErebus.EnumAxis.NONE), 2);
				}
				// Branches sort of
				if (Math.round(Math.sqrt(dSq)) == baseRadius)
					for (int yy = y; height + y >= yy; yy++)
						if (yy < y + height - 1 && yy > y + 5 && rand.nextInt(12) == 0) {
							world.setBlockState(new BlockPos(x + i, yy, z + j), log.withProperty(BlockLogErebus.LOG_AXIS, BlockLogErebus.EnumAxis.NONE), 2);
							world.setBlockState(new BlockPos(x + i + rand.nextInt(2) - 1, yy, z + j + rand.nextInt(2) - 1), log.withProperty(BlockLogErebus.LOG_AXIS, BlockLogErebus.EnumAxis.NONE), 2);
						}
			}

		if (baseRadius >= maxRadius) {
			// Lower Spawner
			world.setBlockState(new BlockPos(x + 1, y + 2, z), Blocks.WEB.getDefaultState(), 2);
			world.setBlockState(new BlockPos(x - 1, y + 2, z), Blocks.WEB.getDefaultState(), 2);
			world.setBlockState(new BlockPos(x, y + 2, z - 1), Blocks.WEB.getDefaultState(), 2);
			world.setBlockState(new BlockPos(x, y + 2, z + 1), Blocks.WEB.getDefaultState(), 2);
			world.setBlockState(new BlockPos(x, y + 3, z), Blocks.WEB.getDefaultState(), 2);
			world.setBlockState(new BlockPos(x, y + 1, z), Blocks.WEB.getDefaultState(), 2);
			world.setBlockState(new BlockPos(x, y + 2, z), ModBlocks.JUMPING_SPIDER_SPAWNER.getDefaultState(), 2);
			// Loot Chest
			world.setBlockState(new BlockPos(x, y, z), Blocks.CHEST.getDefaultState(), 2);
			TileEntityChest chest1 = (TileEntityChest) world.getTileEntity(new BlockPos(x, y, z));
			if (chest1 != null)
				LootUtil.generateLoot(chest1, rand, chestLoot, 3, 10);
			if (height >= maxHeight - 3) {
				// Upper spawner
				world.setBlockState(new BlockPos(x + 1, y + 12, z), Blocks.WEB.getDefaultState(), 2);
				world.setBlockState(new BlockPos(x - 1, y + 12, z), Blocks.WEB.getDefaultState(), 2);
				world.setBlockState(new BlockPos(x, y + 12, z - 1), Blocks.WEB.getDefaultState(), 2);
				world.setBlockState(new BlockPos(x, y + 12, z + 1), Blocks.WEB.getDefaultState(), 2);
				world.setBlockState(new BlockPos(x, y + 11, z), Blocks.WEB.getDefaultState(), 2);
				world.setBlockState(new BlockPos(x, y + 13, z), Blocks.WEB.getDefaultState(), 2);
				world.setBlockState(new BlockPos(x, y + 12, z), ModBlocks.TARANTULA_SPAWNER.getDefaultState(), 2);
				// Loot Chests
				int moveyChest = rand.nextInt(5) - 2;
				world.setBlockState(new BlockPos(x - 4, y + 11, z + moveyChest), Blocks.CHEST.getDefaultState(), 2);
				world.setBlockState(new BlockPos(x + 4, y + 11, z + moveyChest), Blocks.CHEST.getDefaultState(), 2);
				world.setBlockState(new BlockPos(x - 4, y + 12, z + moveyChest), log, 2);
				world.setBlockState(new BlockPos(x + 4, y + 12, z + moveyChest), log, 2);
				TileEntityChest chest2 = (TileEntityChest) world.getTileEntity(new BlockPos(x - 4, y + 11, z + moveyChest));
				TileEntityChest chest3 = (TileEntityChest) world.getTileEntity(new BlockPos(x + 4, y + 11, z + moveyChest));
				if (chest2 != null && chest3 != null) {
					LootUtil.generateLoot(chest1, rand, chestLoot, 3, 10);
					LootUtil.generateLoot(chest2, rand, chestLoot, 3, 10);
					LootUtil.generateLoot(chest3, rand, chestLoot, 3, 10);
				}
			}
		}
		return true;
	}
}
