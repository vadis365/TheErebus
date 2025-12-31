package erebus.utils;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.HolderSetCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterHolderSet<T> implements HolderSet<T> {

    private final HolderSet<T> base;
    private final HolderSet<T> filter;
    private Set<Holder<T>> set = null;
    private List<Holder<T>> list = null;

    public FilterHolderSet(HolderSet<T> base, HolderSet<T> filter) {
        this.base = base;
        this.filter = filter;
    }

    public static <T> MapCodec<FilterHolderSet<T>> codec(ResourceKey<? extends Registry<T>> key, Codec<Holder<T>> holder, boolean forceList) {
        return RecordCodecBuilder.mapCodec(
                builder -> builder
                        .group(
                                HolderSetCodec.create(key, holder, forceList).fieldOf("base").forGetter(FilterHolderSet::base),
                                HolderSetCodec.create(key, holder, forceList).fieldOf("filter").forGetter(FilterHolderSet::filter)
                        )
                        .apply(builder, FilterHolderSet::new)
        );
    }

    public HolderSet<T> base() {
        return base;
    }

    public HolderSet<T> filter() {
        return filter;
    }

    protected Set<Holder<T>> createSet() {
        return base.stream().filter(filter::contains).collect(Collectors.toSet());
    }

    public Set<Holder<T>> getSet() {
        if (set == null) {
            set = createSet();
        }
        return set;
    }

    public List<Holder<T>> getList() {
        if (list == null) {
            list = List.copyOf(getSet());
        }
        return list;
    }

    @Override
    public @NotNull Stream<Holder<T>> stream() {
        return getList().stream();
    }

    @Override
    public int size() {
        return getList().size();
    }

    @Override
    public boolean isBound() {
        return false;
    }

    @Override
    public @NotNull Either<TagKey<T>, List<Holder<T>>> unwrap() {
        return Either.right(getList());
    }

    @Override
    public @NotNull Optional<Holder<T>> getRandomElement(@NotNull RandomSource random) {
        return size() > 0 ? Optional.of(getList().get(random.nextInt(size()))) : Optional.empty();
    }

    @Override
    public @NotNull Holder<T> get(int i) {
        return getList().get(i);
    }

    @Override
    public boolean contains(@NotNull Holder<T> holder) {
        return getSet().contains(holder);
    }

    @Override
    public boolean canSerializeIn(@NotNull HolderOwner<T> holderOwner) {
        return base.canSerializeIn(holderOwner) && filter.canSerializeIn(holderOwner);
    }

    @Override
    public @NotNull Optional<TagKey<T>> unwrapKey() {
        return Optional.empty();
    }

    @Override
    public @NotNull Iterator<Holder<T>> iterator() {
        return getList().iterator();
    }
}
