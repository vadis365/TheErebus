package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.client.render.block.model.GlowingJarModel;
import erebus.client.render.block.renderer.state.GlowingJarBlockEntityRenderState;
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

public final class GlowingJarSpecialRenderer implements NoDataSpecialModelRenderer {
    private final GlowingJarModel model;
    private final Identifier texture;

    public GlowingJarSpecialRenderer(GlowingJarModel model, Identifier texture) {
        this.model = model;
        this.texture = texture;
    }

    private static void applyPose(PoseStack pose) {
        pose.translate(0.5F, 0.75F, 0.5F);
        pose.scale(0.7125F, -0.9999F, -0.7125F);
    }

    @Override
    public void submit(PoseStack pose, SubmitNodeCollector submit, int light, int overlay, boolean hasFoil, int outlineColor) {
        erebus.client.render.block.renderer.GlowingJarRenderer.submitWisp(pose, submit, 1.4F, 180F);
        pose.pushPose();
        applyPose(pose);
        model.submit(pose, submit, RenderTypes.entityTranslucent(texture), light, overlay, null, outlineColor, null);
        pose.popPose();
    }

    @Override
    public void getExtents(@NonNull Consumer<Vector3fc> consumer) {
        PoseStack poseStack = new PoseStack();
        model.setupAnim(new GlowingJarBlockEntityRenderState());
        applyPose(poseStack);
        model.root().getExtentsForGui(poseStack, consumer);
    }

    public record Unbaked(Identifier texture) implements NoDataSpecialModelRenderer.Unbaked {

        public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
                i -> i.group(
                        Identifier.CODEC.fieldOf("texture").forGetter(GlowingJarSpecialRenderer.Unbaked::texture)
                ).apply(i, GlowingJarSpecialRenderer.Unbaked::new)
        );

        @Override
        public @Nullable SpecialModelRenderer<Void> bake(BakingContext bakingContext) {
            return new GlowingJarSpecialRenderer(
                    new GlowingJarModel(
                            bakingContext
                                    .entityModelSet()
                                    .bakeLayer(ModBlockEntityRendering.GLOWING_JAR)
                    ),
                    texture
            );
        }

        @Override
        public @NonNull MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }
    }
}
