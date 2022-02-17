package javaCore34.lambdas.sample5;

public class Main {

    public static void main(String[] args) {

        //Реализация на основе лямбда функции Параметр типа ссылки функционального интерфейса явно указан:
        Modificator<String> mod = (text) -> text.toUpperCase();

        System.out.println(mod.modification("hello"));
    }
}
