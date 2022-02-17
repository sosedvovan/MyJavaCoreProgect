package javaCore34.supplier.sample2;

import java.util.function.Supplier;

//Реализация Supplier лямбда функцией и ссылкой на метод
public class Main {

    public static void main(String[] args) {
        Supplier<Integer> sup1 = () -> (int) (Math.random() * 10);//Реализация лямбда функцией / генерирует случайное число
        Supplier<Integer> sup2 = Main::getRandomNumber;//Реализация ссылкой на метод / генерирует случайное число
        System.out.println(sup1.get());//запускаем метод абстракта get()
        System.out.println(sup2.get());//запускаем метод абстракта get()
    }

    public static Integer getRandomNumber() {
        return (int) (Math.random() * 10);
    }
}
