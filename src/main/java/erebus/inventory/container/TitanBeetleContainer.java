package erebus.inventory.container;

import erebus.entity.TitanBeetle;
import net.minecraft.world.ItemStackWithSlot;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class TitanBeetleContainer extends SimpleContainer {

    private final TitanBeetle entity;

    public TitanBeetleContainer(TitanBeetle entity) {
        super(27);
        this.entity = entity;
    }

    public void fromSlots(ValueInput.TypedInputList<ItemStackWithSlot> list) {
        for (int c = 0; c < getContainerSize(); c++) {
            setItem(c, ItemStack.EMPTY);
        }

        list.forEach(item -> {
            if (item.isValidInContainer(getContainerSize())) {
                setItem(item.slot(), item.stack());
            }
        });
    }

    public void storeSlots(ValueOutput.TypedOutputList<ItemStackWithSlot> list) {
        for (int c = 0; c < getContainerSize(); c++) {
            if (!getItem(c).isEmpty()) list.add(new ItemStackWithSlot(c, getItem(c)));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return entity.canUseCargo(player, false);
    }
}
