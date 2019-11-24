package erebus.world.feature.structure.worlddata;

import java.util.ArrayList;
import java.util.List;

import erebus.lib.Reference;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class WorldDataGiantEucalyptus extends WorldSavedData {

	final static String KEY = Reference.MOD_ID + "_giant_eucalyptus_structures";

	public WorldDataGiantEucalyptus() {
		super(KEY);
	}

	public WorldDataGiantEucalyptus(String name) {
		super(name);
	}

	private List<BlockPos> STRUCTURE_LOCATIONS = new ArrayList<BlockPos>();

	public List<BlockPos> getData() {
		return STRUCTURE_LOCATIONS;
	}

	public boolean addStructurePosition(BlockPos pos) {
		if (STRUCTURE_LOCATIONS.add(pos)) {
			markDirty();
			//System.out.println(String.format("Added Structure %d %d %d to list", pos.getX(), pos.getY(), pos.getZ()));
			return true;
		}
		//System.err.println("Failed to add Structure position to Structure list");
		return false;
	}

	public static WorldDataGiantEucalyptus forWorld(World world) {
		MapStorage storage = world.getPerWorldStorage();
		WorldDataGiantEucalyptus result = (WorldDataGiantEucalyptus) storage.getOrLoadData(WorldDataGiantEucalyptus.class, KEY);
		if (result == null) {
			result = new WorldDataGiantEucalyptus(KEY);
			storage.setData(KEY, result);
		}
		return result;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		NBTTagList tagList = (NBTTagList)nbt.getTag("giant_eucalyptus");
		if(tagList.isEmpty())
			return;
		for (int i = 0; i < tagList.tagCount(); ++i)
			addStructurePosition(NBTUtil.getPosFromTag(tagList.getCompoundTagAt(i)));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagList tagList = new NBTTagList();
		for (BlockPos blockPos : STRUCTURE_LOCATIONS)
			tagList.appendTag(NBTUtil.createPosTag(blockPos));
		nbt.setTag("giant_eucalyptus", tagList);
		return nbt;
	}
}
