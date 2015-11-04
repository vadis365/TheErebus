package erebus.integration;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.integration.ModIntegrationHandler.IModIntegration;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class ThaumcraftIntegration implements IModIntegration {
	@Override
	public String getModId() {
		return "Thaumcraft";
	}

	@Override
	public void onInit() {
	}

	@Override
	public void onPostInit() {
		registerBlock(ModBlocks.amber, 0, Aspect.ENTROPY, 2, Aspect.CRYSTAL, 2, Aspect.SLIME, 1);
		registerBlock(ModBlocks.amber, 1, Aspect.ENTROPY, 2, Aspect.CRYSTAL, 5);
		registerBlock(ModBlocks.amber, 2, Aspect.ENTROPY, 2, Aspect.CRYSTAL, 2, Aspect.SLIME, 1);

		// planks, leaves
		registerBlock(ModBlocks.blockSilk, -1, Aspect.CLOTH, 4);
		registerBlock(ModBlocks.mirBrick, -1, Aspect.ENTROPY, 2, Aspect.MIND, 1);
		registerBlock(ModBlocks.spiderSpawner, -1, Aspect.BEAST, 5, Aspect.TAINT, 2, Aspect.WEAPON, 2, Aspect.SOUL, 3, Aspect.POISON, 1);
		registerBlock(ModBlocks.jumpingSpiderSpawner, -1, Aspect.BEAST, 5, Aspect.TAINT, 2, Aspect.WEAPON, 2, Aspect.SOUL, 3, Aspect.POISON, 1);
		registerBlock(ModBlocks.thorns, -1, Aspect.PLANT, 2, Aspect.WEAPON, 1);
		registerBlock(ModBlocks.fern, -1, Aspect.PLANT, 2, Aspect.PLANT, 2);
		// tall grass
		registerBlock(ModBlocks.quickSand, -1, Aspect.TRAP, 4, Aspect.DEATH, 2, Aspect.EARTH, 2);
		registerBlock(ModBlocks.ghostSand, -1, Aspect.TRAP, 4, Aspect.VOID, 3);
		registerBlock(ModBlocks.hollowLogAcacia, -1, Aspect.SLIME, 1, Aspect.TREE, 3);

		registerBlock(ModBlocks.oreJade, 0, Aspect.GREED, 4, Aspect.CRYSTAL, 3, Aspect.EARTH, 2);
		registerBlock(ModBlocks.orePetrifiedWood, 0, Aspect.EARTH, 1, Aspect.TREE, 2, Aspect.ENTROPY, 2);
		registerBlock(ModBlocks.oreFossil, -1, Aspect.DEATH, 1, Aspect.BEAST, 1, Aspect.EARTH, 1);
		registerBlock(ModBlocks.umberstone, 0, Aspect.EARTH, 2, Aspect.ENTROPY, 2);
		registerBlock(ModBlocks.umberstone, 1, Aspect.EARTH, 1, Aspect.ENTROPY, 2);
		registerBlock(ModBlocks.umberstone, 2, Aspect.EARTH, 1, Aspect.ENTROPY, 2, Aspect.PLANT, 2);
		registerBlock(ModBlocks.umberstone, 3, Aspect.EARTH, 1, Aspect.ENTROPY, 2, Aspect.BEAST, 1);
		registerBlock(ModBlocks.umberstone, 4, Aspect.ENTROPY, 2);

		registerBlock(ModBlocks.bambooCrop, -1, Aspect.PLANT, 2, Aspect.CROP, 2, Aspect.CRAFT, 1);
		registerBlock(ModBlocks.bambooCrate, -1, Aspect.PLANT, 4, Aspect.VOID, 3, Aspect.CRAFT, 2);
		registerBlock(ModBlocks.redGem, 0, Aspect.LIGHT, 2, Aspect.CRYSTAL, 3, Aspect.ENERGY, 2);
		registerBlock(ModBlocks.redGem, 1, Aspect.LIGHT, 2, Aspect.CRYSTAL, 2, Aspect.MECHANISM, 2);
		registerBlock(ModBlocks.redGem, 2, Aspect.LIGHT, 2, Aspect.CRYSTAL, 2, Aspect.MECHANISM, 2);

		registerBlock(ModBlocks.petrifiedWoodPlanks, -1, Aspect.TREE, 2, Aspect.ENTROPY, 2);
		registerBlock(ModBlocks.petrifiedWoodStairs, -1, Aspect.TREE, 2, Aspect.ENTROPY, 2, Aspect.TRAVEL, 1);
		registerBlock(ModBlocks.petrifiedCraftingTable, -1, Aspect.TREE, 2, Aspect.ENTROPY, 2, Aspect.CRAFT, 4);

		registerBlock(ModBlocks.umberPaver, 0, Aspect.EARTH, 1, Aspect.ENTROPY, 2);
		registerBlock(ModBlocks.umberPaver, 1, Aspect.EARTH, 1, Aspect.ENTROPY, 2, Aspect.PLANT, 2);
		registerBlock(ModBlocks.umberPaver, 2, Aspect.EARTH, 1, Aspect.ENTROPY, 2, Aspect.BEAST, 1);
		registerBlock(ModBlocks.umberFurnace, -1, Aspect.MECHANISM, 3, Aspect.ENTROPY, 2, Aspect.FIRE, 3);
		registerBlock(ModBlocks.fiddlehead, -1, Aspect.PLANT, 2);
		registerBlock(ModBlocks.insectRepellent, -1, Aspect.POISON, 1, Aspect.AURA, 2);
		registerBlock(ModBlocks.bambooTorch, -1, Aspect.LIGHT, 2, Aspect.PLANT, 1);
		registerBlock(ModBlocks.altarBase, -1, Aspect.MAGIC, 2, Aspect.ELDRITCH, 3, Aspect.ENTROPY, 2);
		registerBlock(ModBlocks.altarLightning, -1, Aspect.MAGIC, 2, Aspect.ELDRITCH, 3, Aspect.ENTROPY, 2, Aspect.ENERGY, 2);

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

		registerItem(ModItems.cavemanClub, -1, Aspect.DEATH, 2, Aspect.BEAST, 1);
		registerItem(ModItems.maxSpeedBow, -1, Aspect.WEAPON, 5, Aspect.DEATH, 2, Aspect.AIR, 3);
		registerItem(ModItems.waspSword, -1, Aspect.WEAPON, 4, Aspect.BEAST, 2, Aspect.POISON, 3);

		registerItem(ModItems.compoundGoggles, -1, Aspect.LIGHT, 2, Aspect.BEAST, 1, Aspect.DARKNESS, 2);
		registerItem(ModItems.jumpBoots, -1, Aspect.AIR, 2, Aspect.TRAVEL, 3, Aspect.BEAST, 1);
		registerItem(ModItems.sprintLeggings, -1, Aspect.TRAVEL, 5, Aspect.BEAST, 2, Aspect.ARMOR, 1);
		registerItem(ModItems.armorGlider, -1, Aspect.TRAVEL, 4, Aspect.ARMOR, 2, Aspect.AIR, 2, Aspect.FLIGHT, 3);

		registerItem(ModItems.materials, 0, Aspect.BEAST, 2);
		registerItem(ModItems.materials, 1, Aspect.GREED, 2, Aspect.CRYSTAL, 1);
		registerItem(ModItems.materials, 2, Aspect.DEATH, 1, Aspect.BEAST, 1);
		registerItem(ModItems.materials, 3, Aspect.PLANT, 2, Aspect.HARVEST, 1, Aspect.CRAFT, 1);
		registerItem(ModItems.materials, 4, Aspect.BEAST, 2, Aspect.LIGHT, 2, Aspect.DARKNESS, 1);
		registerItem(ModItems.materials, 5, Aspect.BEAST, 1, Aspect.LIGHT, 1);
		registerItem(ModItems.materials, 6, Aspect.FLIGHT, 1, Aspect.TRAVEL, 1);
		registerItem(ModItems.materials, 7, Aspect.ENTROPY, 2, Aspect.TREE, 2);
		registerItem(ModItems.materials, 8, Aspect.TRAVEL, 1, Aspect.BEAST, 2);
		registerItem(ModItems.materials, 9, Aspect.BEAST, 3);
		registerItem(ModItems.materials, 10, Aspect.BEAST, 3, Aspect.POISON, 2);
		registerItem(ModItems.materials, 11, Aspect.LIFE, 1, Aspect.PLANT, 2, Aspect.HARVEST, 1);
		registerItem(ModItems.materials, 12, Aspect.LIGHT, 2, Aspect.CRYSTAL, 1);
		registerItem(ModItems.materials, 13, Aspect.LIGHT, 4, Aspect.BEAST, 2);
		registerItem(ModItems.materials, 14, Aspect.TRAVEL, 6, Aspect.BEAST, 3);

		registerItem(ModItems.food, 0, Aspect.BEAST, 1);
		registerItem(ModItems.food, 1, Aspect.BEAST, 1, Aspect.FIRE, 1);
		registerItem(ModItems.food, 2, Aspect.TRAVEL, 1, Aspect.BEAST, 1);
		registerItem(ModItems.food, 3, Aspect.TRAVEL, 1, Aspect.BEAST, 1, Aspect.FIRE, 1);
		registerItem(ModItems.food, 4, Aspect.BEAST, 1, Aspect.POISON, 1);
		registerItem(ModItems.food, 5, Aspect.BEAST, 1, Aspect.POISON, 1, Aspect.FIRE, 1);
		registerItem(ModItems.food, 6, Aspect.PLANT, 2, Aspect.HUNGER, 1);
		registerItem(ModItems.food, 7, Aspect.HUNGER, 1, Aspect.LIFE, 2);
		registerItem(ModItems.food, 8, Aspect.HUNGER, 1, Aspect.LIFE, 3, Aspect.GREED, 2);

		registerItem(ModItems.turnip, -1, Aspect.HUNGER, 2, Aspect.LIFE, 2, Aspect.PLANT, 1, Aspect.CROP, 1);
		registerItem(ModItems.bambucket, 0, Aspect.VOID, 1, Aspect.PLANT, 2);
		registerItem(ModItems.bambucket, 1, Aspect.VOID, 1, Aspect.PLANT, 2, Aspect.WATER, 2);
		registerItem(ModItems.sprayCan, -1, Aspect.POISON, 1, Aspect.AURA, 2);
		registerItem(ModItems.waspDagger, -1, Aspect.WEAPON, 2, Aspect.AIR, 1, Aspect.POISON, 2);
		registerItem(ModItems.wandOfAnimation, -1, Aspect.MAGIC, 10, Aspect.ELDRITCH, 8, Aspect.CRYSTAL, 2, Aspect.ENERGY, 4);

		registerEntity("erebus.BeetleLarva", Aspect.BEAST, 1, Aspect.HUNGER, 1, Aspect.SLIME, 2);
		registerEntity("erebus.Wasp", Aspect.AIR, 4, Aspect.POISON, 2);
		registerEntity("erebus.Centipede", Aspect.TRAVEL, 3, Aspect.MOTION, 2, Aspect.POISON, 1);
		registerEntity("erebus.Beetle", Aspect.BEAST, 1, Aspect.ARMOR, 2);
		registerEntity("erebus.Fly", Aspect.AIR, 1, Aspect.FLIGHT, 1);
		registerEntity("erebus.Mosquito", Aspect.AIR, 2, Aspect.DARKNESS, 1, Aspect.WATER, 1);
		registerEntity("erebus.Tarantula", Aspect.BEAST, 2, Aspect.POISON, 3);
		registerEntity("erebus.BotFly", Aspect.HUNGER, 3, Aspect.AIR, 2, Aspect.FLIGHT, 2);
		registerEntity("erebus.BlackWidow", Aspect.POISON, 5, Aspect.DARKNESS, 3, Aspect.FIRE, 1);
		registerEntity("erebus.Scorpion", Aspect.FIRE, 1, Aspect.POISON, 4, Aspect.WEAPON, 2);
		registerEntity("erebus.Grasshopper", Aspect.TRAVEL, 3, Aspect.HUNGER, 1, Aspect.FLIGHT, 2);
		registerEntity("erebus.Locust", Aspect.TRAVEL, 3, Aspect.BEAST, 2, Aspect.FLIGHT, 2);
		registerEntity("erebus.Solifuge", Aspect.MOTION, 3, Aspect.BEAST, 2);
		registerEntity("erebus.Moth", Aspect.FLIGHT, 3, Aspect.LIGHT, 1);
		registerEntity("erebus.Firebrat", Aspect.FIRE, 2, Aspect.BEAST, 1);
		registerEntity("erebus.Antlion", Aspect.TRAP, 3, Aspect.EARTH, 2);
	}

	private void registerBlock(Block block, int metadata, Object... aspects) {
		if (metadata < 0)
			metadata = OreDictionary.WILDCARD_VALUE;
		ThaumcraftApi.registerObjectTag(new ItemStack(block, metadata), generateAspectList(aspects));
	}

	private void registerItem(Item item, int metadata, Object... aspects) {
		if (metadata < 0)
			metadata = OreDictionary.WILDCARD_VALUE;
		ThaumcraftApi.registerObjectTag(new ItemStack(item, metadata), generateAspectList(aspects));
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