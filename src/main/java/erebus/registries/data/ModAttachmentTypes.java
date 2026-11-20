package erebus.registries.data;

import erebus.Erebus;
import erebus.network.data.GliderInput;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public final class ModAttachmentTypes {
    public static final DeferredRegister<AttachmentType<?>> TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, Erebus.MODID);
    public static final Supplier<AttachmentType<GliderInput>> GLIDER_INPUT = TYPES.register("glider_input",
            () -> AttachmentType.builder(() -> GliderInput.NONE).build());
    public static final Supplier<AttachmentType<Direction>> CLIMBING_SURFACE = TYPES.register("climbing_surface",
            () -> AttachmentType.builder(() -> Direction.DOWN).serialize(Direction.CODEC.fieldOf("direction"))
                    .sync(Direction.STREAM_CODEC).build());
}
