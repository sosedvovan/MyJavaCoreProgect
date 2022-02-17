package javaCore34.streamAPI_7ТерминальныеМетодыCollect.sample3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

// Todo: Пример implements Collector классом

//надо реализовать все абстрактные методы интерфейса Collector
public class NumberClassificator implements Collector<Integer, List<Integer>, List<Integer>> {
    //поле- ссылка на реализацию Predicate
    private Predicate<Integer> addOrNot;

    //конструктор
    public NumberClassificator(Predicate<Integer> addOrNot) {
        super();
        this.addOrNot = addOrNot;
    }

    @Override//метод для первого этапа- создает структуру данных
    public Supplier<List<Integer>> supplier() {
        return ArrayList::new;//Возврат реализации javaCore34.supplier
    }

    @Override//метод для второго этапа - аккумулятора- накапливает данные в созданной выше структуре данных
    public BiConsumer<List<Integer>, Integer> accumulator() {
        return (a, b) -> {
            if (addOrNot.test(b)) {
                a.add(b);
            }//Возврат реализации для accumulator
        };
    }

    @Override//метод для третьего этапа -работает при параллельных потоках- аккумулирует данные с нескольких потоков в новую структуру данных
    public BinaryOperator<List<Integer>> combiner() {
        return (a, b) -> {
            List<Integer> result = new ArrayList<>();
            result.addAll(a);
            result.addAll(b);
            return result;//Возврат реализации для combiner
        };
    }

    @Override//метод для четвертого этапа - для финальных действий- сортировки, например
    public Function<List<Integer>, List<Integer>> finisher() {
        return (a) -> {
            List<Integer> sortedList = new ArrayList<>();
            sortedList.addAll(a);
            Collections.sort(sortedList);
            return sortedList;//Вернуть реализацию finisher
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }
}