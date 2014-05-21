package erebus.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

@SideOnly(Side.CLIENT)
public class TextureHomingBeecon extends TextureAtlasSprite {

    public double currentAngle;
    public double angleDelta;
    public int targetX, targetZ;

    public TextureHomingBeecon() {
        super("erebus:homingBeecon");
    }
    
@Override
    public void updateAnimation() {
        Minecraft minecraft = Minecraft.getMinecraft();
        if (minecraft.theWorld != null && minecraft.thePlayer != null) {
            this.updateCompass(minecraft.theWorld, minecraft.thePlayer.posX, minecraft.thePlayer.posZ, minecraft.thePlayer.rotationYaw, false, false);
        }
        else {
            this.updateCompass((World)null, 0.0D, 0.0D, 0.0D, true, false);
        }
    }

    public void updateCompass(World world, double x, double z, double rotation, boolean flag1, boolean flag2) {
    	getBeeconHome();
    	if (!this.framesTextureData.isEmpty()) {
            double angle = 0.0D;
            if (world != null && !flag1) {
                double locationX = targetX - x;
                double locationZ = targetZ - z;
                rotation %= 360.0D;
                angle = -((rotation - 90.0D) * Math.PI / 180.0D - Math.atan2(locationZ, locationX));
            }

            if (flag2) {
                this.currentAngle = angle;
            }
            else {
                double angle2;
                for (angle2 = angle - this.currentAngle; angle2 < -Math.PI; angle2 += (Math.PI * 2D)) {
                    ;
                }

                while (angle2 >= Math.PI) {
                    angle2 -= (Math.PI * 2D);
                }

                if (angle2 < -1.0D) {
                    angle2 = -1.0D;
                }

                if (angle2 > 1.0D) {
                    angle2 = 1.0D;
                }

                this.angleDelta += angle2 * 0.1D;
                this.angleDelta *= 0.8D;
                this.currentAngle += this.angleDelta;
            }

            int i;
            for (i = (int)((this.currentAngle / (Math.PI * 2D) + 1.0D) * this.framesTextureData.size()) % this.framesTextureData.size(); i < 0; i = (i + this.framesTextureData.size()) % this.framesTextureData.size()) {
                ;
            }

            if (i != this.frameCounter) {
                this.frameCounter = i;
                TextureUtil.uploadTextureSub((int[])this.framesTextureData.get(this.frameCounter), this.width, this.height, this.originX, this.originY, false, false);
            }
        }
    }
    
    public void getBeeconHome(){
        Minecraft minecraft = Minecraft.getMinecraft();
        if (minecraft.theWorld != null && minecraft.thePlayer != null) {
        	ItemStack is = minecraft.thePlayer.inventory.getCurrentItem();
        	if (is != null && is.itemID == ModItems.homingBeecon.itemID && is.hasTagCompound() && is.stackTagCompound.hasKey("homeX")) {
        		targetX = is.getTagCompound().getInteger("homeX");
        		targetZ= is.getTagCompound().getInteger("homeZ");
        	}
    	}
   }
}
