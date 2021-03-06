package javaCore34.functionalInterfaceAndContainingMethod.sample11;

import java.util.Arrays;
import java.util.function.Function;//встроенный в Яву функциональный интерфейс

//Более сложный пример. Композиция функций:
//возьмем один функциональный интерфейс, кот параметризированный 2-мя объектами  Function<T, R>
//напишем 2-а метода которые  по разному его реализуют - реализуют логической цепочкой  Main::intToStr  ,  Main::strToStrArray
//напишем 3-й метод, кот будет являться композицией из 2-х реализаций с определенной очередностью в аргументах  --- functionComposition(Main::intToStr, Main::strToStrArray)-----
//результат которого можно было бы назвать intToArray, те Str останется как промежутка в логике

//Целью этого примера была реализация приема композиции функций. Т.е. когда на
//вход приходят две функции, а результатом который возвращает функция является третья функция.

//Первая функция на основе параметра F генерирует значение S, вторая на основе
//параметра S генерирует R. Композиция — функция которая на основе F генерирует R.

public class Main {

    public static void main(String[] args) {

        //Ссылки на методы как параметры метода:
        //создаем ссылку на функцианальный интерфейс ---Function<Integer, String[]> fun = ----
        //причем параметризированна она тем, что на выходе у метода functionComposition()
        //и присваеваем этой ссылке реализацию композиции двух методов:    -----  = functionComposition(Main::intToStr, Main::strToStrArray)-----

        Function<Integer, String[]> fun = functionComposition(Main::intToStr, Main::strToStrArray);// = a::comp;
        //то метод functionComposition() ЯВЛЯЕТСЯ КОМПОЗИЦИОННОЙ РЕАЛИЗАЦИЕЙ абстрактного метода: ---R apply(T var1); ---
        //функционального интерфейса Function<T, R>, встроенного в Яву (то есть в аргументах метода functionComposition()
        // 2-а раза реализуется абстрактный метод функционального интерфейса : ---R apply(T var1);
        // с помощью ссылок на два нижних метода : Main::intToStr  и  Main::strToStrArray )

        String[] res = fun.apply(5);//ссылка на интерфейс запускает свой абстрактный метод apply(T var1), который запускает
                                      //соотнесенный с ним functionComposition()
 //   !!!    //на основании 5-ки формируется строка, на основании этой строки формируется массив строк
 //   !!!    //те из одной цифры получился целый строковый массив, заполненный строками. Это и есть функциональная парадигма.

        System.out.println(Arrays.toString(res));//[Hello, Hello, Hello, Hello, Hello]
    }


    //метод на вход принимает 2-е функции, каждая и которых:
    //1-я принимает F(Integer), возвращает S(String)
    //2-я принимает S(String), возвращает R(String[])
    //на выходе из метода будет функция, которая принимает F, возвращает R (то S остается внутри такой композитной реализации: на выходе S нет, она промежуточная)
    public static <F, S, R> Function<F, R> functionComposition(Function<F, S> f1, Function<S, R> f2) {//здесь в аргументах 2-е ссылки на методы Main::intToStr  и  Main::strToStrArray

        class Composition {
            public R comp(F t) {//в локальном классе описан метод, который принимает F (5 в нашем случае), возвращает R (массив из 5-и элементов)
                S temp = f1.apply(t);//создаем объект S типа используя применения 1-й функции f1 к входящим аргументам (F t) типа (получилась строка "Hello " + "5")
                return f2.apply(temp);//возвращаем результат применения 2-й функции к полученным данным S типа (получился массив из 5-ти элементов со значениеми Hello каждый)
            }
        } //закрыли локальный класс

        Composition a = new Composition();//после чего создаем объект этого класса

        return a::comp;//и возвращаем ссылку на указанный метод(который как раз примет F и вернет R (Function<F, R>) - те совместим с функцион. интерфейсом)
    }

    //далее опишем первый метод, который на вход принимае число, а на выходе у него строка (это Function<F, S> f1 из метода выше)
    public static String intToStr(Integer count) {
        return "Hello " + count;
    }


    //и второй метод, кот на входе принимает строку, а на выходе у него массив строк (это Function<S, R> f2 из метода выше)
    public static String[] strToStrArray(String text) {
        //генерация массива строк:(тк метод должен вернуть массив строк)
        String[] r = text.split(" ");//разбиваем строку по пробелам
        String[] result = new String[Integer.valueOf(r[1])];//создаем новый массив, размером с count из return вышележащего метода
        for (int i = 0; i < result.length; i++) {//заполняем массив строками "Hello " из return вышележащего метода
            result[i] = r[0];
        } return result;//и возвращаем этот массив
    }
}


//Связь ссылок на методы и лямбда функций:
//Ссылки на методы тесно связанны с таких механизмом как лямбда функции. Однако
//их изучение мы оставим на следующую лекцию.

