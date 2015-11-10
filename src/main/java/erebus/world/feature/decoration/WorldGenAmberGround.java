package erebus.world.feature.decoration;

import java.util.Random;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.item.ItemSpawnEggs;
import erebus.tileentity.TileEntityPreservedBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenAmberGround extends WorldGenerator {

	private static final float BUGGED_AMBER_CHANCE = 0.01F;
	private static final float WAND_CHANCE = 0.05F;

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

	protected void setBlock(World world, int x, int y, int z, Random rand) {
		if (rand.nextFloat() > BUGGED_AMBER_CHANCE)
			world.setBlock(x, y, z, ModBlocks.amber);
		else {
			world.setBlock(x, y, z, ModBlocks.preservedBlock, 6 + rand.nextInt(4), 3);
			TileEntityPreservedBlock tile = Utils.getTileEntity(world, x, y, z, TileEntityPreservedBlock.class);
			if (tile != null)
				try {
					Entity entity;
					if (rand.nextFloat() > WAND_CHANCE) {
						Class<? extends EntityLiving> cls = ItemSpawnEggs.getRandomEntityClass();
						entity = cls.getConstructor(new Class[] { World.class }).newInstance(world);
					} else {
						EntityItem item = new EntityItem(world);
						item.setEntityItemStack(new ItemStack(ModItems.wandOfPreservation));
						entity = item;
					}

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