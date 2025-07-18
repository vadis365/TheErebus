package erebus.utils;

import java.lang.ref.WeakReference;
import java.util.UUID;

import javax.annotation.Nullable;

import com.mojang.authlib.GameProfile;

import erebus.Erebus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.common.util.FakePlayerFactory;

public class FakePlayerHandler {
	
	public static final GameProfile GAME_PROFILE = new GameProfile(
			UUID.nameUUIDFromBytes(Erebus.MODID.getBytes()),
			"[%s]".formatted(Component.translatable("fakeplayer.erebus").getString())
			// Different name from the player controlled ones so the name is different between the two types
	);

    private static FakePlayer getDefault(ServerLevel level) {
        return FakePlayerFactory.get(level, GAME_PROFILE);
    }

    private static FakePlayer get(ServerLevel level, @Nullable UUID placer) {
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
    
    public static void rightClickItemAt(Level level, ItemStack stack, UUID id) {
		if (level.isClientSide() || stack.isEmpty() || stack.getItem() == null)
			return;
		Player player = get((ServerLevel) level, id);
		player.setItemSlot(EquipmentSlot.MAINHAND, stack);
		try {
			player.startUsingItem(InteractionHand.MAIN_HAND);
		} finally {
			player.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
		}
	}

}
