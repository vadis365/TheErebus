package erebus.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jspecify.annotations.NonNull;

import java.util.Iterator;
import java.util.List;

public abstract class ErebusOpenersCounter extends ContainerOpenersCounter {
    private boolean isForPhysicalBlock = true;

    public void setForPhysicalBlock(boolean isForPhysicalBlock) {
        this.isForPhysicalBlock = isForPhysicalBlock;
    }

    @Override
    public void incrementOpeners(@NonNull LivingEntity player, @NonNull Level level, @NonNull BlockPos pos, @NonNull BlockState state, double maxInteractionRange) {
        int count = openCount++;
        if(count == 0) {
            onOpen(level, pos, state);
            if(isForPhysicalBlock) {
                level.gameEvent(player, GameEvent.CONTAINER_OPEN, pos);
                scheduleRecheck(level, pos, state);
            }
        }

        openerCountChanged(level, pos, state, count, openCount);
        this.maxInteractionRange = Math.max(maxInteractionRange, this.maxInteractionRange);
    }

    @Override
    public void recheckOpeners(@NonNull Level level, @NonNull BlockPos pos, @NonNull BlockState state) {
        List<ContainerUser> list = getEntitiesWithContainerOpen(level, pos);
        maxInteractionRange = 0.0;

        ContainerUser user;
        for(Iterator<ContainerUser> users = list.iterator(); users.hasNext(); maxInteractionRange = Math.max(user.getContainerInteractionRange(), maxInteractionRange)) {
            user = users.next();
        }

        int playersInteracting = list.size();
        int count = openCount;

        if(playersInteracting != count) {
            boolean hasPlayersInteracting = playersInteracting != 0;
            boolean hasCount = count != 0;

            if(hasPlayersInteracting && !hasCount) {
                onOpen(level, pos, state);
                if(isForPhysicalBlock)
                    level.gameEvent(null, GameEvent.CONTAINER_OPEN, pos);
            } else if(!hasPlayersInteracting) {
                onClose(level, pos, state);
                if(isForPhysicalBlock)
                    level.gameEvent(null, GameEvent.CONTAINER_CLOSE, pos);
            }

            openCount = count;
        }

        openerCountChanged(level, pos, state, playersInteracting, count);

        if(isForPhysicalBlock && count > 0)
            scheduleRecheck(level, pos, state);
    }
}
