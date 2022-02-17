/**
 *            Задача выполнения каких либо действий при отличной от null ссылки в Optional:
 *
 * Довольно частой является ситуация когда в случае успешного поиска или получения
 * объекта нужно произвести то или иное действие. Если же объект не был получен то
 * действий производить не нужно было. Ниже приведен пример как это реализовывали в
 * случае если в результате поиска могла быть возвращена ссылка на объект или null.
 *
 *    Cat cat = findCatByName("Vaska", cats);
 *    if (cat != null) {  //Если возвращенная ссылка не равна null. Выполнить действие
 *    System.out.println(cat);
 *    }
 *
 *
 *            Задача выполнения каких либо действий при отличной от null ссылки в Optional:
 *
 * Класс Optional предлагает ряд методов которые упрощают реализацию данной
 * задачи.
 *                     Метод                                                 Когда использовать
 * void ifPresent (Consumer<? super T> action)               Если значение ссылки равно не равно null то выполнить
 *                                                           действие с использованием ссылки, в противном случае
 *                                                           ничего не делать.
 *
 * void ifPresentOrElse (Consumer<? super T> action, Runnable emptyAction)
 *                                                           Если значение ссылки не равно null то выполнить
 *                                                           действие с использованием ссылки, в противном случае
 *                                                           выполнить альтернативное действие.
 *
 ***********************************************************************************************************************
 *                                       Метод stream():
 * В классе Optional присутствует метод Stream<T> stream(). Если ссылка не равна null
 * то будет возвращен Stream с этой ссылкой, в противном случае пустой Stream.
 * Рассмотрение потоков (Stream) и методов работы с ними будет рассмотрено позже.
 *
 ***********************************************************************************************************************
 *                                 Где стоит использовать Optional и где нет:
 *                Использовать Optional стоит в:
 * ● Методы поиска
 * ● Методы получения объекта
 * ● Если множественные параметры метода могут иметь значение null, для их
 * передачи стоит использовать Optional
 *
 *               Использовать Optional не стоит в:
 * ● Качестве полей класса (не сериализуется)
 * ● В качестве параметра метода установки
 */
package javaCore34.optional.sample3;

import javaCore34.optional.sample1.Cat;

import java.util.Optional;

import static javaCore34.optional.sample1.Main.findCatByNameOptional;//статический импорт статического метода из другого класса

public class Main {

    public static void main(String[] args) {

        Cat cat1 = new Cat("Vaska", "Black");
        Cat cat2 = new Cat("Barsik", "Whit");
        Cat cat3 = new Cat("Umka", "Gray");

        Cat[] cats = new Cat[] {cat1, cat2, cat3};


        //                        Пример применения ifPresent:
        Optional<Cat> result = findCatByNameOptional("Vaska", cats);
        result.ifPresent(a -> System.out.println(a)); //Если в Optional не null. Выполнить действие.(Консуммер)
        //В данном примере Optional<Cat> является результатом метода поиска. В случае если
        //в Optional не null то будет выполнен метод подставленный в качестве параметра
        //(реализация Consumer). В данном случае в качестве реализации используется лямбда
        //функция.


        //                        Пример применения ifPresentOrElse:
        Optional<Cat> result2 = findCatByNameOptional("Vaskaр", cats);
        result2.ifPresentOrElse(System.out::println, () -> System.out.println("Not found"));
        //где System.out::println Выполнится если ссылка не null
        //а System.out.println("Not found") Выполнится если ссылка равна null

        //В данном примере Optional<Cat> является результатом метода поиска. В случае если
        //в Optional не null то будет выполнен метод подставленный в качестве первого параметра
        //(реализация Consumer). В данном случае в качестве реализации используется ссылку на
        //метод System.out.println. Если же ссылка равна null то будет вызван метод подставленный
        //в качестве второго параметра (реализация Runnable). В примере лямбда функция
        //приводящая в выводу на экран надписи «Not found».
    }


}