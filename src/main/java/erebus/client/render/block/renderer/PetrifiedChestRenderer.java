package erebus.client.render.block.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.PetrifiedChestBlock;
import erebus.block.entity.PetrifiedChestBlockEntity;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

public class PetrifiedChestRenderer extends ChestRenderer<PetrifiedChestBlockEntity> {

    public static final Map<Block, EnumMap<ChestType, Material>> MATERIALS;

    static {
        ImmutableMap.Builder<Block, EnumMap<ChestType, Material>> builder = ImmutableMap.builder();
        builder.put(OtherBlocks.PETRIFIED_WOOD_CHEST.get(), chestMaterials("petrified_chest"));
        MATERIALS = builder.build();
    }

    public PetrifiedChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    private static EnumMap<ChestType, Material> chestMaterials(String wood) {
        EnumMap<ChestType, Material> map = new EnumMap<>(ChestType.class);

        map.put(ChestType.SINGLE, new Material(Sheets.CHEST_SHEET, Erebus.prefix("entity/chest/%s".formatted(wood))));
        map.put(ChestType.LEFT, new Material(Sheets.CHEST_SHEET, Erebus.prefix("entity/chest/%s_left".formatted(wood))));
        map.put(ChestType.RIGHT, new Material(Sheets.CHEST_SHEET, Erebus.prefix("entity/chest/%s_right".formatted(wood))));
        return map;
    }

    @Override
    public void render(PetrifiedChestBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level level = entity.getLevel();
        boolean flag = level != null;
        BlockState blockstate = flag ? entity.getBlockState() : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
        ChestType chesttype = blockstate.hasProperty(ChestBlock.TYPE) ? blockstate.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
        if (blockstate.getBlock() instanceof PetrifiedChestBlock chest) {
            boolean flag1 = chesttype != ChestType.SINGLE;
            poseStack.pushPose();
            float f = blockstate.getValue(ChestBlock.FACING).toYRot();
            poseStack.translate(0.5F, 0.5F, 0.5F);
            poseStack.mulPose(Axis.YP.rotationDegrees(-f));
            poseStack.translate(-0.5F, -0.5F, -0.5F);
            DoubleBlockCombiner.NeighborCombineResult<PetrifiedChestBlockEntity> neighborcombineresult;
            if (flag) {
                neighborcombineresult = (DoubleBlockCombiner.NeighborCombineResult<PetrifiedChestBlockEntity>) chest.combine(blockstate, level, entity.getBlockPos(), true);
            } else {
                neighborcombineresult = DoubleBlockCombiner.Combiner::acceptNone;
            }

            float f1 = neighborcombineresult.apply(PetrifiedChestBlock.getOpennessCombiner(entity)).get(partialTick);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = neighborcombineresult.apply(new BrightnessCombiner<>()).applyAsInt(packedLight);
            Material material = this.getMaterial(entity, chesttype);
            VertexConsumer vertexconsumer = material.buffer(bufferSource, RenderType::entityCutout);
            if (flag1) {
                if (chesttype == ChestType.LEFT) {
                    this.render(poseStack, vertexconsumer, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, f1, i, packedOverlay);
                } else {
                    this.render(poseStack, vertexconsumer, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, f1, i, packedOverlay);
                }
            } else {
                this.render(poseStack, vertexconsumer, this.lid, this.lock, this.bottom, f1, i, packedOverlay);
            }

            poseStack.popPose();
        }
    }

    private void render(PoseStack poseStack, VertexConsumer consumer, ModelPart lidPart, ModelPart lockPart, ModelPart bottomPart, float lidAngle, int packedLight, int packedOverlay) {
        lidPart.xRot = -(lidAngle * (float) (Math.PI / 2));
        lockPart.xRot = lidPart.xRot;
        lidPart.render(poseStack, consumer, packedLight, packedOverlay);
        lockPart.render(poseStack, consumer, packedLight, packedOverlay);
        bottomPart.render(poseStack, consumer, packedLight, packedOverlay);
    }

    @NotNull
    protected Material getMaterial(PetrifiedChestBlockEntity entity, ChestType type) {
        EnumMap<ChestType, Material> materials = MATERIALS.get(entity.getBlockState().getBlock());
        if (materials == null) return super.getMaterial(entity, type);
        Material material = materials.get(type);
        return material != null ? material : super.getMaterial(entity, type);
    }
}
