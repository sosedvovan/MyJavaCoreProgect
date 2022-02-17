package dopMaterial.IO_File.sample7_Alishev.searization;

import dopMaterial.IO_File.sample7_Alishev.People;

import java.io.*;

public class WriteObject {

    public static void main(String[] args) throws IOException {
        File file = new File("people.bin");//создали файл
        file.createNewFile();

        People people1 = new People(1, "Name1");//создали объекты
        People people2 = new People(2, "Name2");

        try (FileOutputStream fos = new FileOutputStream(file);//создали поток байтиков
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {//байтики направили в сериалайзер

            oos.writeObject(people1);//сериалайзер сериализовал
            oos.writeObject(people2);//сериалайзер сериализовал
        }


        //сериализация массивов
        //создадим 3-и объекта People, положим их в массив.Массив это тоже объект-его можно серилизовать(но здесь мы этого не делаем).
        //в цикле достаем из массива объекты и сериализуем их
        People[] person = {new People(3, "Name3"), new People(4, "Name4"), new People(5, "Name5")};

        try (FileOutputStream fos2 = new FileOutputStream(file);//откытьфайл на дозапись(true)
             ObjectOutputStream oos2 = new ObjectOutputStream(fos2)) {

            oos2.writeInt(person.length);//зачем сериализовать размер массива? при десериализации будем знать размер массива

            for (People p : person) {
                oos2.writeObject(p);
            }

            //посмотрели в получившийся файл
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
