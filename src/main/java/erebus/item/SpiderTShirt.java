package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModMaterials;

public class SpiderTShirt extends ItemArmor
{

	public SpiderTShirt(int armorType)
	{
		super(ModMaterials.armorREINEXOSKELETON, 2, armorType);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
	{
		return "erebus:textures/models/armor/spiderTShirt.png";
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material)
	{
		return false;
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		int x = MathHelper.floor_double(player.posX);
		int y = MathHelper.floor_double(player.posY + 0.5D);
		int z = MathHelper.floor_double(player.posZ);
		
		Block blockBelow = world.getBlock(x, y - 1, z);
		Block blockBottom = world.getBlock(x, y, z);
		Block blockTop = world.getBlock(x, y + 1, z);
		if (blockBelow != null && blockBelow == Blocks.web)
			world.setBlock(x, y - 1, z, Blocks.air);
		if (blockBottom != null && blockBottom == Blocks.web)
			world.setBlock(x, y, z, Blocks.air);
		if (blockTop != null && blockTop == Blocks.web)
			world.setBlock(x, y + 1, z, Blocks.air);
	}
}