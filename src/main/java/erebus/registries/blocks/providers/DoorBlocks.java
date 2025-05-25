package erebus.registries.blocks.providers;

import erebus.block.util.ModBlockSetTypes;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.PushReaction;
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

    private static final Properties PROPS = Properties.of()
            .mapColor(Blocks.OAK_PLANKS.defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0F).noOcclusion()
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY);

    static {
        DOOR_ASPER = registerDoor("door_asper", ModBlockSetTypes.ASPER, PROPS);
        DOOR_BALSAM = registerDoor("door_balsam", ModBlockSetTypes.BALSAM, PROPS);
        DOOR_BAOBAB = registerDoor("door_baobab", ModBlockSetTypes.BAOBAB, PROPS);
        DOOR_CYPRESS = registerDoor("door_cypress", ModBlockSetTypes.CYPRESS, PROPS);
        DOOR_EUCALYPTUS = registerDoor("door_eucalyptus", ModBlockSetTypes.EUCALYPTUS, PROPS);
        DOOR_MAHOGANY = registerDoor("door_mahogany", ModBlockSetTypes.MAHOGANY, PROPS);
        DOOR_MARSHWOOD = registerDoor("door_marshwood", ModBlockSetTypes.MARSHWOOD, PROPS);
        DOOR_MOSSBARK = registerDoor("door_mossbark", ModBlockSetTypes.MOSSBARK, PROPS);
        DOOR_ROTTEN = registerDoor("door_rotten", ModBlockSetTypes.ROTTEN, PROPS);
        DOOR_SCORCHED = registerDoor("door_scorched", ModBlockSetTypes.SCORCHED, PROPS);
        DOOR_WHITE = registerDoor("door_white", ModBlockSetTypes.WHITE, PROPS);
    }

    public static void init() {
    }
}
