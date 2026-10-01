package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.AnimatedChestRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.util.Mth;

public class AnimatedChestModel extends EntityModel<AnimatedChestRenderState> {
    private final ModelPart lid, lock;
    private final ModelPart[][] legs = new ModelPart[6][4];

    public AnimatedChestModel(ModelPart root) {
        super(root);
        lid = root.getChild("Lid");
        lock = root.getChild("Lock");
        String[] groups = {"LBL", "LML", "LFL", "RBL", "RML", "RFL"};
        for (int group = 0; group < 6; group++) for (int part = 0; part < 4; part++) legs[group][part] = root.getChild(groups[group] + (part + 1));
    }

    public static LayerDefinition createBodyLayer() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();
        root.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(0, 46).addBox(0F, -1F, -1.5F, 5, 3, 3), PartPose.offsetAndRotation(6F, 18F, 6F, 0F, -0.3490659F, -0.3490659F));
        root.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 46).addBox(5F, 0F, -1F, 2, 4, 2), PartPose.offsetAndRotation(6F, 18F, 6F, 0F, -0.3490659F, -0.3490659F));
        root.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(0, 46).addBox(3.5F, 5.5F, -0.5F, 2, 4, 1), PartPose.offsetAndRotation(6F, 18F, 6F, 0F, -0.3490659F, -0.6981317F));
        root.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(1, 0).addBox(2.5F, 9F, -0.5F, 1, 4, 1), PartPose.offsetAndRotation(6F, 18F, 6F, 0F, -0.3490659F, -0.8726646F));
        root.addOrReplaceChild("LML1", CubeListBuilder.create().texOffs(0, 46).addBox(-2F, -1F, -1.5F, 5, 3, 3), PartPose.offsetAndRotation(8F, 17F, 0F, 0F, 0F, -0.3490659F));
        root.addOrReplaceChild("LML2", CubeListBuilder.create().texOffs(0, 46).addBox(3F, 0F, -1F, 2, 4, 2), PartPose.offsetAndRotation(8F, 17F, 0F, 0F, 0F, -0.3490659F));
        root.addOrReplaceChild("LML3", CubeListBuilder.create().texOffs(0, 46).addBox(1.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.offsetAndRotation(8F, 17F, 0F, 0F, 0F, -0.6981317F));
        root.addOrReplaceChild("LML4", CubeListBuilder.create().texOffs(1, 0).addBox(0.5F, 8F, -0.5F, 1, 4, 1), PartPose.offsetAndRotation(8F, 17F, 0F, 0F, 0F, -0.8726646F));
        root.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 46).addBox(-2F, -1F, -1.5F, 5, 3, 3), PartPose.offsetAndRotation(8F, 17F, -6F, 0F, 0.3490659F, -0.3490659F));
        root.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 46).addBox(3F, 0F, -1F, 2, 4, 2), PartPose.offsetAndRotation(8F, 17F, -6F, 0F, 0.3490659F, -0.3490659F));
        root.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(0, 46).addBox(1.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.offsetAndRotation(8F, 17F, -6F, 0F, 0.3490659F, -0.6981317F));
        root.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(1, 0).addBox(0.5F, 8F, -0.5F, 1, 4, 1), PartPose.offsetAndRotation(8F, 17F, -6F, 0F, 0.3490659F, -0.8726646F));
        root.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(0, 46).addBox(-5F, -1F, -1.5F, 5, 3, 3), PartPose.offsetAndRotation(-6F, 18F, 6F, 0F, 0.3490659F, 0.3490659F));
        root.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 46).addBox(-7F, 0F, -1F, 2, 4, 2), PartPose.offsetAndRotation(-6F, 18F, 6F, 0F, 0.3490659F, 0.3490659F));
        root.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(0, 46).addBox(-5.5F, 5.5F, -0.5F, 2, 4, 1), PartPose.offsetAndRotation(-6F, 18F, 6F, 0F, 0.3490659F, 0.6981317F));
        root.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(1, 0).addBox(-3.5F, 9F, -0.5F, 1, 4, 1), PartPose.offsetAndRotation(-6F, 18F, 6F, 0F, 0.3490659F, 0.8726646F));
        root.addOrReplaceChild("RML1", CubeListBuilder.create().texOffs(0, 46).addBox(-3F, -1F, -1.5F, 5, 3, 3), PartPose.offsetAndRotation(-8F, 17F, 0F, 0F, 0F, 0.3490659F));
        root.addOrReplaceChild("RML2", CubeListBuilder.create().texOffs(0, 46).addBox(-5F, 0F, -1F, 2, 4, 2), PartPose.offsetAndRotation(-8F, 17F, 0F, 0F, 0F, 0.3490659F));
        root.addOrReplaceChild("RML3", CubeListBuilder.create().texOffs(0, 46).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.offsetAndRotation(-8F, 17F, 0F, 0F, 0F, 0.6981317F));
        root.addOrReplaceChild("RML4", CubeListBuilder.create().texOffs(1, 0).addBox(-1.5F, 8F, -0.5F, 1, 4, 1), PartPose.offsetAndRotation(-8F, 17F, 0F, 0F, 0F, 0.8726646F));
        root.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 46).addBox(-3F, -1F, -1.5F, 5, 3, 3), PartPose.offsetAndRotation(-8F, 17F, -6F, 0F, -0.3490659F, 0.3490659F));
        root.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 46).addBox(-5F, 0F, -1F, 2, 4, 2), PartPose.offsetAndRotation(-8F, 17F, -6F, 0F, -0.3490659F, 0.3490659F));
        root.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(0, 46).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.offsetAndRotation(-8F, 17F, -6F, 0F, -0.3490659F, 0.6981317F));
        root.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(1, 0).addBox(-1.5F, 8F, -0.5F, 1, 4, 1), PartPose.offsetAndRotation(-8F, 17F, -6F, 0F, -0.3490659F, 0.8726646F));
        root.addOrReplaceChild("Lid", CubeListBuilder.create().texOffs(0, 0).addBox(-7F, -5F, -14F, 14, 5, 14), PartPose.offsetAndRotation(0F, 12F, 7F, 0F, 0F, 0F));
        root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 19).addBox(-7F, -9F, -7F, 14, 10, 14), PartPose.offsetAndRotation(0F, 20F, 0F, 0F, 0F, 0F));
        root.addOrReplaceChild("Lock", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -2F, -15F, 2, 4, 1), PartPose.offsetAndRotation(0F, 12F, 7F, 0F, 0F, 0F));
        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(AnimatedChestRenderState state) {
        super.setupAnim(state);
        float closed = 1 - state.openness;
        lid.xRot = lock.xRot = -(1 - closed * closed * closed) * Mth.HALF_PI;
        for (int group = 0; group < 6; group++) {
            float phase = group == 0 || group == 2 || group == 4 ? Mth.PI : 0;
            float swing = Mth.cos(state.walkAnimationPos * 2 + phase) * 0.7F * state.walkAnimationSpeed;
            for (int part = 0; part < 4; part++) {
                float offset = part < 2 ? 0.25F : part == 2 ? 0.3F : 0.334F;
                legs[group][part].xRot = swing + (group % 3 == 0 ? offset : group % 3 == 2 ? -offset : 0);
            }
        }
    }
}
