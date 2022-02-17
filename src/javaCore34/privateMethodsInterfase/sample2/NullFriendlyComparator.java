package javaCore34.privateMethodsInterfase.sample2;

import java.util.Comparator;


//Область применения private static методов в интерфейсах

//static методами данного интерфейса можно пользоваться не наследуясь от этого интерфейса,
//а обращаясь к ним по имени интерфейса из статического контекста (напр из статического метода main)

//методы данного интерфейса принимает на вход объект класса-компаратора и возвращает другой - безопасный на null
//объект класса-компаратора
public interface NullFriendlyComparator {
    public static final int NOT_NULL = 11;

    public static <T> Comparator<T> rangeNullFirst(Comparator<T> unsafeComparator) {
        class NullSafeComparator implements Comparator<T> {//локал класс имплементит компаратор
            @Override//и переопределяет другим образом:
            public int compare(T o1, T o2) {
                int result = nullSafeCompare(o1, o2);//проверка на нулл в нижнем методе
                if (result != NOT_NULL) {//если один из проверяемых нулл возвр 1 или -1 или возвр 0 если оба нулл
                    return result;
                }
                //если с нулл не столкнулись - возвращаем результат работы обычного compare()
                return unsafeComparator.compare(o1, o2);
            }
        }//закрываем локал класс
        return new NullSafeComparator();//возвращаем из метода(в класс main) новый безопасный компаратор
    }

    private static <T> int nullSafeCompare(T first, T second) {//private static method-принимает 2-а объекта
        if (first != null && second == null) {//если первый не нулл- возвращает 1
            return 1;
        }
        if (first == null && second != null) {//если второй не нулл- возвращает -1
            return -1;
        }
        if (
                first == null && second == null) {//если оба нулл возвращает 0
            return 0;
        }
        return NOT_NULL;//если оба НЕ нулл возвращает константу ---public static final int NOT_NULL = 11;---
    }
}
