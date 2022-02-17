/**
 * основной инструмент - класс File
 * Работа с файлами в Java. Класс File. Основные методы работы
 * В данной теме расписаны базовые методы класса File, который есть частью системы ввода/вывода Java. Для рассмотренных
 * методов приведены примеры с объяснениями.
 *
 * Содержание
 * 1. Создание экземпляра класса File. Конструкторы класса
 * 2. Метод getName(). Получить имя файла или каталога
 * 3. Метод getPath(). Получить имя файла
 * 4. Метод isAbsolute(). Определить, указан ли полный путь к файлу
 * 5. Метод getAbsolutePath(). Получить полный путь к файлу
 * 6. Методы canRead(), canWrite(). Определение того, допускает ли файловый объект чтение и запись
 * 7. Метод exists(). Определение наличия файла (каталога)
 * 8. Метод isDirectory(). Определить, связан ли файловый объект с директорием
 * 9. Метод isFile(). Определить, связан ли файловый объект с файлом
 * 10. Метод isHidden(). Определить, есть ли файловый объект скрытым
 * 11. Метод length(). Определить размер в байтах файла, который связан с файловым объектом
 * 12. Метод delete(). Удаление файла
 * 13. Метод mkdir(). Создание папки или каталога
 * 14. Метод mkdirs(). Создание нескольких вложенных папок
 * 15. Метод renameTo(). Переименование файла
 * 16. Метод getTotalSpace(). Определение объема диска
 * 17. Метод getFreeSpace(). Определить свободное место на диске
 * 18. Метод getUsableSpace(). Определение полезного места на диске
 * https://www.bestprog.net/ru/2020/06/11/java-work-with-files-in-java-class-file-basic-working-methods-ru/
 *
 */
