package erebus.item;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.ModTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemPortalActivator extends Item {

	public ItemPortalActivator() {
		setFull3D();
		setMaxDamage(64);
		setMaxStackSize(1);
		setCreativeTab(ModTabs.gears);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
	}
}