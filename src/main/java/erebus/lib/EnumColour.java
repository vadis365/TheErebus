package erebus.lib;

import java.awt.Color;

import net.minecraft.block.BlockColored;
import net.minecraft.entity.passive.EntitySheep;

public enum EnumColour {

	BLACK("Black"),
	RED("Red"),
	GREEN("Green"),
	BROWN("Brown"),
	BLUE("Blue"),
	PURPLE("Purple"),
	CYAN("Cyan"),
	LIGHT_GREY("LightGray"),
	GREY("Gray"),
	PINK("Pink"),
	LIME("Lime"),
	YELLOW("Yellow"),
	LIGHT_BLUE("LightBlue"),
	MAGENTA("Magenta"),
	ORANGE("Orange"),
	WHITE("White");

	final String dye;

	EnumColour(String name) {
		dye = "dye" + name;
	}

	public String getOreName() {
		return dye;
	}

	public Color getColour() {
		int i = BlockColored.func_150031_c(ordinal());
		return new Color(EntitySheep.fleeceColorTable[i][0], EntitySheep.fleeceColorTable[i][1], EntitySheep.fleeceColorTable[i][2]);
	}

	public int getRGB() {
		return getColour().getRGB();
	}

	public int getDarker() {
		return getColour().darker().getRGB();
	}

	public int getBrighter() {
		return getColour().brighter().getRGB();
	}

	public String getUnlocalisedName() {
		return name().toLowerCase();
	}
}