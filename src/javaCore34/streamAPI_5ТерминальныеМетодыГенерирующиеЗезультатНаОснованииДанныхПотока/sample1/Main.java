/**
 * Терминальные методы генерирующие результат на основании данных потока
 * <p>
 * Терминальные методы
 * Терминальные методы (“terminal”) - методы обрабатывающие элементы потока и
 * завершающие его работу. Терминальный метод в потоке может быть только один. Именно
 * вызов терминального метода и запускает процесс выполнения цепочки промежуточных
 * методов.
 * В этой лекции будут рассмотрены терминальные методы которые возвращают одно
 * значение на основании данных потока.
 * <p>
 * Список рассматриваемых абстрактных терминальных методов:
 * <p>
 * boolean allMatch (Predicate<? super T> javaCore34.predicate)
 * Вернет true если все элементы потока удовлетворяют Predicate
 * <p>
 * boolean anyMatch (Predicate<? super T> javaCore34.predicate)
 * Вернет true если хотя бы один элемент потока удовлетворяет Predicate
 * <p>
 * boolean noneMatch (Predicate<? super T> javaCore34.predicate)
 * Вернет true если не один элемент не удовлетворяет Predicate
 * <p>
 * Optional<T> findAny()
 * Вернет Optional если элемент в потоке есть, или пустой если нет
 * <p>
 * Optional<T> findFirst()
 * Вернет Optional с первым элементом в потоке есть, или пустой если нет
 * <p>
 * long count()
 * Вернет количество элементов в потоке данных
 * <p>
 * Optional<T> max (Comparator<? super T> javaCore34.comparator)
 * Вернет максимальный элемент из потока данных
 * <p>
 * Optional<T> min (Comparator<? super T> javaCore34.comparator)
 * Вернет минимальный элемент из потока данных
 * *********************************************************************
 */
package javaCore34.streamAPI_5ТерминальныеМетодыГенерирующиеЗезультатНаОснованииДанныхПотока.sample1;

import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        //             Методы возвращающие значение boolean типа

        //СРАВНИВАЕМ КАЖДЫЙ ЭЛЕМЕНТ ОДНОГО СПИСКА С КАЖДЫМ ЭЛЕМЕНТОМ ВТОРОГО СПИСКА!!!!!!!!!!!!!!!!!!!

        //Группа методов предназначена для генерации значения boolean типа в зависимости
        //от данных потока.

        //boolean allMatch (Predicate<? super T> javaCore34.predicate) — вернет true в том случае если все
        //элементы потока удовлетворяют реализации Predicate.

        //boolean anyMatch (Predicate<? super T> javaCore34.predicate) — вернет true если хотя бы один
        //элемент потока удовлетворяет реализации Predicate.

        //boolean noneMatch (Predicate<? super T> javaCore34.predicate) — вернет true если все элементы
        //потока не удовлетворяют реализации Predicate.

        //                     Пример использования этих методов

        //список языков, которые надо знать для выполнения проекта
        List<String> projectLanguages = List.of("Java", "Fortran", "C", "C++", "Python", "Ruby", "JS");

        //список языков, которые я знаю
        List<String> iKnow = List.of("Java", "Fortran", "C", "Python");

        //получим реализацию предиката из метода, его возвращающего(возвращает объект, но это действие)
        Predicate<String> predicate = check(iKnow);//в метод, создающ предикат отправляем iKnow, ищи внизу

        //проверка есть ли хотя бы один элемент потока в списке языков которые знаю я
        //Терминальный метод anyMatch Вернет true если хотя бы один элемент потока удовлетворяет Predicate
        //в данном случае поток предикатов будет: true true true false true false false  -> true будет в if
        if (projectLanguages.stream().anyMatch(predicate)) {//поток порождает список языков которые надо знать -> в аргумент абстракта попадают его элементы
            System.out.println("I can implement part of the task ");//
        } else {
            System.out.println("I cant help ");
        }

        //проверка что все элементы потока находятся в этом списке
        //Терминальный метод allMatch Вернет true если все элементы потока удовлетворяют Predicate
        //в данном случае поток предикатов будет: true true true false true false false  -> false будет в if
        if (projectLanguages.stream().allMatch(predicate)) {
            System.out.println("I can implement the whole task");
        } else {
            System.out.println("I cannot complete the whole task");
        }

        //В пример есть список строк которые представляют набор языков программирования
        //которые используются в текущей задаче. С помощью метода anyMath выполняется
        //проверка есть ли хотя бы один элемент потока в списке языков которые знаю я. И с
        //помощью метода allMath что все элементы потока находятся в этом списке.


    }//закрыли главный метод

    //Это вспомогательный метод который возвращает реализацию интерфейса Predicate.(один предикат для 2-х методов- anyMatch и allMatch)
    //Суть реализации если элемент из iKnow есть в списке projectLanguages то вернуть true, в противном случае false.
    //абстракт test(T t) принимает текущий элемент projectLanguages в потоке и в цикле for сравнивает с каждым элементом iKnow ->
    //-> при первом совпадении возвр true метод } и -> в stream; если нет совпадений, тогда возвр false, метод } и -> в stream до след вызова
    public static <T> Predicate<T> check(List<T> list) {//в аргументы ПРИНИМАЕМ список iKnow(языков, которые я знаю) !!!!!!!!!

        class CheckLanguage implements Predicate<T> {//имплементим Predicate<T>

            @Override//в аргументы ПРИНИМАЕМ лист projectLanguages породивший поток !!!!!!!!!!!!!!!
            public boolean test(T t) {//переопределяем абстракт интерфейса.он принимает текущий элемент листа, кот породил stream: projectLanguages
                for (T element : list) {//итерируемся по iKnow(лист, который пришел в аргументы этого метода check(List<T> list) )
                    if (element.equals(t)) {//сравниваем каждый элемент iKnow (element.) с equals(t) ->
                        return true;        //-> где t - это текущий элемент projectLanguages в потоке
                    }
                }
                return false;
            }
        }
        return new CheckLanguage();
    }
}
