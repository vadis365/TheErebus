package erebus.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import erebus.Erebus;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModEntityTags extends IntrinsicHolderTagsProvider<EntityType<?>> {
	
    public ModEntityTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.ENTITY_TYPE, provider, entity -> entity.builtInRegistryHolder().key(), modId, existingFileHelper);
    }

	@Override
	protected void addTags(Provider provider) {
		tag(EntityTypeTags.ARTHROPOD)
        .add(ModEntities.BLACK_WIDOW.get())
        .add(ModEntities.BOT_FLY.get())
        .add(ModEntities.BOT_FLY_LARVA.get())
        .add(ModEntities.CENTIPEDE.get())
        .add(ModEntities.DRAGON_FLY.get())
        .add(ModEntities.FLY.get())
        .add(ModEntities.GRASSHOPPER.get())
        .add(ModEntities.LAVA_WEB_SPIDER.get())
        .add(ModEntities.LOCUST.get())
        .add(ModEntities.MONEY_SPIDER.get())
        .add(ModEntities.MOTH.get())
        .add(ModEntities.SCYTODES.get())
        .add(ModEntities.VELVET_WORM.get())
        .add(ModEntities.WASP.get());
	}

}
