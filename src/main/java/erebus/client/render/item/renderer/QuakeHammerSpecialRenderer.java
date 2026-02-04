

package erebus.client.render.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import erebus.client.render.item.model.QuakeHammerModel;
import erebus.registries.client.ModItemRendering;
import erebus.registries.data.ModDataComponents;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class QuakeHammerSpecialRenderer implements SpecialModelRenderer<QuakeHammerSpecialRenderer.RenderData> {

    private final QuakeHammerModel model;

    public QuakeHammerSpecialRenderer(QuakeHammerModel model) {
        this.model = model;
    }

    @Override
    public void submit(RenderData data, @NonNull ItemDisplayContext context, PoseStack pose, SubmitNodeCollector submit, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        pose.pushPose();
        pose.rotateAround(Axis.YN.rotationDegrees(90), 0, 1, 0);
        float scale = 1.75F + data.charge * 0.03F;
        pose.translate(0F, 0.25F - scale, 0F);
        pose.scale(scale, scale, scale);
        pose.scale(1, 1, 1);
        submit.submitModelPart(
                model.root(),
                pose,
                model.renderType(Erebus.prefix("textures/special/items/quake_hammer.png")),
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

    @Override
    public @Nullable RenderData extractArgument(ItemStack stack) {
        float charge = (!stack.has(ModDataComponents.QUAKE_HAMMER) ? 0 : stack.get(ModDataComponents.QUAKE_HAMMER).charge());
        return new RenderData(charge);
    }

    public record RenderData(float charge) {
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked {

        public static final MapCodec<Unbaked> MAP_CODEC = MapCodec.unit(new Unbaked());

        @Override
        public @NonNull SpecialModelRenderer<?> bake(BakingContext context) {
            return new QuakeHammerSpecialRenderer(new QuakeHammerModel(context.entityModelSet().bakeLayer(ModItemRendering.QUAKE_HAMMER)));
        }

        @Override
        public @NonNull MapCodec<? extends SpecialModelRenderer.Unbaked> type() {
            return MAP_CODEC;
        }
    }

}
