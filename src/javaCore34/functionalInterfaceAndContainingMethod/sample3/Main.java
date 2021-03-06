package javaCore34.functionalInterfaceAndContainingMethod.sample3;

//Использование ссылки на не статический метод любого объекта

public class Main {

    // КОГДА ССЫЛКА НА НЕ СТАТИЧЕСКИЙ МЕТОД любого объекта класса, обращаемся чз Имя класса::название метода
    //----------------------------------------------------------------------------------

    public static void main(String[] args) {

        //Получение ссылки на не статический метод, для любого объекта - чз имя класса (без создания объекта)
        Generator gen1 = IntGenerator::next;

        //теперь создаем любой объект этого класса тк абстрактный метод ожидает его в своих аргументах
        IntGenerator a = new IntGenerator();

       // запускаем абстрактный метод интерфейса, реализованный чз не статический метод класса IntGenerator
        System.out.println(gen1.getNextElement(a));//0
    }
}
