package dopMaterial.IO_File.sample7_Alishev.readWhenFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadWhenFile {

    public static void main(String[] args) throws IOException {
        //читаем из файла строки текста, пакуим их в архив, выводим содержимое архива в консоль
        File file = new File("StringText.txt");
        Scanner scan = new Scanner(file);
        List<String> stringList = new ArrayList<>();
        while (scan.hasNextLine()){
            String temp = scan.nextLine();
            stringList.add(temp);
        }
        System.out.println(stringList);




        //создаем файл, записываем в него 3-и цифры через пробел(будет строка), считываем строку сканнером, сплитуем
        //ее в строковый массив по пробелу, в цикле каждый элемент массива превращаем в интеджер и кладем в Аррайлист и выводим его
        File intFile = new File("intFile.txt");
        intFile.createNewFile();

        PrintWriter pw = new PrintWriter(intFile);
        pw.print("1 ");
        pw.print("2 ");
        pw.print("3 ");
        pw.close();

        Scanner scan2 = new Scanner(intFile);
        String line = scan2.nextLine();
        String[] stringMass = line.split(" ");
        List<Integer> listInteger = new ArrayList<>();
        for (String temp: stringMass) {
            listInteger.add(Integer.parseInt(temp));
        }
        System.out.println(listInteger);


    }
}
