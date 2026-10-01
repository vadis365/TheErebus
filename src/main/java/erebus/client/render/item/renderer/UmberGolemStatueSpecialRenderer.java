package erebus.client.render.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import erebus.client.render.block.model.UmberGolemStatueModel;
import erebus.client.render.block.renderer.UmberGolemStatueRenderer;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class UmberGolemStatueSpecialRenderer implements NoDataSpecialModelRenderer {
    private final UmberGolemStatueModel model;

    public UmberGolemStatueSpecialRenderer(UmberGolemStatueModel model) {
        this.model = model;
    }

    private static void transform(PoseStack pose) {
        pose.translate(0.5, 1.5, 0.5);
        pose.scale(-1, -1, 1);
    }

    @Override
    public void submit(PoseStack pose, SubmitNodeCollector collector, int light, int overlay, boolean foil, int outline) {
        pose.pushPose();
        transform(pose);
        collector.submitModelPart(model.root(), pose, RenderTypes.entityCutout(UmberGolemStatueRenderer.TEXTURE), light, overlay, null, false, foil, -1, null, outline);
        pose.popPose();
    }

    @Override
    public void getExtents(@NonNull Consumer<Vector3fc> consumer) {
        var pose = new PoseStack();
        transform(pose);
        model.root().getExtentsForGui(pose, consumer);
    }

    public record Unbaked() implements NoDataSpecialModelRenderer.Unbaked {
        public static final MapCodec<Unbaked> MAP_CODEC = MapCodec.unit(new Unbaked());

        @Override
        public SpecialModelRenderer<Void> bake(BakingContext context) {
            return new UmberGolemStatueSpecialRenderer(new UmberGolemStatueModel(context.entityModelSet().bakeLayer(ModBlockEntityRendering.UMBER_GOLEM_STATUE)));
        }

        @Override
        public MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }
    }
}
