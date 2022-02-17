package javaCore34.staticNestedClasses;

public class Main {
    public static void main(String[] args) {

        //Для создания объекта статического вложенного класса нужно указывать тип как
        //Имя внешнего класса. Имя внутреннего класса
        //В остальном процедура создания идентична обычному классу
        OuterClass.NestedClass on = new OuterClass.NestedClass("Hello");

        OuterClass oc = new OuterClass("World");
        //вызываем метод вложенного класса и передаем туда объект внешнего класса, чтобы внутри метода
        // вложенного класса обратиться к private переменной внешнего класса
        //тк если в теле статического вложенного класса получить
        //ссылку на объект внешнего класса, то можно обращаться к его private членам.
        on.printText(oc);
    }
}
