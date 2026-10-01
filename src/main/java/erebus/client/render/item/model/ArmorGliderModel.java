package erebus.client.render.item.model;

import erebus.network.data.GliderData;
import erebus.registries.data.ModDataComponents;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.util.Mth;
import org.jspecify.annotations.Nullable;

public final class ArmorGliderModel extends EntityModel<HumanoidRenderState> {
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart back;
    private final ModelPart mounts;
    private final boolean wingsOnly;
    private final @Nullable HumanoidModel<HumanoidRenderState> original;

    public ArmorGliderModel(ModelPart root, boolean wingsOnly, @Nullable HumanoidModel<HumanoidRenderState> original) {
        super(root);
        this.wingsOnly = wingsOnly;
        this.original = original;
        body = root.getChild("Body");
        rightArm = root.getChild("RightArm");
        leftArm = root.getChild("LeftArm");
        back = root.getChild("Back");
        mounts = root.getChild("Mounts");
    }

    public static LayerDefinition createBodyLayer() {
        return create(false);
    }

    public static LayerDefinition createPoweredBodyLayer() {
        return create(true);
    }

    private static LayerDefinition create(boolean powered) {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();
        var body = root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(19, 12).addBox(-4, 0, -2, 8, 11, 4), PartPose.ZERO);
        root.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(42, 0).addBox(-3, -2, -2, 4, 5, 4), PartPose.offset(-5, 2, 0));
        root.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 0).addBox(-1, -2, -2, 4, 5, 4), PartPose.offset(5, 2, 0));
        if (powered) body.addOrReplaceChild("Engine", CubeListBuilder.create().texOffs(24, 3).addBox(-2, 2, -4, 4, 4, 2), PartPose.ZERO);
        var back = root.addOrReplaceChild("Back", CubeListBuilder.create(), PartPose.ZERO);
        var mounts = root.addOrReplaceChild("Mounts", CubeListBuilder.create(), PartPose.ZERO);
        mounts.addOrReplaceChild("RightBase", CubeListBuilder.create().texOffs(52, 16).addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3), PartPose.offset(-2, 2, 3.5F));
        mounts.addOrReplaceChild("LeftBase", CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3), PartPose.offset(2, 2, 3.5F));
        if (powered) {
            wing(back, "RightTop", 54, 49, -4.5F, 4, 14, -2);
            wing(back, "RightMiddle", 45, 49, -0.5F, 3, 10, -2);
            wing(back, "RightBottom", 38, 49, 2.5F, 2, 7, -2);
            wing(back, "LeftTop", 0, 49, 0.5F, 4, 14, 2);
            wing(back, "LeftMiddle", 11, 49, -2.5F, 3, 10, 2);
            wing(back, "LeftBottom", 20, 49, -4.5F, 2, 7, 2);
        } else {
            wing(back, "RightWing", 44, 33, -4.5F, 9, 14, -2);
            wing(back, "LeftWing", 0, 33, -4.5F, 9, 14, 2);
        }
        return LayerDefinition.create(mesh, 64, 64);
    }

    private static void wing(PartDefinition parent, String name, int u, int v, float x, float width, float height, float pivotX) {
        parent.addOrReplaceChild(name, CubeListBuilder.create().texOffs(u, v).addBox(x, 0, -0.5F, width, height, 1), PartPose.offset(pivotX, 3, 3.5F));
    }

    private static void pose(ModelPart source, ModelPart target) {
        target.loadPose(source.storePose());
        target.xScale = source.xScale;
        target.yScale = source.yScale;
        target.zScale = source.zScale;
    }

    @Override
    public void setupAnim(HumanoidRenderState state) {
        super.setupAnim(state);
        body.visible = rightArm.visible = leftArm.visible = mounts.visible = !wingsOnly;
        back.visible = wingsOnly;
        if (original != null) {
            original.setupAnim(state);
            pose(original.root(), root());
            pose(original.body, body);
            pose(original.body, mounts);
            pose(original.rightArm, rightArm);
            pose(original.leftArm, leftArm);
        }
        body.xScale *= 1.1F;
        body.yScale *= 1.2F;
        body.zScale *= 1.3F;
        body.y -= 0.8F;
        rightArm.xScale *= 1.5F;
        leftArm.xScale *= 1.5F;
        rightArm.yScale *= 1.2F;
        leftArm.yScale *= 1.2F;
        rightArm.zScale *= 1.3F;
        leftArm.zScale *= 1.3F;
        rightArm.y -= 0.4F;
        leftArm.y -= 0.4F;
        if (wingsOnly) {
            if (state.isBaby) {
                root().xScale = root().yScale = root().zScale = 0.5F;
                root().y = 12;
            }
            root().z -= 2; // Counter the native WingsLayer's two-pixel back offset.
            back.xRot = state.isCrouching ? 0.5F : 0;
            back.y = state.isCrouching ? 3.2F : 0;
            var data = state.chestEquipment.getOrDefault(ModDataComponents.GLIDER, GliderData.EMPTY);
            for (String side : new String[]{"Right", "Left"}) {
                String[] parts = back.hasChild(side + "Wing") ? new String[]{side + "Wing"}
                        : new String[]{side + "Top", side + "Middle", side + "Bottom"};
                for (String name : parts) {
                    var wing = back.getChild(name);
                    wing.zRot = data.active() ? (side.equals("Right") ? Mth.HALF_PI : -Mth.HALF_PI) : 0;
                    wing.xRot = data.powered() ? 0.3F + Mth.cos(state.ageInTicks) * 4.8F * state.walkAnimationSpeed
                            : data.active() ? 0 : state.walkAnimationSpeed > 0.01F ? 0.7F : 0;
                }
            }
        }
    }
}
