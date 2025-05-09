package erebus.registries.blocks.providers;

import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;

public class DoorBlocks extends ModBlockHelpers {

    public static final DeferredBlock<DoorBlock> DOOR_BAOBAB;
    public static final DeferredBlock<DoorBlock> DOOR_EUCALYPTUS;
    public static final DeferredBlock<DoorBlock> DOOR_MAHOGANY;
    public static final DeferredBlock<DoorBlock> DOOR_MOSSBARK;
    public static final DeferredBlock<DoorBlock> DOOR_ASPER;
    public static final DeferredBlock<DoorBlock> DOOR_CYPRESS;
    public static final DeferredBlock<DoorBlock> DOOR_BALSAM;
    public static final DeferredBlock<DoorBlock> DOOR_WHITE;
    public static final DeferredBlock<DoorBlock> DOOR_ROTTEN;
    public static final DeferredBlock<DoorBlock> DOOR_MARSHWOOD;
    public static final DeferredBlock<DoorBlock> DOOR_SCORCHED;

    static {
        DOOR_BAOBAB = registerDoor("door_baobab", BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_EUCALYPTUS = registerDoor("door_eucalyptus", BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_MAHOGANY = registerDoor("door_mahogany", BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_MOSSBARK = registerDoor("door_mossbark", BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_ASPER = registerDoor("door_asper", BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_CYPRESS = registerDoor("door_cypress", BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_BALSAM = registerDoor("door_balsam", BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_WHITE = registerDoor("door_white", BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_ROTTEN = registerDoor("door_rotten", BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_MARSHWOOD = registerDoor("door_marshwood", BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_SCORCHED = registerDoor("door_scorched", BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_DOOR));
    }

    public static void init() {
    }
}
