package erebus.datagen.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import erebus.Erebus;
import erebus.block.HangingWebBlock;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

public final class HangingWebModels {
    private HangingWebModels() {
    }

    public static void create(BlockModelGenerators models) {
        var block = ModBlocks.HANGING_WEB.get();
        var ids = new Identifier[10];
        for (int part = 0; part < 10; part++) {
            ids[part] = Erebus.prefix("block/hanging_web" + (part == 9 ? "" : "_" + part));
            var json = model(part);
            models.modelOutput.accept(ids[part], () -> json);
        }
        models.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(
                PropertyDispatch.initial(HangingWebBlock.FACING, HangingWebBlock.PART).generate((facing, part) -> {
                    var variant = plainVariant(ids[part]);
                    return switch (facing) {
                        case EAST -> variant.with(Y_ROT_90);
                        case SOUTH -> variant.with(Y_ROT_180);
                        case WEST -> variant.with(Y_ROT_270);
                        default -> variant;
                    };
                })));
        models.registerSimpleItemModel(block, ids[9]);
    }

    private static JsonObject model(int part) {
        double size = part == 9 ? 16 : 48;
        double center = size / 2;
        var lines = new ArrayList<double[]>();
        double[][] directions = {{1, .5}, {.5, 1}, {-.5, 1}, {-1, .5}, {-1, -.5}, {-.5, -1}, {.5, -1}, {1, -.5}};

        for (double radius : new double[]{size * .13, size * .24, size * .36, size * .48}) {
            for (int i = 0; i < 8; i++) {
                var a = directions[i];
                var b = directions[(i + 1) % 8];
                lines.add(new double[]{center + a[0] * radius, center + a[1] * radius, center + b[0] * radius, center + b[1] * radius});
            }
        }

        for (int x = -1; x <= 1; x++)
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;
                double radius = x != 0 && y != 0 ? size * .36 : size * .48;
                lines.add(new double[]{center, center, center + x * radius, center + y * radius});
            }

        lines.add(new double[]{center, 0, center, size});
        double minX = part == 9 ? 0 : part % 3 * 16;
        double minY = part == 9 ? 0 : (2 - (double) part / 3) * 16;
        var elements = new JsonArray();
        for (var line : lines) addLine(elements, line, minX, minY);
        var json = new JsonObject();
        json.addProperty("ambientocclusion", false);
        var textures = new JsonObject();
        textures.addProperty("silk", "minecraft:block/white_wool");
        textures.addProperty("particle", "erebus:block/hanging_web");
        json.add("textures", textures);
        json.add("elements", elements);
        return json;
    }

    private static void addLine(JsonArray elements, double[] line, double minX, double minY) {
        double dx = line[2] - line[0], dy = line[3] - line[1];
        double start = 0, end = 1;
        for (int axis = 0; axis < 2; axis++) {
            double delta = axis == 0 ? dx : dy, origin = line[axis], min = axis == 0 ? minX : minY;
            if (Math.abs(delta) < 1e-9) {
                if (origin < min || origin > min + 16) return;
            } else {
                double a = (min - origin) / delta, b = (min + 16 - origin) / delta;
                start = Math.max(start, Math.min(a, b));
                end = Math.min(end, Math.max(a, b));
            }
        }
        if (end - start < 1e-9) return;
        double x1 = line[0] + dx * start - minX, y1 = line[1] + dy * start - minY;
        double x2 = line[0] + dx * end - minX, y2 = line[1] + dy * end - minY;
        double cx = (x1 + x2) / 2, cy = (y1 + y2) / 2;
        double length = Math.hypot(x2 - x1, y2 - y1), halfWidth = .25;
        var element = new JsonObject();
        if (Math.abs(x2 - x1) < 1e-9) {
            element.add("from", array(cx - halfWidth, cy - length / 2, 7.96));
            element.add("to", array(cx + halfWidth, cy + length / 2, 8.04));
        } else {
            element.add("from", array(cx - length / 2, cy - halfWidth, 7.96));
            element.add("to", array(cx + length / 2, cy + halfWidth, 8.04));
            if (Math.abs(y2 - y1) > 1e-9) {
                var rotation = new JsonObject();
                rotation.add("origin", array(cx, cy, 8));
                rotation.addProperty("axis", "z");
                rotation.addProperty("angle", Math.signum((x2 - x1) * (y2 - y1)) * 45);
                element.add("rotation", rotation);
            }
        }
        var faces = new JsonObject();
        for (String side : List.of("north", "south", "east", "west", "up", "down")) {
            var face = new JsonObject();
            face.addProperty("texture", "#silk");
            face.add("uv", array(0, 0, 16, 16));
            faces.add(side, face);
        }
        element.add("faces", faces);
        elements.add(element);
    }

    private static JsonArray array(double... values) {
        var array = new JsonArray();
        for (double value : values) array.add(value);
        return array;
    }
}
