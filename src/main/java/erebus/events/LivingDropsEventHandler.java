package erebus.events;

import erebus.Erebus;
import erebus.block.BlockOfBonesBlock;
import erebus.block.entity.BlockOfBonesBlockEntity;
import erebus.item.DeathCompass;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gamerules.GameRules;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EventBusSubscriber(modid = Erebus.MODID)
public class LivingDropsEventHandler {
    private static final String GRAVE = "erebus_grave";
    private static final String DEATH_TIME = "erebus_death_time";
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerDrops(LivingDropsEvent event) {
        if (!(event.getEntity() instanceof Player player) || !(player.level() instanceof ServerLevel level)
                || event.getDrops().isEmpty() || level.getGameRules().get(GameRules.KEEP_INVENTORY)) return;
        BlockPos death = player.blockPosition();
        // If no safe grave can be placed, retain the normal drops and point to the death location.
        GlobalPos target = GlobalPos.of(level.dimension(), death);
        BlockPos base = new BlockPos(death.getX(), Math.clamp(death.getY() + 1, level.getMinY() + 2, level.getMaxY()), death.getZ());
        BlockPos place = null;
        for (Direction side : Direction.Plane.HORIZONTAL) {
            if (canPlace(level, base.relative(side))) {
                place = base.relative(side);
                break;
            }
        }
        if (place == null && canPlace(level, base)) place = base;
        boolean hasStorableDrops = event.getDrops().stream().anyMatch(drop -> !drop.getItem().isEmpty() && drop.getItem().getCount() <= 64);
        if (place != null && hasStorableDrops && level.setBlockAndUpdate(place,
                ModBlocks.BLOCK_OF_BONES.get().defaultBlockState().setValue(BlockOfBonesBlock.FACING, player.getDirection()))
                && level.getBlockEntity(place) instanceof BlockOfBonesBlockEntity bones) {
            bones.setDrops(event.getDrops());
            bones.setDisplayName(player.getDisplayName());
            target = GlobalPos.of(level.dimension(), place);
        }
        player.getPersistentData().store(GRAVE, GlobalPos.CODEC, target);
        player.getPersistentData().putString(DEATH_TIME, LocalDateTime.now().format(TIME_FORMAT));
    }

    private static boolean canPlace(ServerLevel level, BlockPos pos) {
        return !level.isOutsideBuildHeight(pos) && level.getWorldBorder().isWithinBounds(pos) && level.hasChunkAt(pos)
                && level.getBlockEntity(pos) == null && level.getBlockState(pos).canBeReplaced();
    }

    @SubscribeEvent
    public static void clonePlayer(PlayerEvent.Clone event) {
        if (!event.isWasDeath() || !(event.getOriginal().level() instanceof ServerLevel level)
                || level.getGameRules().get(GameRules.KEEP_INVENTORY)) return;
        var oldData = event.getOriginal().getPersistentData();
        oldData.read(GRAVE, GlobalPos.CODEC).ifPresent(target -> {
            event.getEntity().getPersistentData().store(GRAVE, GlobalPos.CODEC, target);
            event.getEntity().getPersistentData().putString(DEATH_TIME, oldData.getStringOr(DEATH_TIME, ""));
        });
    }

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide() || event.isEndConquered()) return;
        var data = player.getPersistentData();
        var target = data.read(GRAVE, GlobalPos.CODEC);
        if (target.isEmpty()) return;
        var stack = DeathCompass.create(target.get(), data.getStringOr(DEATH_TIME, ""));
        data.remove(GRAVE);
        data.remove(DEATH_TIME);
        if (!player.getInventory().add(stack)) player.drop(stack, false);
    }
}
