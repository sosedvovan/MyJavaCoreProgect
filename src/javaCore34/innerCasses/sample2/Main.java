package javaCore34.innerCasses.sample2;

//использование такой реализации:
//мы отправляем 2-а числа, и получаем разницу их кадратов, или разницу их кубов
public class Main {

    public static void main(String[] args) {
        //создаем объект внешнего класса:
        MathFunction mf = new MathFunction();
        //обращаемся к статическому методу этого класса:
        //в зависимости- какой объект положим в calculate()- будет или квадратура или кубатура
        System.out.println(calculate(mf.getSquare(), 1, 4));
        System.out.println(calculate(mf.getCube(), 1, 4));
    }


    //значит начинаем разработку программы с метода, кот принимает разные объекты(носители возведения в квадрат или куб)
    // и числа и возвр число-результат:
    public static int calculate(Function fun, int begin, int end) {
        return fun.calculate(begin, end);
        //fun- это объект одного из внутреннего класса- он обращается к методу своего класса.
    }
}
