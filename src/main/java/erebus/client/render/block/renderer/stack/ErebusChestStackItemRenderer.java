package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.Erebus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class ErebusChestStackItemRenderer extends BlockEntityWithoutLevelRenderer {

    private final ResourceLocation TEXTURE;
    private final ModelPart model;

    public ErebusChestStackItemRenderer(BlockEntityRenderDispatcher renderer, EntityModelSet modelSet, String texture) {
        super(renderer, modelSet);
        EntityModelSet nonNull = Minecraft.getInstance().getEntityModels();
        model = nonNull.bakeLayer(ModelLayers.CHEST);
        this.TEXTURE = Erebus.prefix("textures/entity/chest/%s.png".formatted(texture));
    }

    @Override
    public void renderByItem(@Nonnull ItemStack itemStackIn, @Nonnull ItemDisplayContext transformType, PoseStack stack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        VertexConsumer consumer = buffer.getBuffer(RenderType.entitySolid(TEXTURE));
        stack.pushPose();
        model.render(stack, consumer, packedLight, packedOverlay);
        stack.popPose();
    }
}
