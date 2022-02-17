package javaCore34.functionalInterfaceAndContainingMethod.sample2;

//Использование ссылки на не статический метод в качестве
//реализации функционального интерфейса
public class Main {

    public static void main(String[] args) {


        //     КОГДА ССЫЛКА НА НЕ СТАТИЧЕСКИЙ МЕТОД конкретного объекта, обращаемся чз объект::название метода
        //-------------------------------------------------------------------------------------------------

        //для использования ссылки на метод нужен объект класса, в котором этот метод находится:
        SimpleGen sg = new SimpleGen(5);

        //ссылке типа интерфейс Generator gen1 присвоили ссылку на метод
        //те эта ссылка gen1 указывается в качестве реализации функционального интерфейса
        //то соотнесли абстрактный метод интерфейса с нестатическим методом класса
        Generator gen1 = sg::getNumber;//Получение ссылки на не статический метод
        //sg::getNumber - создали ссылку на метод и в ней указали на метод, тело которого будет реализовывать абстрактный метод из интерфейса
        //Generator gen1 =  - создали ссылку типа интерфейс и присвоили ей ссылку на метод (соотнесли му собой абстрактный метод и обычный)

        //в результате при вызове абстрактного метода интерфейса выполнится соотнесенный с ним метод из класса SimpleGen
        //причем класс SimpleGen не имплементит никакой интерфейс и названия у методов разные (совпадают кол-во аргументов и тив возвр. значения)
        int temp = gen1.getNextElement();//вызвали абстрактный метод getNextElement() из интерфейса, который запустил соотнесенный с ним метод класса
        //то получилось  что не статический метод реализовал  метод функционального интерфейса
        //
        // (зачем мы так делаем??? Допустим нам надо в метод sort() подать объект компоратора:
        // делаем так: Arrays.sort(list, sg::getNumber???) компилятор знает что сюда положат объект компоратора
        // и компилятор сам поймет что делать дальше (через спец механизм ссылок на методы) и не нужен анонимный класс
        System.out.println(temp);

        //     КОГДА ССЫЛКА НА СТАТИЧЕСКИЙ МЕТОД, обращаемся чз Имя класса::название метода
        //-------------------------------------------------------------------------------------------------

        Generator gen2 = SimpleGen::getRandomNumber;//Получение ссылки на статический метод чз Имя класса
        int temp2 = gen2.getNextElement();//вызов абстрактного метода интерфейса
        System.out.println(temp2);
    }

}

/**
 * Ссылки на методы:
 *
 * Ссылку на метод можно использовать как реализацию функционального интерфейса (вместо анонимного класса и лямбды)
 *
 * В Java 1.8 появился новый механизм. Это возможность ссылаться на метод, и
 * указывать в качестве реализации функционального интерфейса такую ссылку.
 * ------Generator gen1 = sg::getNumber;--------
 * Ссылка на метод может использоваться только в качестве реализации функционального интерфейса.
 * Для возможности ссылаться на метод введен новый оператор :: .
 *
 *      Тип метода:                                        Синтаксис ссылки:
 * Статический метод                             ContainingClass::staticMethodName
 * Не статический метод конкретного объекта      containingObject::instanceMethodName
 * Не статический метод любого объекта           ContainingType::methodName
 * Ссылка на конструктор                         ClassName::new
 *
 *     ЭТИ ВСЕ СЛУЧАИ МЫ РАССМОТРЕЛИ
 *
 * Как соотносится метод функционального интерфейса и
 * метод на который создается ссылка:
 * Для того, что бы можно было использовать ссылку на метод в качестве реализации
 * функционального интерфейса достаточно, чтобы совпадали тип возвращаемого значения
 * и список параметров.
 * Совпадение имен методов не обязательно! Класс в котором описан метод не
 * обязательно должен реализовать функциональный интерфейс! :
 *
 * ТО имея такой функциональный интерфейс:
 * @FunctionalInterface
 * public interface Generator {
 * public int getNextElement();
 * }
 * Соотнесем его с методом, на который создается ссылка:
 *
 *public class SimpleGen {
 * ...
 * public int getNumber() { //видим, что совпадают тип возвращаемого значения и список параметров в методе и интерфейсе
 * int temp = number;
 * number = number + 1;
 * return temp;
 * }
 * }
 *
 *
 *
 *
 */