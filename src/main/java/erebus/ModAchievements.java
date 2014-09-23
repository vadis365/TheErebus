package erebus;

import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class ModAchievements extends AchievementPage{
	public static Achievement welcome = new Achievement("welcome", "welcome", -2, -1, ModBlocks.portal, (Achievement)null).registerStat().initIndependentStat();
	public static Achievement mob = new Achievement("mob", "mob", 1, 1, ModItems.food, welcome).registerStat();
	public static Achievement beetle = new Achievement("beetle", "beetle", 1, 2, ModItems.food, mob).registerStat();
	public static Achievement beetleSpecial = new Achievement("beetleSpecial", "beetleSpecial", 1, 3, ModItems.food, mob).registerStat().setSpecial();
	public static Achievement diamond = new Achievement("diamond", "diamond", 1, 3, Items.diamond, beetleSpecial).registerStat();
	public static Achievement umberstone = new Achievement("umberstone", "umberstone", 1, 4, ModBlocks.umberstone, mob).registerStat();
	
	public ModAchievements(){
		super("The Erebus", new Achievement[] {welcome, beetle, beetleSpecial, mob, diamond, umberstone});
	}
}
