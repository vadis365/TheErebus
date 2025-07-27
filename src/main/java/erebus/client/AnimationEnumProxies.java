package erebus.client;

import erebus.registries.ModItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.neoforge.client.IArmPoseTransformer;

public class AnimationEnumProxies {
    public static final EnumProxy<HumanoidModel.ArmPose> MAX_SPEED_BOW_POSE = new EnumProxy<>(
            HumanoidModel.ArmPose.class,
            true,
            (IArmPoseTransformer) AnimationEnumProxies::maxSpeedBowTransformer
    );

    private static void maxSpeedBowTransformer(HumanoidModel<?> model, LivingEntity entity, HumanoidArm arm) {
        if(entity.getUseItem().is(ModItems.MAX_SPEED_BOW)) {
            model.rightArm.yRot = -0.1F + model.head.yRot;
            model.leftArm.yRot = 0.1F + model.head.yRot + 0.4F;
            model.rightArm.xRot = -Mth.HALF_PI + model.head.xRot;
            model.leftArm.xRot = -Mth.HALF_PI + model.head.xRot;
        }
    }
}
