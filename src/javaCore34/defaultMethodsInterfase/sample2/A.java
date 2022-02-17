package javaCore34.defaultMethodsInterfase.sample2;

interface A {
    public default String getMessage() {//Метод по умолчанию
        return "Hello world";
    }
}

interface B extends A{//Наследование интерфейса
    @Override
    public default String getMessage() {//Переопределение метода по умолчанию
        return "Hello Java";
    }
}

class C implements B {//Класс, реализующий интерфейс B
    private String text;
    public C(String text) {
        super();
        this.text = text;
    }
    @Override
    public String toString() {
        return "C [text=" + text + "]";
    }
}

class Main {
    public static void main(String[] args) {
        C classC = new C("Hello");//Создание объекта класса С
        A interfaceA = classC;//создание ссылок типа A и B и присваивание им объект класса C ->
        B interfaceB = classC;//-> все равно это один и тот же объект
        System.out.println(classC.getMessage());//Вызов методов по умолчанию //Hello Java
        System.out.println(interfaceA.getMessage());//Вызов методов по умолчанию //Hello Java
        System.out.println(interfaceB.getMessage());//Вызов методов по умолчанию //Hello Java
        //тк в class C не переопределяется метод:  default String getMessage() - выполняется ближайшая его реализация
        //по ветке наследования- те метод: default String getMessage() из interface B

        //При вызове у объекта класса не реализованного метода по умолчанию, вызывается
        //ближайший по иерархии наследования метод.
    }
}


/**
 * При наследовании интерфейсов друг от друга возможно переопределение default
 * методов. Если класс реализует подинтерфейс, при этом не реализуя default метод, то
 * будет вызван ближайший default метод по иерархии наследования.
 *
 * В интерфейсе A определен метод по умолчанию -
 * getMessage(default methods). Интерфейс B наследует интерфейс A и
 * переопределяет метод по умолчанию. Класс C реализует
 * интерфейс B, при этом не реализуя метод по умолчанию.
 * При вызове метода по умолчанию у объекта класса C
 * будет вызван ближайший default метод по иерархии
 * наследования. Это метод из интерфейса B.
 */


