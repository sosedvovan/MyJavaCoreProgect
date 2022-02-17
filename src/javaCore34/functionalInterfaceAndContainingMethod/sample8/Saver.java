package javaCore34.functionalInterfaceAndContainingMethod.sample8;

//Функциональные интерфейсы и исключения

import java.io.IOException;

@FunctionalInterface
public interface Saver<T> {

    public void saveTo(T element) throws IOException;
}

//Метод, ссылка на который используется в качестве реализации функционального
//интерфейса, должен быть совместим по исключениям с методом функционального
//интерфейса. Т.е. если метод генерирует проверяемые исключения, то и в методе
//функционального интерфейса они должны быть перечислены с помощью throws.
