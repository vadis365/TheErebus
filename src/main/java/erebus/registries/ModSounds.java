package erebus.registries;

import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, Erebus.MODID);

	//Entities
	public static final DeferredHolder<SoundEvent, SoundEvent> SQUISH = register("squish");
	public static final DeferredHolder<SoundEvent, SoundEvent> WASP_SOUND = register("wasp_sound");
	public static final DeferredHolder<SoundEvent, SoundEvent> WASP_HURT = register("wasp_hurt");
	// the rest
	public static final DeferredHolder<SoundEvent, SoundEvent> BEETLE_HURT = register("beetle_hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> BEETLE_SOUND = register("beetle_sound");
	public static final DeferredHolder<SoundEvent, SoundEvent> BEETLE_LARVA_HURT = register("beetle_larva_hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> BEETLE_LARVA_MUNCH = register("beetle_larva_munch");
	public static final DeferredHolder<SoundEvent, SoundEvent> BEETLE_LARVA_SOUND = register("beetle_larva_sound");
	public static final DeferredHolder<SoundEvent, SoundEvent> BEETLE_LARVA_SPLAT = register("beetle_larva_splat");
	public static final DeferredHolder<SoundEvent, SoundEvent> BLACK_WIDOW_HURT = register("black_widow_hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> BLACK_WIDOW_SOUND = register("black_widow_sound");
	public static final DeferredHolder<SoundEvent, SoundEvent> BOMBARDIER_BEETLE_HURT = register("bombardier_beetle_hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> BOMBARDIER_BEETLE_SOUND = register("bombardier_beetle_sound");
	public static final DeferredHolder<SoundEvent, SoundEvent> CENTIPEDE_HURT = register("centipede_hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> CENTIPEDE_SOUND = register("centipede_sound");
	public static final DeferredHolder<SoundEvent, SoundEvent> CENTIPEDE_WALK = register("centipede_walk");
	public static final DeferredHolder<SoundEvent, SoundEvent> ANT_HURT = register("ant_hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> ANT_SOUND = register("ant_sound");
	public static final DeferredHolder<SoundEvent, SoundEvent> FLY_HURT = register("fly_hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> FLY_SOUND = register("fly_sound");
	public static final DeferredHolder<SoundEvent, SoundEvent> GLOW_WORM_HURT = register("glow_worm_hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> GLOW_WORM_SOUND = register("glow_worm_sound");
	public static final DeferredHolder<SoundEvent, SoundEvent> GRASSHOPPER_HURT = register("grasshopper_hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> GRASSHOPPER_SOUND = register("grasshopper_sound");
	public static final DeferredHolder<SoundEvent, SoundEvent> LOCUST_HURT = register("locust_hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> LOCUST_SOUND = register("locust_sound");
	public static final DeferredHolder<SoundEvent, SoundEvent> LOCUST_SPAWN = register("locust_spawn");
	public static final DeferredHolder<SoundEvent, SoundEvent> MANTIS_HURT = register("mantis_hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> MANTIS_SOUND = register("mantis_sound");
	public static final DeferredHolder<SoundEvent, SoundEvent> MOSQUITO_DEATH = register("mosquito_death");
	public static final DeferredHolder<SoundEvent, SoundEvent> MOSQUITO_FLYING = register("mosquito_flying");
	public static final DeferredHolder<SoundEvent, SoundEvent> MOSQUITO_HIT = register("mosquito_hit");
	public static final DeferredHolder<SoundEvent, SoundEvent> MOSQUITO_SUCKING = register("mosquito_sucking");
	public static final DeferredHolder<SoundEvent, SoundEvent> SLIME_CHARGE = register("slime_charge");
	public static final DeferredHolder<SoundEvent, SoundEvent> SNAIL_DEATH = register("snail_death");
	public static final DeferredHolder<SoundEvent, SoundEvent> SNAIL_HURT = register("snail_hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> SNAIL_LIVING = register("snail_living");
	public static final DeferredHolder<SoundEvent, SoundEvent> SPIDER_SCREECH = register("spider_screech");
	public static final DeferredHolder<SoundEvent, SoundEvent> CRUSHLING_DEATH = register("crushling_death");
	public static final DeferredHolder<SoundEvent, SoundEvent> CRUSHLING_HURT = register("crushling_hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> CRUSHLING_LIVING = register("crushling_living");
	public static final DeferredHolder<SoundEvent, SoundEvent> TUNNELING_SOUND = register("tunneling_sound");
	public static final DeferredHolder<SoundEvent, SoundEvent> ANTLION_EXPLODE = register("antlion_explode");
	public static final DeferredHolder<SoundEvent, SoundEvent> ANTLION_GROWL = register("antlion_growl");
	public static final DeferredHolder<SoundEvent, SoundEvent> ANTLION_SLAM = register("antlion_slam");
	public static final DeferredHolder<SoundEvent, SoundEvent> MAGMACRAWLER = register("magmacrawler");
	public static final DeferredHolder<SoundEvent, SoundEvent> MAGMACRAWLER_HURT = register("magmacrawler_hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> MAGMACRAWLER_DEATH = register("magmacrawler_death");
	public static final DeferredHolder<SoundEvent, SoundEvent> RHINO_BEETLE_SOUND = register("rhino_beetle_sound");
	public static final DeferredHolder<SoundEvent, SoundEvent> RHINO_BEETLE_HURT = register("rhino_beetle_hurt");

	//Blocks
	public static final DeferredHolder<SoundEvent, SoundEvent> ALTAR_CHANGE_STATE = register("altar_change_state");
	public static final DeferredHolder<SoundEvent, SoundEvent> ALTAR_OFFERING = register("altar_offering");

	//Items
	public static final DeferredHolder<SoundEvent, SoundEvent> SPRAY_CAN_SOUND = register("spray_can_sound");
	public static final DeferredHolder<SoundEvent, SoundEvent> WEBSLING_SPLAT = register("websling_splat");
	public static final DeferredHolder<SoundEvent, SoundEvent> WEBSLING_THROW = register("websling_throw");
	public static final DeferredHolder<SoundEvent, SoundEvent> CABBAGE_FART = register("cabbage_fart");
	public static final DeferredHolder<SoundEvent, SoundEvent> HORN_BLOW = register("horn_blow");

	//Misc 
	public static final DeferredHolder<SoundEvent, SoundEvent> BLAM_SOUND = register("blam_sound");

	//World
	public static final DeferredHolder<SoundEvent, SoundEvent> AMBIENT_BUG_IN_THE_SYSTEM = register("music/ambient_bug_in_the_system");
	public static final DeferredHolder<SoundEvent, SoundEvent> AMBIENT_FEINT_SLEEPLESS = register("music/ambient_feint_sleepless");

	public static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
		return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(Erebus.prefix(name)));
	}
}
