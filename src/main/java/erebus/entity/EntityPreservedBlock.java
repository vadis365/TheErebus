package erebus.entity;

import erebus.ModBlocks;
import erebus.api.ErebusAPI;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityPreservedBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class EntityPreservedBlock extends EntityThrowable {

	public EntityPreservedBlock(World world) {
		super(world);
	}

	public EntityPreservedBlock(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityPreservedBlock(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (worldObj.isRemote)
			return;

		int x = MathHelper.floor_double(posX);
		int y = MathHelper.floor_double(posY);
		int z = MathHelper.floor_double(posZ);

		if (mop.typeOfHit == MovingObjectType.ENTITY && mop.entityHit != null && !(mop.entityHit instanceof EntityPlayer)) {
			if (canTrap(mop.entityHit)) {
				worldObj.setBlock(x, y, z, ModBlocks.preservedBlock, 2 + rand.nextInt(4), 3);
				TileEntityPreservedBlock tile = Utils.getTileEntity(worldObj, x, y, z, TileEntityPreservedBlock.class);
				tile.setEntityNBT(trapEntity(mop.entityHit));
				mop.entityHit.setDead();
			}
		} else if (mop.entityHit == null && ModBlocks.amber.canPlaceBlockAt(worldObj, x, y, z))
			worldObj.setBlock(x, y, z, ModBlocks.amber, 1, 2);

		setDead();
	}

	private boolean canTrap(Entity entity) {
		return ErebusAPI.preservableEntityRegistry.canBePreserved(entity);
	}

	private NBTTagCompound trapEntity(Entity entity) {
		NBTTagCompound entityNBT = new NBTTagCompound();
		entity.writeToNBT(entityNBT);
		entityNBT.setString("id", EntityList.getEntityString(entity));

		return entityNBT;
	}
}