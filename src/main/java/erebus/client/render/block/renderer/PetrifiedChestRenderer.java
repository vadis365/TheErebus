package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractChestBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

public class PetrifiedChestRenderer<T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<T> {

    private static final String BOTTOM = "bottom";
    private static final String LID = "lid";
    private static final String LOCK = "lock";
    private final ModelPart lid, bottom, lock;
    private final ModelPart doubleLeftLid, doubleLeftBottom, doubleLeftLock;
    private final ModelPart doubleRightLid, doubleRightBottom, doubleRightLock;

    public PetrifiedChestRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart chest = context.bakeLayer(ModelLayers.CHEST);
        bottom = chest.getChild(BOTTOM);
        lid = chest.getChild(LID);
        lock = chest.getChild(LOCK);

        ModelPart doubleLeft = context.bakeLayer(ModelLayers.DOUBLE_CHEST_LEFT);
        doubleLeftBottom = doubleLeft.getChild(BOTTOM);
        doubleLeftLid = doubleLeft.getChild(LID);
        doubleLeftLock = doubleLeft.getChild(LOCK);

        ModelPart doubleRight = context.bakeLayer(ModelLayers.DOUBLE_CHEST_RIGHT);
        doubleRightBottom = doubleRight.getChild(BOTTOM);
        doubleRightLid = doubleRight.getChild(LID);
        doubleRightLock = doubleRight.getChild(LOCK);
    }

    public static LayerDefinition createSingleBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();
        part.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 19).addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F), PartPose.ZERO);
        part.addOrReplaceChild(
                "lid", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F), PartPose.offset(0.0F, 9.0F, 1.0F)
        );
        part.addOrReplaceChild(
                "lock", CubeListBuilder.create().texOffs(0, 0).addBox(7.0F, -2.0F, 14.0F, 2.0F, 4.0F, 1.0F), PartPose.offset(0.0F, 9.0F, 1.0F)
        );
        return LayerDefinition.create(mesh, 64, 64);
    }

    public static LayerDefinition createDoubleBodyRightLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();
        part.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 19).addBox(1.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F), PartPose.ZERO);
        part.addOrReplaceChild(
                "lid", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F), PartPose.offset(0.0F, 9.0F, 1.0F)
        );
        part.addOrReplaceChild(
                "lock", CubeListBuilder.create().texOffs(0, 0).addBox(15.0F, -2.0F, 14.0F, 1.0F, 4.0F, 1.0F), PartPose.offset(0.0F, 9.0F, 1.0F)
        );
        return LayerDefinition.create(mesh, 64, 64);
    }

    public static LayerDefinition createDoubleBodyLeftLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();
        part.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F), PartPose.ZERO);
        part.addOrReplaceChild(
                "lid", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F), PartPose.offset(0.0F, 9.0F, 1.0F)
        );
        part.addOrReplaceChild(
                "lock", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.0F, 14.0F, 1.0F, 4.0F, 1.0F), PartPose.offset(0.0F, 9.0F, 1.0F)
        );
        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void render(@NotNull T block, float partialTick, @NotNull PoseStack pose, @NotNull MultiBufferSource buffer, int light, int overlay) {
        Level level = block.getLevel();
        boolean flag = level != null;
        BlockState state = flag ? block.getBlockState() : OtherBlocks.PETRIFIED_WOOD_CHEST.get().defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
        ChestType chestType = state.hasProperty(ChestBlock.TYPE) ? state.getValue(ChestBlock.TYPE) : ChestType.SINGLE;

        if(state.getBlock() instanceof AbstractChestBlock<?> chest) {
            boolean isDating = chestType != ChestType.SINGLE;
            pose.pushPose();
            float yRot = state.getValue(ChestBlock.FACING).toYRot();
            pose.translate(0.5F, 0.5F, 0.5F);
            pose.mulPose(Axis.YP.rotationDegrees(-yRot));
            pose.translate(-0.5F, -0.5F, -0.5F);

            DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> result;

            if(flag) {
                result = chest.combine(state, level, block.getBlockPos(), true);
            } else {
                result = DoubleBlockCombiner.Combiner::acceptNone;
            }

            float appliedTick = result.apply(ChestBlock.opennessCombiner(block)).get(partialTick);
            appliedTick = 1 - appliedTick;
            appliedTick = 1 - appliedTick * appliedTick * appliedTick;

            int appliedLight = result.apply(new BrightnessCombiner<>()).get(light);
            Material material = getMaterial(block, chestType);
            VertexConsumer vertex = material.buffer(buffer, RenderType::entityCutout);

            if(isDating) {
                if(chestType == ChestType.LEFT) {
                    render(pose, vertex, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, appliedTick, appliedLight, overlay);
                } else {
                    render(pose, vertex, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, appliedTick, appliedLight, overlay);
                }
            } else {
                render(pose, vertex, this.lid, this.lock, this.bottom, appliedTick, appliedLight, overlay);
            }

            pose.popPose();
        }
    }

    private void render(PoseStack pose, VertexConsumer buffer, ModelPart lid, ModelPart lock, ModelPart bottom, float lidAngle, int light, int overlay) {
        lid.xRot = (float) -(lidAngle * (Math.PI / 2F));
        lock.xRot = lid.xRot;
        lid.render(pose, buffer, light, overlay);
        lock.render(pose, buffer, light, overlay);
        bottom.render(pose, buffer, light, overlay);
    }

    protected Material getMaterial(T blockEntity, ChestType chestType) {
        return Sheets.chooseMaterial(blockEntity, chestType, false);
    }

    @Override
    public @NotNull AABB getRenderBoundingBox(T be) {
        BlockPos pos = be.getBlockPos();
        return AABB.encapsulatingFullBlocks(pos.offset(-1, 0, -1), pos.offset(1, 1, 1));
    }
}
