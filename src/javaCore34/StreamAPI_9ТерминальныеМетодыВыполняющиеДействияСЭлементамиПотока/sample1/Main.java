/**
 * Stream API.
 * Часть 9. Терминальные методы выполняющие действия с элементами потока
 * <p>
 * Ряд терминальных методов предназначен для выполнения действия над каждым
 * элементом потока. Этими действиями могут быть, например сохранение элемента в
 * файл, вывод на экран и т.д.
 * <p>
 * Список терминальных методов:
 * <p>
 * void forEach (Consumer<? super T> action)
 * Выполнит действие над каждым элементом потока
 * Выполняет действие определяемое реализацией Consumer над каждым элементом потока.
 * Порядок выполнения не гарантируется. Таким образом порядок элементов в потоке может и не
 * соблюдаться. Обладает преимуществом для параллельных потоков.
 * <p>
 * void forEachOrdered (Consumer<? super T> action)
 * Выполнит действие над каждым элементом потока в порядке потока
 * Выполняет действие определяемое реализацией Consumer над каждым элементом потока.
 * Причем учитывается порядок элементов потока (если для потока определен порядок).
 *
 * ***********************************************************************************************************
 *                           Список терминальных методов для сбора в массив
 *
 * Object[] toArray()
 * Вернет данные в виде массива
 *
 * <A> A[] toArray (IntFunction<A[]> generator)
 * Вернет данные в виде массива с возможностью управлять размером массива
 *
 */

package javaCore34.StreamAPI_9ТерминальныеМетодыВыполняющиеДействияСЭлементамиПотока.sample1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {

        //Todo Пример применения метода forEach. В этом примере терминальный метод forEach выводит на экран каждый элемент
        // потока. Т.е. действием тут является вывод на экран.

        List<String> language = List.of("Java", "Fortran", "Python");

        language.stream().forEach(a -> System.out.println(a));

        //---------------------------------------------------------------------------------------------------------------

        //Todo Пример применения метода forEach. дозапись в файл

        //имеем список, который хотим записать в файл
        List<String> message = List.of("start service", "runnable", "shutdown");

        //создали файл в ОЗУ
        File file = new File("message.txt");

        //порождаем поток строк листа.forEach() обработает каждую строку способом, ко указан в реализации Консаммера(дозапись в файл)
        //Использование реализации Consumer в методе forEach. В абстракт Консаммера прийдет файл,
        //там будет побобочное действие- дозапись в файл
        message.stream().forEach(getFileLogger(file));


    }

    //Метод возвращающий реализацию Consumer
    public static Consumer<String> getFileLogger(File file) {
        //локальный класс
        class FileLogger implements Consumer<String> {
            private File file;

            public FileLogger(File file) {
                super();
                this.file = file;
            }

            @Override//реализует абстракт Консаммера-побочного действия
            public void accept(String t) {
                //открываем файл на дозапись
                try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
                    pw.println(t);//производим запись в файл
                    System.out.println(t);//выведем строку которую дозаписываем
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new FileLogger(file);//возвращаем объект-действие-реализацию в forEach()

        //--------------------------------------------------------------------------------------------------------





    }
}
