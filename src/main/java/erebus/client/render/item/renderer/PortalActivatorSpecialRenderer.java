

package erebus.client.render.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import erebus.client.render.item.model.PortalActivatorModel;
import erebus.registries.client.ModItemRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class PortalActivatorSpecialRenderer implements NoDataSpecialModelRenderer {

    private final PortalActivatorModel model;

    public PortalActivatorSpecialRenderer(PortalActivatorModel model) {
        this.model = model;
    }

    @Override
    public void submit(@NonNull ItemDisplayContext context, PoseStack pose, SubmitNodeCollector submit, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        pose.pushPose();
        pose.scale(1, 1, 1);
        submit.submitModelPart(
                model.root(),
                pose,
                model.renderType(Erebus.prefix("textures/special/items/portal_activator.png")),
                lightCoords,
                overlayCoords,
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

    public record Unbaked() implements SpecialModelRenderer.Unbaked {

        public static final MapCodec<Unbaked> MAP_CODEC = MapCodec.unit(new Unbaked());

        @Override
        public @NonNull SpecialModelRenderer<?> bake(BakingContext context) {
            return new PortalActivatorSpecialRenderer(new PortalActivatorModel(context.entityModelSet().bakeLayer(ModItemRendering.PORTAL_ACTIVATOR)));
        }

        @Override
        public @NonNull MapCodec<? extends SpecialModelRenderer.Unbaked> type() {
            return MAP_CODEC;
        }
    }

}
