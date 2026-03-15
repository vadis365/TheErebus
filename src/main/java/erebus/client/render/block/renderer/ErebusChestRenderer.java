package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.block.ErebusChestBlock;
import erebus.block.entity.ErebusChestBlockEntity;
import erebus.client.render.block.renderer.state.ErebusChestRenderState;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.client.ModSheets;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.chest.ChestModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ErebusChestRenderer implements BlockEntityRenderer<ErebusChestBlockEntity, ErebusChestRenderState> {

    private final SpriteGetter materials;
    private final ChestModel model;
    private final ChestModel doubleLeftModel;
    private final ChestModel doubleRightModel;

    public ErebusChestRenderer(BlockEntityRendererProvider.Context context) {
        materials = context.sprites();
        model = new ChestModel(context.bakeLayer(ModelLayers.CHEST));
        doubleLeftModel = new ChestModel(context.bakeLayer(ModelLayers.DOUBLE_CHEST_LEFT));
        doubleRightModel = new ChestModel(context.bakeLayer(ModelLayers.DOUBLE_CHEST_RIGHT));
    }

    @Override
    public void extractRenderState(ErebusChestBlockEntity entity, ErebusChestRenderState state, float partialTicks, @NonNull Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> combineResult;

        searchForNeighbor: {
            BlockEntityRenderer.super.extractRenderState(entity, state, partialTicks, cameraPosition, breakProgress);
            boolean hasLevel = entity.getLevel() != null;
            BlockState blockState = hasLevel ? entity.getBlockState() : ModBlocks.CHEST_ASPER.get().defaultBlockState().setValue(ErebusChestBlock.FACING, Direction.SOUTH);
            state.type = blockState.getValue(ErebusChestBlock.TYPE);
            state.angle = blockState.getValue(ErebusChestBlock.FACING).toYRot();
            state.material = getChestMaterial(entity);

            if(hasLevel) {
                Block block = blockState.getBlock();
                if(block instanceof ErebusChestBlock chest) {
                    combineResult = chest.combine(blockState, entity.getLevel(), entity.getBlockPos(), true);
                    break searchForNeighbor;
                }
            }

            combineResult = DoubleBlockCombiner.Combiner::acceptNone;
        }

        state.open = combineResult.apply(ErebusChestBlock.opennessCombiner(entity)).get(partialTicks);
        if(state.type != ChestType.SINGLE) {
            state.lightCoords = combineResult.apply(new BrightnessCombiner<>()).applyAsInt(state.lightCoords);
        }
    }

    @Override
    public ErebusChestRenderState createRenderState() {
        return new ErebusChestRenderState();
    }

    @Override
    public void submit(ErebusChestRenderState state, @NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, @NonNull CameraRenderState camera) {
        pose.pushPose();
        pose.translate(0.5F, 0.5F, 0.5F);
        pose.mulPose(Axis.YP.rotationDegrees(-state.angle));
        pose.translate(-0.5F, -0.5F, -0.5F);
        float open = state.open;
        open = 1.0F - open;
        open = 1.0F - open * open * open;
        SpriteId material = ModSheets.chooseSpriteId(state.material, state.type);
        RenderType renderType = material.renderType(RenderTypes::entityCutout);

        switch(state.type) {
            case LEFT -> submit.submitModel(doubleLeftModel, open, pose, renderType, state.lightCoords, OverlayTexture.NO_OVERLAY, -1, materials.get(material), 0, state.breakProgress);
            case RIGHT -> submit.submitModel(doubleRightModel, open, pose, renderType, state.lightCoords, OverlayTexture.NO_OVERLAY, -1, materials.get(material), 0, state.breakProgress);
            case SINGLE -> submit.submitModel(model, open, pose, renderType, state.lightCoords, OverlayTexture.NO_OVERLAY, -1, materials.get(material), 0, state.breakProgress);
            default -> {}
        }

        pose.popPose();
    }

    private ErebusChestRenderState.ErebusChestMaterialType getChestMaterial(BlockEntity entity) {
        if(entity.getBlockState().getBlock() instanceof ErebusChestBlock chest) {
            switch (chest.getUnlocalizedName()) {
                case "chest_asper": return ErebusChestRenderState.ErebusChestMaterialType.ASPER;
                case "chest_bamboo": return ErebusChestRenderState.ErebusChestMaterialType.BAMBOO;
                case "chest_baobab": return ErebusChestRenderState.ErebusChestMaterialType.BAOBAB;
                case "chest_balsam": return ErebusChestRenderState.ErebusChestMaterialType.BALSAM;
                case "chest_cypress": return ErebusChestRenderState.ErebusChestMaterialType.CYPRESS;
                case "chest_eucalyptus": return ErebusChestRenderState.ErebusChestMaterialType.EUCALYPTUS;
                case "chest_mahogany": return ErebusChestRenderState.ErebusChestMaterialType.MAHOGANY;
                case "chest_marshwood": return ErebusChestRenderState.ErebusChestMaterialType.MARSHWOOD;
                case "chest_mossbark": return ErebusChestRenderState.ErebusChestMaterialType.MOSSBARK;
                case "chest_petrified": return ErebusChestRenderState.ErebusChestMaterialType.PETRIFIED;
                case "chest_rotten": return ErebusChestRenderState.ErebusChestMaterialType.ROTTEN;
                case "chest_scorched": return ErebusChestRenderState.ErebusChestMaterialType.SCORCHED;
                case "chest_varnished": return ErebusChestRenderState.ErebusChestMaterialType.VARNISHED;
                case "chest_white": return ErebusChestRenderState.ErebusChestMaterialType.WHITE;
            }
        }
        return ErebusChestRenderState.ErebusChestMaterialType.ASPER;
    }

    public @NonNull AABB getRenderBoundingBox(ErebusChestBlockEntity chest) {
        BlockPos pos = chest.getBlockPos();
        return AABB.encapsulatingFullBlocks(pos.offset(-1, 0, -1), pos.offset(1, 1, 1));
    }
}