package dopMaterial.IO_File.sample1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        //1. Создание экземпляра класса File. Конструкторы класса

        //конструктор принимает абсолютный или относительный путь
        //f1 - это объект ассоциированный с файлом
        //если файла нет в дисковой системе, то объект ассоциируется с вирт файлом в озу

        // Конструкторы класса File
        // 1. Конструктор, который получает одну строку типа String,
        //     создается файловый объект (переменная) с именем f1
        File f1 = new File("output.txt"); // задан сокращенный путь
        String path1 = f1.getPath();
        System.out.println(path1);//output.txt

        // 2. Конструктор, который получает две строки:
        //     - первая строка задает абсолютный путь к файловому элементу,
        //     - вторая строка задает имя файлового элемента.
        File f2 = new File("C:\\", "Program Files");
        String path2 = f2.getPath(); // path2 = "C:\\Program Files"
        System.out.println("path2 = " + path2);//path2 = C:\Program Files

        // 3. Конструктор типа File(File, String)
        File f3 = new File("C:\\Programs\\Java\\Project22");
        File f4 = new File(f3, "output.txt");
        String path3 = f3.getPath();
        String path4 = f4.getPath();
        System.out.println("path3 = " + path3); // path3 = C:\Programs\Java\Project22
        System.out.println("path4 = " + path4); // path4 = C:\Programs\Java\Project22\output.txt
        //******************************************************************************************************
        //2. Метод getName(). Получить имя файла или каталога

        // Метод getName() - получить имя файла
        // Объявить файловый объект и связать его с именем файла
        File f = new File("C:\\Program Files");
        String name = f.getName(); // name = "Program Files"
        System.out.println("name = " + name);//name = Program Files
        //********************************************************************************************************
        //3. Метод getPath(). Получить имя файла

        // Метод getPath() - получить полный путь к файлу
        // Объявить файловый объект и связать его с именем файла
        File ff = new File("output.txt"); // имя текстового файла
        String Path = ff.getPath();
        System.out.println("Path = " + Path);//Path = output.txt

        // Указать полное имя при создании файлового объекта
        ff = new File("C:\\Program Files");
        Path = ff.getPath();
        System.out.println("Path = " + Path); // Path = C:\Program Files
        //**********************************************************************************************************
        //4. Метод isAbsolute(). Определить, указан ли полный путь к файлу.
        // true, если при создании файловой переменной fileObj был задан абсолютный путь.

        // Метод isAbsolute() - задан ли полный путь к файлу (каталогу)
        // Объявить файловый объект и связать его с именем файла
        File f11 = new File("output.txt"); // задан сокращенный путь
        boolean res1;
        res1 = f11.isAbsolute();
        System.out.println("res1 = " + res1); // res1 = false

        // Задать полное имя при создании файлового объекта
        File f22 = new File("C:\\Program Files");
        boolean res2 = f22.isAbsolute();
        System.out.println("res2 = " + res2); // res2 = true
        //**********************************************************************************************************
        //5. Метод getAbsolutePath(). Получить полный путь к файлу

        // Метод getAbsolutePath() - получить полный путь к файлу
        // Объявить файловый объект и связать его с именем файла
        File f12 = new File("output.txt"); // имя текстового файла
        String absPath = f12.getAbsolutePath();
        System.out.println("Path = " + absPath);//Path = D:\MyJavaCore\MyJavaCoreProgect\output.txt
        //**************************************************************************************************************
        //6. Методы canRead(), canWrite(). Определение того, допускает ли файловый объект чтение и запись
        //В случае положительного результата методы возвращают true.

        File f13 = new File("output.txt");

        if (f13.canRead())
            System.out.println("The result of canRead() is true.");
        else
            System.out.println("The result of canRead() is false");//The result of canRead() is false

        if (f13.canWrite())
            System.out.println("The result of canWrite() is true.");
        else
            System.out.println("The result of canWrite() is false");//The result of canRead() is false
        //**************************************************************************************************************
        //7. Метод exists(). Определение наличия файла (каталога)

        // Метод exists() - определить, существует ли файл (директорий)
        // Объявить файловый объект и связать его с именем файла
        File f14 = new File("output.txt"); // имя текстового файла

        // Если файл f существует, то вывести соответствующее сообщение
        if (f14.exists())
            System.out.println("File is present.");
        else
            System.out.println("File is not present"); // файл отсутствует

        // Определить, существует ли каталог
        File f15 = new File("C:\\Program Files");

        if (f15.exists())
            System.out.println("The directory is present.");//The directory is present.
        else
            System.out.println("The directory is not present.");
        //**************************************************************************************************
        //8. Метод isDirectory(). Определить, связан ли файловый объект с директорием

        // Метод isDirectory - определить, есть ли файл директорием (папкой)
        // 1. Объявить файловую переменную и связать ее именем файла или папки
        File f16 = new File("C:\\Program Files"); // имя папки, которую нужно проверить

        // Если файл f существует, то определить его тип
        if (f16.exists()) {
            if (f16.isDirectory()) {
                System.out.println("Directory.");//Directory.
            }
            else
                System.out.println("File.");
        }
        else
            System.out.println("The file is not present"); // файл отсутствует
        //*****************************************************************************************************
        //9. Метод isFile(). Определить, связан ли файловый объект с файлом

        // Метод isFile() - определить, связан ли файловый объект с файлом
        // 1. Объявить файловый объект и связать его с именем файла
        File f17 = new File("output.txt"); // имя файла

        // Если файл f существует, то определить его тип
        if (f17.exists()) {
            if (f17.isFile()) {
                System.out.println("File object is file.");
            }
            else
                System.out.println("File object is not file.");
        }
        else
            System.out.println("The file is not present"); // файл отсутствует
        //********************************************************************************************************
        // 10. Метод isHidden(). Определить, есть ли файловый объект скрытым

        File f18 = new File("output.txt");

        if (f18.isHidden())
            System.out.println("The file \"output.txt\" is hidden.");
        else
            System.out.println("The file \"output.txt\" is not hidden.");//The file "output.txt" is not hidden.
        //********************************************************************************************************
        //11. Метод length(). Определить размер в байтах файла, который связан с файловым объектом

        // Определить размер файла в байтах
        // 1. Объявить файловую переменную
        File f19 = new File("abc.txt");

        // 2. Создать поток, который записывает символы в файл f
        FileWriter fw = new FileWriter(f19);

        // 3. Записать в файл строку "Hello world!"
        fw.write("Hello world!");

        // 4. Закрыть поток
        fw.close();

        // 5. Определить длину файла "abc.txt"
        long len = f19.length();

        // 6. Вывести длину файла на консоль
        System.out.println("len = " + len); // len = 12
        //********************************************************************************************************
        //12. Метод delete(). Удаление файла

        // Метод delete() - удалить файл по его имени
        // Объявить файловую переменную
        File f20 = new File("somefile.txt");

        // Удалить файл, если он существует
        if (f20.exists()) {
            f20.delete();
            System.out.println("The file is deleted.");
        }
        else
            System.out.println("The file is not present.");//The file is not present.
        //*********************************************************************************************************
        //13. Метод mkdir(). Создание папки или каталога

        // Метод mkdir() - создать каталог
        // 1. Объявить файловую переменную и связать ее с папкой в текущем каталоге
        //     (задается относительный путь).
        File f21 = new File("123");

        // Если каталог не существует, то создать его
        if (!f21.exists()) {
            if (f21.mkdir()) {
                System.out.println("The folder is created!"); // создан успешно
            }
            else
                System.out.println("The folder is not created.");
        }
        else
            System.out.println("The folder is present.");

        // 2. Создать файловую переменную и связать ее с папкой
        //     C:\Programs\345 - абсолютный путь к папке.
        File f23 = new File("C:\\Programs\\345");
        boolean res = f23.mkdir();

        if (res)
            System.out.println("The folder C:\\Programs\\345 is created.");
        else
            System.out.println("The folder C:\\Programs\\345 is not created.");//The folder C:\Programs\345 is not created.???
        //************************************************************************************************************
        //14. Метод mkdirs(). Создание нескольких вложенных папок

        // Создать папки по указанному пути
        File f24 = new File("C:\\ABC\\DEF\\GHI");

        // Создать папки C:\ABC, C:\ABC\DEF, C:\ABC\DEF\GHI
        if (f24.mkdirs())
            System.out.println("The folders is created.");//...
        else
            System.out.println("The folders is not created");
        //*************************************************************************************************************
        //15. Метод renameTo(File). Переименование файла

        // Метод renameTo() - переименовать файл.
        // Переименовать файл strings.txt в файл array.txt.
        // В обеих файлах задается относительный путь
        // 1. Объявить файловую переменную и связать ее с файлом
        File f25 = new File("abc.txt");
        File f26 = new File("array.txt");

        // Если файл f1 существует, то переименовать его
        if (f25.exists()) {
            if (f25.renameTo(f26)) {
                System.out.println("Ok!"); // переименован успешно ЭТО
            }
            else
                System.out.println("The file is not renamed."); // файл не переименован
        }
        else
            System.out.println("The file is not present");
        //************************************************************************************************************
        //16. Метод getTotalSpace(). Определение объема диска

        // Определить размер диска
        File f27 = new File("E:\\");

        // Определить общий объем жесткого диска E:
        long totalSpace = f27.getTotalSpace();
        System.out.println("Drive E: - Total space = " + totalSpace);//Drive E: - Total space = 0

        // Определить объем диска C:
        f27 = new File("C:\\");
        totalSpace = f27.getTotalSpace();
        System.out.println("Drive C: - Total space = " + totalSpace);//Drive C: - Total space = 240062033920
        //************************************************************************************************************
       // 17. Метод getFreeSpace(). Определить свободное место на диске

        // Определить размер диска
        File f28 = new File("E:\\");

        // Определить размер свободной памяти на диске
        long freeSpace = f28.getFreeSpace();
        System.out.println("Drive E: - Free space = " + freeSpace);

        // Определить свободное место на диске C:
        f28 = new File("C:\\");
        freeSpace = f28.getFreeSpace();
        System.out.println("Drive C: - Free space = " + freeSpace);//Drive C: - Free space = 110500380672
        //**************************************************************************************************
        //18. Метод getUsableSpace(). Определение полезного места на диске

        // Определить размер диска
        File f29 = new File("E:\\");

        // Определить объем полезного места на диске E:
        long usableSpace = f29.getUsableSpace();
        System.out.println("Drive E: - Usable space = " + usableSpace);

        // Определить объем диска C: и свободное место на диске C:
        f29 = new File("C:\\");
        usableSpace = f29.getUsableSpace();
        System.out.println("Drive C: - Usable space = " + usableSpace);//Drive C: - Usable space = 110479519744




    }
}
