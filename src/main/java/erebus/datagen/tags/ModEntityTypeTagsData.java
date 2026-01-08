package erebus.datagen.tags;

import erebus.Erebus;
import erebus.registries.data.tags.ModEntityTypeTags;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagsData extends EntityTypeTagsProvider {
	
    public ModEntityTypeTagsData(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, Erebus.MODID);
    }

	@Override
	protected void addTags(@NotNull Provider provider) {
		tag(EntityTypeTags.ARTHROPOD)
			.add(ModEntities.BEETLE.get())
			.add(ModEntities.BEETLE_LARVA.get())
			.add(ModEntities.BOMBARDIER_BEETLE_LARVA.get())
			.add(ModEntities.BLACK_WIDOW.get())
			.add(ModEntities.BOT_FLY.get())
			.add(ModEntities.BOT_FLY_LARVA.get())
			.add(ModEntities.CENTIPEDE.get())
			.add(ModEntities.DRAGON_FLY.get())
			.add(ModEntities.FLY.get())
			.add(ModEntities.GRASSHOPPER.get())
			.add(ModEntities.LAVA_WEB_SPIDER.get())
			.add(ModEntities.LOCUST.get())
			.add(ModEntities.MONEY_SPIDER.get())
			.add(ModEntities.MOTH.get())
			.add(ModEntities.SCYTODES.get())
			.add(ModEntities.VELVET_WORM.get())
			.add(ModEntities.WASP.get())
			.add(ModEntities.WORKER_BEE.get())
			.add(ModEntities.BOMBARDIER_BEETLE.get());

		tag(ModEntityTypeTags.CAN_BE_PRESERVED)
				.add(EntityType.ARMADILLO)
				.add(EntityType.AXOLOTL)
				.add(EntityType.BAT)
				.add(EntityType.BEE)
				.add(EntityType.BLAZE)
				.add(EntityType.CAMEL)
				.add(EntityType.CAT)
				.add(EntityType.CAVE_SPIDER)
				.add(EntityType.CHICKEN)
				.add(EntityType.COD)
				.add(EntityType.COW)
				.add(EntityType.DOLPHIN)
				.add(EntityType.DONKEY)
				.add(EntityType.DROWNED)
				.add(EntityType.ENDERMAN)
				.add(EntityType.ENDERMITE)
				.add(EntityType.EVOKER)
				.add(EntityType.FOX)
				.add(EntityType.FROG)
				.add(EntityType.GHAST)
				.add(EntityType.GLOW_SQUID)
				.add(EntityType.GOAT)
				.add(EntityType.GUARDIAN)
				.add(EntityType.HOGLIN)
				.add(EntityType.HORSE)
				.add(EntityType.HUSK)
				.add(EntityType.ILLUSIONER)
				.add(EntityType.IRON_GOLEM)
				.add(EntityType.LLAMA)
				.add(EntityType.MAGMA_CUBE)
				.add(EntityType.MOOSHROOM)
				.add(EntityType.MULE)
				.add(EntityType.OCELOT)
				.add(EntityType.PAINTING)
				.add(EntityType.PANDA)
				.add(EntityType.PARROT)
				.add(EntityType.PHANTOM)
				.add(EntityType.PIG)
				.add(EntityType.PIGLIN)
				.add(EntityType.PIGLIN_BRUTE)
				.add(EntityType.PILLAGER)
				.add(EntityType.POLAR_BEAR)
				.add(EntityType.PUFFERFISH)
				.add(EntityType.RABBIT)
				.add(EntityType.RAVAGER)
				.add(EntityType.SALMON)
				.add(EntityType.SHEEP)
				.add(EntityType.SILVERFISH)
				.add(EntityType.SKELETON)
				.add(EntityType.SKELETON_HORSE)
				.add(EntityType.SLIME)
				.add(EntityType.SNIFFER)
				.add(EntityType.SNOW_GOLEM)
				.add(EntityType.SPIDER)
				.add(EntityType.SQUID)
				.add(EntityType.STRAY)
				.add(EntityType.TADPOLE)
				.add(EntityType.TRADER_LLAMA)
				.add(EntityType.TROPICAL_FISH)
				.add(EntityType.TURTLE)
				.add(EntityType.VILLAGER)
				.add(EntityType.VINDICATOR)
				.add(EntityType.WANDERING_TRADER)
				.add(EntityType.WARDEN)
				.add(EntityType.WITCH)
				.add(EntityType.WITHER)
				.add(EntityType.WITHER_SKELETON)
				.add(EntityType.WOLF)
				.add(EntityType.ZOGLIN)
				.add(EntityType.ZOMBIE)
				.add(EntityType.ZOMBIE_HORSE)
				.add(EntityType.ZOMBIE_VILLAGER)
				.add(EntityType.ZOMBIFIED_PIGLIN)
				.add(ModEntities.BEETLE.get())
				.add(ModEntities.BEETLE_LARVA.get())
				.add(ModEntities.BOMBARDIER_BEETLE_LARVA.get())
				.add(ModEntities.BLACK_WIDOW.get())
				.add(ModEntities.BOT_FLY.get())
				.add(ModEntities.BOT_FLY_LARVA.get())
				.add(ModEntities.CENTIPEDE.get())
				.add(ModEntities.DRAGON_FLY.get())
				.add(ModEntities.FLY.get())
				.add(ModEntities.GRASSHOPPER.get())
				.add(ModEntities.LAVA_WEB_SPIDER.get())
				.add(ModEntities.LOCUST.get())
				.add(ModEntities.MONEY_SPIDER.get())
				.add(ModEntities.MOTH.get())
				.add(ModEntities.SCYTODES.get())
				.add(ModEntities.VELVET_WORM.get())
				.add(ModEntities.WASP.get())
				.add(ModEntities.WORKER_BEE.get())
				.add(ModEntities.BOMBARDIER_BEETLE.get());
	}

}
