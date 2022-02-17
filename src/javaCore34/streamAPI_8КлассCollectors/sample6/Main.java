package javaCore34.streamAPI_8КлассCollectors.sample6;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //Todo Пример применения методов для получения статистики

        List<String> names = List.of("Katia", "Alexander", "Olga", "Inna");
        ToIntFunction<String> mapper = a -> a.length();
        Collector<String, ?, Double> collector1 = Collectors.averagingInt(mapper);
        double average = names.stream().collect(collector1);//Нахождение среднего
        System.out.println(average);
        long total = names.stream().count();//Подсчет элементов в потоке
        System.out.println(total);
        long totalSum = names.stream().collect(Collectors.summingInt(mapper));//Нахождение суммы
        System.out.println(totalSum);
        IntSummaryStatistics istat = names.stream().collect(Collectors.summarizingInt(mapper));//Сборная статистика
        System.out.println("Sum = " + istat.getSum());
        System.out.println("Average = " + istat.getAverage());
        System.out.println("Max = " + istat.getMax());
        System.out.println("Min = " + istat.getMin());
        System.out.println("Count = " + istat.getCount());

        //5.5
        //4
        //22
        //Sum = 22
        //Average = 5.5
        //Max = 9
        //Min = 4
        //Count = 4

        //------------------------------------------------------------------------------------------------------------

        //Todo Пример применения joining. В примере элементы потока строк объединяются с помощью joining в одну строку, при этом в
        // качестве разделителя между его элементами используется запятая. В начале строки вставляется
        // слово Hello в конце восклицательный знак.

        List<String> names2 = List.of("Katia", "Alexander", "Olga", "Inna");
        String result = names2.stream().collect(Collectors.joining(", ", "Hello ", "!"));
        System.out.println(result);

        //Hello Katia, Alexander, Olga, Inna!
    }
}
