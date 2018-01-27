package erebus.items;

import erebus.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSmoothieBook extends ItemWritableBook {

	public int pageAmount = 12;

	public ItemSmoothieBook() {
	}

	@Override
	 public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
		NBTTagList pages = new NBTTagList();

		book.setTagInfo("author", new NBTTagString("ErebusCo."));
		book.setTagInfo("title", new NBTTagString("Smoothie-matic 2000"));
		book.setTagInfo("pages", pages);

		for (int a = 0; a < pageAmount; a++) {
			pages.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentTranslation(Reference.MOD_ID + ".book.smoothie." + a))));
		}
		player.openBook(book, hand);
        player.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.PASS, book);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
}