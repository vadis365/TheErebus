package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.BogMawModel;
import erebus.client.render.entity.renderer.state.BogMawRenderState;
import erebus.entity.BogMaw;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class BogMawRenderer extends MobRenderer<BogMaw, BogMawRenderState, BogMawModel> {
    private static final Identifier TEXTURE = Erebus.prefix("textures/entity/bog_maw.png");

    public BogMawRenderer(EntityRendererProvider.Context context) {
        super(context, new BogMawModel(context.bakeLayer(ModEntityRendering.BOG_MAW)), 0F);
    }

    @Override
    public void extractRenderState(BogMaw entity, BogMawRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.jawAngle = entity.getJawAngle(partialTicks);
        state.leapRotation = entity.getRotation();
        state.onGround = entity.onGround();
    }

    @Override
    public BogMawRenderState createRenderState() {
        return new BogMawRenderState();
    }

    @Override
    public Identifier getTextureLocation(BogMawRenderState state) {
        return TEXTURE;
    }

    @Override
    protected void scale(BogMawRenderState state, PoseStack pose) {
        if (state.onGround) return;
        int facing = Math.floorMod((int) state.leapRotation + 22, 360) / 45;
        float x = switch (facing) {
            case 0, 1, 7 -> -1;
            case 3, 4, 5 -> 1;
            default -> 0;
        };
        float z = switch (facing) {
            case 1, 2, 3 -> 1;
            case 5, 6, 7 -> -1;
            default -> 0;
        };
        pose.mulPose(new Quaternionf().rotationAxis((float) Math.PI / 4, new Vector3f(x, 0, z).normalize()));
    }
}
