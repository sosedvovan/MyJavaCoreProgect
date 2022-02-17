package javaCore34.streamAPI_7ТерминальныеМетодыCollect.sample4;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

//другой Пример (параметризованный )реализации Collector
public class GroupByEquals<T> implements Collector<T, Map<T, Integer>, Map<T, Integer>> {

    @Override
    public Supplier<Map<T, Integer>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<T, Integer>, T> accumulator() {
        return (map1, element) -> {
            Integer n = map1.getOrDefault(element, 0);
            map1.put(element, n + 1);
        };
    }

    @Override
    public BinaryOperator<Map<T, Integer>> combiner() {
        return (map1, map2) -> {
            Map<T, Integer> result = new HashMap<>(map1);
            map2.forEach((k, v) -> {
                Integer n = result.getOrDefault(k, 0);
                result.put(k, v + n);
            });
            return result;
        };
    }

    @Override
    public Function<Map<T, Integer>, Map<T, Integer>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH, Collector.Characteristics.UNORDERED);
    }
}
