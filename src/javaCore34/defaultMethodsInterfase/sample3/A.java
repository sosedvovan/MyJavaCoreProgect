package javaCore34.defaultMethodsInterfase.sample3;

public interface A {
    public default String getMessage() {//Метод по умолчанию
        return "Hello world";
    }
}

