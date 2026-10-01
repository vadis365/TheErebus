package erebus.client.render.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.Erebus;
import erebus.client.render.item.model.ErebusShieldPartsModel;
import erebus.registries.client.ModItemRendering;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class ErebusShieldSpecialRenderer implements NoDataSpecialModelRenderer {

    private final ErebusShieldPartsModel model;
    private final ModelPart face;
    private final Identifier faceTexture;

    public ErebusShieldSpecialRenderer(ErebusShieldPartsModel model, Identifier faceTexture) {
        this.model = model;
        this.faceTexture = faceTexture;
        var mesh = new MeshDefinition();
        mesh.getRoot().addOrReplaceChild("face", CubeListBuilder.create().texOffs(0, 0).addBox(-8, -8, 0, 16, 16, 0), PartPose.ZERO);
        face = LayerDefinition.create(mesh, 16, 16).bakeRoot();
    }

    @Override
    public void submit(PoseStack pose, SubmitNodeCollector submit, int light, int overlay, boolean hasFoil, int outlineColor) {
        pose.pushPose();
        pose.scale(1, -1, -1);
        submit.submitModelPart(
                model.root(),
                pose,
                model.renderType(Erebus.prefix("textures/item/shield_boss_and_handle.png")),
                light,
                overlay,
                null,
                false,
                hasFoil,
                -1,
                null,
                outlineColor
        );
        pose.popPose();
        pose.pushPose();
        pose.scale(1.25F, -1.25F, 1.25F);
        pose.translate(0, 0, 0.08125);
        submit.submitModelPart(face, pose, RenderTypes.entityCutoutCull(faceTexture), light, overlay,
                null, false, hasFoil, -1, null, outlineColor);
        pose.popPose();
    }

    @Override
    public void getExtents(@NonNull Consumer<Vector3fc> consumer) {
        PoseStack pose = new PoseStack();
        pose.scale(1, -1, -1);
        model.root().getExtentsForGui(pose, consumer);
        pose = new PoseStack();
        pose.scale(1.25F, -1.25F, 1.25F);
        pose.translate(0, 0, 0.08125);
        face.getExtentsForGui(pose, consumer);
    }

    public record Unbaked(Identifier faceTexture) implements NoDataSpecialModelRenderer.Unbaked {

        public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Identifier.CODEC.fieldOf("face_texture").forGetter(Unbaked::faceTexture)).apply(instance, Unbaked::new));

        @Override
        public @NonNull SpecialModelRenderer<Void> bake(BakingContext context) {
            return new ErebusShieldSpecialRenderer(new ErebusShieldPartsModel(context.entityModelSet().bakeLayer(ModItemRendering.EREBUS_SHIELD_PARTS)), faceTexture);
        }

        @Override
        public @NonNull MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }
    }
}
