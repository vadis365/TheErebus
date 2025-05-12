package erebus.registries.world.tree;

import erebus.Erebus;
import erebus.world.feature.tree.decorator.LeaveDarkFruitVineDecorator;
import erebus.world.feature.tree.decorator.LeaveThornDecorator;
import erebus.world.feature.tree.decorator.TrunkThornDecorator;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTreeDecorators {

    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, Erebus.MODID);

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<LeaveThornDecorator>> LEAVE_THORN_DECORATOR;
    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<TrunkThornDecorator>> TRUNK_THORN_DECORATOR;
    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<LeaveDarkFruitVineDecorator>> LEAVE_DARK_FRUIT_VINE_DECORATOR;

    static {
        LEAVE_THORN_DECORATOR = TREE_DECORATORS.register("leave_thorn_decorator", () -> new TreeDecoratorType<>(LeaveThornDecorator.CODEC));
        TRUNK_THORN_DECORATOR = TREE_DECORATORS.register("trunk_thorn_decorator", () -> new TreeDecoratorType<>(TrunkThornDecorator.CODEC));
        LEAVE_DARK_FRUIT_VINE_DECORATOR = TREE_DECORATORS.register("leave_dark_fruit_vine_decorator", () -> new TreeDecoratorType<>(LeaveDarkFruitVineDecorator.CODEC));
    }
}
