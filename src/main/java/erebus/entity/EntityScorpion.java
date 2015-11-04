package erebus.entity;

import erebus.item.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityScorpion extends EntityMob {
	private boolean sting;
	private boolean poisoned;
	public static float stingticks;

	public EntityScorpion(World world) {
		super(world);
		stepHeight = 0.1F;
		isImmuneToFire = true;
		setSize(2.0F, 2.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D); // followRange
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote && !captured())
			setisStinging(false);
		if (!worldObj.isRemote && captured())
			updateRiderPosition();
		if (sting && stingticks < 0.64F) {
			stingticks = stingticks + 0.16F;
			if (stingticks >= 0.64F) {
				setHasBeenStung(true);
				setisStinging(false);
			}
		}
		if (!sting && stingticks > 0F && poisoned) {
			stingticks = stingticks - 0.16F;
			if (stingticks <= 0.0F)
				setHasBeenStung(false);
		}
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	/*
	 * just to avoid crashes
	 * @Override protected String getLivingSound() { return "erebus:scorpionsound"; }
	 * @Override protected String getHurtSound() { return "erebus:scorpionhurt"; }
	 */

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount) {
			int pincerChance = rand.nextInt(30);
			if (pincerChance == 0)
				entityDropItem(ItemMaterials.DATA.scorpionPincer.makeStack(), 0.0F);
			entityDropItem(ItemMaterials.DATA.poisonGland.makeStack(1 + rand.nextInt(2)), 0.0F);
		}
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}

	@Override
	public boolean canRiderInteract() {
		return true;
	}

	@Override
	public boolean shouldRiderSit() {
		return false;
	}

	public boolean captured() {
		return riddenByEntity != null;
	}

	private void setisStinging(boolean par1) {
		sting = par1;
	}

	private void setHasBeenStung(boolean par1) {
		poisoned = par1;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		if (player.isSneaking())
			player.setSneaking(false);
		byte var2 = 0;
		if (!worldObj.isRemote && player.boundingBox.maxY >= boundingBox.minY && player.boundingBox.minY <= boundingBox.maxY && captured())
			if (worldObj.difficultySetting.ordinal() > 1)
				if (worldObj.difficultySetting == EnumDifficulty.NORMAL)
					var2 = 7;
				else if (worldObj.difficultySetting == EnumDifficulty.HARD)
					var2 = 15;
		if (var2 > 0 && rand.nextInt(200) == 0) {
			player.addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 10, 0));
			setisStinging(true);
		}
		if (!player.capabilities.isCreativeMode && !worldObj.isRemote && !captured())
			player.mountEntity(this);
	}

	@Override
	public void updateRiderPosition() {
		double a = Math.toRadians(renderYawOffset);
		double offSetX = -Math.sin(a) * 0.75D;
		double offSetZ = Math.cos(a) * 0.75D;
		if (captured())
			riddenByEntity.setPosition(posX + offSetX, posY + 0.75D + riddenByEntity.getYOffset(), posZ + offSetZ);
	}

	@Override
	protected void attackEntity(Entity entity, float par2) {
		super.attackEntity(entity, par2);
		if (par2 < 1.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
			attackEntityAsMob(entity);
	}
}