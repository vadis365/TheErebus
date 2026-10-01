package erebus.client.render.entity.model.layer.mosquito;

import erebus.client.render.entity.renderer.state.MosquitoRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class MosquitoRidingModel extends EntityModel<MosquitoRenderState> {

    public final ModelPart ArmLeft1, ArmLeft2, ArmRight1, ArmRight2;

    public MosquitoRidingModel(ModelPart root) {
        super(root);
        this.ArmLeft1 = root.getChild("ArmLeft1");
        this.ArmLeft2 = root.getChild("ArmLeft2");
        this.ArmRight1 = root.getChild("ArmRight1");
        this.ArmRight2 = root.getChild("ArmRight2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(0, 41).addBox(-3.0F, -3.0F, 16.0F, 6, 6, 40), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3141593F, 0.0F, 0.0F));
        root.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(0, 61).addBox(-5.0F, 0.0F, 0.0F, 10, 10, 10), PartPose.offsetAndRotation(0.0F, 0.0F, 5.0F, -2.129302F, 0.0F, 0.0F));
        root.addOrReplaceChild("LegLeft1", CubeListBuilder.create().texOffs(52, 53).addBox(-2.0F, -2.0F, -24.0F, 4, 4, 24), PartPose.offsetAndRotation(6.0F, 6.0F, 16.0F, 1.22173F, 3.141593F, -0.2617994F));
        root.addOrReplaceChild("LegLeft2", CubeListBuilder.create().texOffs(80, 6).addBox(-1.0F, -13.0F, -20.0F, 2, 16, 2), PartPose.offsetAndRotation(6.0F, 6.0F, 16.0F, 1.884956F, 3.141593F, -0.2617994F));
        root.addOrReplaceChild("LegLeft3", CubeListBuilder.create().texOffs(64, 26).addBox(-9.5F, -4.5F, -41.0F, 1, 1, 24), PartPose.offsetAndRotation(6.0F, 6.0F, 16.0F, 2.303835F, 3.167191F, 0.2617994F));
        root.addOrReplaceChild("LegRight1", CubeListBuilder.create().texOffs(52, 53).addBox(-2.0F, -2.0F, -24.0F, 4, 4, 24), PartPose.offsetAndRotation(-6.0F, 6.0F, 16.0F, 1.22173F, 3.141593F, 0.2617994F));
        root.addOrReplaceChild("LegRight2", CubeListBuilder.create().texOffs(80, 6).addBox(-1.0F, -13.0F, -20.0F, 2, 16, 2), PartPose.offsetAndRotation(-6.0F, 6.0F, 16.0F, 1.884956F, 3.141593F, 0.2617994F));
        root.addOrReplaceChild("LegRight3", CubeListBuilder.create().texOffs(64, 26).addBox(8.5F, -4.5F, -41.0F, 1, 1, 24), PartPose.offsetAndRotation(-6.0F, 6.0F, 16.0F, 2.303835F, 3.141593F, -0.2617994F));
        root.addOrReplaceChild("ArmLeft1", CubeListBuilder.create().texOffs(64, 0).addBox(-1.0F, -1.0F, -24.0F, 2, 2, 24), PartPose.offsetAndRotation(6.0F, 8.0F, 4.0F, 1.570796F, 0.0F, 0.0F));
        root.addOrReplaceChild("ArmLeft2", CubeListBuilder.create().texOffs(64, 0).addBox(-1.0F, -1.0F, -24.0F, 2, 2, 24), PartPose.offsetAndRotation(6.0F, 8.0F, 8.0F, 1.570796F, 0.0F, 0.0F));
        root.addOrReplaceChild("ArmRight1", CubeListBuilder.create().texOffs(64, 0).addBox(-1.0F, -1.0F, -24.0F, 2, 2, 24), PartPose.offsetAndRotation(-6.0F, 8.0F, 4.0F, 1.570796F, 0.0F, 0.0F));
        root.addOrReplaceChild("ArmRight2", CubeListBuilder.create().texOffs(64, 0).addBox(-1.0F, -1.0F, -24.0F, 2, 2, 24), PartPose.offsetAndRotation(-6.0F, 8.0F, 8.0F, 1.570796F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }
}
