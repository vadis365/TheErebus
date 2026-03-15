package erebus.registries.client;

import erebus.Erebus;
import erebus.client.render.block.renderer.state.ErebusChestRenderState;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SpriteMapper;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.world.level.block.state.properties.ChestType;

public class ModSheets {
    private static final SpriteMapper CHEST_MAPPER = new SpriteMapper(Sheets.CHEST_SHEET, "entity/chest");

    private static final SpriteId CHEST_ASPER = CHEST_MAPPER.apply(Erebus.prefix("asper_chest"));
    private static final SpriteId CHEST_ASPER_LEFT = CHEST_MAPPER.apply(Erebus.prefix("asper_chest_left"));
    private static final SpriteId CHEST_ASPER_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("asper_chest_right"));
    private static final SpriteId CHEST_BAMBOO = CHEST_MAPPER.apply(Erebus.prefix("bamboo_chest"));
    private static final SpriteId CHEST_BAMBOO_LEFT = CHEST_MAPPER.apply(Erebus.prefix("bamboo_chest_left"));
    private static final SpriteId CHEST_BAMBOO_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("bamboo_chest_right"));
    private static final SpriteId CHEST_BAOBAB = CHEST_MAPPER.apply(Erebus.prefix("baobab_chest"));
    private static final SpriteId CHEST_BAOBAB_LEFT = CHEST_MAPPER.apply(Erebus.prefix("baobab_chest_left"));
    private static final SpriteId CHEST_BAOBAB_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("baobab_chest_right"));
    private static final SpriteId CHEST_BALSAM = CHEST_MAPPER.apply(Erebus.prefix("balsam_chest"));
    private static final SpriteId CHEST_BALSAM_LEFT = CHEST_MAPPER.apply(Erebus.prefix("balsam_chest_left"));
    private static final SpriteId CHEST_BALSAM_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("balsam_chest_right"));
    private static final SpriteId CHEST_CYPRESS = CHEST_MAPPER.apply(Erebus.prefix("cypress_chest"));
    private static final SpriteId CHEST_CYPRESS_LEFT = CHEST_MAPPER.apply(Erebus.prefix("cypress_chest_left"));
    private static final SpriteId CHEST_CYPRESS_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("cypress_chest_right"));
    private static final SpriteId CHEST_EUCALYPTUS = CHEST_MAPPER.apply(Erebus.prefix("eucalyptus_chest"));
    private static final SpriteId CHEST_EUCALYPTUS_LEFT = CHEST_MAPPER.apply(Erebus.prefix("eucalyptus_chest_left"));
    private static final SpriteId CHEST_EUCALYPTUS_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("eucalyptus_chest_right"));
    private static final SpriteId CHEST_MAHOGANY = CHEST_MAPPER.apply(Erebus.prefix("mahogany_chest"));
    private static final SpriteId CHEST_MAHOGANY_LEFT = CHEST_MAPPER.apply(Erebus.prefix("mahogany_chest_left"));
    private static final SpriteId CHEST_MAHOGANY_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("mahogany_chest_right"));
    private static final SpriteId CHEST_MARSHWOOD = CHEST_MAPPER.apply(Erebus.prefix("marshwood_chest"));
    private static final SpriteId CHEST_MARSHWOOD_LEFT = CHEST_MAPPER.apply(Erebus.prefix("marshwood_chest_left"));
    private static final SpriteId CHEST_MARSHWOOD_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("marshwood_chest_right"));
    private static final SpriteId CHEST_MOSSBARK = CHEST_MAPPER.apply(Erebus.prefix("mossbark_chest"));
    private static final SpriteId CHEST_MOSSBARK_LEFT = CHEST_MAPPER.apply(Erebus.prefix("mossbark_chest_left"));
    private static final SpriteId CHEST_MOSSBARK_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("mossbark_chest_right"));
    private static final SpriteId CHEST_PETRIFIED = CHEST_MAPPER.apply(Erebus.prefix("petrified_chest"));
    private static final SpriteId CHEST_PETRIFIED_LEFT = CHEST_MAPPER.apply(Erebus.prefix("petrified_chest_left"));
    private static final SpriteId CHEST_PETRIFIED_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("petrified_chest_right"));
    private static final SpriteId CHEST_ROTTEN = CHEST_MAPPER.apply(Erebus.prefix("rotten_chest"));
    private static final SpriteId CHEST_ROTTEN_LEFT = CHEST_MAPPER.apply(Erebus.prefix("rotten_chest_left"));
    private static final SpriteId CHEST_ROTTEN_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("rotten_chest_right"));
    private static final SpriteId CHEST_SCORCHED = CHEST_MAPPER.apply(Erebus.prefix("scorched_chest"));
    private static final SpriteId CHEST_SCORCHED_LEFT = CHEST_MAPPER.apply(Erebus.prefix("scorched_chest_left"));
    private static final SpriteId CHEST_SCORCHED_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("scorched_chest_right"));
    private static final SpriteId CHEST_VARNISHED = CHEST_MAPPER.apply(Erebus.prefix("varnished_chest"));
    private static final SpriteId CHEST_VARNISHED_LEFT = CHEST_MAPPER.apply(Erebus.prefix("varnished_chest_left"));
    private static final SpriteId CHEST_VARNISHED_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("varnished_chest_right"));
    private static final SpriteId CHEST_WHITE = CHEST_MAPPER.apply(Erebus.prefix("white_chest"));
    private static final SpriteId CHEST_WHITE_LEFT = CHEST_MAPPER.apply(Erebus.prefix("white_chest_left"));
    private static final SpriteId CHEST_WHITE_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("white_chest_right"));

    @SuppressWarnings("DuplicatedCode")
    public static SpriteId chooseSpriteId(ErebusChestRenderState.ErebusChestMaterialType materialType, ChestType type) {
        return switch(materialType) {
            case ASPER -> chooseSpriteId(type, CHEST_ASPER, CHEST_ASPER_LEFT, CHEST_ASPER_RIGHT);
            case BAMBOO -> chooseSpriteId(type, CHEST_BAMBOO, CHEST_BAMBOO_LEFT, CHEST_BAMBOO_RIGHT);
            case BAOBAB -> chooseSpriteId(type, CHEST_BAOBAB, CHEST_BAOBAB_LEFT, CHEST_BAOBAB_RIGHT);
            case BALSAM -> chooseSpriteId(type, CHEST_BALSAM, CHEST_BALSAM_LEFT, CHEST_BALSAM_RIGHT);
            case CYPRESS -> chooseSpriteId(type, CHEST_CYPRESS, CHEST_CYPRESS_LEFT, CHEST_CYPRESS_RIGHT);
            case EUCALYPTUS -> chooseSpriteId(type, CHEST_EUCALYPTUS, CHEST_EUCALYPTUS_LEFT, CHEST_EUCALYPTUS_RIGHT);
            case MAHOGANY -> chooseSpriteId(type, CHEST_MAHOGANY, CHEST_MAHOGANY_LEFT, CHEST_MAHOGANY_RIGHT);
            case MARSHWOOD -> chooseSpriteId(type, CHEST_MARSHWOOD, CHEST_MARSHWOOD_LEFT, CHEST_MARSHWOOD_RIGHT);
            case MOSSBARK -> chooseSpriteId(type, CHEST_MOSSBARK, CHEST_MOSSBARK_LEFT, CHEST_MOSSBARK_RIGHT);
            case PETRIFIED -> chooseSpriteId(type, CHEST_PETRIFIED, CHEST_PETRIFIED_LEFT, CHEST_PETRIFIED_RIGHT);
            case ROTTEN -> chooseSpriteId(type, CHEST_ROTTEN, CHEST_ROTTEN_LEFT, CHEST_ROTTEN_RIGHT);
            case SCORCHED -> chooseSpriteId(type, CHEST_SCORCHED, CHEST_SCORCHED_LEFT, CHEST_SCORCHED_RIGHT);
            case VARNISHED -> chooseSpriteId(type, CHEST_VARNISHED, CHEST_VARNISHED_LEFT, CHEST_VARNISHED_RIGHT);
            case WHITE -> chooseSpriteId(type, CHEST_WHITE, CHEST_WHITE_LEFT, CHEST_WHITE_RIGHT);
        };
    }

    private static SpriteId chooseSpriteId(ChestType type, SpriteId single, SpriteId left, SpriteId right) {
        return switch (type) {
            case LEFT -> left;
            case RIGHT -> right;
            default -> single;
        };
    }
}
