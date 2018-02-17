package erebus.world.feature.structure;

import java.util.Random;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.blocks.BlockUmberstone;
import erebus.blocks.BlockUmberstone.EnumType;
import erebus.items.ItemErebusFood;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import erebus.world.loot.LootItemStack;
import erebus.world.loot.LootUtil;
import erebus.world.loot.WeightedLootList;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSpiderDungeons extends WorldGenerator {

	public static final WeightedLootList chestLoot = new WeightedLootList(new LootItemStack[] {
			new LootItemStack(Items.STRING).setAmount(5, 10).setWeight(13),
			new LootItemStack(ModItems.EREBUS_FOOD).setAmount(1, 2).setDamage(ItemErebusFood.EnumFoodType.DARK_FRUIT_PIE.ordinal()).setWeight(13),
			new LootItemStack(Blocks.WEB).setAmount(3, 8).setWeight(13),
			new LootItemStack(Items.STICK).setAmount(1, 8).setWeight(12),
			new LootItemStack(Items.GOLD_NUGGET).setAmount(3, 11).setWeight(12),
			new LootItemStack(ModItems.MATERIALS).setAmount(3, 8).setDamage(EnumErebusMaterialsType.SHARD_BONE.ordinal()).setWeight(12),
			new LootItemStack(Items.BONE).setAmount(1, 3).setWeight(11),
			new LootItemStack(Items.IRON_INGOT).setAmount(1, 3).setWeight(10),
			new LootItemStack(Items.GOLD_INGOT).setAmount(1, 2).setWeight(10),
			new LootItemStack(ModItems.MATERIALS).setAmount(1, 5).setDamage(EnumErebusMaterialsType.FLY_WING.ordinal()).setWeight(10),
			new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(EnumErebusMaterialsType.JADE.ordinal()).setWeight(9),
			new LootItemStack(ModItems.MATERIALS).setAmount(3, 6).setDamage(EnumErebusMaterialsType.PLATE_EXO.ordinal()).setWeight(8),
			new LootItemStack(ModItems.MATERIALS).setAmount(2, 6).setDamage(EnumErebusMaterialsType.COMPOUND_EYES.ordinal()).setWeight(7),
			new LootItemStack(ModItems.MATERIALS).setDamage(EnumErebusMaterialsType.COMPOUND_LENS.ordinal()).setWeight(2),
			new LootItemStack(ModBlocks.UMBER_GOLEM_STATUE).setWeight(1),
			new LootItemStack(ModItems.MAX_SPEED_BOW).setWeight(1)
			});

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		byte height = 4;
		int halfSizeX = rand.nextInt(4) + 4;
		int halfSizeZ = rand.nextInt(4) + 4;
		int j1 = 0;
		int i2;

		if (pos.getY() + height + 1 >= 127)
			return false;

		for (int xx = - halfSizeX - 1; xx <= halfSizeX + 1; ++xx)
			for (int yy = - 1; yy <= height + 1; ++yy)
				for (int zz = - halfSizeZ - 1; zz <= halfSizeZ + 1; ++zz) {
					Material mat = world.getBlockState(pos.add(xx, yy, zz)).getMaterial();

					if ((yy == - 1 || yy == height + 1) && !mat.isSolid())
						return false;
					if ((xx == - halfSizeX - 1 || xx == halfSizeX + 1 || zz == - halfSizeZ - 1 || zz == + halfSizeZ + 1) && yy == 0 && world.isAirBlock(pos.add(xx, yy, zz)) && world.isAirBlock(pos.add(xx, yy + 1, zz)))
						++j1;
				}

		if (j1 >= 1 && j1 <= 5) {
			for (int xx = - halfSizeX - 1; xx <= halfSizeX + 1; ++xx)
				for (int yy =  height + 1; yy >= - 1; --yy)
					for (int zz = - halfSizeZ - 1; zz <= halfSizeZ + 1; ++zz)
						if (xx != - halfSizeX - 1 && yy != - 1 && zz != - halfSizeZ - 1 && xx !=  halfSizeX + 1 && yy != height + 1 && zz != halfSizeZ + 1)
							world.setBlockToAir(pos.add(xx, yy, zz));
						else if (yy >= 0 && !world.getBlockState(pos.add(xx, yy - 1, zz)).getMaterial().isSolid())
							world.setBlockToAir(pos.add(xx, yy, zz));
						else if (world.getBlockState(pos.add(xx, yy, zz)).getMaterial().isSolid())
							if (yy == - 1 && rand.nextInt(4) == 0 || yy == height + 1 && rand.nextInt(4) == 0)
								world.setBlockState(pos.add(xx, yy, zz), ModBlocks.UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERCOBBLE_WEBBED), 16); // umbercobbleWebbed
							else if (yy == - 1 && rand.nextInt(4) == 0 || yy == height + 1 && rand.nextInt(4) == 0)
								world.setBlockState(pos.add(xx, yy, zz), ModBlocks.UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERCOBBLE_MOSSY), 16); // umbercobbleMossy
							else
								world.setBlockState(pos.add(xx, yy, zz), ModBlocks.UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERCOBBLE), 16); // umbercobble
			for (int iteration = 0; iteration < 2; iteration++)
				for (int attempt = 0; attempt < (iteration == 0 ? Integer.MAX_VALUE : 3); attempt++) {

					i2 = rand.nextInt(halfSizeX * 2 + 1) - halfSizeX;
					int j2 = rand.nextInt(halfSizeZ * 2 + 1) - halfSizeZ;

					if (world.isAirBlock(pos.add(i2, 0, j2))) {
						int adjacentSolidBlocks = 0;
						EnumFacing facing = EnumFacing.EAST;
						if (world.getBlockState(pos.add(i2 - 1, 0, j2)).getMaterial().isSolid()) {
							++adjacentSolidBlocks;
							facing = EnumFacing.EAST;
						}
						if (world.getBlockState(pos.add(i2 + 1, 0, j2)).getMaterial().isSolid()) {
							++adjacentSolidBlocks;
							facing = EnumFacing.WEST;
						}
						if (world.getBlockState(pos.add(i2, 0, j2 - 1)).getMaterial().isSolid()) {
							++adjacentSolidBlocks;
							facing = EnumFacing.SOUTH;
						}
						if (world.getBlockState(pos.add(i2, 0, j2 + 1)).getMaterial().isSolid()) {
							++adjacentSolidBlocks;
							facing = EnumFacing.NORTH;
						}

						if (adjacentSolidBlocks == 1) {
							world.setBlockState(pos.add(i2, 0, j2), Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, facing), 16);

							TileEntityChest chest = (TileEntityChest) world.getTileEntity(pos.add(i2, 0, j2));
							if (chest != null)
								LootUtil.generateLoot(chest, rand, chestLoot, 3, 10);

							break;
						}
					}
				}

			world.setBlockState(pos.add(1, 0, 0), Blocks.WEB.getDefaultState(), 16);
			world.setBlockState(pos.add(- 1, 0, 0), Blocks.WEB.getDefaultState(), 16);
			world.setBlockState(pos.add(0, 0, - 1), Blocks.WEB.getDefaultState(), 16);
			world.setBlockState(pos.add(0, 0, 1), Blocks.WEB.getDefaultState(), 16);
			world.setBlockState(pos.add(0, 1, 0), Blocks.WEB.getDefaultState(), 16);
			world.setBlockState(pos.add(0, - 1, 0), Blocks.WEB.getDefaultState(), 16);
			world.setBlockState(pos.add(0, 0, 0), getRandomSpawner(rand), 16);
			//System.out.println("Spider Dungeon Here: "  + pos);
			return true;
		}
		return false;
	}

	public IBlockState getRandomSpawner(Random rand) {
		int type = rand.nextInt(3);
		switch (type) {
		case 0:
			return ModBlocks.SPIDER_SPAWNER.getDefaultState();
		case 1:
			return ModBlocks.JUMPING_SPIDER_SPAWNER.getDefaultState();
		case 2:
			return ModBlocks.TARANTULA_SPAWNER.getDefaultState();
		}
		return ModBlocks.SPIDER_SPAWNER.getDefaultState();
	}
}