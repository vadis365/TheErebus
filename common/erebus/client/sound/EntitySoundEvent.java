package erebus.client.sound;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class EntitySoundEvent {

	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		try {
			// Squish sound
			event.manager.addSound("erebus:squish.ogg");

			// Beetle Larva
			event.manager.addSound("erebus:beetlelarvasound.ogg");
			event.manager.addSound("erebus:beetlelarvahurt.ogg");
			event.manager.addSound("erebus:beetlelarvasplat.ogg");
			event.manager.addSound("erebus:beetlelarvamunch.ogg");

			// Black Widow
			event.manager.addSound("erebus:blackwidowsound.ogg");
			event.manager.addSound("erebus:blackwidowhurt.ogg");
			event.manager.addSound("erebus:webslingthrow.ogg");
			event.manager.addSound("erebus:webslingsplat.ogg");

			// Centipede
			event.manager.addSound("erebus:CentipedeSound.ogg");
			event.manager.addSound("erebus:CentipedeHurt.ogg");
			event.manager.addSound("erebus:TunnelingSound.ogg");
			event.manager.addSound("erebus:CentipedeWalk.ogg");

			// Fly
			event.manager.addSound("erebus:FlySound.ogg");
			event.manager.addSound("erebus:FlyHurt.ogg");

			// Mosquito
			event.manager.addSound("erebus:mosquito_sucking.ogg");
			event.manager.addSound("erebus:mosquito_flying.ogg");
			event.manager.addSound("erebus:mosquito_hit.ogg");
			event.manager.addSound("erebus:mosquito_death.ogg");

			// Wasp
			event.manager.addSound("erebus:WaspSound.ogg");
			event.manager.addSound("erebus:WaspHurt.ogg");

			// Grasshopper & Locust
			event.manager.addSound("erebus:grasshoppersound.ogg");
			event.manager.addSound("erebus:grasshopperhurt.ogg");
			event.manager.addSound("erebus:locustsound.ogg");
			event.manager.addSound("erebus:locusthurt.ogg");
			event.manager.addSound("erebus:locustspawn.ogg");

			// Glow worm
			event.manager.addSound("erebus:glowwormsound.ogg");
			event.manager.addSound("erebus:glowwormhurt.ogg");

			// Bombardier Beetle
			event.manager.addSound("erebus:BombardierBeetleSound.ogg");
			event.manager.addSound("erebus:BombardierBeetleHurt.ogg");

			// Praying Mantis
			event.manager.addSound("erebus:MantisSound.ogg");
			event.manager.addSound("erebus:MantisHurt.ogg");

			// Fire Ant
			event.manager.addSound("erebus:FireantSound.ogg");
			event.manager.addSound("erebus:FireantHurt.ogg");

			// Beetle
			event.manager.addSound("erebus:beetleSound.ogg");
			event.manager.addSound("erebus:beetleHurt.ogg");

			// Insect Repellent
			event.manager.addSound("erebus:SprayCanSound.ogg");

			// Altar sounds
			event.manager.addSound("erebus:altarchangestate.ogg");
			event.manager.addSound("erebus:altaroffering.ogg");

			// Horn of the Swarm
			event.manager.addSound("erebus:hornblow.ogg");

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Erebus had a problem loading its sounds. Please report it to the authors.");
		}
	}
}
