package erebus.world.feature.decoration;

import java.util.Random;

import erebus.ModBlocks;
import erebus.core.helper.Utils;
import erebus.item.ItemSpawnEggs;
import erebus.tileentity.TileEntityPreservedBlock;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenAmberGround extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		boolean plausible = false;

		for (int a = 0; a < 10; a++) {
			if (world.isAirBlock(x, y, z) && world.getBlock(x, y - 1, z) == Blocks.grass) {
				plausible = true;
				break;
			}

			if (--y <= 1)
				return false;
		}

		if (!plausible)
			return false;

		float rad = rand.nextFloat() + 2.6F;
		int ceilRad = 1 + (int) Math.ceil(rad);

		for (int xx = -ceilRad; xx <= ceilRad; xx++)
			for (int yy = -ceilRad; yy <= ceilRad; yy++)
				for (int zz = -ceilRad; zz <= ceilRad; zz++)
					if (Math.sqrt(xx * xx + yy * yy + zz * zz) <= rad + rand.nextFloat() * 0.4F)
						setBlock(world, x + xx, y + yy, z + zz, rand);

		return true;
	}

	private void setBlock(World world, int x, int y, int z, Random rand) {
		if (rand.nextFloat() > 0.5F)
			world.setBlock(x, y, z, ModBlocks.amber);
		else {
			world.setBlock(x, y, z, ModBlocks.preservedBlock);
			TileEntityPreservedBlock tile = Utils.getTileEntity(world, x, y, z, TileEntityPreservedBlock.class);
			if (tile != null)
				try {
					Class<? extends EntityLiving> cls = ItemSpawnEggs.getRandomEntityClass();
					EntityLiving entity = cls.getConstructor(new Class[] { World.class }).newInstance(world);
					entity.setPosition(x, y, z);
					world.spawnEntityInWorld(entity);

					NBTTagCompound nbt = new NBTTagCompound();
					entity.writeToNBT(nbt);
					nbt.setString("id", EntityList.getEntityString(entity));
					tile.setEntityNBT(nbt);

					entity.setDead();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
}