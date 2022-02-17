package javaCore34.streamAPI_7ТерминальныеМетодыCollect.sample2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // Todo: Применение collect(метод от stream()). Из листа Интеджеров сделаем 2-а листа в мапе с четными и нечетными элементами

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Map<String, List<Integer>> result = numbers.stream()

                //для получения реализаций абстрактов исспользуем ссылки на методы:
                .collect(Main::createMap, Main::addToMap, Main::mergeMap);

        System.out.println(result);

    }//закрыли главный метод

    //генератор нового Map,( List, Set, Queque). С этой структурой будет работать след аккумулирующий метод
    public static Map<String, List<Integer>> createMap() {
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("even", new ArrayList<>());//по ключу "even" кладем new ArrayList для четных чисел
        map.put("odd", new ArrayList<>());//по ключу "odd" кладем new ArrayList для нечетных чисел
        return map;
    }

    //BiConsumer - аккумулирует в 2-х ArrayList'ах четные и нечетные, и добавляет эти ArrayList'ы в map, созданной в предидущем методе
    public static void addToMap(Map<String, List<Integer>> map, Integer element) {
        if (element % 2 == 0) {
            map.get("even").add(element);
        } else {
            map.get("odd").add(element);
        }
    }

    //BiConsumer - Собираем ArrayList'ы с разных потоков в один, если разрешенны параллельные потоки - работает
    //в аргументах принимает два объекта с одного потока и объединяет их в один
    public static void mergeMap(Map<String, List<Integer>> map1, Map<String, List<Integer>> map2) {
        map2.forEach((k, v) -> map1.get(k).addAll(v));//все элементы map2 добавляем к map1
    }
}

/**
 *
 *               Метод:   <R, A> R collect (Collector<? super T, A, R> collector)  С ОДНИМ АРГУМЕНТОМ
 *
 * Создает результат аккумулируя элементы потока в структуру данных. Для этого используется
 * реализация интерфейса Collector. (ОН НЕ ФУНКЦИОНАЛЬНЫЙ)
 * Параметры:
 * T - данные элемента потока
 * A - тип структуры для накопления данных
 * R - тип результата (структура данных)
 *
 * В КЛАССЕ Collectors  ЕСТЬ МНОГО ГОТОВЫХ МЕТОДОВ-РЕАЛИЗАЦИЙ ДЛЯ ЕГО АРГУМЕНТА
 */