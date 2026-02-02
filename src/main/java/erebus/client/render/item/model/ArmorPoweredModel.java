package erebus.client.render.item.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.util.Mth;

public class ArmorPoweredModel extends EntityModel<HumanoidRenderState> {

    private final ModelPart Body;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart RightWingBase;
    private final ModelPart LeftWingBase;
    private final ModelPart ChestEngine;
    private final ModelPart RightWingUpgradeTop;
    private final ModelPart RightWingUpgradeMid;
    private final ModelPart RightWingUpgradeBottom;
    private final ModelPart LeftWingUpgradeTop;
    private final ModelPart LeftWingUpgradeMid;
    private final ModelPart LeftWingUpgradeBottom;

    public boolean isPowered;

    public ArmorPoweredModel(ModelPart root) {
        super(root);
        Body = root.getChild("Body");
        RightArm = root.getChild("RightArm");
        LeftArm = root.getChild("LeftArm");
        RightWingBase = root.getChild("RightWingBase");
        LeftWingBase = root.getChild("LeftWingBase");
        ChestEngine = root.getChild("ChestEngine");
        RightWingUpgradeTop = root.getChild("RightWingUpgradeTop");
        RightWingUpgradeMid = root.getChild("RightWingUpgradeMid");
        RightWingUpgradeBottom = root.getChild("RightWingUpgradeBottom");
        LeftWingUpgradeTop = root.getChild("LeftWingUpgradeTop");
        LeftWingUpgradeMid = root.getChild("LeftWingUpgradeMid");
        LeftWingUpgradeBottom = root.getChild("LeftWingUpgradeBottom");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild(
                "Body",
                CubeListBuilder.create()
                        .texOffs(38, 24)
                        .addBox(-4, 0, -2, 16, 22, 8),
                PartPose.ZERO
        );

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(HumanoidRenderState state) {
        super.setupAnim(state);
        this.LeftWingBase.y = state.isCrouching ? 3.0F : 0.0F;
        this.LeftWingUpgradeTop.y = state.isCrouching ? 3.0F : 0.0F;
        this.LeftWingUpgradeMid.y = state.isCrouching ? 3.0F : 0.0F;
        this.LeftWingUpgradeBottom.y = state.isCrouching ? 3.0F : 0.0F;
        this.LeftWingBase.xRot = state.elytraRotX;
        this.LeftWingUpgradeTop.xRot = state.elytraRotX;
        this.LeftWingUpgradeMid.xRot = state.elytraRotX;
        this.LeftWingUpgradeBottom.xRot = state.elytraRotX;
        this.LeftWingBase.zRot = state.elytraRotZ;
        this.LeftWingUpgradeTop.zRot = state.elytraRotZ;
        this.LeftWingUpgradeMid.zRot = state.elytraRotZ;
        this.LeftWingUpgradeBottom.zRot = state.elytraRotZ;
        this.LeftWingBase.yRot = state.elytraRotY;
        this.LeftWingUpgradeTop.yRot = state.elytraRotY;
        this.LeftWingUpgradeMid.yRot = state.elytraRotY;
        this.LeftWingUpgradeBottom.yRot = state.elytraRotY;
        this.RightWingBase.yRot = -this.LeftWingBase.yRot;
        this.RightWingUpgradeTop.yRot = -this.LeftWingUpgradeTop.yRot;
        this.RightWingUpgradeMid.yRot = -this.LeftWingUpgradeMid.yRot;
        this.RightWingUpgradeBottom.yRot = -this.LeftWingUpgradeBottom.yRot;
        this.RightWingBase.y = this.LeftWingBase.y;
        this.RightWingUpgradeTop.y = this.LeftWingUpgradeTop.y;
        this.RightWingUpgradeMid.y = this.LeftWingUpgradeMid.y;
        this.RightWingUpgradeBottom.y = this.LeftWingUpgradeBottom.y;
        this.RightWingBase.xRot = this.LeftWingBase.xRot;
        this.RightWingUpgradeTop.xRot = this.LeftWingUpgradeTop.xRot;
        this.RightWingUpgradeMid.xRot = this.LeftWingUpgradeMid.xRot;
        this.RightWingUpgradeBottom.xRot = this.LeftWingUpgradeBottom.xRot;
        this.RightWingBase.zRot = -this.LeftWingBase.zRot;
        this.RightWingUpgradeTop.zRot = -this.LeftWingUpgradeTop.zRot;
        this.RightWingUpgradeMid.zRot = -this.LeftWingUpgradeMid.zRot;
        this.RightWingUpgradeBottom.zRot = -this.LeftWingUpgradeBottom.zRot;

        float ageScale = state.ageScale;
        this.RightArm.z = Mth.sin(state.bodyRot) * 5.0F * ageScale;
        this.RightArm.x = -Mth.cos(state.bodyRot) * 5.0F * ageScale;
        this.LeftArm.z = -Mth.sin(state.bodyRot) * 5.0F * ageScale;
        this.LeftArm.x = Mth.cos(state.bodyRot) * 5.0F * ageScale;
        this.RightArm.yRot += state.bodyRot;
        this.LeftArm.yRot += state.bodyRot;
        this.LeftArm.xRot += state.bodyRot;
    }
}
