package erebus.utils;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import it.unimi.dsi.fastutil.doubles.Double2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.doubles.Double2ObjectSortedMap;

public class Codecs {
    public static final Codec<Double> DOUBLE_STRING = Codec.STRING.comapFlatMap(Codecs::parseString2Double, f -> Double.toString(f));

    private Codecs() {
    }

    public static <T> Codec<Double2ObjectSortedMap<T>> doubleTreeCodec(Codec<T> elementCodec) {
        return Codec
                .compoundList(Codecs.DOUBLE_STRING, elementCodec)
                .xmap(floatEList -> floatEList.stream().collect(Double2ObjectAVLTreeMap::new, (map, pair) -> map.put(pair.getFirst(), pair.getSecond()), Double2ObjectAVLTreeMap::putAll), map -> map.entrySet().stream().map(entry -> new Pair<>(entry.getKey(), entry.getValue())).toList());
    }

    private static DataResult<Double> parseString2Double(String string) {
        try {
            return DataResult.success(Double.valueOf(string));
        } catch (Throwable e) {
            return DataResult.error(e::getMessage);
        }
    }

}
