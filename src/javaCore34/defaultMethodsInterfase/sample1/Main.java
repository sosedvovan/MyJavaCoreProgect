package javaCore34.defaultMethodsInterfase.sample1;

/**
 * ClassA реализует указанный интерфейс  SampleInterface и переопределяет default метод, ClassB
 * реализует интерфейс SampleInterface, но не переопределяет default метод. При вызове этого метода в
 * первом случае был вызван переопределенный метод в классе, во втором реализация в
 * интерфейсе.
 */
public class Main {
    public static void main(String[] args) {
        SampleInterface a = new ClassA("Hello");
        SampleInterface b = new ClassB("Hello");
        System.out.println(a.getMessage());//был вызван переопределенный метод в классе //Hello
        System.out.println(b.getMessage());//был вызван метод в интерфейсе //Default message
    }
}
