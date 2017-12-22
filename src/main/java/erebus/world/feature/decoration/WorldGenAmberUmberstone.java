package erebus.world.feature.decoration;

import java.util.Random;

import erebus.ModBlocks;
import erebus.ModEntities;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityPreservedBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenAmberUmberstone extends WorldGenerator {

	private static final float BUGGED_AMBER_CHANCE = 0.01F;
	private static final float WAND_CHANCE = 0.05F;

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		if (world.getBlockState(pos) != ModBlocks.UMBERSTONE)
			return false;

		float rad = rand.nextFloat() + 2.6F;
		int ceilRad = 1 + (int) Math.ceil(rad);

		for (int xx = -ceilRad; xx <= ceilRad; xx++)
			for (int yy = -ceilRad; yy <= ceilRad; yy++)
				for (int zz = -ceilRad; zz <= ceilRad; zz++)
					if (Math.sqrt(xx * xx + yy * yy + zz * zz) <= rad + rand.nextFloat() * 0.4F && world.getBlockState(pos.add(xx, yy, zz)) == ModBlocks.UMBERSTONE.getDefaultState())
						setAmberBlock(world, pos.add(xx, yy, zz), rand);

		return true;
	}

	protected void setAmberBlock(World world, BlockPos pos, Random rand) {
		if (rand.nextFloat() > BUGGED_AMBER_CHANCE)
			world.setBlockState(pos, ModBlocks.AMBER.getDefaultState());
		else {
			world.setBlockState(pos, ModBlocks.PRESERVED_BLOCK.getDefaultState(), 3);
			TileEntityPreservedBlock tile = Utils.getTileEntity(world, pos, TileEntityPreservedBlock.class);
			if (tile != null)
				try {
					Entity entity;
					if (rand.nextFloat() > WAND_CHANCE) {
						Class<? extends EntityLiving> cls = ModEntities.getRandomEntityClass();
						entity = cls.getConstructor(new Class[] { World.class }).newInstance(world);
					} else {
						EntityItem item = new EntityItem(world);
						item.setItem(new ItemStack(ModItems.WAND_OF_PRESERVATION));
						entity = item;
					}

					entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
					world.spawnEntity(entity);

					NBTTagCompound nbt = new NBTTagCompound();
					entity.writeToNBT(nbt);
					nbt.setString("id", EntityList.getKey(entity).toString());
					tile.rotation = (byte) rand.nextInt(4);
					tile.setEntityNBT(nbt);

					entity.setDead();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

}