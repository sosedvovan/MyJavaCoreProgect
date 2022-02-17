package javaCore34.lambdas.sample5;

//Лямбда функции и обобщенные функциональные интерфейсы
//
//В случае если лямбда функция используется как реализация обобщенного
//интерфейса и возможна двузначность используемых типов, то необходимо явно указывать
//параметр типа интерфейса.

@FunctionalInterface
public interface Modificator<T> {

    public T modification(T element);
}
