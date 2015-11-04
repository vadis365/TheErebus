package erebus.entity;

import erebus.item.ItemMaterials;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityPondSkater extends EntityMob {

	public EntityPondSkater(World world) {
		super(world);
		setSize(1.0F, 0.8F);
		stepHeight = 0.0F;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.2D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean getCanSpawnHere() {
		int x = MathHelper.floor_double(posX);
		int y = MathHelper.floor_double(boundingBox.minY);
		int z = MathHelper.floor_double(posZ);
		return worldObj.getBlock(x, y, z).getMaterial() == Material.water;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 6;
	}

	/*
	 * To stop console spam
	 * @Override protected String getLivingSound() { return ""; }
	 * @Override protected String getHurtSound() { return ""; }
	 */

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(ItemMaterials.DATA.hydrofuge.makeStack(), 0.0F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		int x = MathHelper.floor_double(posX);
		int y = MathHelper.floor_double(boundingBox.minY);
		int z = MathHelper.floor_double(posZ);

		if (worldObj.getBlock(x, y, z).getMaterial() == Material.water) {
			if (motionY < 0.0D)
				motionY = 0.0D;
			fallDistance = 0.0F;
		}
	}

	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer player = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return player != null && isPlayerInWater(player) && canEntityBeSeen(player) ? player : null;
	}

	public boolean isPlayerInWater(EntityPlayer player) {
		int x = MathHelper.floor_double(player.posX);
		int y = MathHelper.floor_double(player.boundingBox.minY);
		int z = MathHelper.floor_double(player.posZ);
		return worldObj.getBlock(x, y, z).getMaterial() == Material.water;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (entity instanceof EntityLivingBase) {
				byte duration = 0;
				if (worldObj.difficultySetting == EnumDifficulty.NORMAL)
					duration = 7;
				else if (worldObj.difficultySetting == EnumDifficulty.HARD)
					duration = 15;

				if (duration > 0) {
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, duration * 20, 0));
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.confusion.id, duration * 20, 0));
				}
			}
			return true;
		} else
			return false;
	}
}
