package erebus.client.render.block.renderer;

import com.google.common.collect.ImmutableMap;
import erebus.Erebus;
import erebus.registries.blocks.providers.ChestBlocks;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

public class ErebusChestRenderer<T extends ChestBlockEntity> extends ChestRenderer<T> {

    public static final Map<Block, EnumMap<ChestType, Material>> MATERIALS;

    static {
        ImmutableMap.Builder<Block, EnumMap<ChestType, Material>> builder = ImmutableMap.builder();
        builder.put(ChestBlocks.CHEST_ASPER.get(), chestMaterials("asper_chest"));
        builder.put(ChestBlocks.CHEST_BAOBAB.get(), chestMaterials("baobab_chest"));
        builder.put(ChestBlocks.CHEST_BAMBOO.get(), chestMaterials("bamboo_chest"));
        builder.put(ChestBlocks.CHEST_BALSAM.get(), chestMaterials("balsam_chest"));
        builder.put(ChestBlocks.CHEST_CYPRESS.get(), chestMaterials("cypress_chest"));
        builder.put(ChestBlocks.CHEST_EUCALYPTUS.get(), chestMaterials("eucalyptus_chest"));
        builder.put(ChestBlocks.CHEST_MAHOGANY.get(), chestMaterials("mahogany_chest"));
        builder.put(ChestBlocks.CHEST_MARSHWOOD.get(), chestMaterials("marshwood_chest"));
        builder.put(ChestBlocks.CHEST_MOSSBARK.get(), chestMaterials("mossbark_chest"));
        builder.put(ChestBlocks.CHEST_PETRIFIED.get(), chestMaterials("petrified_chest"));
        builder.put(ChestBlocks.CHEST_ROTTEN.get(), chestMaterials("rotten_chest"));
        builder.put(ChestBlocks.CHEST_SCORCHED.get(), chestMaterials("scorched_chest"));
        builder.put(ChestBlocks.CHEST_VARNISHED.get(), chestMaterials("varnished_chest"));
        builder.put(ChestBlocks.CHEST_WHITE.get(), chestMaterials("white_chest"));
        MATERIALS = builder.build();
    }

    public ErebusChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    private static EnumMap<ChestType, Material> chestMaterials(String wood) {
        EnumMap<ChestType, Material> map = new EnumMap<>(ChestType.class);

        map.put(ChestType.SINGLE, new Material(Sheets.CHEST_SHEET, Erebus.prefix("entity/chest/%s".formatted(wood))));
        map.put(ChestType.LEFT, new Material(Sheets.CHEST_SHEET, Erebus.prefix("entity/chest/%s_left".formatted(wood))));
        map.put(ChestType.RIGHT, new Material(Sheets.CHEST_SHEET, Erebus.prefix("entity/chest/%s_right".formatted(wood))));
        return map;
    }

    @NotNull
    protected  Material getMaterial(T entity, ChestType type) {
        EnumMap<ChestType, Material> materials = MATERIALS.get(entity.getBlockState().getBlock());
        if (materials == null) return super.getMaterial(entity, type);
        Material material = materials.get(type);
        return material != null ? material : super.getMaterial(entity, type);
    }
}
