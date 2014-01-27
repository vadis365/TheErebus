package erebus.entity.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.EntityLiving;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityFirebrat;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityScorpion;
import erebus.entity.EntitySolifuge;

public final class RandomMobNames {
	public static final RandomMobNames instance = new RandomMobNames();

	private static final Map<Class<? extends EntityLiving>, NameData> names = new HashMap<Class<? extends EntityLiving>, NameData>();

	static {
		names.put(EntityBotFly.class, new NameData(380, new String[] { "Butt Fly" }));
		names.put(EntityGrasshopper.class, new NameData(360, new String[] { "Grasshumper", "Jimminey" }));
		names.put(EntityBeetleLarva.class, new NameData(320, new String[] { "Trampoline" }));
		names.put(EntityBeetle.class, new NameData(280, new String[] { "John Lennon", "Paul McCartney", "George Harisson", "Ringo Starr" }));
		names.put(EntityCentipede.class, new NameData(220, new String[] { "Centipaedo" }));
		names.put(EntitySolifuge.class, new NameData(220, new String[] { "Fast & Furious" }));
		names.put(EntityFirebrat.class, new NameData(200, new String[] { "Firebra", "Ginger Silverfish" }));
		names.put(EntityScorpion.class, new NameData(150, new String[] { "Nippletwister" }));
	};

	private RandomMobNames() {
	}

	@ForgeSubscribe
	public void onLivingSpawn(LivingSpawnEvent e) {
		EntityLiving entity = (EntityLiving) e.entityLiving;

		NameData data = names.get(entity.getClass());

		if (data != null && entity.getRNG().nextInt(data.diceSides) == 0 && !entity.hasCustomNameTag())
			entity.setCustomNameTag(data.nameList[entity.getRNG().nextInt(data.nameList.length)]);
	}

	static final class NameData {
		final short diceSides;
		final String[] nameList;

		NameData(int diceSides, String[] nameList) {
			this.diceSides = (short) diceSides;
			this.nameList = nameList;
		}
	}
}
