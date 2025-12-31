package erebus.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.item.MaxSpeedBowItem;
import erebus.registries.item.ModItems;
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
            int armMultiplier = arm == HumanoidArm.RIGHT ? 1 : -1;

            applyItemArmTransform(pose, arm, equipProcess);
            pose.translate((float) armMultiplier * -0.2785682F, 0.18344387F, 0.15731531F);
            pose.mulPose(Axis.XP.rotationDegrees(-13.935F));
            pose.mulPose(Axis.YP.rotationDegrees((float) armMultiplier * 35.3F));
            pose.mulPose(Axis.ZP.rotationDegrees((float) armMultiplier * -9.785F));
            float useTimeRemaining = (float) itemInHand.getUseDuration(player) - ((float) player.getUseItemRemainingTicks() - partialTick + 1.0F);
            float drawProgress = useTimeRemaining / MaxSpeedBowItem.DRAW_SPEED;
            drawProgress = (drawProgress * drawProgress + drawProgress * 2.0F) / 3.0F;
            if (drawProgress > 1.0F) {
                drawProgress = 1.0F;
            }

            if (drawProgress > 0.1F) {
                float sinValue = Mth.sin((useTimeRemaining - 0.1F) * 1.3F);
                float adjustedProgress = drawProgress - 0.1F;
                float animationFactor = sinValue * adjustedProgress;
                pose.translate(animationFactor * 0.0F, animationFactor * 0.004F, animationFactor * 0.0F);
            }

            pose.translate(drawProgress * 0.0F, drawProgress * 0.0F, drawProgress * 0.04F);
            pose.scale(1.0F, 1.0F, 1.0F + drawProgress * 0.2F);
            pose.mulPose(Axis.YN.rotationDegrees((float) armMultiplier * 45.0F));
            return true;
        }
        return false;
    }

    private void applyItemArmTransform(PoseStack poseStack, HumanoidArm hand, float equippedProg) {
        int handSideMultiplier = hand == HumanoidArm.RIGHT ? 1 : -1;
        poseStack.translate((float)handSideMultiplier * 0.56F, -0.52F + equippedProg * -0.6F, -0.72F);
    }


}
