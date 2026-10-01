package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.MidgeSwarmRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;

/**
 * Legacy midge geometry, repeated in five independently moving swarm members.
 */
public class MidgeSwarmModel extends EntityModel<MidgeSwarmRenderState> {
    private final ModelPart[] midges = new ModelPart[5];
    private final boolean wingsOnly;

    public MidgeSwarmModel(ModelPart root, boolean wingsOnly) {
        super(root);
        this.wingsOnly = wingsOnly;
        for (int i = 0; i < midges.length; i++) midges[i] = root.getChild("midge" + i);
    }

    public static LayerDefinition createBodyLayer() {
        var mesh = new MeshDefinition();
        for (int i = 0; i < 5; i++) {
            var midge = mesh.getRoot().addOrReplaceChild("midge" + i, CubeListBuilder.create(), PartPose.ZERO);
            var body = midge.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
            var wings = midge.addOrReplaceChild("wings", CubeListBuilder.create(), PartPose.ZERO);
            body.addOrReplaceChild("LFLeg", CubeListBuilder.create().texOffs(0, 10).addBox(-0.5F, 0F, -0.5F, 1, 7, 1), PartPose.offsetAndRotation(4.5F, 17F, -4.5F, 0F, 0F, 0F));
            body.addOrReplaceChild("LFLeg2", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -0.4666667F, -1F, 4, 1, 1), PartPose.offsetAndRotation(2F, 20F, -4F, 0F, 0F, -0.9599311F));
            body.addOrReplaceChild("LMLeg", CubeListBuilder.create().texOffs(0, 10).addBox(-0.5F, 0F, -0.5F, 1, 7, 1), PartPose.offsetAndRotation(4.5F, 17F, -3F, 0F, 0F, 0F));
            body.addOrReplaceChild("LMLeg2", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -0.4666667F, -0.5F, 4, 1, 1), PartPose.offsetAndRotation(2F, 20F, -3F, 0F, 0F, -0.9599311F));
            body.addOrReplaceChild("LBLeg", CubeListBuilder.create().texOffs(0, 10).addBox(-0.5F, 0F, -0.5F, 1, 7, 1), PartPose.offsetAndRotation(4.5F, 17F, -1.5F, 0F, 0F, 0F));
            body.addOrReplaceChild("LBLeg2", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -0.4666667F, -0.5F, 4, 1, 1), PartPose.offsetAndRotation(2F, 20F, -1.5F, 0F, 0F, -0.9599311F));
            body.addOrReplaceChild("RBLeg", CubeListBuilder.create().texOffs(0, 10).addBox(-0.5F, 0F, -0.5F, 1, 7, 1), PartPose.offsetAndRotation(-4.5F, 17F, -1.5F, 0F, 0F, 0F));
            body.addOrReplaceChild("RBLeg2", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, -0.4666667F, -0.5F, 4, 1, 1), PartPose.offsetAndRotation(-2F, 20F, -1.5F, 0F, 0F, 0.9599311F));
            body.addOrReplaceChild("RMLeg", CubeListBuilder.create().texOffs(0, 10).addBox(-0.5F, 0F, -0.5F, 1, 7, 1), PartPose.offsetAndRotation(-4.5F, 17F, -3F, 0F, 0F, 0F));
            body.addOrReplaceChild("RMLeg2", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, -0.5F, -0.5F, 4, 1, 1), PartPose.offsetAndRotation(-2F, 20F, -3F, 0F, 0F, 0.9599311F));
            body.addOrReplaceChild("RFLeg", CubeListBuilder.create().texOffs(0, 10).addBox(-0.5F, 0F, -0.5F, 1, 7, 1), PartPose.offsetAndRotation(-4.5F, 17F, -4.5F, 0F, 0F, 0F));
            body.addOrReplaceChild("RFLeg2", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, -0.4666667F, -1F, 4, 1, 1), PartPose.offsetAndRotation(-2F, 20F, -4F, 0F, 0F, 0.9599311F));
            body.addOrReplaceChild("Proboscis", CubeListBuilder.create().texOffs(21, 0).addBox(-0.5F, 0F, -1F, 1, 4, 1), PartPose.offsetAndRotation(0F, 19F, -6F, -0.2443461F, 0F, 0F));
            body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(26, 0).addBox(-1.5F, -1F, -2F, 3, 2, 2), PartPose.offsetAndRotation(0F, 19F, -5F, 0F, 0F, 0F));
            body.addOrReplaceChild("Thorax_Front", CubeListBuilder.create().texOffs(24, 5).addBox(-2F, -2F, 0F, 4, 3, 3), PartPose.offsetAndRotation(0F, 19F, -5F, 0F, 0F, 0F));
            body.addOrReplaceChild("Thorax_Mid", CubeListBuilder.create().texOffs(24, 12).addBox(-2F, -3F, 1F, 4, 3, 3), PartPose.offsetAndRotation(0F, 19F, -5F, 0F, 0F, 0F));
            body.addOrReplaceChild("Thorax_Back", CubeListBuilder.create().texOffs(30, 19).addBox(-0.5F, -2F, 4F, 1, 1, 1), PartPose.offsetAndRotation(0F, 19F, -5F, 0F, 0F, 0F));
            body.addOrReplaceChild("AB1", CubeListBuilder.create().texOffs(43, 17).addBox(-1.5F, -1F, 0F, 3, 3, 2), PartPose.offsetAndRotation(0F, 17F, 0F, 0F, 0F, 0F));
            body.addOrReplaceChild("AB2", CubeListBuilder.create().texOffs(42, 11).addBox(-2F, 0F, 2F, 4, 3, 2), PartPose.offsetAndRotation(0F, 17F, 0F, 0F, 0F, 0F));
            body.addOrReplaceChild("AB3", CubeListBuilder.create().texOffs(43, 5).addBox(-1.5F, 1F, 4F, 3, 3, 2), PartPose.offsetAndRotation(0F, 17F, 0F, 0F, 0F, 0F));
            body.addOrReplaceChild("AB4", CubeListBuilder.create().texOffs(43, 0).addBox(-1.5F, 3F, 6F, 3, 2, 2), PartPose.offsetAndRotation(0F, 17F, 0F, 0F, 0F, 0F));
            wings.addOrReplaceChild("WingL", CubeListBuilder.create().texOffs(0, 20).addBox(-3F, -3.5F, 2F, 2, 0, 2), PartPose.offsetAndRotation(0F, 19F, -5F, 0F, 0F, 0F));
            wings.addOrReplaceChild("WingL2", CubeListBuilder.create().texOffs(0, 24).addBox(-5F, -3.5F, 3F, 3, 0, 2), PartPose.offsetAndRotation(0F, 19F, -5F, 0F, 0F, 0F));
            wings.addOrReplaceChild("WingL3", CubeListBuilder.create().texOffs(0, 27).addBox(-6F, -3.5F, 4F, 3, 0, 4), PartPose.offsetAndRotation(0F, 19F, -5F, 0F, 0F, 0F));
            wings.addOrReplaceChild("WingL4", CubeListBuilder.create().texOffs(6, 10).addBox(-8F, -3.5F, 7F, 3, 0, 5), PartPose.offsetAndRotation(0F, 19F, -5F, 0F, 0F, 0F));
            wings.addOrReplaceChild("WingR", CubeListBuilder.create().texOffs(0, 20).addBox(1F, -3.5F, 2F, 2, 0, 2), PartPose.offsetAndRotation(0F, 19F, -5F, 0F, 0F, 0F));
            wings.addOrReplaceChild("WingR2", CubeListBuilder.create().texOffs(0, 24).addBox(2F, -3.5F, 3F, 3, 0, 2), PartPose.offsetAndRotation(0F, 19F, -5F, 0F, 0F, 0F));
            wings.addOrReplaceChild("WingR3", CubeListBuilder.create().texOffs(0, 27).addBox(3F, -3.5F, 4F, 3, 0, 4), PartPose.offsetAndRotation(0F, 19F, -5F, 0F, 0F, 0F));
            wings.addOrReplaceChild("WingR4", CubeListBuilder.create().texOffs(6, 10).addBox(5F, -3.5F, 7F, 3, 0, 5), PartPose.offsetAndRotation(0F, 19F, -5F, 0F, 0F, 0F));
            body.addOrReplaceChild("MidWing", CubeListBuilder.create().texOffs(12, 0).addBox(-1F, -4F, 2F, 2, 1, 1), PartPose.offsetAndRotation(0F, 19F, -5F, 0F, 0F, 0F));
        }
        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(MidgeSwarmRenderState state) {
        super.setupAnim(state);
        float drift = (float) Math.sin(state.ageInTicks * 0.25F) * 4;
        float flap = (float) Math.sin(state.ageInTicks * 1.2F) * 0.5F;
        for (int i = 0; i < midges.length; i++) {
            var midge = midges[i];
            midge.visible = state.health > i * 3;
            midge.getChild("body").visible = !wingsOnly;
            var wings = midge.getChild("wings");
            wings.visible = wingsOnly;
            for (var part : wings.getAllParts()) if (part != wings) part.xRot = flap;
        }
        midges[0].setPos(-drift, -16 + drift, -drift);
        midges[1].setPos(8 + drift, -24 + drift, 8 + drift);
        midges[2].setPos(-8 - drift, -drift, -8 - drift);
        midges[3].setPos(8 + drift, drift, -8 + drift);
        midges[4].setPos(-8 - drift, -24 - drift, -8 - drift);
    }
}
