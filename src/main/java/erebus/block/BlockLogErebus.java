package erebus.block;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.lib.EnumWood;
import erebus.lib.Reference;

public class BlockLogErebus extends BlockLog {

	@SideOnly(Side.CLIENT)
	private IIcon iconSide, iconTop;

	private final EnumWood wood;

	public BlockLogErebus(EnumWood wood) {
		this.wood = wood;
		setCreativeTab(ModTabs.blocks);
		setBlockName(Reference.MOD_ID + ".log" + wood.name());
	}

	@Override
	public String getLocalizedName() {
		return String.format(StatCollector.translateToLocal("tile." + Reference.MOD_ID + ".log.name"), wood.getTranslatedName());
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int meta) {
		return iconSide;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getTopIcon(int meta) {
		return iconTop;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		iconSide = iconRegister.registerIcon("erebus:log_" + wood.name().toLowerCase());
		iconTop = iconRegister.registerIcon("erebus:log_" + wood.name().toLowerCase() + "_top");
	}
}