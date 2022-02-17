package javaCore34.defaultMethodsInterfase.sample1;

// ClassB тоже, как и  ClassA - implements SampleInterface, но нет Переопределение метода интерфейса по умолчанию:
public class ClassB implements SampleInterface{

    private String message;
    public ClassB(String message) {
        super();
        this.message = message;
    }
    @Override
    public String toString() {
        return "ClassB [message=" + message + "]";
    }
}
