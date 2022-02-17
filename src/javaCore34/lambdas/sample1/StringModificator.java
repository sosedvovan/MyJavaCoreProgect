package javaCore34.lambdas.sample1;

//Функциональный интерфейс для реализации через лямбда функцию

@FunctionalInterface
public interface StringModificator {

    public String getString(String text);
}
