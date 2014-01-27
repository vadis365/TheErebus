package erebus.integration;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.block.BlockErebusOre;

public class ThaumcraftIntegration implements IModIntegration {

	@Override
	public String getModId() {
		return "Thaumcraft";
	}

	@Override
	public void integrate() {
		registerBlock(ModBlocks.blockAmber, 0, Aspect.STONE, 2, Aspect.CRYSTAL, 2, Aspect.SLIME, 1);
		registerBlock(ModBlocks.blockAmber, 1, Aspect.STONE, 2, Aspect.CRYSTAL, 5);
		registerBlock(ModBlocks.blockAmber, 2, Aspect.STONE, 2, Aspect.CRYSTAL, 2, Aspect.SLIME, 1);

		registerBlock(ModBlocks.logErebusGroup1, -1, Aspect.TREE, 4, Aspect.PLANT, 2, Aspect.ELDRITCH, 1, Aspect.CRAFT, 3, Aspect.LIFE, 1, Aspect.EARTH, 1);
		registerBlock(ModBlocks.logErebusGroup2, -1, Aspect.TREE, 4, Aspect.PLANT, 2, Aspect.ELDRITCH, 1, Aspect.CRAFT, 3, Aspect.LIFE, 1, Aspect.EARTH, 1);
		// planks, leaves
		registerBlock(ModBlocks.erebusSapling, -1, Aspect.TREE, 1, Aspect.SEED, 2, Aspect.LIFE, 1);

		registerBlock(ModBlocks.blockSilk, -1, Aspect.CLOTH, 4);
		registerBlock(ModBlocks.mirBrick, -1, Aspect.STONE, 2, Aspect.MIND, 1);
		registerBlock(ModBlocks.spiderSpawner, -1, Aspect.BEAST, 5, Aspect.TAINT, 2, Aspect.WEAPON, 2, Aspect.SOUL, 3, Aspect.POISON, 1);
		registerBlock(ModBlocks.jumpingSpiderSpawner, -1, Aspect.BEAST, 5, Aspect.TAINT, 2, Aspect.WEAPON, 2, Aspect.SOUL, 3, Aspect.POISON, 1);
		registerBlock(ModBlocks.thorns, -1, Aspect.PLANT, 2, Aspect.WEAPON, 1);
		registerBlock(ModBlocks.fern, -1, Aspect.PLANT, 2, Aspect.SEED, 2);
		// tall grass
		registerBlock(ModBlocks.quickSand, -1, Aspect.TRAP, 4, Aspect.DEATH, 2, Aspect.EARTH, 2);
		registerBlock(ModBlocks.ghostSand, -1, Aspect.TRAP, 4, Aspect.VOID, 3);
		registerBlock(ModBlocks.hollowLogAcacia, -1, Aspect.SLIME, 1, Aspect.TREE, 3);

		registerBlock(ModBlocks.umberOreBlock, BlockErebusOre.dataCoal, Aspect.EARTH, 1, Aspect.ENERGY, 4, Aspect.FIRE, 2, Aspect.STONE, 3);
		registerBlock(ModBlocks.umberOreBlock, BlockErebusOre.dataIron, Aspect.EARTH, 1, Aspect.MECHANISM, 2, Aspect.CRAFT, 2, Aspect.METAL, 4, Aspect.STONE, 3);
		registerBlock(ModBlocks.umberOreBlock, BlockErebusOre.dataGold, Aspect.EARTH, 1, Aspect.GREED, 4, Aspect.METAL, 4, Aspect.STONE, 3);
		registerBlock(ModBlocks.umberOreBlock, BlockErebusOre.dataDiamond, Aspect.EARTH, 1, Aspect.CRYSTAL, 8, Aspect.ORDER, 4, Aspect.STONE, 3);
		registerBlock(ModBlocks.umberOreBlock, BlockErebusOre.dataEmerald, Aspect.EARTH, 1, Aspect.GREED, 16, Aspect.CRYSTAL, 8, Aspect.STONE, 3);
		registerBlock(ModBlocks.umberOreBlock, BlockErebusOre.dataLapis, Aspect.EARTH, 1, Aspect.GREED, 8, Aspect.CRYSTAL, 2, Aspect.STONE, 3);
		registerBlock(ModBlocks.umberOreBlock, BlockErebusOre.dataJade, Aspect.GREED, 4, Aspect.CRYSTAL, 3, Aspect.EARTH, 2);
		registerBlock(ModBlocks.umberOreBlock, BlockErebusOre.dataPetrifiedWood, Aspect.EARTH, 1, Aspect.TREE, 2, Aspect.STONE, 2);
		registerBlock(ModBlocks.oreFossil, -1, Aspect.DEATH, 1, Aspect.BEAST, 1, Aspect.EARTH, 1);
		registerBlock(ModBlocks.umberstone, 0, Aspect.EARTH, 2, Aspect.STONE, 2);
		registerBlock(ModBlocks.umberstone, 1, Aspect.EARTH, 1, Aspect.STONE, 2);
		registerBlock(ModBlocks.umberstone, 2, Aspect.EARTH, 1, Aspect.STONE, 2, Aspect.PLANT, 2);
		registerBlock(ModBlocks.umberstone, 3, Aspect.EARTH, 1, Aspect.STONE, 2, Aspect.BEAST, 1);
		registerBlock(ModBlocks.umberstone, 4, Aspect.STONE, 2);

		// stairs, slabs, walls
		for (int i = 0; i < ModBlocks.umbercobbleStairs.length; i++)
			registerBlock(ModBlocks.umbercobbleStairs[i], -1, Aspect.EARTH, 1, Aspect.STONE, 2, Aspect.TRAVEL, 1);
		// for (int i = 0; i < ModBlocks.plankStairs.length; i++)
		// registerBlock(ModBlocks.plankStairs[i], -1);
		for (int i = 0; i < ModBlocks.stoneSlabs.length; i++)
			registerBlock(ModBlocks.stoneSlabs[i], -1, Aspect.EARTH, 1, Aspect.STONE, 1);
		// for (int i = 0; i < ModBlocks.plankSlabs.length; i++)
		// registerBlock(ModBlocks.plankSlabs[i], -1);
		registerBlock(ModBlocks.petrifiedWoodStairs, -1, Aspect.TREE, 1, Aspect.STONE, 1, Aspect.TRAVEL, 1);
		for (int i = 0; i < ModBlocks.petrifiedWoodSlab.length; i++)
			registerBlock(ModBlocks.petrifiedWoodSlab[i], -1, Aspect.TREE, 1, Aspect.STONE, 1);
		// registerBlock(ModBlocks.amberBrickStairs, -1);
		// registerBlock(ModBlocks.waspNestStairs, -1);

		registerBlock(ModBlocks.bambooCrop, -1, Aspect.PLANT, 2, Aspect.CROP, 2, Aspect.CRAFT, 1);
		registerBlock(ModBlocks.bambooCrate, -1, Aspect.PLANT, 4, Aspect.VOID, 3, Aspect.CRAFT, 2);
		registerBlock(ModBlocks.redGem, 0, Aspect.LIGHT, 2, Aspect.CRYSTAL, 3, Aspect.ENERGY, 2);
		registerBlock(ModBlocks.redGem, 1, Aspect.LIGHT, 2, Aspect.CRYSTAL, 2, Aspect.MECHANISM, 2);
		registerBlock(ModBlocks.redGem, 2, Aspect.LIGHT, 2, Aspect.CRYSTAL, 2, Aspect.MECHANISM, 2);

		registerBlock(ModBlocks.petrifiedWoodPlanks, -1, Aspect.TREE, 2, Aspect.STONE, 2);
		registerBlock(ModBlocks.petrifiedWoodStairs, -1, Aspect.TREE, 2, Aspect.STONE, 2, Aspect.TRAVEL, 1);
		registerBlock(ModBlocks.petrifiedCraftingTable, -1, Aspect.TREE, 2, Aspect.STONE, 2, Aspect.CRAFT, 4);

		registerBlock(ModBlocks.umberPaver, 0, Aspect.EARTH, 1, Aspect.STONE, 2);
		registerBlock(ModBlocks.umberPaver, 1, Aspect.EARTH, 1, Aspect.STONE, 2, Aspect.PLANT, 2);
		registerBlock(ModBlocks.umberPaver, 2, Aspect.EARTH, 1, Aspect.STONE, 2, Aspect.BEAST, 1);
		registerBlock(ModBlocks.umberFurnace, -1, Aspect.MECHANISM, 3, Aspect.STONE, 2, Aspect.FIRE, 3);
		registerBlock(ModBlocks.fiddlehead, -1, Aspect.PLANT, 2);
		registerBlock(ModBlocks.insectRepellent, -1, Aspect.POISON, 1, Aspect.AURA, 2);
		registerBlock(ModBlocks.bambooTorch, -1, Aspect.LIGHT, 2, Aspect.PLANT, 1);
		registerBlock(ModBlocks.erebusAltar, -1, Aspect.MAGIC, 2, Aspect.ELDRITCH, 3, Aspect.STONE, 2);
		registerBlock(ModBlocks.erebusAltarLightning, -1, Aspect.MAGIC, 2, Aspect.ELDRITCH, 3, Aspect.STONE, 2, Aspect.ENERGY, 2);

		registerItem(ModItems.portalActivator, -1, Aspect.MAGIC, 2, Aspect.ELDRITCH, 3);

		registerItem(ModItems.exoskeletonHelmet, -1, Aspect.BEAST, 1, Aspect.ARMOR, 2);
		registerItem(ModItems.exoskeletonBody, -1, Aspect.BEAST, 1, Aspect.ARMOR, 5);
		registerItem(ModItems.exoskeletonLegs, -1, Aspect.BEAST, 1, Aspect.ARMOR, 4, Aspect.MAN, 1, Aspect.MOTION, 2);
		registerItem(ModItems.exoskeletonBoots, -1, Aspect.BEAST, 1, Aspect.ARMOR, 3, Aspect.TRAVEL, 2);

		registerItem(ModItems.jadeHelmet, -1, Aspect.GREED, 5, Aspect.CRYSTAL, 3, Aspect.ARMOR, 4);
		registerItem(ModItems.jadeBody, -1, Aspect.GREED, 8, Aspect.CRYSTAL, 4, Aspect.ARMOR, 6);
		registerItem(ModItems.jadeLegs, -1, Aspect.GREED, 7, Aspect.CRYSTAL, 4, Aspect.ARMOR, 5, Aspect.MOTION, 2);
		registerItem(ModItems.jadeBoots, -1, Aspect.GREED, 4, Aspect.CRYSTAL, 2, Aspect.ARMOR, 3, Aspect.TRAVEL, 3);

		registerItem(ModItems.jadeSword, -1, Aspect.GREED, 3, Aspect.WEAPON, 4, Aspect.CRYSTAL, 2);
		registerItem(ModItems.jadePickaxe, -1, Aspect.GREED, 3, Aspect.MINE, 4, Aspect.CRYSTAL, 2);
		registerItem(ModItems.jadeAxe, -1, Aspect.GREED, 3, Aspect.MINE, 2, Aspect.TREE, 1, Aspect.CRYSTAL, 2);
		registerItem(ModItems.jadeShovel, -1, Aspect.GREED, 2, Aspect.MINE, 3, Aspect.CRYSTAL, 2);
		registerItem(ModItems.jadePaxel, -1, Aspect.GREED, 7, Aspect.MINE, 5, Aspect.CRYSTAL, 3);
		registerItem(ModItems.jadeHoe, -1, Aspect.GREED, 2, Aspect.HARVEST, 3, Aspect.CRYSTAL, 2);

		registerItem(ModItems.fossilClub, -1, Aspect.DEATH, 2, Aspect.BEAST, 1);
		registerItem(ModItems.maxSpeedBow, -1, Aspect.WEAPON, 5, Aspect.DEATH, 2, Aspect.AIR, 3);
		registerItem(ModItems.waspSword, -1, Aspect.WEAPON, 4, Aspect.BEAST, 2, Aspect.POISON, 3);

		registerItem(ModItems.compoundGoggles, -1, Aspect.LIGHT, 2, Aspect.BEAST, 1, Aspect.DARKNESS, 2);
		registerItem(ModItems.jumpBoots, -1, Aspect.AIR, 2, Aspect.TRAVEL, 3, Aspect.BEAST, 1);
		registerItem(ModItems.sprintLeggings, -1, Aspect.TRAVEL, 5, Aspect.BEAST, 2, Aspect.ARMOR, 1);
		registerItem(ModItems.armorGlider, -1, Aspect.TRAVEL, 4, Aspect.ARMOR, 2, Aspect.AIR, 2, Aspect.FLIGHT, 3);

		registerItem(ModItems.erebusMaterials, 0, Aspect.BEAST, 2);
		registerItem(ModItems.erebusMaterials, 1, Aspect.GREED, 2, Aspect.CRYSTAL, 1);
		registerItem(ModItems.erebusMaterials, 2, Aspect.DEATH, 1, Aspect.BEAST, 1);
		registerItem(ModItems.erebusMaterials, 3, Aspect.PLANT, 2, Aspect.HARVEST, 1, Aspect.CRAFT, 1);
		registerItem(ModItems.erebusMaterials, 4, Aspect.BEAST, 2, Aspect.LIGHT, 2, Aspect.DARKNESS, 1);
		registerItem(ModItems.erebusMaterials, 5, Aspect.BEAST, 1, Aspect.LIGHT, 1);
		registerItem(ModItems.erebusMaterials, 6, Aspect.FLIGHT, 1, Aspect.TRAVEL, 1);
		registerItem(ModItems.erebusMaterials, 7, Aspect.STONE, 2, Aspect.TREE, 2);
		registerItem(ModItems.erebusMaterials, 8, Aspect.TRAVEL, 1, Aspect.BEAST, 2);
		registerItem(ModItems.erebusMaterials, 9, Aspect.BEAST, 3);
		registerItem(ModItems.erebusMaterials, 10, Aspect.BEAST, 3, Aspect.POISON, 2);
		registerItem(ModItems.erebusMaterials, 11, Aspect.LIFE, 1, Aspect.PLANT, 2, Aspect.HARVEST, 1);
		registerItem(ModItems.erebusMaterials, 12, Aspect.LIGHT, 2, Aspect.CRYSTAL, 1);
		registerItem(ModItems.erebusMaterials, 13, Aspect.LIGHT, 4, Aspect.BEAST, 2);
		registerItem(ModItems.erebusMaterials, 14, Aspect.TRAVEL, 6, Aspect.BEAST, 3);

		registerItem(ModItems.erebusFood, 0, Aspect.BEAST, 1);
		registerItem(ModItems.erebusFood, 1, Aspect.BEAST, 1, Aspect.FIRE, 1);
		registerItem(ModItems.erebusFood, 2, Aspect.TRAVEL, 1, Aspect.BEAST, 1);
		registerItem(ModItems.erebusFood, 3, Aspect.TRAVEL, 1, Aspect.BEAST, 1, Aspect.FIRE, 1);
		registerItem(ModItems.erebusFood, 4, Aspect.BEAST, 1, Aspect.POISON, 1);
		registerItem(ModItems.erebusFood, 5, Aspect.BEAST, 1, Aspect.POISON, 1, Aspect.FIRE, 1);
		registerItem(ModItems.erebusFood, 6, Aspect.PLANT, 2, Aspect.HUNGER, 1);
		registerItem(ModItems.erebusFood, 7, Aspect.HUNGER, 1, Aspect.LIFE, 2);
		registerItem(ModItems.erebusFood, 8, Aspect.HUNGER, 1, Aspect.LIFE, 3, Aspect.GREED, 2);

		registerItem(ModItems.turnip, -1, Aspect.HUNGER, 2, Aspect.LIFE, 2, Aspect.SEED, 1, Aspect.CROP, 1);
		registerItem(ModItems.bamBucket, 0, Aspect.VOID, 1, Aspect.PLANT, 2);
		registerItem(ModItems.bamBucket, 1, Aspect.VOID, 1, Aspect.PLANT, 2, Aspect.WATER, 2);
		registerItem(ModItems.sprayCan, -1, Aspect.POISON, 1, Aspect.AURA, 2);
		registerItem(ModItems.waspDagger, -1, Aspect.WEAPON, 2, Aspect.AIR, 1, Aspect.POISON, 2);
		registerItem(ModItems.wandOfAnimation, -1, Aspect.MAGIC, 10, Aspect.ELDRITCH, 8, Aspect.CRYSTAL, 2, Aspect.ENERGY, 4);

		registerEntity("Erebus.BeetleLarva", Aspect.BEAST, 1, Aspect.HUNGER, 1, Aspect.SLIME, 2);
		registerEntity("Erebus.Wasp", Aspect.AIR, 4, Aspect.POISON, 2);
		registerEntity("Erebus.Centipede", Aspect.TRAVEL, 3, Aspect.MOTION, 2, Aspect.POISON, 1);
		registerEntity("Erebus.Beetle", Aspect.BEAST, 1, Aspect.ARMOR, 2);
		registerEntity("Erebus.Fly", Aspect.AIR, 1, Aspect.FLIGHT, 1);
		registerEntity("Erebus.Mosquito", Aspect.AIR, 2, Aspect.DARKNESS, 1, Aspect.WATER, 1);
		registerEntity("Erebus.Tarantula", Aspect.BEAST, 2, Aspect.POISON, 3);
		registerEntity("Erebus.BotFly", Aspect.HUNGER, 3, Aspect.AIR, 2, Aspect.FLIGHT, 2);
		registerEntity("Erebus.BlackWidow", Aspect.POISON, 5, Aspect.DARKNESS, 3, Aspect.FIRE, 1);
		registerEntity("Erebus.Scorpion", Aspect.FIRE, 1, Aspect.POISON, 4, Aspect.WEAPON, 2);
		registerEntity("Erebus.Grasshopper", Aspect.TRAVEL, 3, Aspect.HUNGER, 1, Aspect.FLIGHT, 2);
		registerEntity("Erebus.Locust", Aspect.TRAVEL, 3, Aspect.BEAST, 2, Aspect.FLIGHT, 2);
		registerEntity("Erebus.Solifuge", Aspect.MOTION, 3, Aspect.BEAST, 2);
		registerEntity("Erebus.Moth", Aspect.FLIGHT, 3, Aspect.LIGHT, 1);
		registerEntity("Erebus.Firebrat", Aspect.FIRE, 2, Aspect.BEAST, 1);
		registerEntity("Erebus.Antlion", Aspect.TRAP, 3, Aspect.EARTH, 2);
	}

	private void registerBlock(Block block, int metadata, Object... aspects) {
		ThaumcraftApi.registerObjectTag(block.blockID, metadata, generateAspectList(aspects));
	}

	private void registerItem(Item item, int metadata, Object... aspects) {
		ThaumcraftApi.registerObjectTag(item.itemID, metadata, generateAspectList(aspects));
	}

	private void registerEntity(String entityName, Object... aspects) {
		ThaumcraftApi.registerEntityTag(entityName, generateAspectList(aspects));
	}

	private AspectList generateAspectList(Object... aspectData) {
		int index = 0;
		AspectList list = new AspectList();
		Aspect currentAspect = null;

		try {
			for (Object o : aspectData) {
				if ((index & 1) == 0)
					currentAspect = (Aspect) o;
				else
					list.add(currentAspect, (Integer) o);
				++index;
			}
		} catch (Exception e) {
		}

		return list;
	}
}
