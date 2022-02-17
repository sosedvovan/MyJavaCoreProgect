package javaCore34.functionalInterfaceAndContainingMethod.sample6;

//Особенности работы с обобщенными функциональными интерфейсами
//этот случай, когда параметризованный интерфейс использует для своей реализации не параметризованные методы

//параметризуем интерфейс:
@FunctionalInterface
public interface Modifier<T> {

    //этот абстрактный метод возыращает объект типа Т, и такой же объект принимает:
    public T change(T obj);

}
