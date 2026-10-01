package erebus.utils;

import com.mojang.authlib.GameProfile;
import erebus.Erebus;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.common.util.FakePlayerFactory;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.UUID;

public class FakePlayerHandler {

    public static final GameProfile GAME_PROFILE = new GameProfile(
            UUID.nameUUIDFromBytes(Erebus.MODID.getBytes()),
            "[%s]".formatted(Component.translatable("fakeplayer.erebus").getString())
            // Different name from the player controlled ones so the name is different between the two types
    );

    private static FakePlayer getDefault(ServerLevel level) {
        return FakePlayerFactory.get(level, GAME_PROFILE);
    }

    public static FakePlayer get(ServerLevel level, @Nullable UUID placer) {
        FakePlayer fakePlayer;
        if (placer == null)
            fakePlayer = getDefault(level);
        else
            fakePlayer = FakePlayerFactory.get(level, new GameProfile(placer, Component.translatable("fakeplayer.erebus").getString()));

        fakePlayer.getPersistentData().putBoolean(Erebus.MODID, true);
        return fakePlayer;
    }

    public static WeakReference<FakePlayer> get(WeakReference<FakePlayer> previous, ServerLevel level, @Nullable UUID placer, BlockPos pos) {
        FakePlayer fakePlayer = previous.get();
        if (fakePlayer == null) {
            fakePlayer = get(level, placer);
            fakePlayer.setPos(pos.getX(), pos.getY(), pos.getZ());
            return new WeakReference<>(fakePlayer);
        } else {
            fakePlayer.setPos(pos.getX(), pos.getY(), pos.getZ());
            return previous;
        }
    }

    public static boolean isErebusFakePlayer(FakePlayer fakePlayer) {
        return fakePlayer.getPersistentData().contains(Erebus.MODID);
    }

    public static InteractionResult rightClickItemAt(Level level, BlockPos pos, InteractionHand hand, Direction direction, ItemStack stack, UUID owner) {
        if (!(level instanceof ServerLevel server) || stack.isEmpty()) return InteractionResult.PASS;
        var player = get(server, owner);
        var previous = player.getItemInHand(hand);
        var previousPos = player.position();
        player.setItemInHand(hand, stack);
        player.setPos(Vec3.atBottomCenterOf(pos.relative(direction)));
        try {
            if (!level.mayInteract(player, pos) || !player.mayUseItemAt(pos, direction, stack)) return InteractionResult.FAIL;
            var hit = Vec3.atCenterOf(pos).add(Vec3.atLowerCornerOf(direction.getUnitVec3i()).scale(0.5));
            return player.gameMode.useItemOn(player, level, stack, hand, new BlockHitResult(hit, direction, pos, false));
        } finally {
            player.setItemInHand(hand, previous);
            player.setPos(previousPos);
        }
    }

    public static boolean breakBlockAt(Level level, BlockPos pos, UUID owner) {
        if (!(level instanceof ServerLevel server)) return false;
        var player = get(server, owner);
        var previous = player.getMainHandItem();
        var previousPos = player.position();
        player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
        player.setPos(Vec3.atBottomCenterOf(pos.above()));
        try {
            return level.mayInteract(player, pos) && player.gameMode.destroyBlock(pos);
        } finally {
            player.setItemInHand(InteractionHand.MAIN_HAND, previous);
            player.setPos(previousPos);
        }
    }

}
