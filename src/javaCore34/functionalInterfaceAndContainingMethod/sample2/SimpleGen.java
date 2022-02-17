package javaCore34.functionalInterfaceAndContainingMethod.sample2;

//Класс для демонстрации Ссылки на методы
//Обратите внимание, этот класс не реализует никакого интерфейса. И в нем нет
//методов с названием как в описанном выше интерфейсе.

public class SimpleGen {

    private int number;

    public SimpleGen(int number) {
        super();
        this.number = number;
    }

    public SimpleGen() {
        super();
    }

    //НЕ статический метод с подходящим типом возвращаемого значения и списком параметров - как у функционального интерфейса:
    //это значит, что этот метод может использоваться как реализация функц. интерфейса с пом ССЫЛКИ НА МЕТОД(::)
    public int getNumber() {
        int temp = number;
        number = number + 1;
        return temp;
    }

    //Статический метод с подходящим типом возвращаемого значения и списком параметров - как у функционального интерфейса:
    //это значит, что этот метод может использоваться как реализация функц. интерфейса с пом ССЫЛКИ НА МЕТОД(::)
    public static int getRandomNumber() {
        return (int) (Math.random() * 10);
    }
}
