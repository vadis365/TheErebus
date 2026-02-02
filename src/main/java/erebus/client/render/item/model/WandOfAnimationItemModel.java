package erebus.client.render.item.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Unit;

public class WandOfAnimationItemModel extends Model<Unit> {

	public final ModelPart Jewel1;
	public final ModelPart TopR3;
	public final ModelPart Shaft;
	boolean up;

	public WandOfAnimationItemModel(ModelPart root) {
		super(root, RenderTypes::entitySolid);
		this.Jewel1 = root.getChild("Jewel1");
		this.TopR3 = root.getChild("TopR3");
		this.Shaft = root.getChild("Shaft");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Jewel1 = partdefinition.addOrReplaceChild("Jewel1", CubeListBuilder.create().texOffs(0, 7).addBox(-2.0F, -7.7F, -2.0F, 4.0F, 4.0F, 4.0F,	new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		Jewel1.addOrReplaceChild("Jewel2", CubeListBuilder.create().texOffs(0, 7).addBox(-2.0F, -6.0F, 2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
		Jewel1.addOrReplaceChild("Jewel3", CubeListBuilder.create().texOffs(0, 7).addBox(-6.0F, -6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));
		PartDefinition TopR3 = partdefinition.addOrReplaceChild("TopR3", CubeListBuilder.create().texOffs(0, 0).addBox(4.0F, -8.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
		TopR3.addOrReplaceChild("TopR1", CubeListBuilder.create().texOffs(5, 0).addBox(-2.0F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));
		TopR3.addOrReplaceChild("TopR2", CubeListBuilder.create().texOffs(10, 0).addBox(-2.0F, -6.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));
		TopR3.addOrReplaceChild("TopR4", CubeListBuilder.create().texOffs(17, 0).addBox(8.3F, -5.3F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.6981F));
		TopR3.addOrReplaceChild("TopR5", CubeListBuilder.create().texOffs(24, 0).addBox(9.0F, -8.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.8727F));
		PartDefinition Shaft = partdefinition.addOrReplaceChild("Shaft", CubeListBuilder.create().texOffs(0, 33).addBox(-1.0F, 6.0F, -1.0F, 2.0F, 19.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));
		Shaft.addOrReplaceChild("Dec4", CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		Shaft.addOrReplaceChild("Dec3", CubeListBuilder.create().texOffs(0, 23).addBox(-1.0F, 3.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		Shaft.addOrReplaceChild("Dec2", CubeListBuilder.create().texOffs(0, 28).addBox(-1.5F, 5.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		Shaft.addOrReplaceChild("Dec1", CubeListBuilder.create().texOffs(0, 28).addBox(-1.5F, 5.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		Shaft.addOrReplaceChild("Pommel1", CubeListBuilder.create().texOffs(0, 55).addBox(-1.5F, 16.25F, -19.25F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
		Shaft.addOrReplaceChild("Pommel2", CubeListBuilder.create().texOffs(0, 55).addBox(16.3F, 16.3F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));
		Shaft.addOrReplaceChild("Pommel3", CubeListBuilder.create().texOffs(0, 55).addBox(-1.5F, 23.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 64);
	}
}
