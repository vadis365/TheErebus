package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import erebus.ModMaterials;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;

public class WarHammer extends ItemSword {

	public WarHammer() {
		super(ModMaterials.weaponWarHammer);
		maxStackSize = 1;
	}

	@Override
	public boolean hitEntity(ItemStack is, EntityLivingBase entity, EntityLivingBase player) {
		is.damageItem(1, player);
		return true;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
			if (!stack.hasTagCompound())
				stack.stackTagCompound = new NBTTagCompound();
			if(!stack.getTagCompound().hasKey("charge"))
				stack.getTagCompound().setInteger("charge", 0);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack is) {
		return 1000;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.setItemInUse(stack, getMaxItemUseDuration(stack)); // TODO Figure out how to stop the animation from constantly firing on NBT change
			if (stack.getTagCompound().getInteger("charge") < 25)
				stack.getTagCompound().setInteger("charge", stack.getTagCompound().getInteger("charge") + 1);
			if (stack.getTagCompound().getInteger("charge") >= 25)
				stack.getTagCompound().setInteger("charge", 25);
			if(world.isRemote)
				System.out.println("Charge: " + stack.getTagCompound().getInteger("charge"));
		return stack;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.block;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!stack.hasTagCompound()) {
			stack.stackTagCompound = new NBTTagCompound();
			return false;
		}
		if(side == 1) {
			PacketPipeline.sendToAllAround(player, 64D, new PacketParticle(player, ParticleType.HAMMER_BLAM)); //Not sure I like this atm
			stack.getTagCompound().setInteger("charge", 0);
			System.out.println("Blam: " + stack.getTagCompound().getInteger("charge"));
			return true;
		}
		return false;
	}
}
