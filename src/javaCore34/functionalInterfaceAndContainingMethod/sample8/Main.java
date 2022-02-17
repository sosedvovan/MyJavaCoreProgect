package javaCore34.functionalInterfaceAndContainingMethod.sample8;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {

        //здесь былы бы ошибка компиляции, если в сигнатуру абстрактного метода интерфейса не добавить: throws IOException
        Saver<String> sr = Main::saveToFile;
        //ТО, ЕСЛИ МЕТОД (КОТ ИСП КАК ССЫЛКА ДЛЯ РЕАЛИЗАЦ ФУНКЦ ИНТЕРФЕЙСА) ГЕНЕРИР ИСКЛЮЧЕНИЕ, ТО И В ИНТЕРФЕЙСЕ
        // ДОЛЖНЫ БЫТЬ ОПИСАНЫ ЭТИЖЕ ИСКЛЮЧЕНИЯ

        //здесь былы бы ошибка компиляции, если в сигнатуру главного метода main не добавить: throws IOException или не взять в try-catch
        try {
            sr.saveTo("Hello world");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //этот метод используется абстрактным методом функционального интерфейса для своей реализации чз механизм ссылок на методы
    //и тк этот метод может выбросить исключение- то в сигнатуре абстрактного метода это исключение должно быть перечисленно
    //и в методе main, тоже должно быть перечисленно, тк оно не отлавливается блоком Try-catch
    public static <T> void saveToFile(T el) throws IOException {
        PrintWriter pw = new PrintWriter(new File("save.txt"));
        pw.println(el);
    }
}
