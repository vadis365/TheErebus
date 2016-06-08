package erebus;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterials {

	public static ArmorMaterial ARMOR_EXOSKELETON = EnumHelper.addArmorMaterial("EXOSKELETON", "plate_exo", 11, new int[] { 2, 3, 2, 2 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0F);
	public static ArmorMaterial ARMOR_REIN_EXOSKELETON = EnumHelper.addArmorMaterial("REIN_EXOSKELETON", "reinforced_plate_exo", 33, new int[] { 3, 8, 6, 3 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F);
	public static ArmorMaterial ARMOR_RHINO = EnumHelper.addArmorMaterial("RHINO", "plate_exo_rhino", 33, new int[] { 3, 8, 6, 3 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F);
	public static ArmorMaterial ARMOR_JADE = EnumHelper.addArmorMaterial("JADE", "jade", 24, new int[] { 3, 7, 5, 2 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F);

	public static ToolMaterial TOOL_JADE = EnumHelper.addToolMaterial("JADE", 2, 863, 10.0F, 2.0F, 18);
	public static ToolMaterial TOOL_JADE_PAXEL = EnumHelper.addToolMaterial("JADE_PAXEL", 2, 1079, 8.0F, 4.0F, 14);
	public static ToolMaterial TOOL_CAVEMAN_CLUB = EnumHelper.addToolMaterial("CAVEMAN_CLUB", 0, 131, 4.0F, 2.0F, 12);
	public static ToolMaterial WEAPON_WASP_DAGGER = EnumHelper.addToolMaterial("WASP_DAGGER", 0, 1, 1.0F, 0.0F, 12);
	public static ToolMaterial WEAPON_WASP_SWORD = EnumHelper.addToolMaterial("WASP_SWORD", 0, 863, 1.0F, 6.0F, 18);
	public static ToolMaterial WEAPON_SCOPION_PINCER = EnumHelper.addToolMaterial("SCORPION_PINCER", 0, 863, 1.0F, 4.0F, 12);
	public static ToolMaterial WEAPON_ROLLED_NEWSPAPER = EnumHelper.addToolMaterial("ROLLED_NEWSPAPER", 0, 64, 1.0F, 0.0F, 0);
	public static ToolMaterial WEAPON_WAR_HAMMER = EnumHelper.addToolMaterial("WAR_HAMMER", 0, 863, 1.0F, 10.0F, 18);

	public static Material HONEY = new MaterialLiquid(MapColor.COLORS[14]);
	public static Material FORMIC_ACID = new MaterialLiquid(MapColor.COLORS[3]);
}