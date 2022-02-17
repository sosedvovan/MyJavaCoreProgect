package javaCore34.innerCasses.sample3;

//Использование нескольких объектов внутреннего класса

public class Main {

    public static void main(String[] args) {
        //создаем объект внешнего класса:
        StringWrapper sw = new StringWrapper("Hello");
        //под капотом цикла for() ->  объект внешнего класса (носитель"Hello") sw создает 2-а объекта внутреннего класса
        for (Character c : sw) {//Получение первого итератора
            System.out.println(c);
            for (Character d : sw) {//Получение второго итератора
                System.out.print(d);
            } System.out.println();
        }
    }
}
