package erebus.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Smoothie extends ItemFood{
	
	public static enum SmoothieType{
		greenTeaGrasshopper,
		moneyHoney,
		nothingInTheMiddle,
		greenGiant,
		seedyGoodness,
		givinMeTheBlues,
		hotHotBaby,
		dontMettleWithTheNettle,
		liquidGold
	}

	public Smoothie() {
		super(3, 0.5F, false);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	public int getHealAmount(ItemStack stack, World world, EntityPlayer player){
		switch(SmoothieType.values()[stack.getItemDamage()]){
		case greenTeaGrasshopper:
			return 5;
		case moneyHoney:
			return 3;
		case nothingInTheMiddle:
			return 1;
		case greenGiant:
			return 2;
		case seedyGoodness:
			return 1;
		case givinMeTheBlues:
			return 3;
		case hotHotBaby:
			return 2;
		case dontMettleWithTheNettle:
			return 2;
		case liquidGold:
			return 0;
		default:
			return 0;
		}
	}
	
	public float getSaturationModifier(ItemStack stack, World world, EntityPlayer player){
		switch(SmoothieType.values()[stack.getItemDamage()]){
		case greenTeaGrasshopper:
			return 0.4F;
		case moneyHoney:
			return 0.2F;
		case nothingInTheMiddle:
			return 0.0F;
		case greenGiant:
			return 0.1F;
		case seedyGoodness:
			return 1.1F;
		case givinMeTheBlues:
			return 0.2F;
		case hotHotBaby:
			return 0.1F;
		case dontMettleWithTheNettle:
			return 0.1F;
		case liquidGold:
			return 0.0F;
		default:
			return 0.0F;
		}
	}
	
	public PotionEffect getPotionEffect(ItemStack stack, World world, EntityPlayer player){
		switch(SmoothieType.values()[stack.getItemDamage()]){
		case moneyHoney:
			return new PotionEffect(Potion.regeneration.id, 200, 2);
		case nothingInTheMiddle:
			return new PotionEffect(Potion.invisibility.id, 500, 1);
		case greenGiant:
			player.curePotionEffects(stack);
		case givinMeTheBlues:
			player.extinguish();
			return new PotionEffect(Potion.moveSlowdown.id, 500, 2);
		case hotHotBaby:
			player.setFire(5);
			return new PotionEffect(Potion.damageBoost.id, 1000, 1);
		case liquidGold:
			player.heal(0.5F);
			return new PotionEffect(Potion.field_76443_y.id, 1000, 1);
		default:
			return null;
		}
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack){
		return EnumAction.drink;
	}
	
	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player){
		stack.stackSize--;
		player.getFoodStats().addStats(getHealAmount(stack, world, player), getSaturationModifier(stack, world, player));
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		onFoodEaten(stack, world, player);
		
		if(stack.stackSize != 0){
			player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
		}
		
		return stack.stackSize == 0 ? new ItemStack(Items.glass_bottle) : stack;
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player){
		PotionEffect effect = this.getPotionEffect(stack, world, player);
		
		if(!world.isRemote && effect != null){
			player.addPotionEffect(effect);
		}
	}
	
	@Override
	public String getPotionEffect(ItemStack stack){
		return "+0+1-2+3&4-4+13";
	}
	
	@SideOnly(Side.CLIENT)
	public static IIcon[] icons;
	
	@Override
	public String getUnlocalizedName(ItemStack stack){
		int damage = stack.getItemDamage();
		return super.getUnlocalizedName() + "." + damage;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list){
		for(int c = 0; c < SmoothieType.values().length; c++){
			list.add(new ItemStack(item, 1, c));
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta){
		if(meta < 0 || meta >= icons.length){
			return null;
		}
		
		return icons[meta];
	}
	
	@Override
	public void registerIcons(IIconRegister icon){
		icons = new IIcon[SmoothieType.values().length];
		int c = 0;
		
		for(SmoothieType type : SmoothieType.values()){
			icons[c++] = icon.registerIcon("erebus:" + type);
		}
	}
}
