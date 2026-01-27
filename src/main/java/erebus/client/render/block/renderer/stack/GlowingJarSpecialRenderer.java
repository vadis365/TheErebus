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
import net.minecraft.world.item.ItemDisplayContext;
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

    @Override
    public void submit(@NonNull ItemDisplayContext context, PoseStack pose, SubmitNodeCollector submit, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        pose.pushPose();
        pose.translate(0.5F, 0.75F, 0.5F);
        pose.scale(0.7125F, -1.069F, -0.7125F);
        submit.submitModel(
                model,
                new GlowingJarBlockEntityRenderState(),
                pose,
                RenderTypes.entitySolid(texture),
                lightCoords,
                overlayCoords,
                outlineColor,
                null
        );
        pose.popPose();
    }

    @Override
    public void getExtents(@NonNull Consumer<Vector3fc> consumer) {
        PoseStack poseStack = new PoseStack();
        model.setupAnim(new GlowingJarBlockEntityRenderState());
        model.root().getExtentsForGui(poseStack, consumer);
    }

    public record Unbaked(Identifier texture) implements SpecialModelRenderer.Unbaked {

        public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
                i -> i.group(
                        Identifier.CODEC.fieldOf("texture").forGetter(GlowingJarSpecialRenderer.Unbaked::texture)
                ).apply(i, GlowingJarSpecialRenderer.Unbaked::new)
        );

        @Override
        public @Nullable SpecialModelRenderer<?> bake(BakingContext bakingContext) {
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
        public @NonNull MapCodec<? extends SpecialModelRenderer.Unbaked> type() {
            return MAP_CODEC;
        }
    }
}
