package erebus.client.sound;

import java.lang.reflect.Field;
import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.ReflectionHelper;
import erebus.core.handler.configs.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

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
			if (mc.theWorld != null) {
				if (mc.theWorld.provider.dimensionId == ConfigHandler.INSTANCE.erebusDimensionID && prevMusicTicker == null) {
					prevMusicTicker = getMcMusicTicker();
					setMcMusicTicker(new BLMusicTicker(mc));
				} else if (mc.theWorld.provider.dimensionId != ConfigHandler.INSTANCE.erebusDimensionID && prevMusicTicker != null) {
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

	public static class BLMusicTicker extends MusicTicker {
		private final Random RNG = new Random();
		private final Minecraft mc;
		private ISound currentSound;
		private int timeUntilMusic = 100;
		private static final ResourceLocation EREBUS_MUSIC_RES = new ResourceLocation("erebus:music.erebusDimension");
		private static final int MIN_WAIT = 6000;
		private static final int MAX_WAIT = 12000;

		public BLMusicTicker(Minecraft minecraft) {
			super(minecraft);
			mc = minecraft;
		}

		public void update() {
			if (currentSound != null) {
				if (!EREBUS_MUSIC_RES.equals(currentSound.getPositionedSoundLocation())) {
					mc.getSoundHandler().stopSound(currentSound);
					timeUntilMusic = MathHelper.getRandomIntegerInRange(RNG, 0, MIN_WAIT / 2);
				}

				if (!mc.getSoundHandler().isSoundPlaying(currentSound)) {
					currentSound = null;
					timeUntilMusic = Math.min(MathHelper.getRandomIntegerInRange(RNG, MIN_WAIT, MAX_WAIT), timeUntilMusic);
				}
			}

			if (currentSound == null && timeUntilMusic-- <= 0) {
				currentSound = PositionedSoundRecord.func_147673_a(EREBUS_MUSIC_RES);
				mc.getSoundHandler().playSound(currentSound);
				timeUntilMusic = Integer.MAX_VALUE;
			}
		}
	}
}
