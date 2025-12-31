package erebus.client.render.item.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class MushroomHelmModel extends EntityModel<HumanoidRenderState> {

    private final ModelPart top;

    public MushroomHelmModel(ModelPart playerModel, ModelPart root) {
        super(playerModel);
        top = root.getChild("Top");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        PartDefinition top = part.addOrReplaceChild(
                "Top",
                CubeListBuilder.create()
                        .texOffs(28,  0)
                        .addBox(-4.5F, -12F, -4.5F, 9, 1, 9),
                PartPose.ZERO
        );

        top.addOrReplaceChild(
                "Middle",
                CubeListBuilder.create()
                        .texOffs(6, 21)
                        .addBox(-6.5F, -11F, -6.5F, 13, 2, 13),
                PartPose.ZERO
        );

        top.addOrReplaceChild(
                "Front",
                CubeListBuilder.create()
                        .texOffs(0, 37)
                        .addBox(-8.5F, -9F, -8.5F, 17, 2, 2),
                PartPose.ZERO
        );

        top.addOrReplaceChild(
                "Back",
                CubeListBuilder.create()
                        .texOffs(0, 42)
                        .addBox(-8.5F, -9F, 6.5F, 17, 2, 2),
                PartPose.ZERO
        );

        top.addOrReplaceChild(
                "Right",
                CubeListBuilder.create()
                        .texOffs(33, 48)
                        .addBox(-8.5F, -9F, -6.5F, 2, 2, 13),
                PartPose.ZERO
        );

        top.addOrReplaceChild(
                "Left",
                CubeListBuilder.create()
                        .texOffs(1, 48)
                        .addBox(6.5F, -9F, -6.5F, 2, 2, 13),
                PartPose.ZERO
        );

        top.addOrReplaceChild(
                "MainHead",
                CubeListBuilder.create()
                        .texOffs(0, 2)
                        .addBox(-4.5F, -9F, -4.5F, 9, 9, 9),
                PartPose.ZERO
        );

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(HumanoidRenderState state) {
    }
}
