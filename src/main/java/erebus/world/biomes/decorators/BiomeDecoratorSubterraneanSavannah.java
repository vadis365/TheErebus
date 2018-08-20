package erebus.world.biomes.decorators;

import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.feature.decoration.WorldGenAmberGround;
import erebus.world.feature.decoration.WorldGenAmberUmberstone;
import erebus.world.feature.decoration.WorldGenPonds;
import erebus.world.feature.decoration.WorldGenRottenAcacia;
import erebus.world.feature.decoration.WorldGenSavannahRock;
import erebus.world.feature.plant.WorldGenBamboo;
import erebus.world.feature.tree.WorldGenAsperTree;
import erebus.world.feature.tree.WorldGenBaobabTree;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenerator;


public class BiomeDecoratorSubterraneanSavannah extends BiomeDecoratorBaseErebus {

	private final WorldGenPonds genPonds = new WorldGenPonds();
	private final WorldGenBamboo genBamboo = new WorldGenBamboo(7, true);
	private final WorldGenSavannahRock genRocks = new WorldGenSavannahRock();
	private final WorldGenRottenAcacia genRottenAcacia = new WorldGenRottenAcacia();
	private final WorldGenAmberGround genAmberGround = new WorldGenAmberGround();
	private final WorldGenAmberUmberstone genAmberUmberstone = new WorldGenAmberUmberstone();

	private final WorldGenerator genTreeAcacia = new WorldGenSavannaTree(true);
	private final WorldGenerator genTreeAsper = new WorldGenAsperTree();
	private final WorldGenerator genTreeBaobab = new WorldGenBaobabTree();


	@Override
	public void populate() {

		if (rand.nextBoolean() && rand.nextBoolean()) {
			for (attempt = 0; attempt < 8; attempt++) {
				xx = x + 16;
				yy = rand.nextInt(120);
				zz = z + 16;
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos)) {
					genPonds.prepare((rand.nextDouble() + 0.75D) * 1.2D);
					genPonds.generate(world, rand, pos.up());
				}
			}

			if (rand.nextInt(3) != 0)
				for (yy = 100; yy > 20; yy--) {
					xx = x + offsetXZ();
					zz = z + offsetXZ();
					BlockPos pos = new BlockPos(xx, yy, zz);
					if (checkSurface(SurfaceType.GRASS, pos))
						genBamboo.generate(world, rand, pos.up());
				}
		}
	}

	@Override
	public void decorate() {
		xx = x + 16;
		yy = 16;
		zz = z + 16;

		if (rand.nextInt(12) == 0)
			for (attempt = 0; attempt < 5; attempt++)
				if (genAmberUmberstone.generate(world, rand, new BlockPos(x + offsetXZ(), rand.nextInt(120), z + offsetXZ())))
					break;

		if (rand.nextInt(24) == 0)
			for (attempt = 0; attempt < 4; attempt++)
				if (genAmberGround.generate(world, rand, new BlockPos(x + offsetXZ(), 10 + rand.nextInt(40), z + offsetXZ())))
					break;

		for (attempt = 0; attempt < 65; attempt++) {
			xx = x + offsetXZ();
			yy = 15 + rand.nextInt(90);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos)) {
				genTreeAcacia.generate(world, rand, pos.up());
			}
		}

		if (rand.nextBoolean() && rand.nextBoolean()) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for (yy = 100; yy > 20; yy--) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos)) {
					if(genRocks.generate(world, rand, pos))
						break;
				}
			}
		}

		for (attempt = 0; attempt < 10; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(120);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos))
				genTreeAsper.generate(world, rand, pos.up());
		}

		for (attempt = 0; attempt < 20; attempt++) {
			xx = x + rand.nextInt(5) + 12;
			yy = rand.nextInt(120);
			zz = z + rand.nextInt(5) + 12;
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos) && checkSurface(SurfaceType.GRASS, pos.east(2)) && checkSurface(SurfaceType.GRASS, pos.west(2)) && checkSurface(SurfaceType.GRASS, pos.north(2)) && checkSurface(SurfaceType.GRASS, pos.south(2)))
				genTreeBaobab.generate(world, rand, pos.up());
		}

		for (attempt = 0; attempt < 28; attempt++) {
			xx = x + offsetXZ();
			yy = 15 + rand.nextInt(90);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos));
				genRottenAcacia.generate(world, rand, pos.up());
		}

		IBlockState tallGrassState = Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS);
		for (attempt = 0; attempt < 35; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for (yy = rand.nextInt(3) == 0 ? 40 + rand.nextInt(35) : 22; yy < 100; yy += rand.nextBoolean() ? 2 : 1) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos)) {
					if (rand.nextInt(10) == 0 && world.isAirBlock(pos.up(2)))
						Blocks.DOUBLE_PLANT.placeAt(world, pos.up(), BlockDoublePlant.EnumPlantType.GRASS, 2);
					else
						if (world.isAirBlock(pos.up()))
							world.setBlockState(pos.up(), tallGrassState);
							
				}
			}
		}
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void modifyOreGen(OreSettings oreGen, OreType oreType, boolean extraOres) {
		switch (oreType) {
			case GOLD:
				oreGen.setChance(0.75F).setIterations(extraOres ? 1 : 2).setY(50, 126).generate(world, rand, x, z); // split into 3 parts, higher areas have less gold
				oreGen.setChance(0.9F).setIterations(extraOres ? 1 : 2, extraOres ? 2 : 3).setY(25, 50).generate(world, rand, x, z); // balanced so there are more veins per chunk
				oreGen.setIterations(extraOres ? 2 : 3, extraOres ? 3 : 4).setY(5, 25);
				break;
			case EMERALD:
				oreGen.setChance(0.22F);
				break; // less common
			case DIAMOND:
				oreGen.setChance(0.4F).setIterations(1, 2).setOreAmount(2).setY(5, 16);
				break; // 2 diamonds per cluster, ~7-8 times smaller area thus lowered chance and iterations
			case JADE:
				oreGen.setChance(0.3F).setIterations(0, 3);
				break; // less common
			case PETRIFIED_WOOD:
				oreGen.setChance(0.5F).setIterations(0, extraOres ? 2 : 3).setY(5, 64);
				break; // less common and lowered area ~2 times, thus lowered chance and iterations
		}
	}
}