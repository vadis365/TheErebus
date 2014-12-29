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
		 					"Green Tea Grasshopper";		 
		 			case 3: return 
		 					"Money Honey";		 
		 			case 4: return 
		 					"Nothing In The Middle";
		 			case 5: return 
		 					"Green Giant";
		 			case 6: return 
		 					"Seedy Goodness";
		 			case 7: return 
		 					"Givin' Me The Blues";
		 			case 8: return 
		 					"Hot Hot Baby";
		 			case 9: return 
		 					"Don't Meddle With The Nettle";
		 			case 10: return 
		 					"Liquid Gold";
		 			case 11: return 
		 					"Bryuf's Brew"; 	 
		 			default: return EnumChatFormatting.RED+"Whoops, page not included in the mod!"; 
		 		} 
		 	} 

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return false;
	}
}
