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
	public static final DeferredHolder<SoundEvent, SoundEvent> WASP_LIVING = register("wasp_living");
	public static final DeferredHolder<SoundEvent, SoundEvent> WASP_HURT = register("wasp_hurt");
	

	//Blocks

	//Items

	//Misc 

	public static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
		return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(Erebus.prefix(name)));
	}
}
