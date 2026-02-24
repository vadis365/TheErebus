package erebus.inventory.container;

import erebus.entity.TitanBeetle;
import net.minecraft.world.ItemStackWithSlot;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

public class TitanBeetleContainer extends SimpleContainer {

    private final TitanBeetle entity;

    public TitanBeetleContainer(TitanBeetle entity) {
        super(27);
        this.entity = entity;
    }

    public void fromSlots(ValueInput.TypedInputList<ItemStackWithSlot> list) {
        for(int c = 0; c < getContainerSize(); c++) {
            setItem(c, ItemStack.EMPTY);
        }

        list.forEach(item -> {
            if(item.isValidInContainer(getContainerSize())) {
                setItem(item.slot(), item.stack());
            }
        });
    }

    public void storeSlots(ValueOutput.TypedOutputList<ItemStackWithSlot> list) {
        for(int c = 0; c < getContainerSize(); c++) {
            list.add(new ItemStackWithSlot(c, getItem(c)));
        }
    }

    @Override
    public void stopOpen(@NonNull ContainerUser user) {
        super.stopOpen(user);
        entity.setOpen(false);
    }
}
