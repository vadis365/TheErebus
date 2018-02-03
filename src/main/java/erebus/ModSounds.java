package erebus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import erebus.lib.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModSounds {
	public static final List<SoundEvent> SOUNDS = new ArrayList<SoundEvent>();
	public static final SoundEvent ALTAR_CHANGE_STATE = registerSoundResource("altar_change_state");
	public static final SoundEvent ALTAR_OFFERING = registerSoundResource("altar_offering");
	public static final SoundEvent SQUISH = registerSoundResource("squish");
	public static final SoundEvent BEETLE_HURT = registerSoundResource("beetle_hurt");
	public static final SoundEvent BEETLE_SOUND = registerSoundResource("beetle_sound");
	public static final SoundEvent BEETLE_LARVA_HURT = registerSoundResource("beetle_larva_hurt");
	public static final SoundEvent BEETLE_LARVA_MUNCH = registerSoundResource("beetle_larva_munch");
	public static final SoundEvent BEETLE_LARVA_SOUND = registerSoundResource("beetle_larva_sound");
	public static final SoundEvent BEETLE_LARVA_SPLAT = registerSoundResource("beetle_larva_splat");
	public static final SoundEvent BLACK_WIDOW_HURT = registerSoundResource("black_widow_hurt");
	public static final SoundEvent BLACK_WIDOW_SOUND = registerSoundResource("black_widow_sound");
	public static final SoundEvent BLAM_SOUND = registerSoundResource("blam_sound");
	public static final SoundEvent BOMBARDIER_BEETLE_HURT = registerSoundResource("bombardier_beetle_hurt");
	public static final SoundEvent BOMBARDIER_BEETLE_SOUND = registerSoundResource("bombardier_beetle_sound");
	public static final SoundEvent CENTIPEDE_HURT = registerSoundResource("centipede_hurt");
	public static final SoundEvent CENTIPEDE_SOUND = registerSoundResource("centipede_sound");
	public static final SoundEvent CENTIPEDE_WALK = registerSoundResource("centipede_walk");
	public static final SoundEvent FIRE_ANT_HURT = registerSoundResource("fire_ant_hurt");
	public static final SoundEvent FIRE_ANT_SOUND = registerSoundResource("fire_ant_sound");
	public static final SoundEvent FLY_HURT = registerSoundResource("fly_hurt");
	public static final SoundEvent FLY_SOUND = registerSoundResource("fly_sound");
	public static final SoundEvent GLOW_WORM_HURT = registerSoundResource("glow_worm_hurt");
	public static final SoundEvent GLOW_WORM_SOUND = registerSoundResource("glow_worm_sound");
	public static final SoundEvent GRASSHOPPER_HURT = registerSoundResource("grasshopper_hurt");
	public static final SoundEvent GRASSHOPPER_SOUND = registerSoundResource("grasshopper_sound");
	public static final SoundEvent HORN_BLOW = registerSoundResource("horn_blow");
	public static final SoundEvent LOCUST_HURT = registerSoundResource("locust_hurt");
	public static final SoundEvent LOCUST_SOUND = registerSoundResource("locust_sound");
	public static final SoundEvent LOCUST_SPAWN = registerSoundResource("locust_spawn");
	public static final SoundEvent MANTIS_HURT = registerSoundResource("mantis_hurt");
	public static final SoundEvent MANTIS_SOUND = registerSoundResource("mantis_sound");
	public static final SoundEvent MOSQUITO_DEATH = registerSoundResource("mosquito_death");
	public static final SoundEvent MOSQUITO_FLYING = registerSoundResource("mosquito_flying");
	public static final SoundEvent MOSQUITO_HIT = registerSoundResource("mosquito_hit");
	public static final SoundEvent MOSQUITO_SUCKING = registerSoundResource("mosquito_sucking");
	public static final SoundEvent SLIME_CHARGE = registerSoundResource("slime_charge");
	public static final SoundEvent SNAIL_DEATH = registerSoundResource("snail_death");
	public static final SoundEvent SNAIL_HURT = registerSoundResource("snail_hurt");
	public static final SoundEvent SNAIL_LIVING = registerSoundResource("snail_living");
	public static final SoundEvent SPIDER_SCREECH = registerSoundResource("spider_screech");
	public static final SoundEvent CRUSHLING_DEATH = registerSoundResource("crushling_death");
	public static final SoundEvent CRUSHLING_HURT = registerSoundResource("crushling_hurt");
	public static final SoundEvent CRUSHLING_LIVING = registerSoundResource("crushling_living");
	public static final SoundEvent SPRAY_CAN_SOUND = registerSoundResource("spray_can_sound");
	public static final SoundEvent TUNNELING_SOUND = registerSoundResource("tunneling_sound");
	public static final SoundEvent WASP_HURT = registerSoundResource("wasp_hurt");
	public static final SoundEvent WASP_SOUND = registerSoundResource("wasp_sound");
	public static final SoundEvent WEBSLING_SPLAT = registerSoundResource("websling_splat");
	public static final SoundEvent WEBSLING_THROW = registerSoundResource("websling_throw");
	public static final SoundEvent CABBAGE_FART = registerSoundResource("cabbage_fart");
	public static final SoundEvent ANTLION_EXPLODE = registerSoundResource("antlion_explode");
	public static final SoundEvent ANTLION_GROWL = registerSoundResource("antlion_growl");
	public static final SoundEvent ANTLION_SLAM = registerSoundResource("antlion_slam");
	public static final SoundEvent MAGMACRAWLER = registerSoundResource("magmacrawler");
	public static final SoundEvent MAGMACRAWLER_HURT = registerSoundResource("magmacrawler_hurt");
	public static final SoundEvent MAGMACRAWLER_DEATH = registerSoundResource("magmacrawler_death");
	public static final SoundEvent RHINO_BEETLE_SOUND = registerSoundResource("rhino_beetle_sound");
	public static final SoundEvent RHINO_BEETLE_HURT = registerSoundResource("rhino_beetle_hurt");
	
	//Music
	public static final SoundEvent MUSIC_EREBUS_DIMENSION = registerSoundResource("music_erebus_dimension");

	public static SoundEvent registerSoundResource(String name) {
		return new SoundEvent(new ResourceLocation(Reference.MOD_ID, name));
	}

	public static void init() {
		try {
			for (Field field : ModSounds.class.getDeclaredFields()) {
				Object obj = field.get(null);
				if (obj instanceof SoundEvent) {
					SoundEvent sound = (SoundEvent) obj;
					String name = field.getName().toLowerCase(Locale.ENGLISH);
					registerSoundName(name, sound);
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static void registerSoundName(String name, SoundEvent sound) {
		SOUNDS.add(sound);
		sound.setRegistryName(Reference.MOD_ID, name);
	}

	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	public static class RegistrationHandlerSounds {
		@SubscribeEvent
		public static void registerSoundEvents(final RegistryEvent.Register<SoundEvent> event) {
			ModSounds.init();
			final IForgeRegistry<SoundEvent> registry = event.getRegistry();
			for (SoundEvent sounds : SOUNDS)
				registry.register(sounds);
		}
	}

}
