package erebus.entity;

import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.Util;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class AntlionBoss extends Monster {

    private final ServerBossEvent bossEvent = Util.make(
            new ServerBossEvent(
                    Mth.createInsecureUUID(random),
                    getDisplayName(),
                    ServerBossEvent.BossBarColor.PURPLE,
                    ServerBossEvent.BossBarOverlay.PROGRESS
            ),
            e -> e.setDarkenScreen(true)
    );

    public AntlionBoss(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }
}
