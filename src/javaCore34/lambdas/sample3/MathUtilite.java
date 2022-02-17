package javaCore34.lambdas.sample3;

//Лямбда функция в качестве статического поля
//В случае если лямбда функция используется в качестве статического поля класса, то
//она имеет доступ ко всем статическим членам этого класса.
//здесь все тоже самое, что и в предидущем сэмпле, только все члены класса в статическом контексте
//для использования этого класса с функцион. интерфейсом можно взять класс Main из предидущего семпла

public class MathUtilite {

    //поле класса- массив
    private static int[] arr = new int[]{1, 2, 3};

    //поле класса - ссылка-интерфейс уже инициированна реализацией
    //Доступ к статическим членам класса:
    //здесь в теле лямда функции вызывается другой метод возвращающий инт
    private static Summator summator = () -> getSum(arr);

    //геттер для массива
    public static int[] getArr() {
        return arr;
    }

    public static void setArr(int[] arr) {
        MathUtilite.arr = arr;
    }

    public static Summator getSummator() {
        return summator;
    }

    public static int getSum(int[] array) {
        int s = 0;
        for (int element : array) {
            s += element;
        }
        return s;
    }
}
