package javaCore34.defaultMethodsInterfase.sample3;

//делай диаграмму  и смотри
//Особенности работы с default методами при наследовании
//интерфейсов и классов, их реализующих

public class RealizationA implements A {
    private String text;

    public RealizationA() {
        this.text = "Defaul text";
    }

    public RealizationA(String text) {
        super();
        this.text = text;
    }

    @Override
    public String getMessage() {
        return text + " " + text;
    }

    @Override
    public String toString() {
        return "C [text=" + text + "]";
    }
}
