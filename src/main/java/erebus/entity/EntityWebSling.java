package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import erebus.ModBlocks;

public class EntityWebSling extends EntityThrowable {

	private byte type;

	public EntityWebSling(World world) {
		super(world);
		setSize(1F, 1F);
	}

	public EntityWebSling(World world, EntityLiving entity) {
		super(world, entity);
	}

	public EntityWebSling(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityWebSling(World world, EntityPlayer player) {
		super(world, player);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(24, new Byte((byte) 0));
	}
	
	protected String getWebSlingSplatSound() {
		return "erebus:webslingsplat";
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (!worldObj.isRemote) {
			int x = MathHelper.floor_double(posX);
			int y = MathHelper.floor_double(posY);
			int z = MathHelper.floor_double(posZ);

			if (mop.entityHit != null) {
				if (type == 0)
					worldObj.setBlock(x, y, z, Block.web.blockID);
				if (type == 1)
					worldObj.setBlock(x, y, z, ModBlocks.blockWitherWeb.blockID);
				if (type == 2)
					mop.entityHit.setFire(10);
			} else if (mop.entityHit == null && Block.web.canPlaceBlockAt(worldObj, x, y, z) || mop.entityHit == null && Block.fire.canPlaceBlockAt(worldObj, x, y, z)) {
				if (type == 0)
					worldObj.setBlock(x, y, z, Block.web.blockID);
				if (type == 1)
					worldObj.setBlock(x, y, z, ModBlocks.blockWitherWeb.blockID);
				if (type == 2)
					worldObj.setBlock(x, y, z, Block.fire.blockID);
			}
			if (!worldObj.isRemote)
				setDead();
		}
		if (type != 2)
			worldObj.playSoundAtEntity(this, getWebSlingSplatSound(), 1.0F, 1.0F);
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	public boolean attackEntityFrom(DamageSource source, int par2) {
		return false;
	}

	public void setType(byte webType) {
		type = webType;
		dataWatcher.updateObject(24, webType);
	}

	public byte getType() {
		return type;
	}
}