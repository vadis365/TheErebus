package erebus.item;

import erebus.Erebus;
import erebus.registries.item.ModItems;
import erebus.registries.world.ModBiomes;
import erebus.registries.world.ModDimensionRegistries;
import erebus.world.biome.util.ErebusBiomeProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public final class ErebusMapItem extends MapItem {
    public ErebusMapItem() {
        super(new Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("erebus_map_filled"))));
    }

    public static ItemStack createMap(ServerLevel level, int x, int z, byte scale) {
        return MapItem.create(level, x, z, scale, true, false).transmuteCopy(ModItems.EREBUS_MAP_FILLED.get());
    }

    private static byte biomeColor(Holder<Biome> biome) {
        if (biome.is(ModBiomes.ELYSIAN_FIELDS_KEY)) return MapColor.COLOR_LIGHT_GREEN.getPackedId(MapColor.Brightness.NORMAL);
        if (biome.is(ModBiomes.ELYSIAN_FOREST_KEY)) return MapColor.COLOR_PINK.getPackedId(MapColor.Brightness.NORMAL);
        if (biome.is(ModBiomes.FUNGAL_FOREST_KEY)) return MapColor.COLOR_GREEN.getPackedId(MapColor.Brightness.LOW);
        if (biome.is(ModBiomes.PETRIFIED_FOREST_KEY)) return MapColor.COLOR_GRAY.getPackedId(MapColor.Brightness.HIGH);
        if (biome.is(ModBiomes.SUBMERGED_SWAMP_KEY)) return MapColor.COLOR_GREEN.getPackedId(MapColor.Brightness.LOWEST);
        if (biome.is(ModBiomes.SUBTERRANEAN_SAVANNAH_KEY)) return MapColor.COLOR_GREEN.getPackedId(MapColor.Brightness.NORMAL);
        if (biome.is(ModBiomes.UNDERGROUND_JUNGLE_KEY)) return MapColor.PLANT.getPackedId(MapColor.Brightness.HIGH);
        if (biome.is(ModBiomes.ULTERIOR_OUTBACK_KEY)) return MapColor.COLOR_ORANGE.getPackedId(MapColor.Brightness.NORMAL);
        if (biome.is(ModBiomes.VOLCANIC_DESERT_KEY)) return MapColor.SAND.getPackedId(MapColor.Brightness.NORMAL);
        return MapColor.GRASS.getPackedId(MapColor.Brightness.NORMAL);
    }

    @Override
    public void inventoryTick(@NonNull ItemStack stack, ServerLevel level, @NonNull Entity owner, @Nullable EquipmentSlot slot) {
        if (!level.dimension().equals(ModDimensionRegistries.DIMENSION_KEY)) return;
        if (MapItem.getSavedData(stack, level) == null) {
            var map = createMap(level, owner.getBlockX(), owner.getBlockZ(), (byte) 3);
            stack.set(DataComponents.MAP_ID, map.get(DataComponents.MAP_ID));
        }
        super.inventoryTick(stack, level, owner, slot);
    }

    @Override
    public void update(@NonNull Level level, @NonNull Entity owner, @NonNull MapItemSavedData data) {
        if (!(level instanceof ServerLevel server) || !(owner instanceof Player player)
                || !level.dimension().equals(data.dimension)) return;
        if (!level.dimension().equals(ModDimensionRegistries.DIMENSION_KEY)) return;
        int scale = 1 << data.scale;
        int viewerX = Mth.floor(owner.getX() - data.centerX) / scale + 64;
        int viewerZ = Mth.floor(owner.getZ() - data.centerZ) / scale + 64;
        int radius = 1024 / scale;
        int step = ++data.getHoldingPlayer(player).step;
        var source = server.getChunkSource().getGenerator().getBiomeSource();
        var sampler = server.getChunkSource().randomState().sampler();

        for (int x = Math.max(0, viewerX - radius + 1); x < Math.min(128, viewerX + radius); x++) {
            if ((x & 15) != (step & 15)) continue;
            for (int z = Math.max(0, viewerZ - radius - 1); z < Math.min(128, viewerZ + radius); z++) {
                int distance = Mth.square(x - viewerX) + Mth.square(z - viewerZ);
                if (distance >= radius * radius || (distance > (radius - 2) * (radius - 2) && ((x + z) & 1) == 0)) continue;
                int worldX = data.centerX + (x - 64) * scale;
                int worldZ = data.centerZ + (z - 64) * scale;
                var biome = source instanceof ErebusBiomeProvider erebus
                        ? erebus.getMainBiome(QuartPos.fromBlock(worldX), QuartPos.fromBlock(worldZ))
                        : source.getNoiseBiome(QuartPos.fromBlock(worldX), QuartPos.fromBlock(owner.getBlockY()),
                        QuartPos.fromBlock(worldZ), sampler);
                data.updateColor(x, z, biomeColor(biome));
            }
        }
    }
}
