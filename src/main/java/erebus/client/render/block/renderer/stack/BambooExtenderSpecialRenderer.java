package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.client.render.block.model.BambooExtenderModel;
import erebus.client.render.block.renderer.state.BambooExtenderBlockEntityRenderState;
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


public final class BambooExtenderSpecialRenderer implements NoDataSpecialModelRenderer {
    private final BambooExtenderModel model;
    private final Identifier texture;

    public BambooExtenderSpecialRenderer(BambooExtenderModel model, Identifier texture) {
        this.model = model;
        this.texture = texture;
    }

    private static void applyPose(PoseStack pose) {
        pose.translate(0.5D, 1.5D, 0.5D);
        pose.scale(-1, -1, 1);
        pose.mulPose(Axis.YN.rotationDegrees(90));
    }

    @Override
    public void submit(PoseStack pose, SubmitNodeCollector submit, int light, int overlay, boolean hasFoil, int outlineColor) {
        pose.pushPose();
        applyPose(pose);
        submit.submitModel(
                model,
                new BambooExtenderBlockEntityRenderState(),
                pose,
                RenderTypes.entitySolid(texture),
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
        model.setupAnim(new BambooExtenderBlockEntityRenderState());
        applyPose(poseStack);
        model.root().getExtentsForGui(poseStack, consumer);
    }

    public record Unbaked(Identifier texture) implements NoDataSpecialModelRenderer.Unbaked {

        public static final MapCodec<BambooExtenderSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
                i -> i.group(
                        Identifier.CODEC.fieldOf("texture").forGetter(BambooExtenderSpecialRenderer.Unbaked::texture)
                ).apply(i, BambooExtenderSpecialRenderer.Unbaked::new)
        );

        @Override
        public @Nullable SpecialModelRenderer<Void> bake(BakingContext bakingContext) {
            return new BambooExtenderSpecialRenderer(
                    new BambooExtenderModel(
                            bakingContext
                                    .entityModelSet()
                                    .bakeLayer(ModBlockEntityRendering.BAMBOO_EXTENDER)
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