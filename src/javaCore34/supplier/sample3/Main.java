package javaCore34.supplier.sample3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        //          Частое использование ссылки на конструктор:

        //Очень часто в качестве реализации Supplier используют ссылку на конструктор
        //класса. Это позволяет реализовать «ленивое» создание объекта. Объект создается
        //только когда вызывается метод get() в реализации этого интерфейса. Если этот метод не
        //вызывается то и объект не создается. Это связанно с тем,что реализация Supplier хранит
        //только ссылку на конструктор.

    //получаем реализацию Supplier с пом. ссылки на конструктор - ArrayList
    Supplier<List<String>> sup1 = ArrayList::new;
    //запускаем абстракт Supplier и будет создан пустой ArrayList
    List<String> list = sup1.get();
    System.out.println(list);


    //             Применение Supplier в стандартной библиотеке:

        //В классе Optional описан метод:
        //public T orElseGet (Supplier<? extends T> javaCore34.supplier)
        //Если значение присутствует в Optional, возвращает значение, в противном случае
        //возвращает результат, полученный с помощью реализации Supplier.

        //имеется обычный массив Стрингов
        String[] names = new String[] { "Luke", "Darth", "Obi-Wan", "R2D2" };
        //имеется стринг-маркер
        String firslLetter = "J";
        //в Optional<String> положим то, что вернет метод findNameByFirstLetter()
        Optional<String> name = Optional.ofNullable(findNameByFirstLetter(names, firslLetter));
        //String result присвоим то, что лежит в Optional<String> (достанем от туда),
        //но если там нуль сработает orElseGet и положит в result то, что в лямбде
        String result = name.orElseGet(() -> "Name not found. May the force be with you.");//Реализация Supplier лямбда функцией
        System.out.println(result);

}//закрыли главный метод

    //метод, к которому обращается Optional
    public static String findNameByFirstLetter(String[] names, String firstLetter) {
        for (String name : names) {//итерируемся по массиву Стрингов
            if (name.startsWith(firstLetter)) {//если элемент начинается с String firslLetter = "J"; тогда его и возвращаем из метода в Optional
                return name;
            }
        } return null;//если на заданную букву ничего не найденно- возвращаем нуль
    }

}//закрыли класс
