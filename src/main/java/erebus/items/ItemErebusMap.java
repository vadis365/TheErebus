package erebus.items;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import erebus.ModBiomes;
import erebus.ModItems;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemErebusMap extends ItemMap
{
    public ItemErebusMap()
    {
        this.setHasSubtypes(true);
    }
    
    private static final HashMap<Biome, MapColorBrightness> BIOME_COLORS = new HashMap<>();
    
    private static class MapColorBrightness {
		public MapColor color;
		public int brightness;

		public MapColorBrightness(MapColor color, int brightness) {
			this.color = color;
			this.brightness = brightness;
		}

		public MapColorBrightness(MapColor color) {
			this.color = color;
			this.brightness = 1;
		}
}

    public static ItemStack setupNewMap(World worldIn, double worldX, double worldZ, byte scale, boolean trackingPosition, boolean unlimitedTracking)
    {
        ItemStack itemstack = new ItemStack(ModItems.EREBUS_MAP_FILLED, 1, worldIn.getUniqueDataId("map"));
        String s = "map_" + itemstack.getMetadata();
        MapData mapdata = new MapData(s);
        worldIn.setData(s, mapdata);
        mapdata.scale = scale;
        mapdata.calculateMapCenter(worldX, worldZ, mapdata.scale);
        mapdata.dimension = worldIn.provider.getDimension();
        mapdata.trackingPosition = trackingPosition;
        mapdata.unlimitedTracking = unlimitedTracking;
        mapdata.markDirty();
        return itemstack;
    }

    @Nullable
    @SideOnly(Side.CLIENT)
    public static MapData loadMapData(int mapId, World worldIn)
    {
        String s = "map_" + mapId;
        return (MapData)worldIn.loadData(MapData.class, s);
    }

    @Nullable
    public MapData getMapData(ItemStack stack, World worldIn)
    {
        String s = "map_" + stack.getMetadata();
        MapData mapdata = (MapData)worldIn.loadData(MapData.class, s);

        if (mapdata == null && !worldIn.isRemote)
        {
            stack.setItemDamage(worldIn.getUniqueDataId("map"));
            s = "map_" + stack.getMetadata();
            mapdata = new MapData(s);
            mapdata.scale = 3;
            mapdata.calculateMapCenter((double)worldIn.getWorldInfo().getSpawnX(), (double)worldIn.getWorldInfo().getSpawnZ(), mapdata.scale);
            mapdata.dimension = worldIn.provider.getDimension();
            mapdata.markDirty();
            worldIn.setData(s, mapdata);
        }

        return mapdata;
    }

    public void updateMapData(World worldIn, Entity viewer, MapData data)
    {
        if (worldIn.provider.getDimension() == data.dimension && viewer instanceof EntityPlayer)
        {
        	int biomesPerPixel = 16;
			int blocksPerPixel = 1 << data.scale;
			int centerX = data.xCenter;
			int centerZ = data.zCenter;
			int viewerX = MathHelper.floor(viewer.posX - (double) centerX) / blocksPerPixel + 64;
			int viewerZ = MathHelper.floor(viewer.posZ - (double) centerZ) / blocksPerPixel + 64;
			int viewRadiusPixels = 1024 / blocksPerPixel;
			
			int startX = (centerX / blocksPerPixel - 64) * biomesPerPixel;
			int startZ = (centerZ / blocksPerPixel - 64) * biomesPerPixel;
			Biome[] biomes = worldIn.getBiomeProvider().getBiomesForGeneration((Biome[]) null, startX, startZ, 128 * biomesPerPixel, 128 * biomesPerPixel);

			for (int xPixel = viewerX - viewRadiusPixels + 1; xPixel < viewerX + viewRadiusPixels; ++xPixel) {
				for (int zPixel = viewerZ - viewRadiusPixels - 1; zPixel < viewerZ + viewRadiusPixels; ++zPixel) {
					if (xPixel >= 0 && zPixel >= 0 && xPixel < 128 && zPixel < 128) {
						int xPixelDist = xPixel - viewerX;
						int zPixelDist = zPixel - viewerZ;
						boolean shouldFuzz = xPixelDist * xPixelDist + zPixelDist * zPixelDist > (viewRadiusPixels - 2) * (viewRadiusPixels - 2);

						Biome biome = biomes[xPixel * biomesPerPixel + zPixel * biomesPerPixel * 128 * biomesPerPixel];
						Biome overBiome = biomes[xPixel * biomesPerPixel + zPixel * biomesPerPixel * 128 * biomesPerPixel + 1];
						Biome downBiome = biomes[xPixel * biomesPerPixel + (zPixel * biomesPerPixel + 1) * 128 * biomesPerPixel];


						MapColorBrightness colorBrightness = this.getMapColorPerBiome(worldIn, biome);

						MapColor mapcolor = colorBrightness.color;
						int brightness = colorBrightness.brightness;

						if (zPixel >= 0 && xPixelDist * xPixelDist + zPixelDist * zPixelDist < viewRadiusPixels * viewRadiusPixels && (!shouldFuzz || (xPixel + zPixel & 1) != 0)) {
							byte orgPixel = data.colors[xPixel + zPixel * 128];
							byte ourPixel = (byte) (mapcolor.colorIndex * 4 + brightness);

							if (orgPixel != ourPixel) {
								data.colors[xPixel + zPixel * 128] = ourPixel;
								data.updateMapData(xPixel, zPixel);
							}

							int worldX = (centerX / blocksPerPixel + xPixel - 64) * blocksPerPixel;
							int worldZ = (centerZ / blocksPerPixel + zPixel - 64) * blocksPerPixel;
						}
					}
				}
			}
        }
}

	private MapColorBrightness getMapColorPerBiome(World world, Biome biome) {
		if (BIOME_COLORS.isEmpty()) {
			ItemErebusMap.setupBiomeColors();
		}

		if (BIOME_COLORS.containsKey(biome)) {
			return BIOME_COLORS.get(biome);
		} else {
			return new MapColorBrightness(biome.topBlock.getMapColor(world, BlockPos.ORIGIN));
		}
	}

	private static void setupBiomeColors() {
		BIOME_COLORS.put(ModBiomes.ELYSIAN_FIELDS, new MapColorBrightness(MapColor.LIME, 1));
		BIOME_COLORS.put(ModBiomes.FIELDS_SUB_FOREST, new MapColorBrightness(MapColor.PINK, 1));
		BIOME_COLORS.put(ModBiomes.FUNGAL_FOREST, new MapColorBrightness(MapColor.GREEN, 0));
		BIOME_COLORS.put(ModBiomes.PETRIFIED_FOREST, new MapColorBrightness(MapColor.GRAY, 2));
		BIOME_COLORS.put(ModBiomes.SUBMERGED_SWAMP, new MapColorBrightness(MapColor.GREEN, 3));
		BIOME_COLORS.put(ModBiomes.SUBTERRANEAN_SAVANNAH, new MapColorBrightness(MapColor.GREEN, 1));
		BIOME_COLORS.put(ModBiomes.UNDERGROUND_JUNGLE, new MapColorBrightness(MapColor.FOLIAGE, 2));
}

    @Nullable
    public Packet<?> createMapDataPacket(ItemStack stack, World worldIn, EntityPlayer player)
    {
        return this.getMapData(stack, worldIn).getMapPacket(stack, worldIn, player);
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound != null)
        {
            if (nbttagcompound.hasKey("map_scale_direction", 99))
            {
                scaleMap(stack, worldIn, nbttagcompound.getInteger("map_scale_direction"));
                nbttagcompound.removeTag("map_scale_direction");
            }
            else if (nbttagcompound.getBoolean("map_tracking_position"))
            {
                enableMapTracking(stack, worldIn);
                nbttagcompound.removeTag("map_tracking_position");
            }
        }
    }

    protected static void scaleMap(ItemStack p_185063_0_, World p_185063_1_, int p_185063_2_)
    {
        MapData mapdata = ((ItemErebusMap) ModItems.EREBUS_MAP_FILLED).getMapData(p_185063_0_, p_185063_1_);
        p_185063_0_.setItemDamage(p_185063_1_.getUniqueDataId("map"));
        MapData mapdata1 = new MapData("map_" + p_185063_0_.getMetadata());

        if (mapdata != null)
        {
            mapdata1.scale = (byte)MathHelper.clamp(mapdata.scale + p_185063_2_, 0, 4);
            mapdata1.trackingPosition = mapdata.trackingPosition;
            mapdata1.calculateMapCenter((double)mapdata.xCenter, (double)mapdata.zCenter, mapdata1.scale);
            mapdata1.dimension = mapdata.dimension;
            mapdata1.markDirty();
            p_185063_1_.setData("map_" + p_185063_0_.getMetadata(), mapdata1);
        }
    }

    protected static void enableMapTracking(ItemStack p_185064_0_, World p_185064_1_)
    {
        MapData mapdata = ((ItemErebusMap) ModItems.EREBUS_MAP_FILLED).getMapData(p_185064_0_, p_185064_1_);
        p_185064_0_.setItemDamage(p_185064_1_.getUniqueDataId("map"));
        MapData mapdata1 = new MapData("map_" + p_185064_0_.getMetadata());
        mapdata1.trackingPosition = true;

        if (mapdata != null)
        {
            mapdata1.xCenter = mapdata.xCenter;
            mapdata1.zCenter = mapdata.zCenter;
            mapdata1.scale = mapdata.scale;
            mapdata1.dimension = mapdata.dimension;
            mapdata1.markDirty();
            p_185064_1_.setData("map_" + p_185064_0_.getMetadata(), mapdata1);
        }
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if (flagIn.isAdvanced())
        {
            MapData mapdata = worldIn == null ? null : this.getMapData(stack, worldIn);

            if (mapdata != null)
            {
                tooltip.add(I18n.translateToLocalFormatted("filled_map.scale", 1 << mapdata.scale));
                tooltip.add(I18n.translateToLocalFormatted("filled_map.level", mapdata.scale, Integer.valueOf(4)));
            }
            else
            {
                tooltip.add(I18n.translateToLocal("filled_map.unknown"));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static int getColor(ItemStack p_190907_0_)
    {
        NBTTagCompound nbttagcompound = p_190907_0_.getSubCompound("display");

        if (nbttagcompound != null && nbttagcompound.hasKey("MapColor", 99))
        {
            int i = nbttagcompound.getInteger("MapColor");
            return -16777216 | i & 16777215;
        }
        else
        {
            return -12173266;
        }
    }
}