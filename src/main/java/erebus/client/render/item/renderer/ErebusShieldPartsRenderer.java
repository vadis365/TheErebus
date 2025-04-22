

package erebus.client.render.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.item.model.ErebusShieldPartsModel;
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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class ErebusShieldPartsRenderer extends BlockEntityWithoutLevelRenderer {

    private final ResourceLocation TEXTURE = Erebus.prefix("textures/items/shield_boss_and_handle.png");
    private final ErebusShieldPartsModel shieldPartsModel;

    public ErebusShieldPartsRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
        super(blockEntityRenderDispatcher, entityModelSet);
        EntityModelSet EntityModelSetThatIsntNULL = Minecraft.getInstance().getEntityModels();
        shieldPartsModel = new ErebusShieldPartsModel(EntityModelSetThatIsntNULL.bakeLayer(ModItemRendering.EREBUS_SHIELD_PARTS));
    }

    @Override
    public void renderByItem(ItemStack stack, @Nonnull ItemDisplayContext transformType, PoseStack matrixStack, MultiBufferSource bufferIn, int combinedLight, int combinedOverlayIn) {
        matrixStack.pushPose();
        matrixStack.scale(1, 1, 1);
        shieldPartsModel.renderToBuffer(matrixStack, bufferIn.getBuffer(RenderType.entitySmoothCutout(TEXTURE)), combinedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
        matrixStack.popPose();
    }

}
