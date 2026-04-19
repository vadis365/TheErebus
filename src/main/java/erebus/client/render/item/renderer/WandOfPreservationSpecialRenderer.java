package erebus.client.render.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import erebus.client.render.item.model.WandOfPreservationModel;
import erebus.registries.client.ModItemRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class WandOfPreservationSpecialRenderer implements NoDataSpecialModelRenderer {

    private final WandOfPreservationModel model;

    public WandOfPreservationSpecialRenderer(WandOfPreservationModel model) {
        this.model = model;
    }

    @Override
    public void submit(PoseStack pose, SubmitNodeCollector submit, int light, int overlay, boolean hasFoil, int outlineColor) {
        pose.pushPose();
        pose.scale(1, 1, 1);
        submit.submitModelPart(
                model.root(),
                pose,
                model.renderType(Erebus.prefix("textures/special/items/wand_of_preservation.png")),
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
    }

    @Override
    public void getExtents(@NonNull Consumer<Vector3fc> consumer) {
        PoseStack pose = new PoseStack();
        pose.scale(1, 1, 1);
        model.root().getExtentsForGui(pose, consumer);
    }

    public record Unbaked() implements NoDataSpecialModelRenderer.Unbaked {

        public static final MapCodec<Unbaked> MAP_CODEC = MapCodec.unit(new Unbaked());

        @Override
        public @NonNull SpecialModelRenderer<Void> bake(BakingContext context) {
            return new WandOfPreservationSpecialRenderer(new WandOfPreservationModel(context.entityModelSet().bakeLayer(ModItemRendering.WAND_OF_PRESERVATION)));
        }

        @Override
        public @NonNull MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }
    }

}
