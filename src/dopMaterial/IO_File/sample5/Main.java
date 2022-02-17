package dopMaterial.IO_File.sample5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) {

        String fileName = "d:\\file.txt";

//Строка, которая будет записана в файл
        String data = "Some data to be written and read.\n";
        try{
            //для записи в файл: созд объект FileWriter fw (если файла не сущ. он будет создан)
            FileWriter fw = new FileWriter(fileName);
            //теперь надо создать объект буффера BufferedWriter bw и передать ему fw
            BufferedWriter bw = new BufferedWriter(fw);
            //начнем запись в файл, о чем выведем сообщение:
            System.out.println("Write some data to file: " + fileName);

            // Несколько раз записать строку
            for(int i=(int)(Math.random()*10);--i>=0;)
                //для непосредственной записи на объекте буффера BufferedWriter bw вызовем метод write() передав ему строку
                bw.write(data);
            bw.close();//закрыли буффер

            // теперь Считываем результат
            //создаем объект FileReader fr (отправим ему в конструктор файл кот надо прочесть)
            FileReader fr = new FileReader(fileName);
            //теперь надо создать объект буффера BufferedReader br и передать ему fr
            BufferedReader br = new BufferedReader(fr);
            //создадим Стринговую(кладем туда очередную строку) и Интовую переменные(счетчиком кол-ва строк в файле будет )
            String s = null;
            int count = 0;
            //Начнем считывание из файла, о чем и выведем сообщение:
            System.out.println("Read data from file: " + fileName);

            // Считывать данные, отображая на экран
            //пока метод readLine() возвращает очередную строку
            while((s=br.readLine())!=null)
                //печатаем очередную строку из файла и счетчик
                System.out.println("row " + ++count + " read:" + s);
            br.close();//закрыли буффер
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * Классы Reader и Writer и их наследники
 *
 * Рассмотренные классы – наследники InputStream и OutputStream – работают с байтовыми данными. Если с их помощью
 * записывать или считывать текст, то сначала необходимо сопоставить каждому символу его числовой код. Такое соответствие
 * называется кодировкой.
 *
 * Известно, что Java использует кодировку Unicode, в которой символы представляются двухбайтовым кодом. Байтовые потоки
 * зачастую работают с текстом упрощенно – они просто отбрасывают старший байт каждого символа. В реальных же приложениях
 * могут использовать различные кодировки (даже для русского языка их существует несколько). Поэтому в версии Java 1.1
 * появился дополнительный набор классов, основывающийся на типах Reader и Writer. Их иерархия представлена на рис. 15.2.
 *
 * Эта иерархия очень схожа с аналогичной для байтовых потоков InputStream и OutputStream. Главное отличие между ними –
 * Reader и Writer работают с потоком символов ( char ). Только чтение массива символов в Reader описывается методом
 * read(char[]), а запись в Writer – write(char[]).
 *
 *  Соответствие классов для байтовых и символьных потоков.
 *  Байтовый поток         	Символьный поток
 * InputStream 	               Reader
 * OutputStream 	           Writer
 * ByteArrayInputStream 	CharArrayReader
 * ByteArrayOutputStream 	CharArrayWriter
 * Нет аналога 	               InputStreamReader
 * Нет аналога               	OutputStreamWriter
 * FileInputStream         	FileReader
 * FileOutputStream 	     FileWriter
 * FilterInputStream 	   FilterReader
 * FilterOutputStream 	   FilterWriter
 * BufferedInputStream    	BufferedReader
 * BufferedOutputStream 	BufferedWriter
 * PrintStream          	PrintWriter
 * DataInputStream 	Нет аналога
 * DataOutputStream 	Нет аналога
 * ObjectInputStream 	Нет аналога
 * ObjectOutputStream 	Нет аналога
 * PipedInputStream 	   PipedReader
 * PipedOutputStream 	  PipedWriter
 * StringBufferInputStream 	StringReader
 * Нет аналога 	StringWriter
 * LineNumberInputStream 	LineNumberReader
 * PushBackInputStream 	PushBackReader
 * SequenceInputStream 	Нет аналога
 *
 * Как видно из таблицы, различия крайне незначительны и предсказуемы.
 *
 * Например, конечно же, отсутствует преобразование в символьное представление примитивных типов Java и объектов
 * ( DataInput/Output, ObjectInput/Output ). Добавлены классы-мосты, преобразующие символьные потоки в байтовые:
 * InputStreamReader и OutputStreamWriter. Именно на их основе реализованы FileReader и FileWriter. Метод available()
 * класса InputStream в классе Reader отсутствует, он заменен методом ready(), возвращающим булевое значение,
 * – готов ли поток к считыванию (то есть будет ли считывание произведено без блокирования).
 */
