package erebus.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

//TODO Srinking eyes
public class ModelBloodSnail extends ModelBase
{
    ModelRenderer Body;
    ModelRenderer Head;
    ModelRenderer Tail;
    ModelRenderer Shell1;
    ModelRenderer Eye1;
    ModelRenderer Eye2;
    ModelRenderer Shell2;

  public ModelBloodSnail()
  {
    textureWidth = 64;
    textureHeight = 32;

      Body = new ModelRenderer(this, 0, 0);
      Body.addBox(0F, 0F, 0F, 4, 2, 14);
      Body.setRotationPoint(-2F, 22F, -7F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);

      Head = new ModelRenderer(this, 36, 0);
      Head.addBox(-1.5F, 0F, 0F, 3, 1, 1);
      Head.setRotationPoint(0F, 23F, -8F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);

      Tail = new ModelRenderer(this, 36, 3);
      Tail.addBox(-1.5F, 0F, 0F, 3, 1, 1);
      Tail.setRotationPoint(0.03333334F, 23F, 7F);
      Tail.setTextureSize(64, 32);
      Tail.mirror = true;
      setRotation(Tail, 0F, 0F, 0F);

      Shell1 = new ModelRenderer(this, 0, 17);
      Shell1.addBox(-1.5F, -8F, 0F, 3, 8, 3);
      Shell1.setRotationPoint(0F, 22.5F, -0.5F);
      Shell1.setTextureSize(64, 32);
      Shell1.mirror = true;
      setRotation(Shell1, -0.4363323F, 0F, 0F);

      Eye1 = new ModelRenderer(this, 40, 6);
      Eye1.addBox(0F, -5F, 0F, 1, 5, 1);
      Eye1.setRotationPoint(0F, 22.6F, -5F);
      Eye1.setTextureSize(64, 32);
      Eye1.mirror = true;
      setRotation(Eye1, 0F, 0F, 0.3490659F);

      Eye2 = new ModelRenderer(this, 36, 6);
      Eye2.addBox(0F, -5F, 0F, 1, 5, 1);
      Eye2.setRotationPoint(-1F, 23F, -5F);
      Eye2.setTextureSize(64, 32);
      Eye2.mirror = true;
      setRotation(Eye2, 0F, 0F, -0.3490659F);

      Shell2 = new ModelRenderer(this, 0, 0);
      Shell2.addBox(0F, 0F, 0F, 2, 2, 2);
      Shell2.setRotationPoint(-1F, 14.5F, 3.8F);
      Shell2.setTextureSize(64, 32);
      Shell2.mirror = true;
      setRotation(Shell2, -0.4363323F, 0F, 0F);
  }

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Body.render(f5);
    Head.render(f5);
    Tail.render(f5);
    Shell1.render(f5);
    Eye1.render(f5);
    Eye2.render(f5);
    Shell2.render(f5);
  }

  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    /*
    Eye1.rotateAngleY = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
    Eye2.rotateAngleY = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
    */
    //TODO Ask wich snail eye movement is best i think this one

    Eye1.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
    Eye2.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;

    /*
    Eye1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
    Eye2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
    */
  }
}
