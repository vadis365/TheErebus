

package erebus.client.render.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.item.model.QuakeHammerModel;
import erebus.registries.client.ModItemRendering;
import erebus.registries.data.ModDataComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class QuakeHammerRenderer extends BlockEntityWithoutLevelRenderer {

    private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/items/quake_hammer.png");
    private final QuakeHammerModel quakeHammerModel;

    public QuakeHammerRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
        super(blockEntityRenderDispatcher, entityModelSet);
        EntityModelSet EntityModelSetThatIsntNULL = Minecraft.getInstance().getEntityModels();
        quakeHammerModel = new QuakeHammerModel(EntityModelSetThatIsntNULL.bakeLayer(ModItemRendering.QUAKE_HAMMER));
    }

    @Override
    public void renderByItem(ItemStack stack, @Nonnull ItemDisplayContext transformType, PoseStack pose, MultiBufferSource bufferIn, int combinedLight, int combinedOverlayIn) {
        pose.pushPose();
        pose.rotateAround(Axis.YN.rotationDegrees(90), 0, 1, 0);
        float scale = 1.75F + (!stack.has(ModDataComponents.QUAKE_HAMMER) ? 0 : stack.get(ModDataComponents.QUAKE_HAMMER).charge()) * 0.03F;
		pose.translate(0F, 0.25F - scale, 0F);
		pose.scale(scale, scale, scale);
        quakeHammerModel.renderToBuffer(pose, bufferIn.getBuffer(RenderType.entitySmoothCutout(TEXTURE)), combinedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
        pose.popPose();
    }

}
