package erebus.datagen;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;

public class ModModelTemplates {

    public static ModelTemplate honeyTreatBite(int bite) {
        int[] bites = new int[]{1, 3, 5, 7, 9, 11};
        return ModelTemplates
                .create(TextureSlot.PARTICLE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE, TextureSlot.INSIDE)
                .extend()
                .element(elements -> elements
                        .from(bites[bite], 0, 1)
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
    }

    public static ModelTemplate dustLayer(int height) {
        return ModelTemplates
                .create(TextureSlot.PARTICLE, TextureSlot.TEXTURE)
                .extend()
                .element(elements -> elements
                        .from(0, 0, 0)
                        .to(16, height, 16)
                        .allFaces((direction, builder) -> {
                            switch (direction) {
                                case DOWN -> builder.texture(TextureSlot.TEXTURE).uvs(0, 0, 16, 16).cullface(Direction.DOWN);
                                case UP -> builder.texture(TextureSlot.TEXTURE).uvs(0, 0, 16, 16);
                                case NORTH -> builder.texture(TextureSlot.TEXTURE).uvs(0, 16 - height, 16, 16).cullface(Direction.NORTH);
                                case SOUTH -> builder.texture(TextureSlot.TEXTURE).uvs(0, 16 - height, 16, 16).cullface(Direction.SOUTH);
                                case EAST -> builder.texture(TextureSlot.TEXTURE).uvs(0, 16 - height, 16, 16).cullface(Direction.EAST);
                                case WEST -> builder.texture(TextureSlot.TEXTURE).uvs(0, 16 - height, 16, 16).cullface(Direction.WEST);
                            }
                        })
                ).build();
    }

    public static ModelTemplate hollowLog() {
        return ModelTemplates
                .create(TextureSlot.PARTICLE, TextureSlot.TOP, TextureSlot.END, TextureSlot.SIDE)
                .extend()
                .element((elements) -> elements
                        .from(1, 0, 0)
                        .to(15, 1, 16)
                        .allFaces((direction, builder) -> {
                            switch (direction) {
                                case NORTH -> builder.texture(TextureSlot.TOP).uvs(0, 15, 14, 16).cullface(Direction.NORTH);
                                case SOUTH -> builder.texture(TextureSlot.TOP).uvs(0, 15, 14, 16).cullface(Direction.SOUTH);
                                case UP, DOWN -> builder.texture(TextureSlot.TOP).uvs(0, 0, 14, 16);
                                case EAST, WEST -> builder.texture(TextureSlot.END).uvs(0, 15, 16, 16);
                            }
                        })
                )
                .element((elements) -> elements
                        .from(1, 15, 0)
                        .to(15, 16, 16)
                        .allFaces((direction, builder) -> {
                            switch (direction) {
                                case NORTH -> builder.texture(TextureSlot.TOP).uvs(0, 0, 14, 1).cullface(Direction.NORTH);
                                case SOUTH -> builder.texture(TextureSlot.TOP).uvs(0, 0, 14, 1).cullface(Direction.SOUTH);
                                case UP, DOWN -> builder.texture(TextureSlot.TOP).uvs(0, 0, 14, 16);
                                case EAST, WEST -> builder.texture(TextureSlot.END).uvs(0, 0, 16, 1);
                            }
                        })
                )
                .element((elements) -> elements
                        .from(0, 1, 0)
                        .to(1, 15, 16)
                        .allFaces((direction, builder) -> {
                            switch (direction) {
                                case NORTH -> builder.texture(TextureSlot.END).uvs(15, 1, 16, 15).cullface(Direction.NORTH);
                                case SOUTH -> builder.texture(TextureSlot.END).uvs(0, 1, 1, 15).cullface(Direction.SOUTH);
                                case EAST, WEST -> builder.texture(TextureSlot.SIDE).uvs(0, 1, 16, 15);
                                case UP, DOWN -> builder.texture(TextureSlot.TOP).uvs(0, 0, 1, 16);
                            }
                        })
                )
                .element((elements) -> elements
                        .from(15, 1, 0)
                        .to(16, 15, 16)
                        .allFaces((direction, builder) -> {
                            switch (direction) {
                                case NORTH -> builder.texture(TextureSlot.END).uvs(0, 1, 1, 15).cullface(Direction.NORTH);
                                case SOUTH -> builder.texture(TextureSlot.END).uvs(15, 1, 16, 15).cullface(Direction.SOUTH);
                                case EAST, WEST -> builder.texture(TextureSlot.SIDE).uvs(0, 1, 16, 15);
                                case UP, DOWN -> builder.texture(TextureSlot.TOP).uvs(0, 0, 1, 16);
                            }
                        })
                )
                .element((elements) -> elements
                        .from(1, 1, 0)
                        .to(15, 15, 0)
                        .face(Direction.NORTH, (builder) -> builder.texture(TextureSlot.END).uvs(1, 1, 15, 15).cullface(Direction.NORTH))
                        .face(Direction.SOUTH, (builder) -> builder.texture(TextureSlot.END).uvs(1, 1, 15, 15).cullface(Direction.SOUTH))
                )
                .element((elements) -> elements
                        .from(1, 1, 16)
                        .to(15, 15, 16)
                        .face(Direction.NORTH, (builder) -> builder.texture(TextureSlot.END).uvs(1, 1, 15, 15).cullface(Direction.NORTH))
                        .face(Direction.SOUTH, (builder) -> builder.texture(TextureSlot.END).uvs(1, 1, 15, 15).cullface(Direction.SOUTH))
                )
                .build();
    }
}
