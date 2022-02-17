/**
 * Метод <U> U reduce (U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
 * <p>
 * Создает результат аккумулируя элементы потока. В качестве базового элемента берется первый
 * параметр метода (он же возвращается если поток пуст) и новый результат получается применение
 * accumulator к базовому элемента и элемента потока. Внимание! Результат и элемент потока могут
 * иметь разные типы. Третий параметр используется только в параллельных потоках или в случае
 * несоответствия типов потока и accumulator.
 * Важные соглашения для этого метода:
 * ● Начальное значение identity должно быть согласованно с combiner. Для любого U u, combiter
 * (identity,u) equals u.
 * ● аccumulator должен быть согласован с combiner. Для любого U u, T t должно выполнятся
 * combiner.apply(u, accumulator.apply(identity,t)) == accumulator.apply(u,t)
 */
package javaCore34.streamAPI_6АккумулирующиеТерминальныеМетоды.sample2;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        //Todo: В данном примере с помощью reduce выполняется суммирование стоимости товаров в потоке.

        //имеется лист объектов-товаров
        List<Goods> list = List.of(new Goods("Apple", 50),
                new Goods("Orange", 70),
                new Goods("Pear", 65),
                new Goods("Cherry", 75));

        //получаем реализацию BiFunction - combiner: где-
        //<Integer, Goods,- Входные параметры
        //, Integer> -Результат
        //Тип первого входного потока и тип результата должны быть одинаковы. Тип второго входного
        //параметра должен быть совместим с типом данных потока.
        BiFunction<Integer, Goods, Integer> biFunc = (a, b) -> a + b.getPrice();

        //получаем реализацию BinaryOperator-из двух Integer получаем Integer
        BinaryOperator<Integer> biOp = (a, b) -> a + b;

        Integer totalSum = list.stream()
                .filter(a -> a.getPrice() > 65)//фильтруем
                .reduce(0, biFunc, biOp);//0,-это Начальное значение,!!!Третий параметр используется
                                            // только в параллельных потоках для суммирования результатов этих потоков

        System.out.println("totalSum :" + totalSum);//145


        //-------------
        //Проверим совместимость начального значения и combiner .reduce(0, biFunc, biOp);
        //BinaryOperator<Integer> biOp = (a, b) -> a + b;
        //Для любого целого числа u справедливо u+0 = 0+u = u
        //----------------
        //Проверим совместимость accumulator и combiner
        //.reduce(0, biFunc, biOp);
        //BinaryOperator<Integer> biOp = (a, b) -> a + b;
        //BiFunction<Integer, Goods, Integer> biFunc = (a, b) -> a + b.getPrice();

        //Для любого Integer u и Goods t справедливо соотношение:
        //u + (0 + t.getPrice) == u + t.getPrice()
        //это есть:
        //combiner.apply(u, accumulator.apply(identity,t)) == accumulator.apply(u,t)
        //------------------

        //---------------------------------------------------------------------------------------------------------
        //                Демонстрация того, что в последовательном потоке
        //                            combiner не вызывается
        //При работе терминального метода reduce реализация BinaryOperator не будет вызвана. В этом
        //легко убедиться по отсутствию вывода на экран надписи Combiner. Так и должно быть.

        //имеется лист с товарами
        List<Goods> list3 = List.of(new Goods("Apple", 50),
                new Goods("Orange", 70),
                new Goods("Pear", 65),
                new Goods("Cherry", 75));
        BiFunction<Integer, Goods, Integer> biFunc2 = (a, b) -> {
            System.out.println("Accumulator: " + b);//добавили эту строчку
            return a + b.getPrice();
        };
        BinaryOperator<Integer> biOp2 = (a, b) ->{
            System.out.println("Combiner:");//добавили эту строчку - она в этом примере никогда не сработант тк поток не параллельный
            return a + b;
        };
        Integer totalSum2 = list3.stream()
                .filter(a -> a.getPrice() > 65)
                .reduce(0, biFunc2, biOp2);

        System.out.println(totalSum2);  //Accumulator: Goods [name=Orange, price=70]
                                        //Accumulator: Goods [name=Cherry, price=75]
                                        //145

        //------------------------------------------------------------------------------------------------
        //                Демонстрация того, что в параллельном потоке combiner
        //                                       вызывается
        //В случае параллельных потоков реализация BinaryOperator вызывается для аккумулирования
        //результата полученного в разных потоках.

        List<Goods> list4 = List.of(new Goods("Apple", 50),
                new Goods("Orange", 70),
                new Goods("Pear", 65),
                new Goods("Cherry", 75));

        //аккумулятор
        BiFunction<Integer, Goods, Integer> biFunc4 = (a, b) -> {
            System.out.println("Accumulator: " + b);
            return a + b.getPrice();
        };

        //комбайн
        BinaryOperator<Integer> biOp4 = (a, b) -> {
            System.out.println("Combiner: a = "+a+", b = " + b);
            return a + b;
        };
        Integer totalSum4 = list4.stream()
                .parallel()//разрешили папаллельное выполнение на ядрах процессора
                .reduce(0, biFunc4, biOp4);

        System.out.println(totalSum4);
        //Вывод:
        //Accumulator: Goods [name=Pear, price=65]   //4-ре потока на 4-х ядрах
        //Accumulator: Goods [name=Cherry, price=75]
        //Accumulator: Goods [name=Orange, price=70]
        //Accumulator: Goods [name=Apple, price=50]
        //Combiner: a = 50, b = 70                     //Combiner суммирует результат 3 и 4 потоков
        //Combiner: a = 65, b = 75                     //Combiner суммирует результат 1 и 2 потоков
        //Combiner: a = 120, b = 140                   //Combiner суммирует 2-е предидущие суммы
        //260

        //------------------------------------------------------------------------------------------------------

        //                Замена с помощью reduce терминального метода allMatch

        List<Integer> list5 = List.of(1, 2, 3, 4);
        Predicate<Integer> predicate = a -> a % 2 == 0;
        BiFunction<Boolean, Integer, Boolean> biFun = (a, b) -> a && predicate.test(b);
        BinaryOperator<Boolean> biOp5 = (a, b) -> a && b;
        Boolean identity = true;
        Boolean allMathc = list5.stream()
                .reduce(identity, biFun, biOp5);
        System.out.println(allMathc);

        //-----------------------------------------------------------------------------------------

        //                Замена с помощью reduce терминального метода anyMatch

        List<Integer> list6 = List.of(1, 2, 3, 4);
        Predicate<Integer> predicate6 = a -> a % 2 == 0;
        BiFunction<Boolean, Integer, Boolean> biFun6 = (a, b) -> a || predicate6.test(b);
        BinaryOperator<Boolean> biOp6 = (a, b) -> a || b;
        Boolean identity6 = false;
        Boolean anyMathc = list6.stream().reduce(identity6, biFun6, biOp6);
        System.out.println(anyMathc);

    }
}
