package erebus.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntitySoldierAntTrap;

@SideOnly(Side.CLIENT)
public class ModelSoldierAntTrap extends ModelBase {
    ModelRenderer rightMandA;
    ModelRenderer leftMandA;
    ModelRenderer headMain;
    ModelRenderer mainBlock;
    ModelRenderer rightMandB;
    ModelRenderer leftMandB;
    ModelRenderer headMid;
    ModelRenderer eyes;
    ModelRenderer rightAntE;
    ModelRenderer rightAntS;
    ModelRenderer leftAntE;
    ModelRenderer leftAntS;

    public ModelSoldierAntTrap() {
        textureWidth = 64;
        textureHeight = 64;
        leftMandA = new ModelRenderer(this, 52, 8);
        leftMandA.setRotationPoint(2.0F, 16.0F, -2.2F);
        leftMandA.addBox(1.0F, -0.5F, -4.0F, 1, 1, 3, 0.0F);
        setRotation(leftMandA, 0.0F, -0.5585053606381855F, 0.0F);
        mainBlock = new ModelRenderer(this, 0, 0);
        mainBlock.setRotationPoint(0.0F, 16.0F, 0.0F);
        mainBlock.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
        rightAntS = new ModelRenderer(this, 0, 6);
        rightAntS.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightAntS.addBox(-7.0F, -0.5F, -8.0F, 2, 1, 1, 0.0F);
        eyes = new ModelRenderer(this, 0, 33);
        eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
        eyes.addBox(-6.0F, -1.0F, -6.0F, 12, 2, 2, 0.0F);
        headMain = new ModelRenderer(this, 24, 41);
        headMain.setRotationPoint(0.0F, 16.0F, 7.9F);
        headMain.addBox(-5.0F, -2.0F, -10.0F, 10, 4, 10, 0.0F);
        leftAntE = new ModelRenderer(this, 0, 0);
        leftAntE.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftAntE.addBox(6.9F, -0.5F, -12.0F, 1, 1, 4, 0.0F);
        headMid = new ModelRenderer(this, 0, 38);
        headMid.setRotationPoint(0.0F, 0.0F, 0.0F);
        headMid.addBox(-4.0F, -3.0F, -8.0F, 8, 6, 6, 0.0F);
        rightMandA = new ModelRenderer(this, 52, 8);
        rightMandA.setRotationPoint(-2.0F, 16.0F, -2.3F);
        rightMandA.addBox(-2.0F, -0.5F, -4.0F, 1, 1, 3, 0.0F);
        setRotation(rightMandA, 0.0F, 0.5585053606381855F, 0.0F);
        rightAntE = new ModelRenderer(this, 0, 0);
        rightAntE.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightAntE.addBox(-7.9F, -0.5F, -12.0F, 1, 1, 4, 0.0F);
        leftMandB = new ModelRenderer(this, 48, 0);
        leftMandB.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftMandB.addBox(-1.0F, -0.5F, -6.0F, 2, 1, 6, 0.0F);
        leftAntS = new ModelRenderer(this, 0, 6);
        leftAntS.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftAntS.addBox(5.0F, -0.5F, -8.0F, 2, 1, 1, 0.0F);
        rightMandB = new ModelRenderer(this, 48, 0);
        rightMandB.mirror = true;
        rightMandB.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightMandB.addBox(-1.0F, -0.5F, -6.0F, 2, 1, 6, 0.0F);
        headMain.addChild(rightAntS);
        headMain.addChild(eyes);
        headMain.addChild(leftAntE);
        headMain.addChild(headMid);
        headMain.addChild(rightAntE);
        leftMandA.addChild(leftMandB);
        headMain.addChild(leftAntS);
        rightMandA.addChild(rightMandB);
    }

    public void render(TileEntitySoldierAntTrap tile) { 
        mainBlock.render(0.0625F);
        GL11.glPushMatrix();
        if(tile.animationTicks <= 8)
        	GL11.glTranslatef(0F, 0F, 0F - 1F/8 * tile.animationTicks);
        if(tile.animationTicks > 8)
        	GL11.glTranslatef(0F, 0F, 0F - 1F);
        if(tile.animationTicks > 1) {
        	headMain.render(0.0625F);

        	GL11.glPushMatrix();
        	GL11.glTranslatef(-0.0625F, 0F, 0.0625F);
        	if(tile.animationTicks > 8)
        		GL11.glRotatef(0F - tile.animationTicks * 2.5F, 0, 1, 0);
        	else
        		GL11.glRotatef(-16, 0, 1, 0);
        	rightMandA.render(0.0625F);
        	GL11.glPopMatrix();

        	GL11.glPushMatrix();
    		GL11.glTranslatef(0.0625F, 0F, 0.0625F);
        	if(tile.animationTicks > 8)
        		GL11.glRotatef(0F + tile.animationTicks * 2.5F, 0, 1, 0);
        	else
        		GL11.glRotatef(16, 0, 1, 0);
        	leftMandA.render(0.0625F);
        	GL11.glPopMatrix();
        }
        GL11.glPopMatrix();

    }

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
