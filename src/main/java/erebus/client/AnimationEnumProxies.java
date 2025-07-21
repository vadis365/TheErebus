package erebus.client;

import net.minecraft.client.model.HumanoidModel;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

public class AnimationEnumProxies {
    public static final EnumProxy<HumanoidModel.ArmPose> MAX_SPEED_BOW_POSE = new EnumProxy<>(
            HumanoidModel.ArmPose.class,
            "max_speed_bow_pose"
    );
}
