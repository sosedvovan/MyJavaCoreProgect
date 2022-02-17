package javaCore34.lambdas.sample6;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        //ссылке типа интерфейс(ССЫЛКЕ НА РЕАЛИЗАЦИЮ ИНТЕРФЕЙСА) Saver saver присваивается данная реализация абстрактного метода
        //функц. интерфейса Saver. причем: (obj)- это то что принимает абстракт на вход, а возвращает он void- те return не нужен
        Saver saver = (obj) -> {

            File file = new File("save.txt");//создаем в директории проекта текстовый файл с названием save.txt

            //создаем объект PrintWriter'ра  передавая в его конструкт этот текстовый файл, с кот. он будет работать:
            PrintWriter pw = new PrintWriter(file);//Генерация проверяемого исключения

            //объект PrintWriter'ра производит печать в текстовый файл
            //а печатает он туда то, что пришло в аргументы абстракта (obj)
            pw.println(obj.toString());

            pw.close();
        };

        List<Integer> intList = List.of(2,2,2,2,2,2);

        saver.save("111");
        saver.save(intList);

    }
}
