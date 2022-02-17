package dopMaterial.IO_File.sample2;

//Todo 5.1 Модифицировать проект FindFiles так, чтобы программа искала в каталоге а искомые значения находились в массиве.

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        //создаем ArrayList для заненсения в него результата поиска файлов
        final List<String> list = new ArrayList<>();

        //перечислим расширения файлов, которые будем искать
        final String[] ends = {"txt", "docx", "rar", "xlsx"};

        //в статик метод поиска отправляем директорию(в кот будем искать, массив с искомыми словами, и список для сохранен. результата)
        findFiles("d:\\MyJavaCore\\", ends, list);

        //метод для вывода массива с полученным результатом на экран
        println(list);
    }

    private static void findFiles(
            final String srcPath,//директория в кот будем искать
            final String[] ends,//то, что ищем
            final List<String> list//туда сохраняем результат поиска
    ) {
        final File dir = new File(srcPath);//привязываем новый объект File к реальной директории

        //метод listFiles() возвращает массив файлов и  берет в аргументы реализацию интерфейса FilenameFilter
        //походу этот метод будет перебирать и фильтровать файлы из указанной dir
        final File[] files = dir.listFiles(

        );//в конструктор передаем массив с искомымы строками
        for (File f : files) {//из массива с файлами получим массив с именами файлов
            list.add(srcPath + f.getName());
        }
    }

    private static void println(final Collection<String> collection) {
        for (String value : collection) {
            System.out.println(value);
        }
    }
}