package erebus.registries.world;

import erebus.Erebus;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

import static erebus.registries.world.ModConfiguredFeatures.*;

public class ModTreeGrowers {

    public static final TreeGrower ASPER;
    public static final TreeGrower BALSAM;
    public static final TreeGrower BAOBAB;
    public static final TreeGrower CYPRESS;
    public static final TreeGrower EUCALYPTUS;
    public static final TreeGrower MAHOGANY;
    public static final TreeGrower MARSHWOOD;
    public static final TreeGrower MOSSBARK;
    public static final TreeGrower BAMBOO;

    static {
        ASPER = new TreeGrower("%s:asper".formatted(Erebus.MODID), Optional.empty(), Optional.of(ASPER_KEY), Optional.empty());
        BALSAM = new TreeGrower("%s:balsam".formatted(Erebus.MODID), Optional.empty(), Optional.of(BALSAM_KEY), Optional.empty());
        BAOBAB = new TreeGrower("%s:baobab".formatted(Erebus.MODID), Optional.empty(), Optional.of(BAOBAB_KEY), Optional.empty());
        CYPRESS = new TreeGrower("%s:cypress".formatted(Erebus.MODID), Optional.empty(), Optional.of(CYPRESS_KEY), Optional.empty());
        EUCALYPTUS = new TreeGrower("%s:eucalyptus".formatted(Erebus.MODID), Optional.of(GIANT_EUCALYPTUS_KEY), Optional.of(EUCALYPTUS_KEY), Optional.empty());
        MAHOGANY = new TreeGrower("%s:mahogany".formatted(Erebus.MODID), Optional.of(GIANT_MAHOGANY_KEY), Optional.of(MAHOGANY_KEY), Optional.empty());
        MARSHWOOD = new TreeGrower("%s:marshwood".formatted(Erebus.MODID), Optional.empty(), Optional.of(MARSHWOOD_KEY), Optional.empty());
        MOSSBARK = new TreeGrower("%s:mossbark".formatted(Erebus.MODID), Optional.empty(), Optional.of(MOSSBARK_KEY), Optional.empty());
        BAMBOO = new TreeGrower("%s:bamboo".formatted(Erebus.MODID), Optional.empty(), Optional.of(BAMBOO_KEY), Optional.empty());
    }
}
