package erebus.proxy;

import erebus.lib.Reference;
import erebus.tileentity.TileEntityGaeanKeystone;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void registerItemAndBlockRenderers() {
	}

	public void setCustomStateMap(Block block, StateMap stateMap) {
	}

	public void postInit() {		
	}

	public void registerItemAndBlockColourRenderers() {
	}
	
	public void registerTileEntities() {
		registerTileEntity(TileEntityGaeanKeystone.class, "gaean_keystone");
	}

	private void registerTileEntity(Class<? extends TileEntity> cls, String baseName) {
		GameRegistry.registerTileEntity(cls, Reference.MOD_ID + "." + baseName);
	}

	public void spawnCustomParticle(String particleName, World world, double x, double y, double z, double vecX, double vecY, double vecZ) {	
	}

	public void registerEnitityRenderers() {
	}
}