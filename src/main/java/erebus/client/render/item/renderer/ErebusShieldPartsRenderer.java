

package erebus.client.render.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.Erebus;
import erebus.client.render.item.model.ErebusShieldPartsModel;
import erebus.item.shield.ErebusShieldItem;
import erebus.registries.client.ModItemRendering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class ErebusShieldPartsRenderer extends BlockEntityWithoutLevelRenderer {

    private final ResourceLocation TEXTURE = Erebus.prefix("textures/item/shield_boss_and_handle.png");
    private final ErebusShieldPartsModel model;

    @SuppressWarnings("DataFlowIssue")
    public ErebusShieldPartsRenderer(BlockEntityRenderDispatcher renderer, EntityModelSet modelSet) {
        super(renderer, modelSet);
        EntityModelSet EntityModelSetThatIsntNULL = Minecraft.getInstance().getEntityModels();
        model = new ErebusShieldPartsModel(EntityModelSetThatIsntNULL.bakeLayer(ModItemRendering.EREBUS_SHIELD_PARTS));
    }

    @Override
    public void renderByItem(ItemStack stack, @Nonnull ItemDisplayContext context, PoseStack pose, MultiBufferSource buffer, int light, int overlay) {
        ErebusShieldItem item = (ErebusShieldItem) stack.getItem();
        VertexConsumer consumer = buffer.getBuffer(model.renderType(TEXTURE));

        pose.pushPose();
        pose.scale(1, -1, -1);
        model.renderToBuffer(pose, consumer, light, overlay, 0xFFFFFFFF);
        pose.popPose();

        pose.pushPose();
        pose.scale(1.25F, 1.25F, 1.25F);
        pose.translate(0, 0, 0.08125);
        pose.popPose();
    }
}
