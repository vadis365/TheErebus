package erebus.datagen.providers;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import erebus.Erebus;
import erebus.utils.LangConversionHelper;
import erebus.utils.LangFormatSplitter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class ModLangProvider extends LanguageProvider {

    private final PackOutput output;
    public final Map<String, String> upsideDownEntries = new HashMap<>();

    public ModLangProvider(PackOutput output) {
        super(output, Erebus.MODID, "en_us");
        this.output = output;
    }

    @Override
    public void add(String key, String value) {
        super.add(key, value);
        List<LangFormatSplitter.Component> splitEnglish = LangFormatSplitter.split(value);
        upsideDownEntries.put(key, LangConversionHelper.convertComponents(splitEnglish));
    }

    protected void addEntity(Supplier<? extends EntityType> entity, String name) {
        add(entity.get().getDescriptionId(), name);
        add("item.erebus.%s_spawn_egg".formatted(entity.get().getDescriptionId().replaceFirst("entity.erebus.", "")), "%s Spawn Egg".formatted(name));
    }

    protected void addAdvTitle(String advancementTitle, String name) {
        add("advancement.%s.%s.title".formatted(Erebus.MODID, advancementTitle), name);
    }

    protected void addAdvDesc(String advancementTitle, String name) {
        add("advancement.%s.%s.desc".formatted(Erebus.MODID, advancementTitle), name);
    }

    protected void addSubtitle(String category, String subtitleName, String name) {
        add("subtitles.%s.%s".formatted(category, subtitleName), name);
    }

    protected void addBiome(ResourceKey<Biome> biomeKey, String name) {
        add("biome.%s.%s".formatted(Erebus.MODID, biomeKey.location().getPath()), name);
    }

    protected void addDeath(String deathName, String name) {
        add("death.attack.%s".formatted(deathName), name);
    }

    protected void addPotion(Supplier<? extends Potion> potion, String name) {
        add("item.minecraft.potion.effect.%s".formatted(BuiltInRegistries.POTION.getKey(potion.get()).getPath()), "Potion of %s".formatted(name));
        add("item.minecraft.splash_potion.effect.%s".formatted(BuiltInRegistries.POTION.getKey(potion.get()).getPath()), "Splash Potion of %s".formatted(name));
        add("item.minecraft.lingering_potion.effect.%s".formatted(BuiltInRegistries.POTION.getKey(potion.get()).getPath()), "Lingering Potion of %s".formatted(name));
        add("item.minecraft.tipped_arrow.effect.%s".formatted(BuiltInRegistries.POTION.getKey(potion.get()).getPath()), "Arrow of %s".formatted(name));
    }

    protected void addConfig(String configName, String name) {
        add("config.%s.%s".formatted(Erebus.MODID, configName), name);
    }

    protected void addEnchantment(ResourceKey<Enchantment> enchantment, String name) {
        add("enchantment.%s.%s".formatted(Erebus.MODID, enchantment.location().getPath()), name);
    }

    protected void addJukeboxSong(ResourceKey<JukeboxSong> song, String name) {
        add("jukebox_song.%s.%s".formatted(Erebus.MODID, song.location().getPath()), name);
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        //generate normal lang file
        CompletableFuture<?> languageGen = super.run(cache);
        ImmutableList.Builder<CompletableFuture<?>> futuresBuilder = new ImmutableList.Builder<>();
        futuresBuilder.add(languageGen);

        //generate en_ud file
        JsonObject upsideDownFile = new JsonObject();
        this.upsideDownEntries.forEach(upsideDownFile::addProperty);
        futuresBuilder.add(DataProvider.saveStable(cache, upsideDownFile, this.output.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve(Erebus.MODID).resolve("lang").resolve("en_ud.json")));

        return CompletableFuture.allOf(futuresBuilder.build().toArray(CompletableFuture[]::new));
    }
}
