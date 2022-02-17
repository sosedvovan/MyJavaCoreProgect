/**
 * Stream API и исключения
 * Все методы Stream API используют в своей работы функциональные интерфейсы.
 * Методы функциональных интерфейсов не генерируют проверяемых исключений. Как
 * следствие использовать в них методы которые генерируют проверяемые исключения
 * нельзя.
 * Так, как потоки являются способом обработки данных, то если в промежуточном
 * методе может возникнуть исключение, то создано оно будет при запуске терминального
 * метода. Как следствие обработчик исключения нужно применять именно к терминальному
 * методу.
 */
package javaCore34.streamAPI_1.sample5;

import javaCore34.streamAPI_1.sample3.Cat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Порождение потока данных на основе файла
//Для работы создадим вспомогательный текстовый файл cat name.txt
//Luska
//Umka
//Barsic
//Luska
public class Main {

    public static void main(String[] args) {

        //ЗДЕСЬ ПОРОЖДАЕМ СТРИМ НА ФАЙЛЕ - Files.lines()
        //с файлами работаем в блоке try-catch
        //Files.lines - статик метод класса File для порождения стрима для работы с файлами возвращает стрим, выбрасывает эксепшн
        //max-терминальный метод, на основе реализации Компаратора вернет из файла самую длинную строку и положит в Стринговый Optional result
        try {
            Optional<String> result = Files.lines(Path.of("cat name.txt")).max((a, b) -> a.length() - b.length());
            System.out.println(result.get());//возьмем из Optional и выведем в терминале
        } catch (IOException e) {
            e.printStackTrace();
        }

        //-------------------------------------------------------------------------------------------------------
        //                               Stream API и исключения   ЗДЕСЬ ПОРОЖДАЕМ СТРИМ НА СПИСКЕ  - list.stream()

        Cat cat1 = new Cat("Luska", 5, "White");
        Cat cat2 = new Cat(null, 7, "Black");
        Cat cat3 = new Cat("Barsic", 2, "Red");
        Cat cat4 = new Cat("Luska", 3, "Grey");

        List<Cat> list = List.of(cat1, cat2, cat3, cat4);

        //порождаем КОТОстрим на списке котов, применяем промежуточный метод filter
        Stream<Cat> catToName = list.stream().filter(a -> a.getName().length() > 5);

        //Обработку исключений стоит выполнять при вызове терминальных методов:
        try {
            List<Cat> result2 = catToName.collect(Collectors.toList());
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }
}
