package javaCore34.functionalInterfaceAndContainingMethod.sample1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//Пример использования функционального интерфейса: public interface Predicate<T> {boolean test(T var1);}
//в методе removeIf(), в аргументы которого надо подать объект класса, кот имплементирует interface Predicate<T>
public class Main {

    public static void main(String[] args) {

        //у нас есть Аррайлист:
        //если мы параметризовываем List<String>, то и получаем, что предок этого List'а тем же параметризован: Collection<String>
        List<String> list = new ArrayList<>(List.of("Hello", "Cat", "Java", "Bag"));
        System.out.println(list);

        //---------------------------------------------------------------------------------------------------------
        //Реализация Predicate<String> с помощью анонимного класса:

        //на ссылке ---List<String> list--- вызываем default метод removeIf(Predicate<? super E> filter) из interface Collection<E> [(Collection<String>)]
        //-который удаляет по условию,
        //а в аргументы надо подать объект класса наследника функционального интерфейса Predicate,
        //параметризованного String'ом или предком String'а - (можно Object, но нам тогда будет недоступен метод length())

        list.removeIf(new Predicate<String>() {//создали ---new...()--- объект класса кот наследует интерфейс Predicate<String>
            @Override
            public boolean test(String t) {//и переопределили его единственный абстрактный метод
                return t.length() > 3;//если true, то проверка не прошла
            }
        });

        //[ <? super E> - объекты коллекции будут предками для Е, нижний предел типа; (те подать String или предка String)
        // <? extends E> - объекты, которые являются подтипами Е, верхний предел типа ]
        //---------------------------------------------------------------------------------------------------------
        System.out.println(list);
    }
}

/**
 *Начиная с Java 1.8 в Java появляется концепция функционального интерфейса.
 * Функциональный интерфейс — интерфейс содержащий один абстрактный метод.
 * Наличие одного абстрактного метода - это единственное условие, таким образом
 * функциональный интерфейс может содержать так же произвольное количество default и
 * static методов.
 * Функциональные интерфейсы могут быть как обычными, так и обобщенными.
 *
 * Примеры функциональных интерфейсов:
 * Не обобщенный функциональный интерфейс:
 * public interface GetNextElement {
 * public int get();
 * }
 *Обобщенный функциональный интерфейс:
 * public interface Generator<T> {
 * public T getNext();
 * }
 *
 * Для проверки на этапе компиляции является ли интерфейс функциональным в Java
 * 1.8 появилась новая аннотация @FunctionalInterface. Если интерфейс, аннотированный FunctionalInterface. Если интерфейс, аннотированный
 * данной аннотацией, не является функциональным то генерируется ошибка компиляции.
 *
 * Пример применения @FunctionalInterfaceFunctionalInterface:
 * Данный интерфейс функциональный. Один абстрактный метод.:
 * @FunctionalInterface
 * public interface Generator<T> {
 * public T getNext();
 * public default void stopGeneration() {
 * throw new NoSuchElementException();
 * }
 * }
 *
 * Данный интерфейс не функциональный. Больше одного
 * абстрактного метода. Ошибка компиляции.:
 * @FunctionalInterface
 * public interface PrimitiveGenerator {
 * public int getNextInt();
 * public double getNextDouble();
 * }
 *
 * Исключение из правила один абстрактный метод:
 * На самом деле существует одно исключение из правила о наличии в
 * функциональном интерфейсе только одного абстрактного метода. Если в интерфейсе
 * объявить дополнительные абстрактные методы, сигнатура которых совпадает с
 * сигнатурой методов объявленных в суперклассе Object то интерфейс будет оставаться
 * функциональным.:
 * @FunctionalInterface
 * public interface Generator<T> {
 * public T getNext();
 * public default void stopGeneration() {
 * throw new NoSuchElementException();
 * }
 * public boolean equals(Object obj);//Метод описанный в классе Object
 * }
 *И хотя в этом интерфейсе два абстрактных метода, но он функциональный так, как
 * метод equals принадлежит классу Object.
 *
 * Для чего были введены функциональные интерфейсы:
 * В Java 8 была реализована функциональная парадигма программирования. Для ее
 * более полной поддержки в стандартную библиотеку, было добавлено значительное
 * количество функциональных интерфейсов. Они описаны в пакете java.util.javaCore34.function:
 *
 * Название:  Predicate<T>   Сигнатура метода:  boolean test(T t)
 * Проверка удовлетворяет ли состояние объекта тому или иному условию.
 *
 * Consumer<T>   void accept(T t)
 * Выполнение действий над объектом без возврата результата.
 *
 * Function<T, R>   R apply(T t)
 * Принимает значение в качестве аргумента одного типа и возвращает другое значение.
 *
 * Supplier<T>  T get()
 * Возврат результата, без передачи параметра.
 *
 * UnaryOperator<T>  T apply(T t)
 * Принимает значение в качестве аргумента одного типа и возвращает значение такого же типа.
 *
 * Бинарные специализации функциональных интерфейсов:
 * Существуют бинарные специализации интерфейсов Predicate, Consumer, Function и
 * UnaryOperator, которые принимают на вход два элемента.:
 *
 * BiConsumer<T, U>     void accept(T t, U u)
 * Представляет операцию, которая принимает на вход два элемента и не возвращает результат.
 *
 * BiFunction<T, U, R>       R apply(T t, U u)
 * Представляет операцию, которая принимает на вход два элемента и возвращает результат.
 *
 * BiPredicate<T, U>        boolean test(T t, U u)
 * Представляет операцию, которая принимает на вход два элемента и возвращает значение boolean.
 *
 * BinaryOperator<T>       T apply(T t, T u)
 * Представляет операцию, которая принимает на вход два элемента одного типа и возвращает значение того же типа.
 *
 * Также в стандартной библиотеке присутствуют примитивные специализации
 * функциональных интерфейсов.
 *
 * В стандартной библиотеке большое количество методов используют именно
 * функциональные интерфейсы.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/**
 * <? super E> и <? extends E> в коллекциях
 * Многие программисты путают (а некоторые и не знают) эти конструкции при работе с коллекциями. Что же они означают?
 *
 * Первая означает, что объекты коллекции будут предками для Е, а вторая - объекты, которые являются подтипами Е.
 * Таким образом при использовании <? extends E> мы точно знаем, что объект коллекции будет типа E или его потомком
 * (т.е. совместимым с Е).
 * Пусть у нас есть такая иерархия:
 * Parent extends Object
 * Child extends Parent
 * и есть очередь LinkedBlockingQueue<Parent>. Мы можем вызвать конструктор данной очереди, передав ей List<Child>,
 * потому что каждый Child является Parent, и мы не можем передать List<Object>, потому что Object может быть
 * не совместим с Parent.
 *
 * Так же вы можете перенести эту очередь в List<Object>, потому что каждый Parent является Object, но вы не можете
 * перенести очередь в List<Child>, потому что этот лист будет ожидать того, что все элементы будут совместимы с Child.
 *
 * Таким образом имеем простое правило: extends - для вычитки из коллекции, super - для занесения в коллекцию.
 */
