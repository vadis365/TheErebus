package erebus.entity;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.Erebus;
import erebus.core.helper.Utils;
import erebus.core.proxy.CommonProxy;
import erebus.entity.ai.EntityAIHarvestCrops;

public class EntityBlackAnt extends EntityMob implements IInventory {
	private EntityAIPanic aiPanic = new EntityAIPanic(this, 0.8D);
	private EntityAIHarvestCrops aiHarvestCrops = new EntityAIHarvestCrops(this, 0.6D, 1);
	public boolean setAttributes; // needed for logic later
	public boolean isEating;
	public EntityBlackAnt(World world) {
		super(world);
		stepHeight = 1.0F;
		setAttributes = false;
		setSize(1.3F, 0.55F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, aiHarvestCrops);
		tasks.addTask(2, aiPanic);
		tasks.addTask(3, new EntityAILookIdle(this));
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		// TODO probably gonna need some datawatcher shizz here
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}
	
	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
    protected boolean canDespawn() {
        return true;
    }

	@Override
	protected String getLivingSound() {
		return "erebus:fireantsound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:fireanthurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 5;
	}
    
	public void openGUI(EntityPlayer player) {
		player.openGui(Erebus.instance, CommonProxy.GUI_ID_ANT_INVENTORY, player.worldObj, getEntityId(), 0, 0);
     }
    
	@Override
    public boolean interact(EntityPlayer player) {
		// TODO a fair bit of crap to happen here methinks
		openGUI(player);
		return super.interact(player);
	}
	
	public void setIsEating(boolean isEating) {
		this.isEating = isEating;
	}
	
	public void setBlockHarvested(Block block, int meta) {
		Random rand = new Random();
		if (block != null) {
			Utils.dropStack(worldObj, (int)posX, (int)posY, (int)posZ, new ItemStack(block.getItemDropped(meta, rand, 0), rand.nextInt(2) + 1));
			Utils.dropStack(worldObj, (int)posX, (int)posY, (int)posZ,  new ItemStack(block.getItemDropped(0, rand, 0), rand.nextInt(2) + 1));
		}
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		// TODO Put some code here to stop Andre moaning about overriding a method that just calls the super dooper class :P
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int p_70301_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInventoryName() {
		return "container.antInventory";
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		// TODO Auto-generated method stub
		return false;
	}
}
