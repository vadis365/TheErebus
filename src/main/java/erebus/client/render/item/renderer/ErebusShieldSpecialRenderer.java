

package erebus.client.render.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import erebus.client.render.item.model.ErebusShieldPartsModel;
import erebus.registries.client.ModItemRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class ErebusShieldSpecialRenderer implements NoDataSpecialModelRenderer {

    private final ErebusShieldPartsModel model;

    public ErebusShieldSpecialRenderer(ErebusShieldPartsModel model) {
        this.model = model;
    }

    @Override
    public void submit(@NonNull ItemDisplayContext context, PoseStack pose, SubmitNodeCollector submit, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        pose.pushPose();
        pose.scale(1, 1, 1);
        submit.submitModelPart(
                model.root(),
                pose,
                model.renderType(Erebus.prefix("shield_boss_and_handle")),
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
            return new ErebusShieldSpecialRenderer(new ErebusShieldPartsModel(context.entityModelSet().bakeLayer(ModItemRendering.EREBUS_SHIELD_PARTS)));
        }

        @Override
        public @NonNull MapCodec<? extends SpecialModelRenderer.Unbaked> type() {
            return MAP_CODEC;
        }
    }
}
