package erebus.registries.blocks.providers;

import erebus.block.util.ModBlockSetTypes;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.neoforge.registries.DeferredBlock;

public class DoorBlocks extends ModBlockHelpers {

    public static final DeferredBlock<DoorBlock> DOOR_ASPER;
    public static final DeferredBlock<DoorBlock> DOOR_BALSAM;
    public static final DeferredBlock<DoorBlock> DOOR_BAOBAB;
    public static final DeferredBlock<DoorBlock> DOOR_CYPRESS;
    public static final DeferredBlock<DoorBlock> DOOR_EUCALYPTUS;
    public static final DeferredBlock<DoorBlock> DOOR_MAHOGANY;
    public static final DeferredBlock<DoorBlock> DOOR_MARSHWOOD;
    public static final DeferredBlock<DoorBlock> DOOR_MOSSBARK;
    public static final DeferredBlock<DoorBlock> DOOR_ROTTEN;
    public static final DeferredBlock<DoorBlock> DOOR_SCORCHED;
    public static final DeferredBlock<DoorBlock> DOOR_WHITE;

    static {
        DOOR_ASPER = registerDoor("door_asper", ModBlockSetTypes.ASPER, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_BALSAM = registerDoor("door_balsam", ModBlockSetTypes.BALSAM, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_BAOBAB = registerDoor("door_baobab", ModBlockSetTypes.BAOBAB, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_CYPRESS = registerDoor("door_cypress", ModBlockSetTypes.CYPRESS, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_EUCALYPTUS = registerDoor("door_eucalyptus", ModBlockSetTypes.EUCALYPTUS, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_MAHOGANY = registerDoor("door_mahogany", ModBlockSetTypes.MAHOGANY, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_MARSHWOOD = registerDoor("door_marshwood", ModBlockSetTypes.MARSHWOOD, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_MOSSBARK = registerDoor("door_mossbark", ModBlockSetTypes.MOSSBARK, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_ROTTEN = registerDoor("door_rotten", ModBlockSetTypes.ROTTEN, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_SCORCHED = registerDoor("door_scorched", ModBlockSetTypes.SCORCHED, Properties.ofFullCopy(Blocks.OAK_DOOR));
        DOOR_WHITE = registerDoor("door_white", ModBlockSetTypes.WHITE, Properties.ofFullCopy(Blocks.OAK_DOOR));
    }

    public static void init() {
    }
}
