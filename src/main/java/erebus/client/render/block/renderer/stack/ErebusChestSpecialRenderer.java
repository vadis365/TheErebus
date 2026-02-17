package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.Erebus;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.chest.ChestModel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.ChestSpecialRenderer;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.SpriteGetter;
import net.minecraft.client.resources.model.SpriteId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public final class ErebusChestSpecialRenderer implements NoDataSpecialModelRenderer {
    public static final Identifier ASPER_TEXTURE = Erebus.prefix("asper_chest");
    public static final Identifier BAMBOO_TEXTURE = Erebus.prefix("bamboo_chest");
    public static final Identifier BALSAM_TEXTURE = Erebus.prefix("balsam_chest");
    public static final Identifier BAOBAB_TEXTURE = Erebus.prefix("baobab_chest");
    public static final Identifier CYPRESS_TEXTURE = Erebus.prefix("cypress_chest");
    public static final Identifier EUCALYPTUS_TEXTURE = Erebus.prefix("eucalyptus_chest");
    public static final Identifier MAHOGANY_TEXTURE = Erebus.prefix("mahogany_chest");
    public static final Identifier MARSHWOOD_TEXTURE = Erebus.prefix("marshwood_chest");
    public static final Identifier MOSSBARK_TEXTURE = Erebus.prefix("mossbark_chest");
    public static final Identifier PETRIFIED_TEXTURE = Erebus.prefix("petrified_chest");
    public static final Identifier ROTTEN_TEXTURE = Erebus.prefix("rotten_chest");
    public static final Identifier SCORCHED_TEXTURE = Erebus.prefix("scorched_chest");
    public static final Identifier VARNISHED_TEXTURE = Erebus.prefix("varnished_chest");
    public static final Identifier WHITE_TEXTURE = Erebus.prefix("white_chest");

    private final SpriteGetter materials;
    private final ChestModel model;
    private final SpriteId material;
    private final float openness;

    public ErebusChestSpecialRenderer(SpriteGetter materials, ChestModel model, SpriteId material, float openness) {
        this.materials = materials;
        this.model = model;
        this.material = material;
        this.openness = openness;
    }

    @Override
    public void submit(@NonNull ItemDisplayContext context, PoseStack pose, SubmitNodeCollector submit, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        submit.submitModel(model, openness, pose, material.renderType(RenderTypes::entitySolid), lightCoords, overlayCoords, -1, materials.get(material), outlineColor, null);
    }

    @Override
    public void getExtents(@NonNull Consumer<Vector3fc> consumer) {
        PoseStack poseStack = new PoseStack();
        model.setupAnim(openness);
        model.root().getExtentsForGui(poseStack, consumer);
    }

    public record Unbaked(Identifier texture, float openness) implements SpecialModelRenderer.Unbaked {
        public static final MapCodec<ErebusChestSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
                (i) -> i.group(
                        Identifier.CODEC
                                .fieldOf("texture")
                                .forGetter(ErebusChestSpecialRenderer.Unbaked::texture),
                        Codec.FLOAT
                                .optionalFieldOf("openness", 0.0F)
                                .forGetter(ErebusChestSpecialRenderer.Unbaked::openness)
                ).apply(i, ErebusChestSpecialRenderer.Unbaked::new));

        public Unbaked(Identifier texture) {
            this(texture, 0.0F);
        }

        public @NonNull MapCodec<ErebusChestSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        public SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
            ChestModel model = new ChestModel(context.entityModelSet().bakeLayer(ModelLayers.CHEST));
            SpriteId fullTexture = Sheets.CHEST_MAPPER.apply(this.texture);
            return new ChestSpecialRenderer(context.sprites(), model, fullTexture, this.openness);
        }
    }
}