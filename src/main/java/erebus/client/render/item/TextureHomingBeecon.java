package erebus.client.render.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class TextureHomingBeecon extends TextureAtlasSprite {

	public double currentAngle;
	public double angleDelta;
	public int targetX, targetZ;

	public TextureHomingBeecon(String name) {
		super(name);
	}

	@Override
	public void updateAnimation() {
		Minecraft minecraft = Minecraft.getMinecraft();
		if (minecraft.theWorld != null && minecraft.thePlayer != null)
			updateCompass(minecraft.theWorld, minecraft.thePlayer.posX, minecraft.thePlayer.posZ, minecraft.thePlayer.rotationYaw, false, false);
		else
			updateCompass((World) null, 0.0D, 0.0D, 0.0D, true, false);
	}

	public void updateCompass(World world, double x, double z, double rotation, boolean flag1, boolean flag2) {
		getBeeconHome();
		if (!framesTextureData.isEmpty()) {
			double angle = 0.0D;
			if (world != null && !flag1) {
				double locationX = targetX - x;
				double locationZ = targetZ - z;
				rotation %= 360.0D;
				angle = -((rotation - 90.0D) * Math.PI / 180.0D - Math.atan2(locationZ, locationX));
			}

			if (flag2)
				currentAngle = angle;
			else {
				double angle2;
				for (angle2 = angle - currentAngle; angle2 < -Math.PI; angle2 += Math.PI * 2D)
					;

				while (angle2 >= Math.PI)
					angle2 -= Math.PI * 2D;

				if (angle2 < -1.0D)
					angle2 = -1.0D;

				if (angle2 > 1.0D)
					angle2 = 1.0D;

				angleDelta += angle2 * 0.1D;
				angleDelta *= 0.8D;
				currentAngle += angleDelta;
			}

			int i;
			for (i = (int) ((currentAngle / (Math.PI * 2D) + 1.0D) * framesTextureData.size()) % framesTextureData.size(); i < 0; i = (i + framesTextureData.size()) % framesTextureData.size())
				;

			if (i != frameCounter) {
				frameCounter = i;
				TextureUtil.uploadTextureMipmap((int[][]) framesTextureData.get(frameCounter), width, height, originX, originY, false, false);
			}
		}
	}

	private void getBeeconHome() {
		Minecraft minecraft = Minecraft.getMinecraft();
		if (minecraft.theWorld != null && minecraft.thePlayer != null) {
			ItemStack stack = minecraft.thePlayer.inventory.getCurrentItem();
			if (stack == null)
				return;

			boolean isCompassItem = stack.getItem() == ModItems.homingBeecon || stack.getItem() == ModItems.homingBeeconAdvanced || stack.getItem() == ModItems.deathCompass;

			if (isCompassItem && stack.hasTagCompound() && stack.stackTagCompound.hasKey("homeX")) {
				targetX = stack.getTagCompound().getInteger("homeX");
				targetZ = stack.getTagCompound().getInteger("homeZ");
			}
		}
	}
}