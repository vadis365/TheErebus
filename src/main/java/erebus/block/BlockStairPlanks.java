package erebus.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.util.StatCollector;
import erebus.ModTabs;
import erebus.lib.EnumWood;
import erebus.lib.Reference;

public class BlockStairPlanks extends BlockStairs {

	private final EnumWood wood;

	public BlockStairPlanks(Block block, EnumWood wood) {
		super(block, wood.ordinal());
		setHardness(2.0F);
		this.wood = wood;
		setLightOpacity(0);
		setCreativeTab(ModTabs.blocks);
		setStepSound(Block.soundTypeWood);
		setBlockName(Reference.MOD_ID + ".stairPlanks" + wood.name());
	}

	@Override
	public String getLocalizedName() {
		return String.format(StatCollector.translateToLocal("tile." + Reference.MOD_ID + ".stairPlanks.name"), wood.getTranslatedName());
	}
}