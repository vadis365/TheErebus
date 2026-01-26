package erebus.client.render.block.renderer.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.world.level.block.state.properties.ChestType;

public class ErebusChestRenderState extends BlockEntityRenderState {

    public ChestType type;
    public float open;
    public float angle;
    public ErebusChestMaterialType material;

    public ErebusChestRenderState() {
        this.type = ChestType.SINGLE;
        this.material = ErebusChestMaterialType.ASPER;
    }

    public enum ErebusChestMaterialType {
        ASPER,
        BAMBOO,
        BAOBAB,
        BALSAM,
        CYPRESS,
        EUCALYPTUS,
        MAHOGANY,
        MARSHWOOD,
        MOSSBARK,
        PETRIFIED,
        ROTTEN,
        SCORCHED,
        VARNISHED,
        WHITE
    }
}
