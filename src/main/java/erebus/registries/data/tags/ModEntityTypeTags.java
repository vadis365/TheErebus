package erebus.registries.data.tags;

import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class ModEntityTypeTags {
    public static final TagKey<EntityType<?>> CAN_BE_PRESERVED = TagKey.create(Registries.ENTITY_TYPE, Erebus.prefix("can_be_preserved"));
}
