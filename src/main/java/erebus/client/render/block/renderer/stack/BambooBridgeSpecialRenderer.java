package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.client.render.block.model.BambooBridgeModel;
import erebus.client.render.block.renderer.state.BambooBridgeBlockEntityRenderState;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public record BambooBridgeSpecialRenderer(BambooBridgeModel model, Identifier texture) implements NoDataSpecialModelRenderer {

    private static void applyPose(PoseStack pose) {
        pose.translate(0.5D, 1.5D, 0.5D);
        pose.scale(-1, -1, 1);
    }

    @Override
    public void submit(@NonNull PoseStack pose, SubmitNodeCollector submit, int light, int overlay, boolean hasFoil, int outlineColor) {
        pose.pushPose();
        applyPose(pose);
        submit.submitModel(
                model,
                new BambooBridgeBlockEntityRenderState(),
                pose,
                RenderTypes.entityCutout(texture),
                light,
                overlay,
                -1,
                null,
                outlineColor,
                null
        );
        pose.popPose();
    }

    @Override
    public void getExtents(@NonNull Consumer<Vector3fc> consumer) {
        model.setupAnim(new BambooBridgeBlockEntityRenderState());
        var pose = new PoseStack();
        applyPose(pose);
        model.root().getExtentsForGui(pose, consumer);
    }

    public record Unbaked(Identifier texture) implements NoDataSpecialModelRenderer.Unbaked {

        public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
                i -> i.group(
                        Identifier.CODEC.fieldOf("texture").forGetter(BambooBridgeSpecialRenderer.Unbaked::texture)
                ).apply(i, BambooBridgeSpecialRenderer.Unbaked::new)
        );

        @Override
        public @Nullable SpecialModelRenderer<Void> bake(BakingContext bakingContext) {
            return new BambooBridgeSpecialRenderer(new BambooBridgeModel(bakingContext.entityModelSet().bakeLayer(ModBlockEntityRendering.BAMBOO_BRIDGE)), texture);
        }

        @Override
        public @NonNull MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }
    }
}
