package dopMaterial.IO_File.sample7_Alishev.writeInFile;

import dopMaterial.IO_File.sample7_Alishev.People;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteInFile {
    public static void main(String[] args) throws IOException {

        File file = new File("StringText.txt");
        file.createNewFile();

        PrintWriter pw = new PrintWriter(file);//пишет в файл
        pw.println("String one");
        pw.println("String two");
        pw.println("String three");

        People people1 = new People(1, "Name1");
        pw.println(people1);

        pw.close();



        byte[] massInt = {1,2,3,4,5};

        //этот класс работает только с байтами
        FileOutputStream fos = new FileOutputStream(file, true);
        fos.write(massInt);


        fos.close();


    }
}
