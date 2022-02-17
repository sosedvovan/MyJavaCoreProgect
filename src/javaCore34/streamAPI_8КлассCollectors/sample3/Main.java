package javaCore34.streamAPI_8КлассCollectors.sample3;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//Todo Пример применения partitioningBy В данном примере проводится бинарная группировка данных потока на основании javaCore34.predicate. Т.е.
// входные данные группируются на два типа ключей true и false. Каждым значением становиться список
// в который группируются элементы потока.
public class Main {

    public static void main(String[] args) {

        List<Integer> listNumber = List.of(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = a -> a % 2 == 0;
        Map<Boolean, List<Integer>> result = listNumber.stream()
                .collect(Collectors.partitioningBy(predicate));

        System.out.println(result);//{false=[1, 3, 5], true=[2, 4]}

    }
}
