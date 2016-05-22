package erebus.proxy;

import erebus.ModItems;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerItemAndBlockRenderers() {
		ModItems.registerRenderers();
	}
}