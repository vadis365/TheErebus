package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.entity.projectile.TarantulaEgg;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ThrownItemRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class TarantulaEggRenderer extends EntityRenderer<TarantulaEgg, TarantulaEggRenderer.State> {
    private final ItemModelResolver itemModels;
    private ItemStack egg = ItemStack.EMPTY;

    public TarantulaEggRenderer(EntityRendererProvider.Context context) {
        super(context);
        itemModels = context.getItemModelResolver();
    }

    @Override
    public State createRenderState() {
        return new State();
    }

    @Override
    public void extractRenderState(TarantulaEgg entity, State state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        // Renderer construction also runs before item components are bound at the title screen.
        if (egg.isEmpty()) egg = new ItemStack(ModBlocks.TARANTULA_EGG.get());
        state.spin = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()) - entity.rotationTicks;
        itemModels.updateForNonLiving(state.item, egg, ItemDisplayContext.FIXED, entity);
    }

    @Override
    public void submit(State state, PoseStack pose, SubmitNodeCollector collector, CameraRenderState camera) {
        pose.pushPose();
        pose.translate(0, 0.5, 0);
        pose.mulPose(Axis.YP.rotationDegrees(state.spin));
        state.item.submit(pose, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);
        pose.popPose();
        super.submit(state, pose, collector, camera);
    }

    public static class State extends ThrownItemRenderState {
        public float spin;
    }
}
