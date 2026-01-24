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
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public record BambooBridgeItemRenderer(BambooBridgeModel model, Identifier texture) implements NoDataSpecialModelRenderer {

    @Override
    public void submit(@NonNull ItemDisplayContext itemDisplayContext, @NonNull PoseStack pose, SubmitNodeCollector submit, int packedLight, int packedOverlay, boolean hasFoil, int outlineColor) {
        submit.submitModel(
                model,
                new BambooBridgeBlockEntityRenderState(),
                pose,
                RenderTypes.entityCutoutNoCull(texture),
                packedLight,
                packedOverlay,
                outlineColor,
                null
        );
    }

    @Override
    public void getExtents(@NonNull Consumer<Vector3fc> consumer) {
        model.root().getExtentsForGui(new PoseStack(), consumer);
    }

    public record Unbaked(Identifier texture) implements SpecialModelRenderer.Unbaked {

        public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
                i -> i.group(
                        Identifier.CODEC.fieldOf("texture").forGetter(BambooBridgeItemRenderer.Unbaked::texture)
                ).apply(i, BambooBridgeItemRenderer.Unbaked::new)
        );

        @Override
        public @Nullable SpecialModelRenderer<?> bake(BakingContext bakingContext) {
            return new BambooBridgeItemRenderer(new BambooBridgeModel(bakingContext.entityModelSet().bakeLayer(ModBlockEntityRendering.BAMBOO_BRIDGE)), texture);
        }

        @Override
        public @NonNull MapCodec<? extends SpecialModelRenderer.Unbaked> type() {
            return MAP_CODEC;
        }
    }
}
