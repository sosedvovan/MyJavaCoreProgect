package dopMaterial.IO_File.sample3;

import java.io.File;
import java.io.FilenameFilter;
//Todo Пример списка файлов с определенным расширением
public class FileNameFilterExample {

    public static void main(String[] args) {
        // будем искать в папке tmp
        String dir = "d:\\MyJavaCore\\";
        // в этой папке будем искать файлы с расширением .xml
        String ext = ".txt";
        // вызываем метод поиска файлов с расширением .xml в папке tmp
        findFiles(dir, ext);
    }

    // метод поиска
    private static void findFiles(String dir, String ext) {//в аргументах директория для поиска и искомая строка
        File file = new File(dir);//к объекту файл привязываем директорию в кот будем искать
        if (!file.exists()) System.out.println(dir + " папка не существует");//проверка на существование директории
        //в аргументы метода listFiles передаем реализацию интерфейса MyFileNameFilter, на выходе массив с подходящими файлами
        File[] listFiles = file.listFiles(new MyFileNameFilter(ext));
        if (listFiles.length == 0) {
            System.out.println(dir + " не содержит файлов с расширением " + ext);
        } else {
            for (File f : listFiles)//печатаем из массива наиденных файлов их пути и имена
                System.out.println("Файл: " + dir + File.separator + f.getName());
           // System.out.println(File.separator);// это /
        }
    }

    // Реализация интерфейса FileNameFilter
    public static class MyFileNameFilter implements FilenameFilter {

        private String ext;

        public MyFileNameFilter(String ext) {
            this.ext = ext.toLowerCase();
        }

        @Override
        public boolean accept(File dir, String name) {//в аргументах директория для поиска и искомая строка
            return name.toLowerCase().endsWith(ext);//endsWith() - это походу фильтр строк по последним чарам
            //возвращает буль- true, если строка оканчивается на заданное
        }
    }
}