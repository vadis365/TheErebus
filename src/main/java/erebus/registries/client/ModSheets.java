package erebus.registries.client;

import erebus.Erebus;
import erebus.client.render.block.renderer.state.ErebusChestRenderState;
import net.minecraft.client.renderer.MaterialMapper;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.state.properties.ChestType;

public class ModSheets {
    private static final MaterialMapper CHEST_MAPPER = new MaterialMapper(Sheets.CHEST_SHEET, "entity/chest");

    private static final Material CHEST_ASPER = CHEST_MAPPER.apply(Erebus.prefix("asper_chest"));
    private static final Material CHEST_ASPER_LEFT = CHEST_MAPPER.apply(Erebus.prefix("asper_chest_left"));
    private static final Material CHEST_ASPER_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("asper_chest_right"));
    private static final Material CHEST_BAMBOO = CHEST_MAPPER.apply(Erebus.prefix("bamboo_chest"));
    private static final Material CHEST_BAMBOO_LEFT = CHEST_MAPPER.apply(Erebus.prefix("bamboo_chest_left"));
    private static final Material CHEST_BAMBOO_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("bamboo_chest_right"));
    private static final Material CHEST_BAOBAB = CHEST_MAPPER.apply(Erebus.prefix("baobab_chest"));
    private static final Material CHEST_BAOBAB_LEFT = CHEST_MAPPER.apply(Erebus.prefix("baobab_chest_left"));
    private static final Material CHEST_BAOBAB_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("baobab_chest_right"));
    private static final Material CHEST_BALSAM = CHEST_MAPPER.apply(Erebus.prefix("balsam_chest"));
    private static final Material CHEST_BALSAM_LEFT = CHEST_MAPPER.apply(Erebus.prefix("balsam_chest_left"));
    private static final Material CHEST_BALSAM_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("balsam_chest_right"));
    private static final Material CHEST_CYPRESS = CHEST_MAPPER.apply(Erebus.prefix("cypress_chest"));
    private static final Material CHEST_CYPRESS_LEFT = CHEST_MAPPER.apply(Erebus.prefix("cypress_chest_left"));
    private static final Material CHEST_CYPRESS_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("cypress_chest_right"));
    private static final Material CHEST_EUCALYPTUS = CHEST_MAPPER.apply(Erebus.prefix("eucalyptus_chest"));
    private static final Material CHEST_EUCALYPTUS_LEFT = CHEST_MAPPER.apply(Erebus.prefix("eucalyptus_chest_left"));
    private static final Material CHEST_EUCALYPTUS_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("eucalyptus_chest_right"));
    private static final Material CHEST_MAHOGANY = CHEST_MAPPER.apply(Erebus.prefix("mahogany_chest"));
    private static final Material CHEST_MAHOGANY_LEFT = CHEST_MAPPER.apply(Erebus.prefix("mahogany_chest_left"));
    private static final Material CHEST_MAHOGANY_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("mahogany_chest_right"));
    private static final Material CHEST_MARSHWOOD = CHEST_MAPPER.apply(Erebus.prefix("marshwood_chest"));
    private static final Material CHEST_MARSHWOOD_LEFT = CHEST_MAPPER.apply(Erebus.prefix("marshwood_chest_left"));
    private static final Material CHEST_MARSHWOOD_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("marshwood_chest_right"));
    private static final Material CHEST_MOSSBARK = CHEST_MAPPER.apply(Erebus.prefix("mossbark_chest"));
    private static final Material CHEST_MOSSBARK_LEFT = CHEST_MAPPER.apply(Erebus.prefix("mossbark_chest_left"));
    private static final Material CHEST_MOSSBARK_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("mossbark_chest_right"));
    private static final Material CHEST_PETRIFIED = CHEST_MAPPER.apply(Erebus.prefix("petrified_chest"));
    private static final Material CHEST_PETRIFIED_LEFT = CHEST_MAPPER.apply(Erebus.prefix("petrified_chest_left"));
    private static final Material CHEST_PETRIFIED_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("petrified_chest_right"));
    private static final Material CHEST_ROTTEN = CHEST_MAPPER.apply(Erebus.prefix("rotten_chest"));
    private static final Material CHEST_ROTTEN_LEFT = CHEST_MAPPER.apply(Erebus.prefix("rotten_chest_left"));
    private static final Material CHEST_ROTTEN_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("rotten_chest_right"));
    private static final Material CHEST_SCORCHED = CHEST_MAPPER.apply(Erebus.prefix("scorched_chest"));
    private static final Material CHEST_SCORCHED_LEFT = CHEST_MAPPER.apply(Erebus.prefix("scorched_chest_left"));
    private static final Material CHEST_SCORCHED_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("scorched_chest_right"));
    private static final Material CHEST_VARNISHED = CHEST_MAPPER.apply(Erebus.prefix("varnished_chest"));
    private static final Material CHEST_VARNISHED_LEFT = CHEST_MAPPER.apply(Erebus.prefix("varnished_chest_left"));
    private static final Material CHEST_VARNISHED_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("varnished_chest_right"));
    private static final Material CHEST_WHITE = CHEST_MAPPER.apply(Erebus.prefix("white_chest"));
    private static final Material CHEST_WHITE_LEFT = CHEST_MAPPER.apply(Erebus.prefix("white_chest_left"));
    private static final Material CHEST_WHITE_RIGHT = CHEST_MAPPER.apply(Erebus.prefix("white_chest_right"));

    @SuppressWarnings("DuplicatedCode")
    public static Material chooseMaterial(ErebusChestRenderState.ErebusChestMaterialType materialType, ChestType type) {
        return switch(materialType) {
            case ASPER -> chooseMaterial(type, CHEST_ASPER, CHEST_ASPER_LEFT, CHEST_ASPER_RIGHT);
            case BAMBOO -> chooseMaterial(type, CHEST_BAMBOO, CHEST_BAMBOO_LEFT, CHEST_BAMBOO_RIGHT);
            case BAOBAB -> chooseMaterial(type, CHEST_BAOBAB, CHEST_BAOBAB_LEFT, CHEST_BAOBAB_RIGHT);
            case BALSAM -> chooseMaterial(type, CHEST_BALSAM, CHEST_BALSAM_LEFT, CHEST_BALSAM_RIGHT);
            case CYPRESS -> chooseMaterial(type, CHEST_CYPRESS, CHEST_CYPRESS_LEFT, CHEST_CYPRESS_RIGHT);
            case EUCALYPTUS -> chooseMaterial(type, CHEST_EUCALYPTUS, CHEST_EUCALYPTUS_LEFT, CHEST_EUCALYPTUS_RIGHT);
            case MAHOGANY -> chooseMaterial(type, CHEST_MAHOGANY, CHEST_MAHOGANY_LEFT, CHEST_MAHOGANY_RIGHT);
            case MARSHWOOD -> chooseMaterial(type, CHEST_MARSHWOOD, CHEST_MARSHWOOD_LEFT, CHEST_MARSHWOOD_RIGHT);
            case MOSSBARK -> chooseMaterial(type, CHEST_MOSSBARK, CHEST_MOSSBARK_LEFT, CHEST_MOSSBARK_RIGHT);
            case PETRIFIED -> chooseMaterial(type, CHEST_PETRIFIED, CHEST_PETRIFIED_LEFT, CHEST_PETRIFIED_RIGHT);
            case ROTTEN -> chooseMaterial(type, CHEST_ROTTEN, CHEST_ROTTEN_LEFT, CHEST_ROTTEN_RIGHT);
            case SCORCHED -> chooseMaterial(type, CHEST_SCORCHED, CHEST_SCORCHED_LEFT, CHEST_SCORCHED_RIGHT);
            case VARNISHED -> chooseMaterial(type, CHEST_VARNISHED, CHEST_VARNISHED_LEFT, CHEST_VARNISHED_RIGHT);
            case WHITE -> chooseMaterial(type, CHEST_WHITE, CHEST_WHITE_LEFT, CHEST_WHITE_RIGHT);
        };
    }

    private static Material chooseMaterial(ChestType type, Material single, Material left, Material right) {
        return switch (type) {
            case LEFT -> left;
            case RIGHT -> right;
            default -> single;
        };
    }
}
