package erebus.client.render.item.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;

public class ArmorPoweredModel<T extends LivingEntity> extends AgeableListModel<T> {

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
    public void renderToBuffer(PoseStack pose, VertexConsumer vertex, int packedLight, int packedOverlay, int color) {

    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return null;
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return null;
    }

    @Override
    public void setupAnim(T t, float v, float v1, float v2, float v3, float v4) {

    }
}
