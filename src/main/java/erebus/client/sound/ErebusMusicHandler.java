package erebus.client.sound;

import java.lang.reflect.Field;
import java.util.Random;

import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

// Props to SanAndeasP - he wrote this
public class ErebusMusicHandler {
	private MusicTicker prevMusicTicker;
	private Field mcMusicTickerField;

	@SubscribeEvent
	public void onClientTick(ClientTickEvent event) {
		// TODO: check SRG field name when updating MC!
		if (mcMusicTickerField == null) {
			mcMusicTickerField = ReflectionHelper.findField(Minecraft.class, "mcMusicTicker", "field_147126_aw");
		}

		if (event.phase == Phase.START) {
			Minecraft mc = Minecraft.getMinecraft();
			if (mc.world != null) {
				if (mc.world.provider.getDimension() == ConfigHandler.INSTANCE.erebusDimensionID && prevMusicTicker == null) {
					prevMusicTicker = getMcMusicTicker();
					setMcMusicTicker(new ErebusMusicTicker(mc));
				} else if (mc.world.provider.getDimension() != ConfigHandler.INSTANCE.erebusDimensionID && prevMusicTicker != null) {
					setMcMusicTicker(prevMusicTicker);
					prevMusicTicker = null;
				}
			} else if (prevMusicTicker != null) {
				setMcMusicTicker(prevMusicTicker);
				prevMusicTicker = null;
			}
		}
	}

	private void setMcMusicTicker(MusicTicker musicTicker) {
		try {
			mcMusicTickerField.set(Minecraft.getMinecraft(), musicTicker);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private MusicTicker getMcMusicTicker() {
		try {
			return (MusicTicker) mcMusicTickerField.get(Minecraft.getMinecraft());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static class ErebusMusicTicker extends MusicTicker {
		private final Random RNG = new Random();
		private final Minecraft mc;
		private ISound currentSound;
		private int timeUntilMusic = 100;
		private static final int MIN_WAIT = 6000;
		private static final int MAX_WAIT = 12000;

		public ErebusMusicTicker(Minecraft minecraft) {
			super(minecraft);
			mc = minecraft;
		}

		public void update() {
			if (currentSound != null) {
				if (!ModSounds.MUSIC_EREBUS_DIMENSION.getSoundName().equals(currentSound.getSoundLocation())) {
					mc.getSoundHandler().stopSound(currentSound);
					timeUntilMusic = MathHelper.getInt(RNG, 0, MIN_WAIT / 2);
				}

				if (!mc.getSoundHandler().isSoundPlaying(currentSound)) {
					currentSound = null;
					timeUntilMusic = Math.min(MathHelper.getInt(RNG, MIN_WAIT, MAX_WAIT), timeUntilMusic);
				}
			}

			if (currentSound == null && timeUntilMusic-- <= 0) {
				currentSound = PositionedSoundRecord.getMusicRecord(ModSounds.MUSIC_EREBUS_DIMENSION);
				mc.getSoundHandler().playSound(currentSound);
				timeUntilMusic = Integer.MAX_VALUE;
			}
		}
	}
}
