package javaCore34.streamAPI_7ТерминальныеМетодыCollect.sample4;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collector;

public class Main {

    //Todo: Использование реализации Collector. В этой реализации Collector мы получаем карту со статистикой элементов
    // потока и сколько раз в потоке он встречается.

    public static void main(String[] args) {

        //получаем реализацию Collector
        Collector<String, Map<String, Integer>, Map<String, Integer>> collector = new GroupByEquals<>();

        //имеем стрингу, будем делать из нее массив чаров
        String text = "hello world";

        Map<String, Integer> result = Arrays.stream(text.split("")).collect(collector);

        System.out.println(result);
    }
}
