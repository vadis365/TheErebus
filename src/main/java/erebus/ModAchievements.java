package erebus;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class ModAchievements extends AchievementPage {

	public static Achievement WELCOME = new Achievement("welcome", "welcome", 0, 0, ModBlocks.PORTAL, null).registerStat().initIndependentStat();
	//	public static Achievement BEETLE = new Achievement("beetle", "beetle", 0, -2, ModItems.food, welcome).registerStat();
	//	public static Achievement BEETLESPECIAL = new Achievement("beetleSpecial", "beetleSpecial", 1, -3, ModItems.food, welcome).setSpecial().registerStat();
	//	public static Achievement DIAMOND = new Achievement("diamond", "diamond", 2, -4, Items.DIAMOND, beetleSpecial).registerStat();
	public static Achievement UMBERSTONE = new Achievement("umberstone", "umberstone", -1, -2, ModBlocks.UMBERSTONE, WELCOME).registerStat();
	//	public static Achievement TSHIRT = new Achievement("tshirt", "tshirt", 3, 3, ModItems.spiderTShirt, welcome).registerStat();

	public ModAchievements() {
		super("The Erebus", WELCOME, /*BEETLE, BEETLESPECIAL, DIAMOND,*/ UMBERSTONE, /*TSHIRT*/);
	}
}