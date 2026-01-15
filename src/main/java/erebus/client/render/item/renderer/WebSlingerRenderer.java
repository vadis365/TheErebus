


package erebus.client.render.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.item.model.WebSlingerModel;
import erebus.registries.client.ModItemRendering;
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

import javax.annotation.Nonnull;

public class WebSlingerRenderer extends BlockEntityWithoutLevelRenderer {

    private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/items/web_slinger.png");
    private final ResourceLocation TEXTURE_WITHER = Erebus.prefix("textures/special/items/web_slinger_wither.png");
    private final WebSlingerModel webSlingerModel;
    private final boolean isWither;

    public WebSlingerRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet, boolean isWither) {
        super(blockEntityRenderDispatcher, entityModelSet);
        EntityModelSet EntityModelSetThatIsntNULL = Minecraft.getInstance().getEntityModels();
        webSlingerModel = new WebSlingerModel(EntityModelSetThatIsntNULL.bakeLayer(ModItemRendering.WEB_SLINGER));
        this.isWither = isWither;
    }

    @Override
    public void renderByItem(ItemStack stack, @Nonnull ItemDisplayContext transformType, PoseStack matrixStack, MultiBufferSource bufferIn, int combinedLight, int combinedOverlayIn) {
        matrixStack.pushPose();
        matrixStack.scale(1.5F, 1.5F, 1.5F);
        matrixStack.rotateAround(Axis.ZP.rotationDegrees(180), 0, 0, 1);
        matrixStack.rotateAround(Axis.YN.rotationDegrees(45), 0, 1, 0);
        matrixStack.rotateAround(Axis.XP.rotationDegrees(80), 1, 0, 0);
        matrixStack.translate(0, 0, -0.25F);
        webSlingerModel.renderToBuffer(matrixStack, bufferIn.getBuffer(RenderType.entitySmoothCutout(isWither ? TEXTURE_WITHER : TEXTURE)), combinedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
        matrixStack.popPose();
    }

}
