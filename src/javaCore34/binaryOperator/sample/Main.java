package javaCore34.binaryOperator.sample;

import java.util.function.BinaryOperator;

//Функциональный интерфейс
//BinaryOperator, наследник функционального интерфейса BiFunction<T,T,T>
public class Main {

    public static void main(String[] args) {

        //        Абстрактный метод интерфейса BinaryOperator<T>:

        //В качестве абстрактного метода интерфейса BinaryOperator<T> выступает метод:   T apply (T t1, T t2).
        // Его реализация проводит операцию над объектами, ссылки на которые
        //выступают в качестве параметров и возвращает результат такого же типа. Так, как
        //интерфейс функциональный, то его можно реализовать всеми средствами для реализации
        //обычных интерфейсов, так и с помощью лямбда функций и ссылок на методы.

        //Реализация BinaryOperator с помощью класса
        BinaryOperator<String> binOp1 = new StringConcat(5);
        String text = binOp1.apply("Hello", "cat");//Использование метода этого интерфейса
        System.out.println(text);


        //        Пример реализации BinaryOperator с помощью лямбда функции и ссылки на метод:

        //ссылки типа интерфейс на реализацию абстракта интерфейса BinaryOperator<Integer>:
        BinaryOperator<Integer> binOp11 = (a, b) -> a + b;//Реализация с помощью лямбда функции
        BinaryOperator<Integer> binOp22 = Main::sum;//Реализация с помощью ссылки на метод
        System.out.println(binOp11.apply(1, 3));//Использование метода этого интерфейса
        System.out.println(binOp22.apply(1, 3));//Использование метода этого интерфейса


        //        Подробнее о реализации с помощью лямбда функции:
        // BinaryOperator<Integer> fun1 = (a, b) -> a + b;   сопоставляем с абстрактом:   Integer apply (Integer t1, Integer t2)
        //(a, b) - это параметры абстракта:(Integer t1, Integer t2)
        // -> a + b   - это Результат созданный на основе параметров, возвращающий Integer

    }//закрыли главный метод

    //Реализация абстракта с помощью ссылки на этот метод:
    public static Integer sum(Integer a, Integer b) {
        return a + b;
    }
}//закрыли главный класс

//Реализация BinaryOperator с помощью класса
class StringConcat implements BinaryOperator<String> {
    //поле принимаючее инт
    private int minLength;

    //конструктор
    public StringConcat(int minLength) {
        super();
        this.minLength = minLength;
    }

    //метод, реализующий абстракт
    @Override
    public String apply(String t1, String t2) {
        //если длина строки больше или равно полю класса то нищего не меняем, иначе меняем на "", то есть пустая строка
        String a = t1.length() >= minLength ? t1 : "";
        String b = t2.length() >= minLength ? t2 : "";
        return a + b;
    }
}

/**
 * Функциональный интерфейс BinaryOperator:
 * <p>
 * BinaryOperator<T> - функциональный интерфейс появившийся в Java 8. Является
 * наследником функционального интерфейса BiFunction<T,T,T>. Описывает операцию над
 * двумя операндами одного и того же типа и возвращающий результат такого же типа. Для
 * работы необходимо импортировать пакет java.util.javaCore34.function.BinaryOperator.
 * <p>
 * Список методов интерфейса BinaryOperator<T>:
 * <p>
 * T apply(T t1, T t2)
 * Производит операцию на основе объектов, ссылки на
 * которые выступают в качестве параметров, и возвращает
 * результат в виде ссылки такого же типа.
 * <p>
 * default <V> BiFunction<T,T,V> andThen (Function<? super T, ? extends V> after)
 * Возвращает композицию BinaryOperator и Function. Сначала
 * будет применяться текущий оператор и к результату будет
 * применена функция выступающая в качестве параметра.
 * <p>
 * static <T> BinaryOperator<T> minBy (Comparator<? super T> javaCore34.comparator)
 * Возвращает BinaryOperator, который возвращает меньший
 * из двух параметров в соответствии с указанным Comparator.
 * <p>
 * static <T> BinaryOperator<T> maxBy (Comparator<? super T> javaCore34.comparator)
 * Возвращает BinaryOperator, который возвращает больший из
 * двух параметров в соответствии с указанным Comparator.
 */
