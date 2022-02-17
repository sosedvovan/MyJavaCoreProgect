package javaCore34.functionalInterfaceAndContainingMethod.sample7;

public class Main {

    public static void main(String[] args) {

        //создаем объект класса Gen:
        Gen gen = new Gen();

        //Ссылке типа интерфейс (IntElementGenerator ieg) присваиваем ссылку на параметризованный метод (gen::nextElement)
        //не смотря на то, что метод параметризован, в сылке на метод (gen::nextElement) указывать <Integer> не надо (но можно)
        //тк интелектуальный компилятор сам это поймет, что на это место подходит только <Integer>
        IntElementGenerator ieg = gen::<Integer>nextElement;//Тип был выведен автоматически

        //то, если у нас есть НЕ ПАРАМЕТРИЗОВАННЫЙ (не обобщенный) интерфейс, то при написании метода, кот его может
        //реализовать через механизм ссылок на методы - этот наш рукописный метод можно сделать параметризованным.
    }
}
