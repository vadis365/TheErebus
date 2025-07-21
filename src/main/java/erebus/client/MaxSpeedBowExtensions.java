package erebus.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.registries.ModItems;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

public class MaxSpeedBowExtensions implements IClientItemExtensions {

    @Override
    public boolean applyForgeHandTransform(@NotNull PoseStack pose, @NotNull LocalPlayer player, @NotNull HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
        if(itemInHand.is(ModItems.MAX_SPEED_BOW)) {
            int k = arm == HumanoidArm.RIGHT ? 1 : -1;

            applyItemArmTransform(pose, arm, equipProcess);
            pose.translate((float) k * -0.2785682F, 0.18344387F, 0.15731531F);
            pose.mulPose(Axis.XP.rotationDegrees(-13.935F));
            pose.mulPose(Axis.YP.rotationDegrees((float) k * 35.3F));
            pose.mulPose(Axis.ZP.rotationDegrees((float) k * -9.785F));
            float f8 = (float) itemInHand.getUseDuration(player) - ((float) player.getUseItemRemainingTicks() - partialTick + 1.0F);
            float f12 = f8 / 20.0F;
            f12 = (f12 * f12 + f12 * 2.0F) / 3.0F;
            if (f12 > 1.0F) {
                f12 = 1.0F;
            }

            if (f12 > 0.1F) {
                float f15 = Mth.sin((f8 - 0.1F) * 1.3F);
                float f18 = f12 - 0.1F;
                float f20 = f15 * f18;
                pose.translate(f20 * 0.0F, f20 * 0.004F, f20 * 0.0F);
            }

            pose.translate(f12 * 0.0F, f12 * 0.0F, f12 * 0.04F);
            pose.scale(1.0F, 1.0F, 1.0F + f12 * 0.2F);
            pose.mulPose(Axis.YN.rotationDegrees((float) k * 45.0F));
            return true;
        }
        return false;
    }

    private void applyItemArmTransform(PoseStack poseStack, HumanoidArm hand, float equippedProg) {
        int i = hand == HumanoidArm.RIGHT ? 1 : -1;
        poseStack.translate((float)i * 0.56F, -0.52F + equippedProg * -0.6F, -0.72F);
    }
}
