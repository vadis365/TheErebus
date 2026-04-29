package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.LeechRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class LeechModel extends EntityModel<LeechRenderState> {

    private final ModelPart s1;
    private final ModelPart s2;
    private final ModelPart s3;
    private final ModelPart s4;
    private final ModelPart s5;
    private final ModelPart s6;

    public LeechModel(ModelPart root) {
        super(root);
        this.s1 = root.getChild("S1");
        this.s2 = root.getChild("S2");
        this.s3 = root.getChild("S3");
        this.s4 = root.getChild("S4");
        this.s5 = root.getChild("S5");
        this.s6 = root.getChild("S6");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("S1",
                CubeListBuilder.create().texOffs(0, 11)
                        .addBox(0F, 0F, 0F, 2, 2, 1),
                PartPose.offset(-1F, 22F, -7F)
        );

        root.addOrReplaceChild("S2",
                CubeListBuilder.create().texOffs(0, 0)
                        .addBox(0F, 0F, 0F, 3, 3, 2),
                PartPose.offset(-1.5F, 21F, -6F)
        );

        root.addOrReplaceChild("S3",
                CubeListBuilder.create().texOffs(0, 0)
                        .addBox(0F, 0F, 0F, 4, 4, 7),
                PartPose.offset(-2F, 20F, -4F)
        );

        root.addOrReplaceChild("S4",
                CubeListBuilder.create().texOffs(0, 0)
                        .addBox(0F, 0F, 0F, 3, 3, 2),
                PartPose.offset(-1.5F, 21F, 3F)
        );

        root.addOrReplaceChild("S5",
                CubeListBuilder.create().texOffs(0, 0)
                        .addBox(0F, 0F, 0F, 2, 2, 2),
                PartPose.offset(-1F, 22F, 5F)
        );

        root.addOrReplaceChild("S6",
                CubeListBuilder.create().texOffs(6, 11)
                        .addBox(0F, 0F, 0F, 1, 1, 2),
                PartPose.offset(-0.5F, 23F, 7F)
        );

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(LeechRenderState state) {
        if (state.isFeeding) {
            float moveProgress = Mth.cos(state.walkAnimationPos * 0.6662F) * 1.4F * state.walkAnimationSpeed;
            s1.xScale = moveProgress * moveProgress / 2 + 0.5F;
            s1.yScale = moveProgress * moveProgress / 2 + 0.5F;
        } else {
            float ba = Mth.cos(state.walkAnimationPos) * 1.5F * state.walkAnimationSpeed;
            float bb = Mth.cos(state.walkAnimationPos + 1.0F) * 1.5F * state.walkAnimationSpeed;
            float bc = Mth.cos(state.walkAnimationPos + 2.0F) * 1.5F * state.walkAnimationSpeed;
            float bd = Mth.cos(state.walkAnimationPos + 3.0F) * 1.5F * state.walkAnimationSpeed;
            float be = Mth.cos(state.walkAnimationPos + 4.0F) * 1.5F * state.walkAnimationSpeed;
            float bf = Mth.cos(state.walkAnimationPos + 5.0F) * 1.5F * state.walkAnimationSpeed;

            s1.y = ba + 22F;
            s2.y = bb + 21F;
            s3.y = bc + 20F;
            s4.y = bd + 21F;
            s5.y = be + 22F;
            s6.y = bf + 23F;
        }
    }
}
