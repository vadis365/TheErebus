package erebus.item;

import java.util.List;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRolledNewspaper extends Item {

	public ItemRolledNewspaper(int id) {
		super(id);
		maxStackSize = 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag) {
		list.add("+10 Attack Damage");
		if(!is.isItemEnchanted())
			list.add("Bane of Arthropods V");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack is, int pass) {
		return true;
	}

	@Override
	public boolean hitEntity(ItemStack is, EntityLivingBase entity, EntityLivingBase player) {
		is.damageItem(1, player);
		entity.attackEntityFrom(DamageSource.causeMobDamage(player), 10);
		return true;
	}

	@Override
	public void onCreated(ItemStack is, World world, EntityPlayer player) {
		is.addEnchantment(Enchantment.baneOfArthropods, 5);
	}
	
	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int id, boolean map){
		if (!is.isItemEnchanted())is.addEnchantment(Enchantment.baneOfArthropods,5);
	}

}
