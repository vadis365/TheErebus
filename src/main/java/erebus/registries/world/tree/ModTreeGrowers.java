package erebus.registries.world.tree;

import erebus.Erebus;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

import static erebus.registries.world.feature.TreeFeatures.*;

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
        ASPER = new TreeGrower("%s:asper".formatted(Erebus.MODID), Optional.empty(), Optional.of(ASPER_TREE.getConfiguredResourceKey()), Optional.empty());
        BALSAM = new TreeGrower("%s:balsam".formatted(Erebus.MODID), Optional.empty(), Optional.of(BALSAM_TREE.getConfiguredResourceKey()), Optional.empty());
        BAOBAB = new TreeGrower("%s:baobab".formatted(Erebus.MODID), Optional.empty(), Optional.of(BAOBAB_TREE.getConfiguredResourceKey()), Optional.empty());
        CYPRESS = new TreeGrower("%s:cypress".formatted(Erebus.MODID), Optional.empty(), Optional.of(CYPRESS_TREE.getConfiguredResourceKey()), Optional.empty());
        EUCALYPTUS = new TreeGrower("%s:eucalyptus".formatted(Erebus.MODID), Optional.empty(), Optional.of(EUCALYPTUS_TREE.getConfiguredResourceKey()), Optional.empty());
        MAHOGANY = new TreeGrower("%s:mahogany".formatted(Erebus.MODID), Optional.of(GIANT_MAHOGANY_TREE.getConfiguredResourceKey()), Optional.of(MAHOGANY_TREE.getConfiguredResourceKey()), Optional.empty());
        MARSHWOOD = new TreeGrower("%s:marshwood".formatted(Erebus.MODID), Optional.empty(), Optional.of(MARSHWOOD_TREE.getConfiguredResourceKey()), Optional.empty());
        MOSSBARK = new TreeGrower("%s:mossbark".formatted(Erebus.MODID), Optional.empty(), Optional.of(MOSSBARK_TREE.getConfiguredResourceKey()), Optional.empty());
        BAMBOO = new TreeGrower("%s:bamboo".formatted(Erebus.MODID), Optional.empty(), Optional.of(BAMBOO_TREE.getConfiguredResourceKey()), Optional.empty());
    }
}
