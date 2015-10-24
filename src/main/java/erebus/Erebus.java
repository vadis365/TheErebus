package erebus;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import erebus.client.gui.RenderWarHammerChargeBar;
import erebus.client.render.entity.MobGrabbingHealthBarRemoval;
import erebus.client.render.entity.RenderRhinoBeetleChargeBar;
import erebus.client.sound.ErebusMusicHandler;
import erebus.core.handler.BucketFillHandler;
import erebus.core.handler.EntityDeathEventHandler;
import erebus.core.handler.EntityDeathInventoryHandler;
import erebus.core.handler.EntityPickupEventHandler;
import erebus.core.handler.HomingBeeconTextureHandler;
import erebus.core.handler.PlayerChangedDimensionEventHandler;
import erebus.core.handler.configs.ConfigHandler;
import erebus.core.proxy.CommonProxy;
import erebus.debug.ErebusCommandDebug;
import erebus.entity.util.RandomMobNames;
import erebus.integration.FMPIntegration;
import erebus.integration.ModIntegrationHandler;
import erebus.integration.ThaumcraftIntegration;
import erebus.lib.Reference;
import erebus.network.PacketPipeline;
import erebus.recipes.ComposterRegistry;
import erebus.recipes.ErebusRecipesHandler;
import erebus.recipes.RecipeHandler;
import erebus.world.SpawnerErebus;
import erebus.world.WorldProviderErebus;
import erebus.world.feature.structure.WorldGenAntlionMaze;
import erebus.world.teleporter.TeleporterHandler;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = "${version}", dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY_CLASS)
public class Erebus {

	@SidedProxy(clientSide = Reference.SP_CLIENT, serverSide = Reference.SP_SERVER)
	public static CommonProxy proxy;

	@Instance(Reference.MOD_ID)
	public static Erebus instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.INSTANCE.loadConfig(event);

		if (event.getSide() == Side.CLIENT) {
			MinecraftForge.EVENT_BUS.register(new RenderRhinoBeetleChargeBar());
			MinecraftForge.EVENT_BUS.register(new RenderWarHammerChargeBar());
			MinecraftForge.EVENT_BUS.register(new HomingBeeconTextureHandler());
			MinecraftForge.EVENT_BUS.register(new MobGrabbingHealthBarRemoval());
			if (ConfigHandler.INSTANCE.playCustomSongs)
				FMLCommonHandler.instance().bus().register(new ErebusMusicHandler());
		}

		ModFluids.init();
		ModBlocks.init();
		ModItems.init();
		ModEntities.init();

		AchievementPage.registerAchievementPage(new ModAchievements());

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

		DimensionManager.registerProviderType(ConfigHandler.INSTANCE.erebusDimensionID, WorldProviderErebus.class, true);
		DimensionManager.registerDimension(ConfigHandler.INSTANCE.erebusDimensionID, ConfigHandler.INSTANCE.erebusDimensionID);
		GameRegistry.registerWorldGenerator(new WorldGenAntlionMaze(), 0);
	}

	@EventHandler
	@SuppressWarnings("unchecked")
	public void init(FMLInitializationEvent event) {
		// Remove all the door recipes.
		// This is needed otherwise our doors will not be craftable due to the recipe ordering
		List<IRecipe> doorRecipes = new ArrayList<IRecipe>();
		for (IRecipe recipe : (List<IRecipe>) CraftingManager.getInstance().getRecipeList())
			if (recipe != null) {
				ItemStack stack = recipe.getRecipeOutput();
				if (stack != null && stack.getItem() == Items.wooden_door)
					doorRecipes.add(recipe);
			}
		for (IRecipe recipe : doorRecipes)
			CraftingManager.getInstance().getRecipeList().remove(recipe);

		proxy.registerKeyHandlers();
		proxy.registerTileEntities();
		proxy.registerRenderInformation();

		PacketPipeline.initializePipeline();
		ModBiomes.init();
		RecipeHandler.init();
		ErebusRecipesHandler.init();
		TeleporterHandler.init();

		MinecraftForge.EVENT_BUS.register(new EntityDeathEventHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerChangedDimensionEventHandler());
		MinecraftForge.EVENT_BUS.register(new EntityPickupEventHandler());
		MinecraftForge.EVENT_BUS.register(new BucketFillHandler());
		MinecraftForge.EVENT_BUS.register(ModBlocks.quickSand);
		MinecraftForge.EVENT_BUS.register(ModFluids.INSTANCE);
		MinecraftForge.EVENT_BUS.register(ModItems.armorGlider);
		MinecraftForge.EVENT_BUS.register(ModItems.jumpBoots);
		FMLCommonHandler.instance().bus().register(ConfigHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(SpawnerErebus.INSTANCE);

		if (ConfigHandler.INSTANCE.graveMarker)
			MinecraftForge.EVENT_BUS.register(new EntityDeathInventoryHandler());

		if (ConfigHandler.INSTANCE.randomNames)
			MinecraftForge.EVENT_BUS.register(RandomMobNames.instance);

		if (!ConfigHandler.INSTANCE.disableThaumcraft)
			ModIntegrationHandler.addMod(ThaumcraftIntegration.class);

		if (!ConfigHandler.INSTANCE.disableFMP)
			ModIntegrationHandler.addMod(FMPIntegration.class);

		ModIntegrationHandler.init();
		ComposterRegistry.init();

		// Add the other door recipes back
		CraftingManager.getInstance().getRecipeList().addAll(doorRecipes);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		ModIntegrationHandler.postInit();
	}

	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new ErebusCommandDebug());
	}
}