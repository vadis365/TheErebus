package erebus.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PortalActivator extends Item {

	public PortalActivator() {
		setMaxStackSize(1);
		setMaxDamage(64);
		setFull3D();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
	}
}