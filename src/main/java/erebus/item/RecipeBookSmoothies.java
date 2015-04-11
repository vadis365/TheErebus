package erebus.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RecipeBookSmoothies extends ItemEditableBook {

	public int pageAmount = 12;
	
    public RecipeBookSmoothies() {
    }

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		ItemStack book = new ItemStack(Items.written_book);
		NBTTagList pages = new NBTTagList();

		book.setTagInfo("author",new NBTTagString("ErebusCo.")); 
		book.setTagInfo("title",new NBTTagString("Smoothie-matic 2000")); 
		book.setTagInfo("pages",pages); 
		
		for(int a = 0; a < pageAmount; a++){ 
			pages.appendTag(new NBTTagString(getPage(a)));
			} 

		player.displayGUIBook(book);
		return book;
	}
	
	public static String getPage(int page){ 
		switch(page){ 
  
		 			case 0: return 
		 "Thank you for using the new ErebusCo. Smoothie-matic 2000.\n"+"In the following pages you will find some delicious recipes.\n\n"+ 
		 "Each recipe will require a different base fluid. The fluid bars shown in the gui are for: Honey, Beetle Juice, Anti-Venom and Milk."; 
		 			 
		 			case 1: return 
		 "Fluids can be added to the Smoothie-matic 2000 by right clicking on the block with a full bucket.\n\n"+
		 "The Four top slots in the gui are for the required ingredients and the lower slot must contain a Smoothie Glass."; 
		 
		 // Recipes
		 			case 2: return 
		 					EnumChatFormatting.BLUE+"Green Tea Grasshopper\n\n"+
		 					EnumChatFormatting.BLACK+"Grasshopper Leg x2\n"+
		 					"Elastic Fibre\n"+
		 					"Fly Wing\n\n"+
		 					EnumChatFormatting.LIGHT_PURPLE+"Beetle Juice\n\n"+
		 					EnumChatFormatting.RED+"Effects:\n"+"Jumping";
		 			case 3: return 
		 					EnumChatFormatting.BLUE+"Money Honey\n\n"+
		 					EnumChatFormatting.BLACK+"Honey drip x2\n"+
		 					"Nectar\n"+
		 					"Gold Nugget\n\n"+
		 					EnumChatFormatting.LIGHT_PURPLE+"Honey\n\n"+
		 					EnumChatFormatting.RED+"Effects:\n"+"Regeneration";		 
		 			case 4: return 
		 					EnumChatFormatting.BLUE+"Darkness In The Middle\n\n"+
		 					EnumChatFormatting.BLACK+"Cammo powder x2\n"+
		 					"Dark Fruit\n"+
		 					"Swamp Berries\n\n"+
		 					EnumChatFormatting.LIGHT_PURPLE+"Beetle Juice\n\n"+
		 					EnumChatFormatting.RED+"Effects:\n"+"Invisibility";
		 			case 5: return 
		 					EnumChatFormatting.BLUE+"Green Giant\n\n"+
		 					EnumChatFormatting.BLACK+"Repellent\n"+
		 					"Poison Gland x2\n"+
		 					"Wasp Sting\n\n"+
		 					EnumChatFormatting.LIGHT_PURPLE+"Anti-Venom\n\n"+
		 					EnumChatFormatting.RED+"Effects:\n"+"Negates Potions";
		 			case 6: return 
		 					EnumChatFormatting.BLUE+"Seedy Goodness\n\n"+
		 					EnumChatFormatting.BLACK+"Pumpkin Seeds\n"+
		 					"Melon Seeds\n"+
		 					"Dark Fruit Seeds\n"+
		 					"Bio-Velocity\n\n"+
		 					EnumChatFormatting.LIGHT_PURPLE+"Beetle Juice\n\n"+
		 					EnumChatFormatting.RED+"Effects:\n"+"Dig Speed";
		 			case 7: return 
		 					EnumChatFormatting.BLUE+"Givin' Me The Blues\n\n"+
		 					EnumChatFormatting.BLACK+"Weeping Blue Bell Petal x2\n"+
		 					"Lapis Lazuli x2\n\n"+
		 					EnumChatFormatting.LIGHT_PURPLE+"Milk\n\n"+
		 					EnumChatFormatting.RED+"Effects:\n"+"Extinguish";
		 			case 8: return 
		 					EnumChatFormatting.BLUE+"Hot Hot Baby\n\n"+
		 					EnumChatFormatting.BLACK+"Fire Bloom x2\n"+
		 					"Wasp Sting\n"+
		 					"Bog Maw Root\n\n"+
		 					EnumChatFormatting.LIGHT_PURPLE+"Anti-Venom\n\n"+
		 					EnumChatFormatting.RED+"Effects:\n"+"Strength";
		 			case 9: return 
		 					EnumChatFormatting.BLUE+"Don't Meddle With The Nettle\n\n"+
		 					EnumChatFormatting.BLACK+"Nettle Leaf\n"+
		 					"Nettle Flower\n"+
		 					"Jade Berries\n"+
		 					"Exo Skeleton\n\n"+
		 					EnumChatFormatting.LIGHT_PURPLE+"Honey\n\n"+
		 					EnumChatFormatting.RED+"Effects:\n"+"Resistance";
		 			case 10: return 
		 					EnumChatFormatting.BLUE+"Liquid Gold\n\n"+
		 					EnumChatFormatting.BLACK+"Life Blood x2\n"+
		 					"Bamboo Shoot\n"+
		 					"Glistering Melon\n\n"+
		 					EnumChatFormatting.LIGHT_PURPLE+"Milk\n\n"+
		 					EnumChatFormatting.RED+"Effects:\n"+"Saturation";
		 			case 11: return 
		 					EnumChatFormatting.BLUE+"Bryuf's Brew\n\n"+
		 					EnumChatFormatting.BLACK+"Dark Cap Mushroom\n"+
		 					"Turnip\n"+
		 					"Compound Eyes\n"+
		 					"Heart Berries\n\n"+
		 					EnumChatFormatting.LIGHT_PURPLE+"Honey\n"+
		 					EnumChatFormatting.LIGHT_PURPLE+"Beetle Juice\n"+
		 					EnumChatFormatting.LIGHT_PURPLE+"Anti-Venom\n"+
		 					EnumChatFormatting.LIGHT_PURPLE+"Milk\n\n"+
		 					EnumChatFormatting.RED+"Effects:\n"+"Mighty Buffs"; 	 
		 			default:
		 				return EnumChatFormatting.RED+"Whoops, page not included in the mod!"; 
		 		} 
		 	} 

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
}
