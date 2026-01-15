package erebus.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.entity.Moth;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class MothModel<T extends Moth> extends HierarchicalModel<T> {
	public ModelPart root;
	public ModelPart body;
	public ModelPart head;
	public ModelPart rearend;
	public ModelPart head2;
	public ModelPart rightwing;
	public ModelPart leftwing;

	public MothModel(ModelPart root) {
		this.root = root;
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.rearend = root.getChild("rearend");
		this.head2 = root.getChild("head2");
		this.rightwing = root.getChild("rightwing");
		this.leftwing = root.getChild("leftwing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(70, 0).addBox(-3.0F, -3.0F, -5.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 0.0F));

		PartDefinition rearend = partdefinition.addOrReplaceChild("rearend", CubeListBuilder.create().texOffs(37, 0).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 10.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition head2 = partdefinition.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(-4, 18).mirror().addBox(-7.0F, -2.0F, -15.0F, 14.0F, 0.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, -0.2603F, 0.0F, 0.0F));

		PartDefinition rightwing = partdefinition.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(-28, 30).addBox(-32.0F, 0.0F, -15.0F, 33.0F, 0.0F, 37.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 4.0F, 3.0F));

		PartDefinition leftwing = partdefinition.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(-28, 70).addBox(0.0F, 0.0F, -15.0F, 33.0F, 0.0F, 37.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 4.0F, 3.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAngle, float partialRenderTicks) {
		float smoothedTicks = entity.tickCount + (entity.tickCount - (entity.tickCount - 1)) * partialRenderTicks;
		float flap = Mth.sin((smoothedTicks) * 1.2F) * 0.5F;
		rightwing.zRot= flap;
		leftwing.zRot = -flap;
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		body.render(stack, consumer, light, overlay, colour);
		head.render(stack, consumer, light, overlay, colour);
		rearend.render(stack, consumer, light, overlay, colour);
		head2.render(stack, consumer, light, overlay, colour);
		rightwing.render(stack, consumer, light, overlay, colour);
		leftwing.render(stack, consumer, light, overlay, colour);
	}
	
	@Override
	public ModelPart root() {
		return root;
	}
}
