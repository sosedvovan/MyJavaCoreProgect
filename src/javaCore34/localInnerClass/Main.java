package javaCore34.localInnerClass;

//Использование объекта локального внутреннего класса
public class Main {

    public static void main(String[] args) {
        OuterClass oc = new OuterClass();
        //Получение и использование объекта локального внутреннего класса
        //объект внешнего класса обращается к своему методу getLengtComparator(), кот возвращает объект
        // класса-компоратора, который в свою очередь вызывает свой метод compare().
        System.out.println(oc.getLengtComparator().compare("Cat", "Lion"));

        oc.toPrint();
    }
}
