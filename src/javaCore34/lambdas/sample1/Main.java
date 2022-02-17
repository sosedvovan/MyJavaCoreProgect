package javaCore34.lambdas.sample1;

public class Main {

    public static void main(String[] args) {

        StringModificator sm = (text) -> text.toUpperCase();//Лямбда функция- это как и ссылка на метод,
                                                            //только метод не надо гдето описывать и на него ссылаться
                                                       //а реализуем абстракт метод интерфейса сразу за оператором: ->

        //НАПОМНЮ, что sm- это ссылка типа интерфейс, через нее обращаемся к абстрактному методу интерфейса,
        //(который в нашем случае реализован через лямбду) и этот абстрактный метод интерфейса запускает
        // выполнение  своей реализации в теле лямбды   -----sm.getString("hello")------ЗАПУСК АНОН МЕТОДА ЧЗ ССЫЛКУ ТИПА ИНТЕРФЕЙС

        //[можно это проделать еще в теле метода если используем механизм ссылок на методы,
        //или в теле анонимного класса, но там через объект анонимного класса, кот имплементит интерфейс]

        System.out.println(sm.getString("hello"));


        //пример многострочной лямбда функции:
        StringModificator sm2 = (text) -> {//Начало тела лямбда функции
            String result = "";
            for (char let : text.toCharArray()) {
                if (Character.isLetter(let) || let == ' ') {
                    result += let;
                }
            } return result;//Оператор return обязателен
        };//Конец тела лямбда функции. Точка с запятой обязательна

        System.out.println(sm.getString("hello"));


    }
}

/**
 * Лямбда функции в Java:
 *
 * Начиная с Java 1.8 появляются лямбда функции. Лямбда функция — анонимная
 * функция реализующая функциональный интерфейс. Лямбда функции используются в том
 * же контексте, что и ссылки на методы. Т.е. могут быть использованы только как
 * реализация того или иного функционального интерфейса.
 ****************************************************
 * Синтаксис лямбда функции:
 *
 * (params…) -> javaCore34.function body    где -> это Лямбда оператор.
 *
 * (params…) - список параметров. Должен совпадать со списком параметров метода в
 * функциональном интерфейсе.
 * javaCore34.function body — тело лямбда функции. Может быть однострочным выражением, или
 * многострочным
 *****************************************************
 * Лямбда функция как однострочное выражение:
 *
 * public String getString(String text);  -- посмотрим этот на Метод функционального интерфейса:
 *          |                       |          и лямбду, его реализующую:
 *          |                       |
 *          |                     (text) -> text.toUpperCase();    ---  Лямбда функция
 *          |                                   |
 *          |-----------------------------------|---->String в правой части выражения, тк это и возвращает метод интерфейса
 *
 * В круглых скобках в левой части лямбда функции указываются параметры. Внимание
 * они должны совпадать как по количеству, так и по типу с параметрами описанными в
 * абстрактном методе функционального интерфейса. После символа -> следует тело
 * лямбда функции. Лямбда функция должна возвращать значение совместимое со
 * значением указанном в абстрактном методе функционального интерфейса.
 * **********************************************************
 *
 * В случае многострочной лямбда функции ее тело описывается в фигурных скобках.
 *
 * Содержимое тела описывается аналогично телу обычного метода. Поэтому должен быть
 * оператор return после которого должно следовать значение совместимого типа с
 * указанным в абстрактном методе функционального интерфейса. Вызов этого оператора
 * завершит выполнение именно лямбда функции, а не метода где она определенна. После
 * закрытия тела лямбда функции обязательно ставить точку с запятой.
 * ***************************************************************
 *
 * Указание типа параметров в лямбда функции:
 *
 * Обычно указывать тип параметров в лямбда функции не обязательно. Но в случае
 * когда компилятор не может вывести их автоматически, можно указать их в явном виде в
 * списке параметров лямбда функции.
 * StringModificator sm = (String text) -> "length = " + text.length();
 * Здесь Явное указание параметра типа: String text
 * ******************************************************************
 *
 *
 */

/**
 *Получается, что ссылке типа интерфейс можно присвоить реализацию абстрактного метода
 * функционального интерфейса следующими способами:
 *
 * - с помощью объекта класса, реализующего этот интерфейс   (Function<String, Integer> fun = new StrToLength();)
 *
 * - с помощью анонимного класса                           public Comparable<String> cmp = new Comparable<String>() {
 *                                                             @Override
 *                                                             public int compareTo(String o) {
 *                                                             return OuterClass.this.text.compareTo(o);
 *                                                             });
 *
 * - с помощью ссылки на метод                              (Function<String, Integer> fun2 = String::length;)
 *
 * - с помощью лямбда выражения                             (Function<String, Integer> fun1 = a -> a.length();)
 *
 * - с помощью результата работы статического или дефолт метода функционального интерфейса
 *                                                          (Function<Integer, Integer> fun = Function.identity();)
 *                                                          (Function<String, int[]> fun3 = fun1.andThen(fun2);)
 *
 * Можно также обойтись без создания ссылки на реализацию подав в аргументы метода не ссылку,
 * а непосредственно лямбду, или создав там объекта класса, реализующего этот интерфейс(или ссылку на него),
 * или создав в аргументах метода анонимный класс.
 *
 */