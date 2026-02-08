package erebus.client;

import erebus.registries.item.ModItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.neoforge.client.IArmPoseTransformer;

public class AnimationEnumProxies {
    public static final EnumProxy<HumanoidModel.ArmPose> MAX_SPEED_BOW_POSE = new EnumProxy<>(
            HumanoidModel.ArmPose.class,
            true,
            (IArmPoseTransformer) AnimationEnumProxies::maxSpeedBowTransformer
    );

    private static void maxSpeedBowTransformer(HumanoidModel<?> model, HumanoidRenderState state, HumanoidArm arm) {
        if(state.getMainHandItemStack().is(ModItems.MAX_SPEED_BOW)) {
            model.rightArm.yRot = -0.1F + model.head.yRot;
            model.leftArm.yRot = 0.1F + model.head.yRot + 0.4F;
            model.rightArm.xRot = -Mth.HALF_PI + model.head.xRot;
            model.leftArm.xRot = -Mth.HALF_PI + model.head.xRot;
        }
    }
}
