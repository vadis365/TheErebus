package erebus.item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ErebusMod;
import erebus.ModItems;

public class ItemSprintLeggings extends ItemArmor {
	public static final byte maxTier = 9;

	private int armtick;
	private String texture;

	public ItemSprintLeggings(int i, EnumArmorMaterial enumarmormaterial, int k) {
		super(i, enumarmormaterial, 2, k);
		setCreativeTab(ErebusMod.tabErebusGear);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List textLines, boolean showAdvancedInfo) {
		textLines.add(EnumChatFormatting.GRAY + "Tier " + (1 + (is.stackTagCompound == null ? 0 : is.stackTagCompound.getByte("upgradeTier"))));
	}

	@Override
	public String getArmorTexture(ItemStack is, Entity entity, int slot, int layer) {
		if (is.itemID == ModItems.sprintLeggings.itemID) {
			if (armtick >= 0 && armtick <= 20)
				texture = "erebus:textures/models/armor/centipede_0.png";
			if (armtick > 20 && armtick <= 40)
				texture = "erebus:textures/models/armor/centipede_1.png";
			if (armtick > 40 && armtick <= 60)
				texture = "erebus:textures/models/armor/centipede_2.png";
		}
		return texture;
	}

	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack is) {
		if (player.isSprinting() && player.onGround) {
			byte tier = (byte) (3 + (is.stackTagCompound == null ? 0 : is.stackTagCompound.getByte("upgradeTier")));
			player.motionX *= 1D + tier * .0425D;
			player.motionZ *= 1D + tier * .0425D;
		}
		armtick++;
		if (armtick > 60 || player.isSprinting())
			armtick = 0;
	}
}