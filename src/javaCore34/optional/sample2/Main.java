/**
 * В чем разница между map и flatMap:
 * Довольно проблематично отличить разницу между методами map и flatMap ведь оба
 * возвращают Optional. Разница заключается в том:
 * ● map — на основе значения генерирует новое значение. Стоит использовать когда
 * нужно сослаться на метод который принимает значение и возвращает значение.
 * ● flatMap — на основе значения генерируется новый Optional со значением. Стоит
 * использовать когда нужно сослаться на метод который принимает значение и
 * возвращает Optional со значением.
 * По сути метод map для работы с кодом в котором не используется Optional, flatMap
 * для работы с кодом в котором методы его активно используют.
 */

package javaCore34.optional.sample2;

import javaCore34.optional.sample1.Cat;

import java.util.Optional;

//В чем разница между методами Optional, изменяющими данные: map и flatMap
public class Main {

    public static void main(String[] args) {

        Cat cat1 = new Cat("Vaska", "Black");
        Optional<Cat> result = Optional.of(cat1);//of применяем, когда точно объект не нуль

        //По сути метод map для работы с кодом в котором не используется Optional
        Optional<Cat> repCat1 = result.map(Main::repaintCat);//Используем первый метод для map, который вернет измененного кота

        //flatMap для работы с кодом в котором методы активно используют Optional
        Optional<Cat> repCat2 = result.flatMap(Main::repaintCatOptinal);//Используем второй метод для flatMap, который вернет опшионал с изменненым котом
    }//закрыли главный метод


    //Метод принимает Cat и возвращает Cat (принимает кота, перекрашивает кота и возвращает перекрашенного кота)
    public static Cat repaintCat(Cat cat) {
        Cat repaintCat = new Cat(cat.getName(), "white");
        return repaintCat;
    }


    //Метод принимает Cat и возвращает Optional<Cat> (принимает кота, перекрашивает кота, кладет кота в новый опшионал и возвращает кота)
    public static Optional<Cat> repaintCatOptinal(Cat cat) {
        Cat repaintCat = new Cat(cat.getName(), "white");
        Optional<Cat> repaintCatInOptional = Optional.of(repaintCat);
        return repaintCatInOptional;
    }
}


