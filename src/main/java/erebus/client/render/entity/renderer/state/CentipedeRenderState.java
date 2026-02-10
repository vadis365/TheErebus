package erebus.client.render.entity.renderer.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class CentipedeRenderState extends LivingEntityRenderState {
    public int skin;
    public float avgWibbleStrength;
    public PartState head;
    public PartState[] bodyParts;
    public PartState tail;

    public static class PartState {
        public double x, y, z;
        public float yaw;
        public float wibbleStrength;
        public int frame;
        public boolean isPartA;
    }
}
