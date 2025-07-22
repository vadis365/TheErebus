package erebus.registries.client;

import erebus.Erebus;
import erebus.registries.ModItems;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.ItemLike;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.TextureAtlasStitchedEvent;
import org.jetbrains.annotations.ApiStatus;

import java.util.Arrays;
import java.util.Set;

@EventBusSubscriber(value = Dist.CLIENT, modid = Erebus.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModShieldMaterials {

    public static final Material LOCATION_BAMBOO_SHIELD = material("item/shield_boss_and_handle.png");
    public static final Material LOCATION_EXOSKELETON_SHIELD = material("item/shield_boss_and_handle.png");
    public static final Material LOCATION_JADE_SHIELD = material("item/shield_boss_and_handle.png");
    public static final Material LOCATION_RHINO_EXOSKELETON_SHIELD = material("item/shield_boss_and_handle.png");
    public static final Material LOCATION_REIN_EXOSKELETON_SHIELD = material("item/shield_boss_and_handle.png");

    private static final Material[] materials = {
            LOCATION_BAMBOO_SHIELD,
            LOCATION_EXOSKELETON_SHIELD,
            LOCATION_JADE_SHIELD,
            LOCATION_RHINO_EXOSKELETON_SHIELD,
            LOCATION_REIN_EXOSKELETON_SHIELD
    };

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            addShieldPropertyOverrides(
                    Erebus.prefix("blocking"),
                    (stack, level, entity, seed) -> {
                        if (entity != null && entity.isUsingItem() && entity.getUseItem().is(stack.getItem())) {
                            return 1;
                        }
                        return 0;
                    },
                    ModItems.BAMBOO_SHIELD,
                    ModItems.EXOSKELETON_SHIELD,
                    ModItems.JADE_SHIELD,
                    ModItems.RHINO_EXOSKELETON_SHIELD,
                    ModItems.REIN_EXOSKELETON_SHIELD
            );
        });
    }

    @SubscribeEvent
    public static void onStitch(Pre event) {
        if(event.getAtlas().location().equals(InventoryMenu.BLOCK_ATLAS)) {
            Arrays.stream(materials).forEach(material -> {
                event.addSprite(material.texture());
            });
        }
    }

    private static Material material(String path) {
        return new Material(InventoryMenu.BLOCK_ATLAS, Erebus.prefix(path));
    }

    private static void addShieldPropertyOverrides(ResourceLocation override, ClampedItemPropertyFunction propertyGetter, ItemLike... shields) {
        for(ItemLike shield : shields) {
            ItemProperties.register(shield.asItem(), override, propertyGetter);
        }
    }

    public static class Pre extends TextureAtlasStitchedEvent {
        private final Set<ResourceLocation> sprites;

        @ApiStatus.Internal
        public Pre(TextureAtlas map, Set<ResourceLocation> sprites) {
            super(map);
            this.sprites = sprites;
        }

        public void addSprite(ResourceLocation sprite) {
            sprites.add(sprite);
        }
    }
}
