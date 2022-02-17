package javaCore34.defaultMethodsInterfase.sample3;

public interface B{//Наследование интерфейса
    public default String getMessage() {//Переопределение метода по умолчанию
        return "Hello Java";
    }
}