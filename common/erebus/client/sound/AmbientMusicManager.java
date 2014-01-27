package erebus.client.sound;

import java.net.URL;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.handler.ConfigurationHandler;
import erebus.lib.Reference;

@SideOnly(Side.CLIENT)
public class AmbientMusicManager implements IScheduledTickHandler {

	private static AmbientMusicManager instance;

	public static AmbientMusicManager getInstance() {
		return instance;
	}

	public static void register() {
		instance = new AmbientMusicManager();

		MinecraftForge.EVENT_BUS.register(instance);
		TickRegistry.registerScheduledTickHandler(instance, Side.CLIENT);
	}

	private SoundManager sndMan;
	private final Random rand = new Random();
	private final Map<String, URL> poolAmbient = new HashMap<String, URL>();

	private AmbientMusicManager() {
	}

	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		String[] ambientMusicList = new String[] { "bugInTheSystem.ogg", "feint_sleepless.ogg" };

		instance.sndMan = event.manager;

		URL track;

		for (String ambient : ambientMusicList) {
			track = getClass().getResource("/assets/erebus/music/ambient_" + ambient);
			if (track == null)
				continue;

			poolAmbient.put("erebus:" + ambient, track);
		}
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if (ConfigurationHandler.playCustomSongs)
			if (!sndMan.sndSystem.playing("BgMusic") && rand.nextInt(15) == 0 && ((EntityPlayer) tickData[0]).dimension == ConfigurationHandler.erebusDimensionID) {
				List<Entry<String, URL>> entries = new ArrayList<Entry<String, URL>>(poolAmbient.entrySet());
				if (entries.size() == 0)
					return;

				play(entries.get(rand.nextInt(entries.size())));
			}
	}

	public Entry<String, URL> getEntry(String name) {
		name = "erebus:" + name;

		for (Entry<String, URL> entry : poolAmbient.entrySet())
			if (entry.getKey().equals(name))
				return entry;

		return null;
	}

	public void play(Entry<String, URL> entry) {
		if (ConfigurationHandler.playCustomSongs) {
			sndMan.sndSystem.backgroundMusic("BgMusic", entry.getValue(), entry.getKey(), false);
			sndMan.sndSystem.setVolume("BgMusic", Minecraft.getMinecraft().gameSettings.musicVolume);
			sndMan.sndSystem.play("BgMusic");
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		return Reference.MOD_NAME + ": " + getClass().getSimpleName();
	}

	@Override
	public int nextTickSpacing() {
		return 200;
	}
}