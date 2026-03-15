package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.client.render.block.model.BlockOfBonesModel;
import erebus.client.render.block.renderer.state.BlockOfBonesBlockEntityRenderState;
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

public final class BlockOfBonesSpecialRenderer implements NoDataSpecialModelRenderer {
    private final BlockOfBonesModel model;
    private final Identifier texture;

    public BlockOfBonesSpecialRenderer(BlockOfBonesModel model, Identifier texture) {
        this.model = model;
        this.texture = texture;
    }

    @Override
    public void submit(@NonNull ItemDisplayContext context, PoseStack pose, SubmitNodeCollector submit, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        pose.pushPose();
        pose.translate(0.5D, 1.2D, 0.5D);
        pose.scale(1, -1F, -1);
        pose.scale(0.5F, 0.5F, 0.5F);
        pose.rotateAround(Axis.YN.rotationDegrees(180), 0, 1, 0);
        submit.submitModel(
                model,
                new BlockOfBonesBlockEntityRenderState(),
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
        model.setupAnim(new BlockOfBonesBlockEntityRenderState());
        model.root().getExtentsForGui(poseStack, consumer);
    }

    public record Unbaked(Identifier texture) implements NoDataSpecialModelRenderer.Unbaked {

        public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
                i -> i.group(
                        Identifier.CODEC.fieldOf("texture").forGetter(BlockOfBonesSpecialRenderer.Unbaked::texture)
                ).apply(i, BlockOfBonesSpecialRenderer.Unbaked::new)
        );

        @Override
        public @NonNull SpecialModelRenderer<Void> bake(BakingContext bakingContext) {
            return new BlockOfBonesSpecialRenderer(
                    new BlockOfBonesModel(
                            bakingContext
                                    .entityModelSet()
                                    .bakeLayer(ModBlockEntityRendering.BLOCK_OF_BONES)
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