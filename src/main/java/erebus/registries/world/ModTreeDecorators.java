package erebus.registries.world;

import erebus.Erebus;
import erebus.world.tree.decorator.LeaveThornDecorator;
import erebus.world.tree.decorator.TrunkThornDecorator;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTreeDecorators {

    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, Erebus.MODID);

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<LeaveThornDecorator>> LEAVE_THORN_DECORATOR = TREE_DECORATORS.register("leave_thorn_decorator", () -> new TreeDecoratorType<>(LeaveThornDecorator.CODEC));
    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<TrunkThornDecorator>> TRUNK_THORN_DECORATOR = TREE_DECORATORS.register("trunk_thorn_decorator", () -> new TreeDecoratorType<>(TrunkThornDecorator.CODEC));

    public static void register(IEventBus bus) {
        TREE_DECORATORS.register(bus);
    }
}
