


package erebus.client.render.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.Erebus;
import erebus.client.render.item.model.WebSlingerModel;
import erebus.registries.client.ModItemRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class WebSlingerSpecialRenderer implements NoDataSpecialModelRenderer {

    private final WebSlingerModel model;
    private final boolean isWither;

    public WebSlingerSpecialRenderer(WebSlingerModel model, boolean isWither) {
        this.model = model;
        this.isWither = isWither;
    }

    @Override
    public void submit(@NonNull ItemDisplayContext context, PoseStack pose, SubmitNodeCollector submit, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        pose.pushPose();
        pose.scale(1.5F, 1.5F, 1.5F);
        pose.rotateAround(Axis.ZP.rotationDegrees(180), 0, 0, 1);
        pose.rotateAround(Axis.YN.rotationDegrees(45), 0, 1, 0);
        pose.rotateAround(Axis.XP.rotationDegrees(80), 1, 0, 0);
        pose.translate(0, 0, -0.25F);
        submit.submitModelPart(
                model.root(),
                pose,
                model.renderType(Erebus.prefix(isWither ? "textures/special/items/web_slinger_wither.png" : "textures/special/items/web_slinger.png")),
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
        pose.scale(1.5F, 1.5F, 1.5F);
        pose.rotateAround(Axis.ZP.rotationDegrees(180), 0, 0, 1);
        pose.rotateAround(Axis.YN.rotationDegrees(45), 0, 1, 0);
        pose.rotateAround(Axis.XP.rotationDegrees(80), 1, 0, 0);
        pose.translate(0, 0, -0.25F);
        model.root().getExtentsForGui(pose, consumer);
    }

    public record Unbaked(boolean isWither) implements NoDataSpecialModelRenderer.Unbaked {

        public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
                (i) -> i.group(
                        Codec.BOOL.optionalFieldOf("wither", false).forGetter(Unbaked::isWither)
                ).apply(i, Unbaked::new));

        @Override
        public @NonNull SpecialModelRenderer<Void> bake(BakingContext context) {
            return new WebSlingerSpecialRenderer(new WebSlingerModel(context.entityModelSet().bakeLayer(ModItemRendering.WEB_SLINGER)), isWither);
        }

        @Override
        public @NonNull MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }
    }

}
