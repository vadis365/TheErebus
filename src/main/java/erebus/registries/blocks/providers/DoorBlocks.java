package erebus.registries.blocks.providers;

import erebus.block.util.ModBlockSetTypes;
import erebus.registries.blocks.properties.DoorBlockProperties;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.DoorBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class DoorBlocks extends ModBlockHelpers {

    public static final DeferredBlock<DoorBlock> ASPER;
    public static final DeferredBlock<DoorBlock> BALSAM;
    public static final DeferredBlock<DoorBlock> BAOBAB;
    public static final DeferredBlock<DoorBlock> CYPRESS;
    public static final DeferredBlock<DoorBlock> EUCALYPTUS;
    public static final DeferredBlock<DoorBlock> MAHOGANY;
    public static final DeferredBlock<DoorBlock> MARSHWOOD;
    public static final DeferredBlock<DoorBlock> MOSSBARK;
    public static final DeferredBlock<DoorBlock> ROTTEN;
    public static final DeferredBlock<DoorBlock> SCORCHED;
    public static final DeferredBlock<DoorBlock> WHITE;

    static {
        ASPER = registerDoor("door_asper", ModBlockSetTypes.ASPER, DoorBlockProperties.ASPER);
        BALSAM = registerDoor("door_balsam", ModBlockSetTypes.BALSAM, DoorBlockProperties.BALSAM);
        BAOBAB = registerDoor("door_baobab", ModBlockSetTypes.BAOBAB, DoorBlockProperties.BAOBAB);
        CYPRESS = registerDoor("door_cypress", ModBlockSetTypes.CYPRESS, DoorBlockProperties.CYPRESS);
        EUCALYPTUS = registerDoor("door_eucalyptus", ModBlockSetTypes.EUCALYPTUS, DoorBlockProperties.EUCALYPTUS);
        MAHOGANY = registerDoor("door_mahogany", ModBlockSetTypes.MAHOGANY, DoorBlockProperties.MAHOGANY);
        MARSHWOOD = registerDoor("door_marshwood", ModBlockSetTypes.MARSHWOOD, DoorBlockProperties.MARSHWOOD);
        MOSSBARK = registerDoor("door_mossbark", ModBlockSetTypes.MOSSBARK, DoorBlockProperties.MOSSBARK);
        ROTTEN = registerDoor("door_rotten", ModBlockSetTypes.ROTTEN, DoorBlockProperties.ROTTEN);
        SCORCHED = registerDoor("door_scorched", ModBlockSetTypes.SCORCHED, DoorBlockProperties.SCORCHED);
        WHITE = registerDoor("door_white", ModBlockSetTypes.WHITE, DoorBlockProperties.WHITE);
    }

    public static void init() {
    }
}
