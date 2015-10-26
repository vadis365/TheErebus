package erebus;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterials {

	public static ArmorMaterial armorEXOSKELETON = EnumHelper.addArmorMaterial("EXOSKELETON", 11, new int[] { 2, 3, 2, 2 }, 15);
	public static ArmorMaterial armorREINEXOSKELETON = EnumHelper.addArmorMaterial("REINEXOSKELETON", 33, new int[] { 3, 8, 6, 3 }, 10);
	public static ArmorMaterial armorREINEXOSPECIAL = EnumHelper.addArmorMaterial("REINEXOSPECIAL", 33, new int[] { 3, 8, 6, 3 }, 10);
	public static ArmorMaterial armorRHINO = EnumHelper.addArmorMaterial("RHINO", 33, new int[] { 3, 8, 6, 3 }, 10);
	public static ArmorMaterial armorJADE = EnumHelper.addArmorMaterial("JADE", 24, new int[] { 3, 7, 5, 2 }, 15);

	public static ToolMaterial toolJADE = EnumHelper.addToolMaterial("JADE", 2, 863, 10.0F, 2.0F, 18);
	public static ToolMaterial toolJADEPAXEL = EnumHelper.addToolMaterial("JADEPAXEL", 2, 1079, 8.0F, 4.0F, 14);
	public static ToolMaterial toolCAVEMANCLUB = EnumHelper.addToolMaterial("CAVEMANCLUB", 0, 131, 4.0F, 2.0F, 12);
	public static ToolMaterial weaponWaspDagger = EnumHelper.addToolMaterial("WASPDAGGER", 0, 1, 1.0F, 0.0F, 12);
	public static ToolMaterial weaponWaspSword = EnumHelper.addToolMaterial("WASPSWORD", 0, 863, 1.0F, 6.0F, 0);
	public static ToolMaterial weaponScorpionPincer = EnumHelper.addToolMaterial("SCORPIONPINCER", 0, 863, 1.0F, 4.0F, 2);
	public static ToolMaterial ritualDagger = EnumHelper.addToolMaterial("RITUAL_DAGGER", 0, 128, 1.0F, -3.0F, 30);
	public static ToolMaterial rolledNewspaper = EnumHelper.addToolMaterial("ROLLED_NEWSPAPER", 0, 64, 1.0F, 0.0F, 0);
	public static ToolMaterial weaponWarHammer = EnumHelper.addToolMaterial("WARHAMMER", 0, 863, 1.0F, 10.0F, 10);

	public static Material honey = new MaterialLiquid(MapColor.mapColorArray[14]);
}