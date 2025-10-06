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

public class PetrifiedChestStackItemRenderer extends BlockEntityWithoutLevelRenderer {

    private final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/chest/petrified_chest.png");
    private final ModelPart model;

    public PetrifiedChestStackItemRenderer(BlockEntityRenderDispatcher renderer, EntityModelSet modelSet) {
        super(renderer, modelSet);
        EntityModelSet nonNull = Minecraft.getInstance().getEntityModels();
        model = nonNull.bakeLayer(ModelLayers.CHEST);
    }

    @Override
    public void renderByItem(@Nonnull ItemStack itemStackIn, @Nonnull ItemDisplayContext transformType, PoseStack stack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        VertexConsumer consumer = buffer.getBuffer(RenderType.entitySolid(TEXTURE));
        stack.pushPose();
        model.render(stack, consumer, packedLight, packedOverlay);
        stack.popPose();
    }
}
