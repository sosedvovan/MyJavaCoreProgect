package javaCore34.supplier.sample4;

import java.util.Arrays;
import java.util.function.IntSupplier;

//Supplier - ГЕНЕРАТОР, ПОСТАВЩИК
//Примитивные специализации Supplier
public class Main {

    public static void main(String[] args) {
        //получили реализацию абстракта getAsInt() интерфейса IntSupplier с пом класса, имплементирующего IntSupplier (см ниже)
        IntSupplier intsup = new RandomGenerator(10, 20);
        //завели массив из 10-ти элементов
        int[] array = new int[10];
        //в цикле заполнили этот массив получая рандом значения с пом абстракта getAsInt() интерфейса IntSupplier
        for (int i = 0; i < array.length; i++) {
            array[i] = intsup.getAsInt();//Использование метода интерфейса
        }
        System.out.println(Arrays.toString(array));
    }//закрыли главный метод
}//закрыли главный класс

//Реализация IntSupplier с помощью класса
class RandomGenerator implements IntSupplier {
    //поля
    private int min;
    private int max;

    //конструктор
    public RandomGenerator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public int getAsInt() {
        return (int) (min + Math.random() * (max - min));//получение рандомного значения с какой-то логикой
    }
}

/**
 * Примитивные специализации Supplier:
 * <p>
 * Существуют специализации интерфейса Supplier для работы с примитивными типами.
 * Они используются для возврата значений примитивных типов.
 * Название                      Описание
 * BooleanSupplier           Для создания значений типа boolean
 * IntSupplier               Для создания значений типа int
 * LongSupplier              Для создания значений типа long
 * DoubleSupplier            Для создания значений типа double
 * <p>
 * Правило наименования абстрактных методов данных интерфейсов:
 * тип getAs{Тип} ()
 * Например для IntSupplier этот метод будет называться:
 * int getAsInt()
 */
