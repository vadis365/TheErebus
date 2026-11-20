package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.MagmaCrawlerRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

import java.util.List;

public class MagmaCrawlerModel extends EntityModel<MagmaCrawlerRenderState> {
    private final List<ModelPart> firstTripod;
    private final List<ModelPart> secondTripod;

    public MagmaCrawlerModel(ModelPart root) {
        super(root);
        ModelPart legs = root.getChild("legs");
        firstTripod = getLegSegments(legs.getChild("first_tripod"));
        secondTripod = getLegSegments(legs.getChild("second_tripod"));
    }

    private static List<ModelPart> getLegSegments(ModelPart tripod) {
        return tripod.getAllParts().stream().filter(part -> !part.isEmpty()).toList();
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("Thx", CubeListBuilder.create().texOffs(14, 13).addBox(-3.5F, -3.5F, 0.0F, 7, 7, 9), PartPose.offset(0.0F, 17.0F, -4.0F));
        root.addOrReplaceChild("ThxTop", CubeListBuilder.create().texOffs(21, 30).addBox(-2.5F, -4.5F, 1.0F, 5, 1, 7), PartPose.offset(0.0F, 17.0F, -4.0F));
        root.addOrReplaceChild("ThxS", CubeListBuilder.create().texOffs(15, 39).addBox(-4.5F, -2.5F, 1.0F, 9, 5, 7), PartPose.offset(0.0F, 17.0F, -4.0F));
        root.addOrReplaceChild("ThxBottom", CubeListBuilder.create().texOffs(21, 30).addBox(-2.5F, 0.0F, 1.0F, 5, 1, 7), PartPose.offset(0.0F, 20.0F, -4.0F));
        root.addOrReplaceChild("Eyeball", CubeListBuilder.create().texOffs(27, 52).addBox(-1.5F, -1.5F, 0.0F, 3, 3, 1), PartPose.offset(0.0F, 17.0F, -6.0F));
        root.addOrReplaceChild("ThxButt", CubeListBuilder.create().texOffs(14, 30).addBox(-2.5F, -2.5F, 0.0F, 5, 5, 1), PartPose.offset(0.0F, 17.0F, 5.0F));
        root.addOrReplaceChild("ThxFront", CubeListBuilder.create().texOffs(14, 30).addBox(-2.5F, -2.5F, 0.0F, 5, 5, 1), PartPose.offset(0.0F, 17.0F, -5.0F));

        // The original renderer shifted the legs forward by 0.2 blocks.
        PartDefinition legs = root.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0F, 0F, -3.2F));
        PartDefinition first = legs.addOrReplaceChild("first_tripod", CubeListBuilder.create(), PartPose.ZERO);
        addLeg(first, "LBL", 4F, 8F, 1F);
        addLeg(first, "LFL", 4F, 2F, -1F);
        addLeg(first, "RML", -4F, 5F, 0F);

        PartDefinition second = legs.addOrReplaceChild("second_tripod", CubeListBuilder.create(), PartPose.ZERO);
        addLeg(second, "RBL", -4F, 8F, 1F);
        addLeg(second, "RFL", -4F, 2F, -1F);
        addLeg(second, "LML", 4F, 5F, 0F);

        return LayerDefinition.create(mesh, 64, 128);
    }

    private static void addLeg(PartDefinition tripod, String name, float x, float z, float bend) {
        boolean left = x > 0F;
        boolean middle = bend == 0F;
        float side = Math.signum(x);
        float yawDirection = -side * bend;

        // The segments share a pivot and each has its own rest rotation.
        PartPose upperPose = PartPose.offsetAndRotation(x, 17F, z, 0.25F * bend, 0.7F * yawDirection,
                (middle ? -0.3490659F : -0.5F) * side);
        tripod.addOrReplaceChild(name + "A", CubeListBuilder.create().texOffs(0, 95).addBox(left ? 0F : -4F, -1F, -1F, 4, 2, 2), upperPose);
        tripod.addOrReplaceChild(name + "B", CubeListBuilder.create().texOffs(0, 88).addBox(left ? 3F : -5F, 0F, -1F, 2, 4, 2), upperPose);
        tripod.addOrReplaceChild(name + "C", CubeListBuilder.create().texOffs(0, 82).addBox(left ? 1.5F : -3.5F, 4.5F, -0.5F, 2, 4, 1),
                PartPose.offsetAndRotation(x, 17F, z, 0.5F * bend, 0.6F * yawDirection, (middle ? -0.6981317F : -0.95F) * side));
        tripod.addOrReplaceChild(name + "D", CubeListBuilder.create().texOffs(0, 76).addBox(left ? 0.5F : -1.5F, 8F, -0.5F, 1, 4, 1),
                PartPose.offsetAndRotation(x, 17F, z, 0.64F * bend, 0.7F * yawDirection, (middle ? -0.8726646F : -1.27F) * side));
    }

    @Override
    public void setupAnim(MagmaCrawlerRenderState state) {
        super.setupAnim(state);
        float stride = state.walkAnimationPos * 1.5F;
        float first = Mth.cos(stride + (float) Math.PI) * 0.7F * state.walkAnimationSpeed;
        float second = Mth.cos(stride) * 0.7F * state.walkAnimationSpeed;
        for (ModelPart segment : firstTripod) {
            segment.xRot += first;
        }
        for (ModelPart segment : secondTripod) {
            segment.xRot += second;
        }
    }
}
