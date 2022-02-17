package javaCore34.streamAPI_8КлассCollectors.sample4;

import java.util.List;
import java.util.stream.Collectors;

//Todo Пример применения метода reducing/ В примере с помощью метода reducing возвращается аккумулирующая реализация Collector. В
// качестве начального элемента при аккумулировании используется 0. Реализация BinaryOperator
// описывает сложение элементов. Таким образом такая реализация Collector выполнит сложение всех элементов потока
//
public class Main {

    public static void main(String[] args) {

        List<Integer> listNumber = List.of(1, 2, 3, 4, 5);
        Integer result = listNumber.stream().collect(Collectors.reducing(0, (a, b) -> a + b));
    }
}
