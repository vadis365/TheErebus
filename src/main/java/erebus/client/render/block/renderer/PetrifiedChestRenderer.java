package erebus.client.render.block.renderer;

import erebus.block.entity.PetrifiedChestBlockEntity;
import erebus.client.ModAtlases;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.jetbrains.annotations.NotNull;

public class PetrifiedChestRenderer extends ChestRenderer<PetrifiedChestBlockEntity> {

    public PetrifiedChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected @NotNull Material getMaterial(@NotNull PetrifiedChestBlockEntity blockEntity, ChestType chestType) {
        return switch (chestType) {
            case LEFT -> ModAtlases.PETRIFIED_CHEST_LEFT;
            case RIGHT -> ModAtlases.PETRIFIED_CHEST_RIGHT;
            default -> ModAtlases.PETRIFIED_CHEST;
        };
    }
}
