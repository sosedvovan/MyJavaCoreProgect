package javaCore34.innerCasses.sample4;

//Создание объекта подкласса внутреннего класса
public class Main {

    public static void main(String[] args) {
        //Создание объекта внешнего класса
        Outer outer = new Outer(11);
        //Передача ссылки на него в конструктор подкласса
        InnerSubclass ins = new InnerSubclass(outer);
        System.out.println(ins.getTotalInfo()); //Hello 11
    }
}
