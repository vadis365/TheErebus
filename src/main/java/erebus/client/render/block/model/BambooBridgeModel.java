package erebus.client.render.block.model;


import erebus.client.render.block.renderer.state.BambooBridgeBlockEntityRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class BambooBridgeModel extends Model<BambooBridgeBlockEntityRenderState> {

    private final ModelPart[] rightRail;
    private final ModelPart[] leftRail;

    public BambooBridgeModel(ModelPart root) {
        super(root, RenderTypes::entityCutout);
        rightRail = new ModelPart[]{root.getChild("SupportR1"), root.getChild("SupportR2"), root.getChild("StringR1"), root.getChild("StringR2"), root.getChild("String4")};
        leftRail = new ModelPart[]{root.getChild("SupportL1"), root.getChild("SupportL2"), root.getChild("StringL1"), root.getChild("StringL2"), root.getChild("String3")};
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("BambooStep1", CubeListBuilder.create().texOffs(1, 1).addBox(0.0F, 0.0F, 0.0F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 22.0F, -4.5F, -1.5708F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("BambooStep3", CubeListBuilder.create().texOffs(1, 1).addBox(0.0F, 0.0F, 0.0F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 22.0F, 3.5F, -1.5708F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("BambooStep2", CubeListBuilder.create().texOffs(1, 1).addBox(0.0F, 0.0F, 0.0F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 22.0F, -0.5F, -1.5708F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("BambooStep4", CubeListBuilder.create().texOffs(1, 1).addBox(0.0F, 0.0F, 0.0F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 22.0F, 7.5F, -1.5708F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("SupportR1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 14.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 24.0F, -7.5F, 0.0F, 0.0F, -1.5708F));

        partdefinition.addOrReplaceChild("SupportR2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 14.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 24.0F, 4.5F, 0.0F, 0.0F, -1.5708F));

        partdefinition.addOrReplaceChild("SupportL1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 14.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 24.0F, -7.5F, 0.0F, 0.0F, -1.5708F));

        partdefinition.addOrReplaceChild("SupportL2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 14.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 24.0F, 4.5F, 0.0F, 0.0F, -1.5708F));

        partdefinition.addOrReplaceChild("String1", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 0.0F, 0.0F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 22.5F, 8.0F, -1.5708F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("String2", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 0.0F, 0.0F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 22.5F, 8.0F, -1.5708F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("String3", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 0.0F, 0.0F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.6F, 12.0F, -8.0F, 1.5708F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("String4", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 0.0F, 0.0F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.4F, 12.0F, -8.0F, 1.5708F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("StringR1", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 0.2F, 0.0F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.4F, 11.0F, -2.5F, 0.0F, 0.0F, -0.1222F));

        partdefinition.addOrReplaceChild("StringR2", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 0.3F, 0.0F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.4F, 11.0F, 1.5F, 0.0F, 0.0F, -0.1222F));

        partdefinition.addOrReplaceChild("StringL1", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 0.1F, 0.0F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.6F, 11.0F, -2.5F, 0.0F, 0.0F, 0.1222F));

        partdefinition.addOrReplaceChild("StringL2", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 0.2F, 0.0F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.6F, 11.0F, 1.5F, 0.0F, 0.0F, 0.1222F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void setupAnim(BambooBridgeBlockEntityRenderState state) {
        super.setupAnim(state);
        for (ModelPart part : rightRail) part.visible = state.renderSide1;
        for (ModelPart part : leftRail) part.visible = state.renderSide2;
    }
}
