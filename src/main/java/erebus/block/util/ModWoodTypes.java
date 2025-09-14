package erebus.block.util;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {

    public static final WoodType ASPER;
    public static final WoodType BAMBOO;
    public static final WoodType BAOBAB;
    public static final WoodType BALSAM;
    public static final WoodType CYPRESS;
    public static final WoodType EUCALYPTUS;
    public static final WoodType MAHOGANY;
    public static final WoodType MARSHWOOD;
    public static final WoodType MOSSBARK;
    public static final WoodType PETRIFIED;
    public static final WoodType ROTTEN;
    public static final WoodType SCORCHED;
    public static final WoodType VARNISHED;
    public static final WoodType WHITE;

    static {
        ASPER = register("asper", ModBlockSetTypes.ASPER);
        BAMBOO = register("bamboo", ModBlockSetTypes.BAMBOO);
        BAOBAB = register("baobab", ModBlockSetTypes.BAOBAB);
        BALSAM = register("balsam", ModBlockSetTypes.BALSAM);
        CYPRESS = register("cypress", ModBlockSetTypes.CYPRESS);
        EUCALYPTUS = register("eucalyptus", ModBlockSetTypes.EUCALYPTUS);
        MAHOGANY = register("mahogany", ModBlockSetTypes.MAHOGANY);
        MARSHWOOD = register("marshwood", ModBlockSetTypes.MARSHWOOD);
        MOSSBARK = register("mossbark", ModBlockSetTypes.MOSSBARK);
        PETRIFIED = register("petrified", ModBlockSetTypes.SCORCHED);
        ROTTEN = register("rotten", ModBlockSetTypes.ROTTEN);
        SCORCHED = register("scorched", ModBlockSetTypes.SCORCHED);
        VARNISHED = register("varnished", ModBlockSetTypes.VARNISHED);
        WHITE = register("white", ModBlockSetTypes.WHITE);
    }

    private static WoodType register(String name, BlockSetType type) {
        return new WoodType(name, type);
    }

    private static WoodType register(String name, BlockSetType type, SoundType sound, SoundType hangingSignSound, SoundEvent fenceGateClose, SoundEvent fenceGateOpen) {
        return new WoodType(name, type, sound, hangingSignSound, fenceGateClose, fenceGateOpen);
    }
}
