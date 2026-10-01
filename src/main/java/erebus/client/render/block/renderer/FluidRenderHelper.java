package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ARGB;
import net.neoforged.neoforge.fluids.FluidStack;

public final class FluidRenderHelper {
    private static final int[][] FACES = {{1, 0, 3, 2}, {4, 5, 6, 7}, {0, 4, 7, 3}, {5, 1, 2, 6}, {0, 1, 5, 4}, {3, 7, 6, 2}};
    private static final int[][] NORMALS = {{0, 0, -1}, {0, 0, 1}, {-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}};

    private FluidRenderHelper() {
    }

    public static void renderFluid(FluidStack fluid, PoseStack pose, SubmitNodeCollector collector,
                                   float xMin, float xMax, float yMin, float yMax, float zMin, float zMax, int light) {
        if (fluid.isEmpty() || yMax <= yMin) return;
        var model = Minecraft.getInstance().getModelManager().getFluidStateModelSet().get(fluid.getFluid().defaultFluidState());
        var sprite = model.stillMaterial().sprite();
        int color = model.fluidTintSource() == null ? -1 : ARGB.opaque(model.fluidTintSource().colorAsStack(fluid));
        float[][] corners = {{xMin, yMin, zMin}, {xMax, yMin, zMin}, {xMax, yMax, zMin}, {xMin, yMax, zMin},
                {xMin, yMin, zMax}, {xMax, yMin, zMax}, {xMax, yMax, zMax}, {xMin, yMax, zMax}};
        collector.submitCustomGeometry(pose, RenderTypes.entityTranslucent(sprite.atlasLocation()), (matrix, buffer) -> {
            for (int face = 0; face < FACES.length; face++) {
                for (int vertex = 0; vertex < 4; vertex++) {
                    float[] point = corners[FACES[face][vertex]];
                    buffer.addVertex(matrix, point[0], point[1], point[2]).setColor(color)
                            .setUv(vertex == 0 || vertex == 3 ? sprite.getU0() : sprite.getU1(), vertex < 2 ? sprite.getV1() : sprite.getV0())
                            .setOverlay(OverlayTexture.NO_OVERLAY).setLight(light)
                            .setNormal(matrix, NORMALS[face][0], NORMALS[face][1], NORMALS[face][2]);
                }
            }
        });
    }
}
