package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.client.render.block.model.LiquifierModel;
import erebus.client.render.block.renderer.state.LiquifierBlockEntityRenderState;
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

public final class LiquifierSpecialRenderer implements NoDataSpecialModelRenderer {
    private final LiquifierModel model;
    private final Identifier texture;

    public LiquifierSpecialRenderer(LiquifierModel model, Identifier texture) {
        this.model = model;
        this.texture = texture;
    }

    private static void applyPose(PoseStack pose) {
        pose.translate(0.5F, 1.5F, 0.5F);
        pose.scale(-1, -1, 1);
    }

    @Override
    public void submit(PoseStack pose, SubmitNodeCollector submit, int light, int overlay, boolean hasFoil, int outlineColor) {
        pose.pushPose();
        applyPose(pose);
        submit.submitModel(
                model,
                new LiquifierBlockEntityRenderState(),
                pose,
                RenderTypes.entityTranslucent(texture),
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
        PoseStack poseStack = new PoseStack();
        model.setupAnim(new LiquifierBlockEntityRenderState());
        applyPose(poseStack);
        model.root().getExtentsForGui(poseStack, consumer);
    }

    public record Unbaked(Identifier texture) implements NoDataSpecialModelRenderer.Unbaked {

        public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
                i -> i.group(
                        Identifier.CODEC.fieldOf("texture").forGetter(LiquifierSpecialRenderer.Unbaked::texture)
                ).apply(i, LiquifierSpecialRenderer.Unbaked::new)
        );

        @Override
        public @Nullable SpecialModelRenderer<Void> bake(BakingContext bakingContext) {
            return new LiquifierSpecialRenderer(
                    new LiquifierModel(
                            bakingContext
                                    .entityModelSet()
                                    .bakeLayer(ModBlockEntityRendering.LIQUIFIER)
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
