package dopMaterial.IO_File.sample1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Main2 {

    public static void main(String[] args) throws IOException {

        // Создать папки по указанному пути
        File f = new File("C:\\temp\\temp2");

        // Создать папки C:\temp\, C:\temp\\temp2
        if (f.mkdirs())
            System.out.println("The folders is created.");//...
        else
            System.out.println("The folders is not created");



        //метод createNewFile() будет только создавать файл, но не записывать в него никакого содержимого
        //Источник: https://java-blog.ru/osnovy/kak-sozdat-fayl-java
        File file = new File("c://temp//temp2//testFile1.txt");
//create the file.
        if (file.createNewFile()){
            System.out.println("File is created!");
        }
        else{
            System.out.println("File already exists.");
        }
//write content
        FileWriter writer = new FileWriter (file);
        writer.write("Test data");
        writer.close();


        //Если вы хотите создать новый файл и в то же время, если хотите записать в него некоторые данные,
        // вы можете использовать метод записи FileOutputStream. В Java FileOutputStream является классом потока байтов.
        // Чтобы записать данные в файл, вы должны преобразовать данные в байты, а затем сохранить их в файл.
        //Источник: https://java-blog.ru/osnovy/kak-sozdat-fayl-java

        String data = "Test data";
        //поток FileOutputStream  принимает файл "c://temp//temp2//testFile2.txt"  и создает его
        FileOutputStream out = new FileOutputStream("c://temp//temp2//testFile2.txt");
        out.write(data.getBytes());
        out.close();//если не закроем- не будет доступа к файлу

    }

    //как дописать в файл
    //как запустить текстовый файл
    //как скопировать файл
    //как прочитать из файла
}
