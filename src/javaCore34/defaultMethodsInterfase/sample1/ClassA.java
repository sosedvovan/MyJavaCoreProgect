package javaCore34.defaultMethodsInterfase.sample1;

public class ClassA implements SampleInterface {//Реализация интерфейса
    private String message;

    public ClassA(String message) {
        super();
        this.message = message;
    }

    //Переопределение метода интерфейса по умолчанию
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ClassA [message=" + message + "]";
    }
}
