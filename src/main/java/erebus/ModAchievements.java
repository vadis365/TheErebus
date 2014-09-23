package erebus;

import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class ModAchievements extends AchievementPage{
	public static Achievement welcome = new Achievement("welcome", "welcome", 0, 0, ModBlocks.portal, (Achievement)null).registerStat().initIndependentStat();
	public static Achievement beetle = new Achievement("beetle", "beetle", 0, -2, ModItems.food, welcome).registerStat();
	public static Achievement beetleSpecial = new Achievement("beetleSpecial", "beetleSpecial", 1, -3, ModItems.food, welcome).registerStat().setSpecial();
	public static Achievement diamond = new Achievement("diamond", "diamond", 2, -4, Items.diamond, beetleSpecial).registerStat();
	public static Achievement umberstone = new Achievement("umberstone", "umberstone", -1, -2, ModBlocks.umberstone, welcome).registerStat();
	
	public ModAchievements(){
		super("The Erebus", new Achievement[] {welcome, beetle, beetleSpecial, diamond, umberstone});
	}
}
