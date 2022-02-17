package dopMaterial.IO_File.sample7_Alishev.searization;

import dopMaterial.IO_File.sample7_Alishev.People;

import java.io.*;
import java.util.Arrays;

public class ReadObject {

    public static void main(String[] args) {

        //десериализуем объекты
        File file = new File("people.bin");

/** //если раскоментить вторая часть кода не выполяется и это так задуманно
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            People people1 = (People) ois.readObject();
            People people2 = (People) ois.readObject();
            ois.close();
            System.out.println(people1);
            System.out.println(people2);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
*/

        //десериализация массивов
        try {
            FileInputStream fis2 = new FileInputStream(file);
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
           int length = ois2.readInt();
           People[] person = new People[length];
           for(int i = 0; i < length; i++){
              person[i] = (People) ois2.readObject();
           }
            System.out.println(Arrays.toString(person));
           ois2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }
}
