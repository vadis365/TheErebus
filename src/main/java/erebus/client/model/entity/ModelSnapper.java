package erebus.client.model.entity;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import erebus.entity.EntitySnapper;

public class ModelSnapper extends ModelBase
{
    ModelRenderer roots;
    ModelRenderer main;
    ModelRenderer rim1;
    ModelRenderer rim2;
    ModelRenderer rim3;
    ModelRenderer rim4;
    ModelRenderer jawmain1;
    ModelRenderer jawtip1;
    ModelRenderer jawmain2;
    ModelRenderer jawtip2;
    ModelRenderer jawmain3;
    ModelRenderer jawtip3;
    ModelRenderer jawmain4;
    ModelRenderer jawtip4;

  public ModelSnapper()
  {
    textureWidth = 128;
    textureHeight = 32;

    roots = new ModelRenderer(this, -26, 0);
    roots.addBox(0F, 0F, 0F, 25, 0, 25);
    roots.setRotationPoint(-12.5F, 24F, -12.5F);
    roots.setTextureSize(128, 32);
    roots.mirror = true;
    setRotation(roots, 0F, 0F, 0F);

    main = new ModelRenderer(this, 50, 0);
    main.addBox(0F, 0F, 0F, 6, 2, 6);
    main.setRotationPoint(-3F, 22F, -3F);
    main.setTextureSize(128, 32);
    main.mirror = true;
    setRotation(main, 0F, 0F, 0F);

    rim1 = new ModelRenderer(this, 50, 8);
    rim1.addBox(0F, 0F, 0F, 6, 2, 1);
    rim1.setRotationPoint(-3F, 20F, -3F);
    rim1.setTextureSize(128, 32);
    rim1.mirror = true;
    setRotation(rim1, 0F, 0F, 0F);

    rim2 = new ModelRenderer(this, 50, 11);
    rim2.addBox(0F, 0F, 0F, 1, 2, 4);
    rim2.setRotationPoint(-3F, 20F, -2F);
    rim2.setTextureSize(128, 32);
    rim2.mirror = true;
    setRotation(rim2, 0F, 0F, 0F);

    rim3 = new ModelRenderer(this, 50, 8);
    rim3.addBox(0F, 0F, 0F, 6, 2, 1);
    rim3.setRotationPoint(-3F, 20F, 2F);
    rim3.setTextureSize(128, 32);
    rim3.mirror = true;
    setRotation(rim3, 0F, 0F, 0F);

    rim4 = new ModelRenderer(this, 50, 11);
    rim4.addBox(0F, 0F, 0F, 1, 2, 4);
    rim4.setRotationPoint(2F, 20F, -2F);
    rim4.setTextureSize(128, 32);
    rim4.mirror = true;
    setRotation(rim4, 0F, 0F, 0F);

    jawmain1 = new ModelRenderer(this, 50, 17);
    jawmain1.addBox(-2F, 0F, 0F, 4, 1, 9);
    jawmain1.setRotationPoint(0F, 21.5F, 3F);
    jawmain1.setTextureSize(128, 32);
    jawmain1.mirror = true;
    setRotation(jawmain1, -0.122173F, 0F, 0F);

    jawtip1 = new ModelRenderer(this, 50, 27);
    jawtip1.addBox(-1F, 0F, 9F, 2, 1, 1);
    jawtip1.setRotationPoint(0F, 21.5F, 3F);
    jawtip1.setTextureSize(128, 32);
    jawtip1.mirror = true;
    setRotation(jawtip1, -0.122173F, 0F, 0F);

    jawmain2 = new ModelRenderer(this, 50, 17);
    jawmain2.addBox(-2F, 0F, -9F, 4, 1, 9);
    jawmain2.setRotationPoint(0F, 21.5F, -3F);
    jawmain2.setTextureSize(128, 32);
    jawmain2.mirror = true;
    setRotation(jawmain2, 0.122173F, 0F, 0F);

    jawtip2 = new ModelRenderer(this, 50, 27);
    jawtip2.addBox(-1F, 0F, -10F, 2, 1, 1);
    jawtip2.setRotationPoint(0F, 21.5F, -3F);
    jawtip2.setTextureSize(128, 32);
    jawtip2.mirror = true;
    setRotation(jawtip2, 0.122173F, 0F, 0F);

    jawmain3 = new ModelRenderer(this, 50, 17);
    jawmain3.addBox(-2F, 0F, -9F, 4, 1, 9);
    jawmain3.setRotationPoint(3F, 21.5F, 0F);
    jawmain3.setTextureSize(128, 32);
    jawmain3.mirror = true;
    setRotation(jawmain3, 0.122173F, -((float)Math.PI / 2F), 0F);

    jawtip3 = new ModelRenderer(this, 50, 27);
    jawtip3.addBox(-1F, 0F, 9F, 2, 1, 1);
    jawtip3.setRotationPoint(3F, 21.5F, 0F);
    jawtip3.setTextureSize(128, 32);
    jawtip3.mirror = true;
    setRotation(jawtip3, -0.122173F, ((float)Math.PI / 2F), 0F);

    jawmain4 = new ModelRenderer(this, 50, 17);
    jawmain4.addBox(-2F, 0F, 0F, 4, 1, 9);
    jawmain4.setRotationPoint(-3F, 21.5F, 0F);
    jawmain4.setTextureSize(128, 32);
    jawmain4.mirror = true;
    setRotation(jawmain4, -0.122173F, -((float)Math.PI / 2F), 0F);

    jawtip4 = new ModelRenderer(this, 50, 27);
    jawtip4.addBox(-1F, 0F, 9F, 2, 1, 1);
    jawtip4.setRotationPoint(-3F, 21.5F, 0F);
    jawtip4.setTextureSize(128, 32);
    jawtip4.mirror = true;
    setRotation(jawtip4, -0.122173F, -((float)Math.PI / 2F), 0F);
  }

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    roots.render(f5);
    main.render(f5);
    rim1.render(f5);
    rim2.render(f5);
    rim3.render(f5);
    rim4.render(f5);
    jawmain1.render(f5);
    jawtip1.render(f5);
    jawmain2.render(f5);
    jawtip2.render(f5);
    jawmain3.render(f5);
    jawtip3.render(f5);
    jawmain4.render(f5);
    jawtip4.render(f5);
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
    if(EntitySnapper.bite == true)
    {
    	if(jawmain1.rotateAngleX < 1.5F){jawmain1.rotateAngleX+=0.1F;}
    	if(jawtip1.rotateAngleX < 1.5F){jawtip1.rotateAngleX+=0.1F;}

    	if(jawmain2.rotateAngleX > -1.5F){jawmain2.rotateAngleX-=0.1F;}
    	if(jawtip2.rotateAngleX > -1.5F){jawtip2.rotateAngleX-=0.1F;}

    	if(jawmain3.rotateAngleX > -1.5F){jawmain3.rotateAngleX-=0.1F;}
    	if(jawtip3.rotateAngleX < 1.5F){jawtip3.rotateAngleX+=0.1F;}

    	if(jawmain4.rotateAngleX < 1.5F){jawmain4.rotateAngleX+=0.1F;}
    	if(jawtip4.rotateAngleX < 1.5F){jawtip4.rotateAngleX+=0.1F;}

    	if(jawmain1.rotateAngleX >= 1.5F)
    	{
    		EntitySnapper.bite = false;
    	}
    }
    else
    {
    	if(jawmain1.rotateAngleX > 0){jawmain1.rotateAngleX-=0.01F;}
    	if(jawtip1.rotateAngleX > 0){jawtip1.rotateAngleX-=0.01F;}

    	if(jawmain2.rotateAngleX < 0){jawmain2.rotateAngleX+=0.01F;}
    	if(jawtip2.rotateAngleX < 0){jawtip2.rotateAngleX+=0.01F;}

    	if(jawmain3.rotateAngleX < 0){jawmain3.rotateAngleX+=0.01F;}
    	if(jawtip3.rotateAngleX > 0){jawtip3.rotateAngleX-=0.01F;}

    	if(jawmain4.rotateAngleX > 0){jawmain4.rotateAngleX-=0.01F;}
    	if(jawtip4.rotateAngleX > 0){jawtip4.rotateAngleX-=0.01F;}
    }
  }
}
