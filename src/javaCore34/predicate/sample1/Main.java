package javaCore34.predicate.sample1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        Cat cat1 = new Cat("Umka", 12);
        Cat cat2 = new Cat("Luska", 5);
        Cat cat3 = new Cat("Barsic", 8);
        Cat cat4 = new Cat("Timka", 4);
        Cat cat5 = new Cat("Kuzia", 2);

        //метод List.of() появился в 11 Яве. если Ява ниже- используй add()
        List<Cat> cats = new ArrayList<>(List.of(cat1, cat2, cat3, cat4, cat5));
        //---------------------------------------------------------------------------------
        //Использование Predicate в методе removeIf
        //Начиная с Java 8 в интерфейсе Collection<E> (а следовательно и во всех его
        //реализациях) определен метод:
        //default boolean removeIf (Predicate<? super E> filter)
        //Этот метод предназначен для удаления всех элементов для которых реализация
        //Predicate, переданная в качестве параметра этому методу, вернет true.

        //в аргументы removeIf() подаем предикат(метод ожидает предикат) и реализация с пом. лямбды
        //а- это объект класса Cat(компилятор это знает тк removeIf() вызывается на листе с котами
        //метод итерируется по всему списку и удалит всех котов старше 6 лет, тк для них вернется true
        cats.removeIf(a -> a.getAge() < 6);
        //Подробнее о реализации с помощью лямбда функции:
        //cats.removeIf(a -> a.getAge() < 6);
        //первое а- это объект класса Cat тк абстракте интерфейса (boolean test (T t)) на входе ожидается объект
        //после оператора (-> a.getAge() < 6);) это boolean значение полученное на основе объекта
        // кот должно быть на выходе абстракта
        //то эта лямбда аналогична такой реализации абстракта(если бы вместо лямбды исп ссылку на метод Main::test)
        //public boolean test(Cat a) {
        //    return a.getAge() < 6;
        //}
        //-----------------------------------------------------------------------------------
        //Пример реализации с помощью ссылки на метод:
        //в аргументы removeIf() подаем предикат(метод ожидает предикат) и реализация
        // с пом. статич. метода testCat() класса Main
        cats.removeIf(Main::testCat);
        System.out.println(cats);
        //В этом примере Predicate реализован с помощью ссылки на метод. Эта реализация
        //возвращает true для тех кошек возраст которых меньше 6 лет. Это приведет к тому, что из
        //данного списка будут удалены все кошки возраст которых меньше 6 лет.
        //--------------------------------------------------------------------------------------
        //Еще один пример использования Predicate в removeIf:
        List<Integer> numbers = new ArrayList<>(List.of(-1, 4, 5, 0, -2));
        numbers.removeIf(n -> n % 2 == 0);
        System.out.println(numbers);
        //В этом примере была также использована реализация интерфейса Predicate с
        //помощью лямбда функции. Эта реализация вернет true для всех четных чисел и как
        //следствие все четные числа будут удалены из списка.
        //-------------------------------------------------------------------------------------
        //                               Метод and
        //В Predicate объявлен метод по умолчанию:
        //default Predicate<T> and (Predicate<? super T> other)
        //Этот метод принимает в качестве параметра совместимый с текущим предикатом
        //(совместимость по типу входного параметра) предикат и возвращает предикат который
        //является реализацией логического оператора AND на основе текущего предиката и
        //предиката который был параметром. Т.е. такой предикат вернет true только в том случае
        //если оба этих предиката вернут true. Внимание используется краткая форма AND т. е. если
        //первый предикат(текущий) вернет false то второй (параметр) даже не будет проверяться.
        List<Cat> cats2 = new ArrayList<>(List.of(cat1, cat2, cat3, cat4, cat5));
        //покодим с пом ссылок типа интерфейс на реализацию его абстракта:
        Predicate<Cat> pr1 = a -> a.getAge() < 5;//предикат вернет true если коту меньше 5 лет
        Predicate<Cat> pr2 = a -> a.getName().startsWith("K");//предикат вернет true если первая буква имени кота К
        cats2.removeIf(pr1.and(pr2));//removeIf удалит из списка котов удовлетворяющих обоим предикатам тк: pr1.and(pr2)
                                    //те получится новый-третий-составной предикат.
        //В примере описано две реализации интерфейса Predicate. Первая (pr1) вернет true в
        //случае если возраст кота меньше 5 лет. Вторая (pr2) вернет true в случае если имя кошки
        //начинается с заглавной буквы K. Метод and вызванный для pr1 вернет  предикат который
        //вернет true только в случае если возраст кота меньше 5 лет и его имя начинается с буквы
        //К. Применение подобного предиката удалит из списка только кота Kuzia, так как ему
        //меньше 5 и его имя начинается на К.
        //----------------------------------------------------------------------------------------------
        //   Еще один пример использования and с пом. ссылок на методы:
        List<Integer> numbers2 = new ArrayList<>(List.of(-1, 4, 5, 0, -2, -5, -6));
        System.out.println("numbers2" + numbers2);
        Predicate<Integer> pr11 = Main::test1;//Первый предикат. Ссылка на метод.(сам метод ищи внизу)
        Predicate<Integer> pr22 = Main::test2;//Второй предикат. Ссылка на метод.(сам метод ищи внизу)
        //в метод removeIf подали составной предикат pr11.and(pr22)
        numbers2.removeIf(pr11.and(pr22));
        System.out.println("numbers2" + numbers2);
        //В примере объявлено два предиката.
        //Первый вернет true в случае если число
        //меньше 0. Второй вернет true если число
        //четное. Предикат созданный на их основе с
        //помощью метода and будет возвращать true
        //только для чисел меньше нуля и четные. те когда true + true = true
        //-------------------------------------------------------------------------------------------
        //                                      Метод or
        //В Predicate объявлен метод по умолчанию:
        //default Predicate<T> or (Predicate<? super T> other)
        //Этот метод принимает в качестве параметра совместимый с текущим предикатом
        //(совместимость по типу входного параметра) предикат и возвращает предикат который
        //является реализацией логического оператора OR на основе текущего предиката и
        //предиката который был параметром. Т.е. такой предикат вернет true в случае хотя бы один
        //из этих предикатов вернет true. Внимание используется краткая форма OR т. е. если
        //первый предикат(текущий) вернет true то второй (параметр) даже не будет проверяться.
        Cat cat11 = new Cat("Umka", 12);
        Cat cat22 = new Cat("Luska", 5);
        Cat cat33 = new Cat("Barsic", 8);
        Cat cat44 = new Cat("Timka", 4);
        Cat cat55 = new Cat("Kuzia", 2);
        List<Cat> cats3 = new ArrayList<>(List.of(cat11, cat22, cat33, cat44, cat55));
        Predicate<Cat> pr111 = a -> a.getAge() < 5;
        Predicate<Cat> pr222 = a -> a.getAge() > 10;
        cats3.removeIf(pr111.or(pr222));
        System.out.println(cats3);
        //В примере объявлено два предиката. Первый
        //вернет true в случае если возраст меньше 5. Второй
        //вернет true если возраст больше 10. Предикат
        //созданный на их основе с помощью метода or будет
        //возвращать true в случае если возраст меньше 5 или
        //возраст больше 10.
        //---------------------------------------------------------------------------------------
        //                                            Метод negate
        //В Predicate объявлен метод по умолчанию:
        //default Predicate<T> negate()
        //Этот метод возвращает предикат который является реализацией логического
        //оператора NOT на основе текущего предиката. Т.е. такой предикат вернет true в случае
        //если текущий предикат вернет false и наоборот.
        List<Integer> numbers3 = new ArrayList<>(List.of(-1, 4, 5, 0, -2));
        Predicate<Integer> pr10 = n -> n > 0;
        numbers3.removeIf(pr10.negate());
        System.out.println(numbers3);
        //В пример описан предикат возвращающий true для чисел больше 0. Предикат
        //полученный вызовом метода negate для этого предиката наоборот будет возвращать true
        //для чисел меньше нуля. Как следствие из этого списка будут удаленны элементы со
        //значением меньше 0. (o тоже удалил, а если поставить n -> n >= 0 , то 0 останется)
        //---------------------------------------------------------------------------------------
        //                           Статический метод not:
        //В Predicate объявлен статический метод:
        //static <T> Predicate<T> not (Predicate<? super T> other)
        //Этот метод возвращает предикат который является реализацией логического
        //оператора NOT на основе предиката переданного ему в качестве параметра. Т.е. такой
        //предикат вернет true в случае если текущий предикат вернет false и наоборот.
        Cat cat10 = new Cat("Umka", 12);
        Cat cat20 = new Cat("Luska", 5);
        Cat cat30 = new Cat("Barsic", 8);
        Cat cat40 = new Cat("Timka", 4);
        Cat cat50 = new Cat("Kuzia", 2);
        List<Cat> cats0 = new ArrayList<>(List.of(cat10, cat20, cat30, cat40, cat50));
        //разница с предидущим: тк метод статический, то обращаемся к нему по имени интерфейса,
        //а не через ссылку типа интерфейс и лямбда-реализацию передаем в его аргументы(те этот метод с аргументами)
        cats0.removeIf(Predicate.not(a -> a.getAge() < 6));
        System.out.println(cats0);
        //В примере показана работа статического метода not. В качестве его параметра
        //используется реализация Predicate помощью лямбда функции (вернет true если возраст
        //меньше 6 лет) в свою очередь метод вернет «обратный» предикат. Полученный предикат
        //наоборот вернет true если возраст не меньше 6. Поэтому из списка будут удаленны коты
        //чей возраст равен или больше 6.
        //-----------------------------------------------------------------------------------
        //                             Статический метод isEqual
        //В Predicate объявлен статический метод:
        //static <T> Predicate<T> isEqual (Object targetRef)
        //Этот метод возвращает предикат который производит сравнение объекта который
        //будет параметром метода test(T t) с объектом который был параметром этого метода. Для
        //сравнения используется метод equals этих объектов.
        List<String> names = new ArrayList<>(List.of("Anna", "Ira", "Katia", "Anna"));
        Predicate<String> pr15 = Predicate.isEqual("Anna");
        names.removeIf(pr15);
        System.out.println(names);
       //В данном примере мы получили предикат вызовом метода isEqual. Принцип работы
       // такого предиката следующий: если на вход метода test этого предиката будет подставлен
       // объект эквивалентный строке «Anna» то метод вернет true. Таким образом из списка
       // будут удалены все строки эквивалентные строке «Anna».

        //Более подробное объяснение метода isEqual:
        //Для объяснения того как работает статический метод isEqual ниже приведен
        //аналогичная развернутая реализация в виде обобщенного класса.
        /**
         * Predicate<String> pr1 = Predicate.isEqual("Anna");
         *
         * это тоже самое, что и:
         *
         * //параметризованный класс  -- допустим он параметризован String'ом:
         * //этот класс имплементит интерфейс Predicate, значит должен реализовать его абстрактный метод
         * //когда создается объект этого класса, внутрь объекта помещается String - например "Anna"
         * //и в аргументы метода -реализуещем абстракт- подается текущий элемент массива и он сравнивается с "Anna"
         * class NamePredicat<T> implements Predicate<T> {
         *
         * //поле класса:
         * private T baseElement;
         *
         * //конструктор:
         * public NamePredicat(T baseElement) {
         * super();
         * this.baseElement = baseElement;
         * }
         *
         * //метод, реализующий абстракт интерфейса Predicate
         * @Override
         * public boolean test(T t) {
         * return Objects.equals(t, baseElement);
         * }
         * }
         * //то можно сказать:
         * Predicate<String> pr2 = new NamePredicat<>("Anna")
         * names.removeIf(pr2);
         *
         * //или names.removeIf(new NamePredicat<>("Anna");
         * //или names.removeIf(Predicate.isEqual("Anna"));
         * //или names.removeIf(t -> Objects.equals(t, "Anna")) но это не точно/ removeIf сам на место t подставляет элементы из списка
         *
         * //и метод removeIf итерируясь по списку получит предикат в котором в его методе test объект с полем "Anna" будет сравниваться
         * //с объектом из списка приходящим в его аргументы - (T t) и в случае совпадения возвращать true и удалять этот элемент
         * //
         */

    }

    //Методы с совпадающим  c абстрактом параметризованного интерфейса Predicate типом возвращаемого параметра и параметром.
    public static boolean testCat(Cat cat) {
        return cat.getAge() < 6;
    }

    public static boolean test1(Integer n) {
        return n < 0;
    }
    public static boolean test2(Integer n) {
        return n % 2 == 0;
    }
}

