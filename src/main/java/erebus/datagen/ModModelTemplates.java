package erebus.datagen;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.Direction;

public class ModModelTemplates {

    public static ModelTemplate honeyTreatTemplate = ModelTemplates
            .create(TextureSlot.PARTICLE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE)
            .extend()
            .element(elements -> elements
                    .from(1, 0, 1)
                    .to(15, 8, 15)
                    .allFaces((direction, builder) -> {
                        switch (direction) {
                            case DOWN -> builder.texture(TextureSlot.BOTTOM).cullface(Direction.DOWN);
                            case UP -> builder.texture(TextureSlot.TOP);
                            case NORTH, SOUTH, WEST, EAST -> builder.texture(TextureSlot.SIDE);
                        }
                    })
            ).build();

    public static ModelTemplate honeyTreatBite1 = ModelTemplates
            .create(TextureSlot.PARTICLE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE, TextureSlot.INSIDE)
            .extend()
            .element(elements -> elements
                    .from(3, 0, 1)
                    .to(15, 8, 15)
                    .allFaces((direction, builder) -> {
                        switch (direction) {
                            case DOWN -> builder.texture(TextureSlot.BOTTOM).cullface(Direction.DOWN);
                            case UP -> builder.texture(TextureSlot.TOP);
                            case NORTH, SOUTH, EAST -> builder.texture(TextureSlot.SIDE);
                            case WEST -> builder.texture(TextureSlot.INSIDE);
                        }
                    })
            ).build();

    public static ModelTemplate honeyTreatBite2 = ModelTemplates
            .create(TextureSlot.PARTICLE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE, TextureSlot.INSIDE)
            .extend()
            .element(elements -> elements
                    .from(5, 0, 1)
                    .to(15, 8, 15)
                    .allFaces((direction, builder) -> {
                        switch (direction) {
                            case DOWN -> builder.texture(TextureSlot.BOTTOM).cullface(Direction.DOWN);
                            case UP -> builder.texture(TextureSlot.TOP);
                            case NORTH, SOUTH, EAST -> builder.texture(TextureSlot.SIDE);
                            case WEST -> builder.texture(TextureSlot.INSIDE);
                        }
                    })
            ).build();

    public static ModelTemplate honeyTreatBite3 = ModelTemplates
            .create(TextureSlot.PARTICLE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE, TextureSlot.INSIDE)
            .extend()
            .element(elements -> elements
                    .from(7, 0, 1)
                    .to(15, 8, 15)
                    .allFaces((direction, builder) -> {
                        switch (direction) {
                            case DOWN -> builder.texture(TextureSlot.BOTTOM).cullface(Direction.DOWN);
                            case UP -> builder.texture(TextureSlot.TOP);
                            case NORTH, SOUTH, EAST -> builder.texture(TextureSlot.SIDE);
                            case WEST -> builder.texture(TextureSlot.INSIDE);
                        }
                    })
            ).build();

    public static ModelTemplate honeyTreatBite4 = ModelTemplates
            .create(TextureSlot.PARTICLE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE, TextureSlot.INSIDE)
            .extend()
            .element(elements -> elements
                    .from(9, 0, 1)
                    .to(15, 8, 15)
                    .allFaces((direction, builder) -> {
                        switch (direction) {
                            case DOWN -> builder.texture(TextureSlot.BOTTOM).cullface(Direction.DOWN);
                            case UP -> builder.texture(TextureSlot.TOP);
                            case NORTH, SOUTH, EAST -> builder.texture(TextureSlot.SIDE);
                            case WEST -> builder.texture(TextureSlot.INSIDE);
                        }
                    })
            ).build();

    public static ModelTemplate honeyTreatBite5 = ModelTemplates
            .create(TextureSlot.PARTICLE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE, TextureSlot.INSIDE)
            .extend()
            .element(elements -> elements
                    .from(11, 0, 1)
                    .to(15, 8, 15)
                    .allFaces((direction, builder) -> {
                        switch (direction) {
                            case DOWN -> builder.texture(TextureSlot.BOTTOM).cullface(Direction.DOWN);
                            case UP -> builder.texture(TextureSlot.TOP);
                            case NORTH, SOUTH, EAST -> builder.texture(TextureSlot.SIDE);
                            case WEST -> builder.texture(TextureSlot.INSIDE);
                        }
                    })
            ).build();

    public static ModelTemplate honeyTreatBite6 = ModelTemplates
            .create(TextureSlot.PARTICLE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE, TextureSlot.INSIDE)
            .extend()
            .element(elements -> elements
                    .from(13, 0, 1)
                    .to(15, 8, 15)
                    .allFaces((direction, builder) -> {
                        switch (direction) {
                            case DOWN -> builder.texture(TextureSlot.BOTTOM).cullface(Direction.DOWN);
                            case UP -> builder.texture(TextureSlot.TOP);
                            case NORTH, SOUTH, EAST -> builder.texture(TextureSlot.SIDE);
                            case WEST -> builder.texture(TextureSlot.INSIDE);
                        }
                    })
            ).build();

    public static ModelTemplate[] honeyTreatBites = new ModelTemplate[]{honeyTreatBite1, honeyTreatBite2, honeyTreatBite3, honeyTreatBite4, honeyTreatBite5, honeyTreatBite6};
}
