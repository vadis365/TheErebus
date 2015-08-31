package erebus.item;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.ModMaterials;
import erebus.entity.EntityWaspDagger;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemDaggerWasp extends ItemSword {

	public ItemDaggerWasp() {
		super(ModMaterials.weaponWaspDagger);
		maxStackSize = 16;
		setTextureName("paper");
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean isComplex) {
		tooltip.add(EnumChatFormatting.WHITE + "One use.");
		tooltip.add(EnumChatFormatting.WHITE + "Can be thrown");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode)
			is.stackSize--;
		world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!world.isRemote)
			world.spawnEntityInWorld(new EntityWaspDagger(world, player));
		return is;
	}

	@Override
	public boolean hitEntity(ItemStack is, EntityLivingBase entity, EntityLivingBase player) {
		is.damageItem(2, player);
		return true;
	}
}