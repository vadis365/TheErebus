// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
package erebus.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.entity.BombardierBeetle;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BombardierBeetleModel<T extends BombardierBeetle> extends HierarchicalModel<T> {
	public ModelPart root;
	private final ModelPart Thx;
	private final ModelPart ThxS;
	private final ModelPart Ab;
	private final ModelPart AbSide;
	private final ModelPart AbBack;
	private final ModelPart LeftBackLeg;
	private final ModelPart LeftMidLeg;
	private final ModelPart LeftFrontLeg;
	private final ModelPart RightBackLeg;
	private final ModelPart RightMidLeg;
	private final ModelPart RightFrontLeg;
	private final ModelPart Head;

	public BombardierBeetleModel(ModelPart root) {
		this.root = root;
		this.Thx = root.getChild("Thx");
		this.ThxS = root.getChild("ThxS");
		this.Ab = root.getChild("Ab");
		this.AbSide = root.getChild("AbSide");
		this.AbBack = root.getChild("AbBack");
		this.LeftBackLeg = root.getChild("LeftBackLeg");
		this.LeftMidLeg = root.getChild("LeftMidLeg");
		this.LeftFrontLeg = root.getChild("LeftFrontLeg");
		this.RightBackLeg = root.getChild("RightBackLeg");
		this.RightMidLeg = root.getChild("RightMidLeg");
		this.RightFrontLeg = root.getChild("RightFrontLeg");
		this.Head = root.getChild("Head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Thx = partdefinition.addOrReplaceChild("Thx", CubeListBuilder.create().texOffs(14, 13).addBox(-3.5F, -3.0F, -1.0F, 7.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

		PartDefinition ThxS = partdefinition.addOrReplaceChild("ThxS", CubeListBuilder.create().texOffs(15, 27).addBox(-5.0F, -2.0F, 0.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

		PartDefinition Ab = partdefinition.addOrReplaceChild("Ab", CubeListBuilder.create().texOffs(0, 90).addBox(-4.5F, -4.5F, -2.0F, 9.0F, 7.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.0F, 7.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition AbSide = partdefinition.addOrReplaceChild("AbSide", CubeListBuilder.create().texOffs(0, 40).addBox(-6.0F, -2.0F, 1.0F, 12.0F, 4.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, 5.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition AbBack = partdefinition.addOrReplaceChild("AbBack", CubeListBuilder.create().texOffs(22, 120).addBox(-3.0F, -2.0F, 21.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, 5.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition LeftBackLeg = partdefinition.addOrReplaceChild("LeftBackLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, 18.0F, 14.0F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LBLA = LeftBackLeg.addOrReplaceChild("LBLA", CubeListBuilder.create().texOffs(42, 95).addBox(-1.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition LBLB = LeftBackLeg.addOrReplaceChild("LBLB", CubeListBuilder.create().texOffs(0, 88).addBox(5.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition LBLC = LeftBackLeg.addOrReplaceChild("LBLC", CubeListBuilder.create().texOffs(0, 82).addBox(3.5F, 5.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition LBLD = LeftBackLeg.addOrReplaceChild("LBLD", CubeListBuilder.create().texOffs(0, 76).addBox(2.5F, 9.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.829F));

		PartDefinition LeftMidLeg = partdefinition.addOrReplaceChild("LeftMidLeg", CubeListBuilder.create(), PartPose.offset(5.0F, 18.0F, 8.0F));

		PartDefinition LMLA = LeftMidLeg.addOrReplaceChild("LMLA", CubeListBuilder.create().texOffs(0, 95).addBox(0.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition LMLB = LeftMidLeg.addOrReplaceChild("LMLB", CubeListBuilder.create().texOffs(0, 88).addBox(3.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition LMLC = LeftMidLeg.addOrReplaceChild("LMLC", CubeListBuilder.create().texOffs(0, 82).addBox(1.5F, 4.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition LMLD = LeftMidLeg.addOrReplaceChild("LMLD", CubeListBuilder.create().texOffs(0, 76).addBox(0.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition LeftFrontLeg = partdefinition.addOrReplaceChild("LeftFrontLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 18.0F, 2.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LFLA = LeftFrontLeg.addOrReplaceChild("LFLA", CubeListBuilder.create().texOffs(0, 95).addBox(0.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition LFLB = LeftFrontLeg.addOrReplaceChild("LFLB", CubeListBuilder.create().texOffs(0, 88).addBox(3.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition LFLC = LeftFrontLeg.addOrReplaceChild("LFLC", CubeListBuilder.create().texOffs(0, 82).addBox(1.5F, 4.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition LFLD = LeftFrontLeg.addOrReplaceChild("LFLD", CubeListBuilder.create().texOffs(0, 76).addBox(0.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition RightBackLeg = partdefinition.addOrReplaceChild("RightBackLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, 18.0F, 14.0F, 0.0F, -2.4435F, 0.0F));

		PartDefinition RBLA = RightBackLeg.addOrReplaceChild("RBLA", CubeListBuilder.create().texOffs(42, 95).addBox(-1.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition RBLB = RightBackLeg.addOrReplaceChild("RBLB", CubeListBuilder.create().texOffs(0, 88).addBox(5.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition RBLC = RightBackLeg.addOrReplaceChild("RBLC", CubeListBuilder.create().texOffs(0, 82).addBox(3.5F, 5.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition RBLD = RightBackLeg.addOrReplaceChild("RBLD", CubeListBuilder.create().texOffs(0, 76).addBox(2.5F, 9.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.829F));

		PartDefinition RightMidLeg = partdefinition.addOrReplaceChild("RightMidLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, 18.0F, 8.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition RMLA = RightMidLeg.addOrReplaceChild("RMLA", CubeListBuilder.create().texOffs(0, 95).addBox(0.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition RMLB = RightMidLeg.addOrReplaceChild("RMLB", CubeListBuilder.create().texOffs(0, 88).addBox(3.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition RMLC = RightMidLeg.addOrReplaceChild("RMLC", CubeListBuilder.create().texOffs(0, 82).addBox(1.5F, 4.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition RMLD = RightMidLeg.addOrReplaceChild("RMLD", CubeListBuilder.create().texOffs(0, 76).addBox(0.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition RightFrontLeg = partdefinition.addOrReplaceChild("RightFrontLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 18.0F, 2.0F, 0.0F, 2.4435F, 0.0F));

		PartDefinition RFLA = RightFrontLeg.addOrReplaceChild("RFLA", CubeListBuilder.create().texOffs(0, 95).addBox(0.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition RFLB = RightFrontLeg.addOrReplaceChild("RFLB", CubeListBuilder.create().texOffs(0, 88).addBox(3.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition RFLC = RightFrontLeg.addOrReplaceChild("RFLC", CubeListBuilder.create().texOffs(0, 82).addBox(1.5F, 4.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition RFLD = RightFrontLeg.addOrReplaceChild("RFLD", CubeListBuilder.create().texOffs(0, 76).addBox(0.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, -1.0F));

		PartDefinition Head1 = Head.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(16, 0).addBox(-2.5F, -1.5F, -6.0F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition Head2 = Head.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(39, 0).addBox(-2.0F, -1.0F, -7.5F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition Eyes = Head.addOrReplaceChild("Eyes", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -1.0F, -4.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition LMandible = Head.addOrReplaceChild("LMandible", CubeListBuilder.create().texOffs(52, 0).addBox(0.5F, 1.0F, -10.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition RMandible = Head.addOrReplaceChild("RMandible", CubeListBuilder.create().texOffs(52, 0).addBox(-1.5F, 1.0F, -10.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition AntRE = Head.addOrReplaceChild("AntRE", CubeListBuilder.create().texOffs(40, 15).addBox(-7.8842F, -0.5F, -0.3182F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 1.0F, -5.0F, 0.0F, -0.8727F, 0.0F));

		PartDefinition AntLE = Head.addOrReplaceChild("AntLE", CubeListBuilder.create().texOffs(40, 15).addBox(-0.1158F, -0.5F, -0.3182F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.0F, -5.0F, 0.0F, 0.8727F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Head.yRot = netHeadYaw / (180F / (float) Math.PI);
		
		float sin = Mth.sin(limbSwing) * 0.8F * limbSwingAmount;
		float cos = Mth.cos(limbSwing) * 0.2F * limbSwingAmount;

		LeftBackLeg.zRot = -cos;
		LeftMidLeg.zRot = cos;
		LeftFrontLeg.zRot = -cos;
		RightBackLeg.zRot = -cos;
		RightMidLeg.zRot = cos;
		RightFrontLeg.zRot = -cos;
		LeftBackLeg.yRot = -0.6981F + sin;
		LeftMidLeg.yRot = 0F - sin;
		LeftFrontLeg.yRot = 0.6981F + sin;
		RightBackLeg.yRot = -2.4435F + sin;
		RightMidLeg.yRot = 3.1416F - sin;
		RightFrontLeg.yRot = 2.4435F + sin;

	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		Thx.render(stack, consumer, light, overlay, colour);
		ThxS.render(stack, consumer, light, overlay, colour);
		Ab.render(stack, consumer, light, overlay, colour);
		AbSide.render(stack, consumer, light, overlay, colour);
		AbBack.render(stack, consumer, light, overlay, colour);
		LeftBackLeg.render(stack, consumer, light, overlay, colour);
		LeftMidLeg.render(stack, consumer, light, overlay, colour);
		LeftFrontLeg.render(stack, consumer, light, overlay, colour);
		RightBackLeg.render(stack, consumer, light, overlay, colour);
		RightMidLeg.render(stack, consumer, light, overlay, colour);
		RightFrontLeg.render(stack, consumer, light, overlay, colour);
		Head.render(stack, consumer, light, overlay, colour);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}
