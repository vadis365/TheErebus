package erebus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityWoodlouse;

public class BlockSimple extends Block
{

	public BlockSimple(Material material)
	{
		super(material);
	}
		
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
	{
		if (!world.isRemote && this == ModBlocks.rottenWood)
		{
			if (world.rand.nextInt(30) == 0)
			{
				EntityWoodlouse entity = new EntityWoodlouse(world);
				entity.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
				world.spawnEntityInWorld(entity);
			}
			else if (world.rand.nextInt(30) == 0)
			{
				EntityBeetleLarva entity = new EntityBeetleLarva(world);
				entity.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
				world.spawnEntityInWorld(entity);
			}
			else if (world.rand.nextInt(30) == 0)
			{
				EntitySilverfish entity = new EntitySilverfish(world);
				entity.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
				world.spawnEntityInWorld(entity);
			}
		}
		super.onBlockDestroyedByPlayer(world, x, y, z, meta);
	}
}