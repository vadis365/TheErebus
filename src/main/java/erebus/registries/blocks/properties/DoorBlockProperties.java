package erebus.registries.blocks.properties;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.PushReaction;

public class DoorBlockProperties {

    public static final Properties ASPER;
    public static final Properties BALSAM;
    public static final Properties BAOBAB;
    public static final Properties CYPRESS;
    public static final Properties EUCALYPTUS;
    public static final Properties MAHOGANY;
    public static final Properties MARSHWOOD;
    public static final Properties MOSSBARK;
    public static final Properties ROTTEN;
    public static final Properties SCORCHED;
    public static final Properties WHITE;

    static {
        ASPER = Properties.of()
                .mapColor(Blocks.OAK_PLANKS.defaultMapColor())
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0F).noOcclusion()
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY);

        BALSAM = Properties.of()
                .mapColor(Blocks.OAK_PLANKS.defaultMapColor())
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0F).noOcclusion()
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY);

        BAOBAB = Properties.of()
                .mapColor(Blocks.OAK_PLANKS.defaultMapColor())
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0F).noOcclusion()
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY);

        CYPRESS = Properties.of()
                .mapColor(Blocks.OAK_PLANKS.defaultMapColor())
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0F).noOcclusion()
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY);

        EUCALYPTUS = Properties.of()
                .mapColor(Blocks.OAK_PLANKS.defaultMapColor())
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0F).noOcclusion()
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY);

        MAHOGANY = Properties.of()
                .mapColor(Blocks.OAK_PLANKS.defaultMapColor())
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0F).noOcclusion()
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY);

        MARSHWOOD = Properties.of()
                .mapColor(Blocks.OAK_PLANKS.defaultMapColor())
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0F).noOcclusion()
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY);

        MOSSBARK = Properties.of()
                .mapColor(Blocks.OAK_PLANKS.defaultMapColor())
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0F).noOcclusion()
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY);

        ROTTEN = Properties.of()
                .mapColor(Blocks.OAK_PLANKS.defaultMapColor())
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0F).noOcclusion()
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY);

        SCORCHED = Properties.of()
                .mapColor(Blocks.OAK_PLANKS.defaultMapColor())
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0F).noOcclusion()
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY);

        WHITE = Properties.of()
                .mapColor(Blocks.OAK_PLANKS.defaultMapColor())
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0F).noOcclusion()
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY);
    }
}