/**
 * Predicate<T> - функциональный интерфейс появившийся в Java 8. Используется для
 * проверки того или иного условия. Основная область применения это фильтрация данных
 * (подходит объект для дальнейшей обработки или нет).
 *
 * Список методов интерфейса Predicate<T>
 *       Метод                                             Описание
 * boolean test(T t)                              Проверяет удовлетворяет ли объект по ссылке t
 *                                                заданному условию. Если да то метод должен вернуть
 *                                                true, в противном случае false.
 *
 * default Predicate<T> and (Predicate<? super T> other) Возвращает составной предикат (в виде краткого
 *                                                       логического И) на основе текущего и того что выступает
 *                                                       параметром other.
 *
 * default Predicate<T> or (Predicate<? super T> other)  Возвращает составной предикат (в виде краткого
 *                                                       логического ИЛИ) на основе текущего и того что выступает
 *                                                       параметром other.
 *
 * default Predicate<T> negate()                         Возвращает предикат в виде логического НЕ на основании
 *                                                       текущего предиката
 *
 * static <T> Predicate<T> not (Predicate<? super T> other) Возвращает предикат в виде логического НЕ на основе
 *                                                          предиката используемого в качестве параметра other.
 *
 * static <T> Predicate<T> isEqual (Object targetRef)       Возвращает предикат, который проверяет, равны ли два
 *                                                          аргумента согласно Objects.equals (Object, Object). Объект
 *                                                          с которым будут сравниваться другие задается
 *                                                          параметром targetRef
 *
 *     теперь подробнее:
 *
 *     Абстрактный метод интерфейса Predicate
 * Так как Predicate функциональный интерфейс, то обязательным к реализации должен
 * быть только один метод. В данном интерфейсе это метод boolean test (T t). Его
 * реализация и должна вернуть значение true если объект по ссылке t удовлетворяет
 * нужному критерию, и false если нет. Так, как интерфейс функциональный то в качестве его
 * реализации можно использовать как ссылки на методы, так и лямбда функции.
 *
 *
 */
