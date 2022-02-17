package javaCore34.functionalInterfaceAndContainingMethod.sample3;

//Этот Класс для демонстрации не реализует функциональный интерфейс и не обладает
//методом с нужным названием. Однако метод функционального интерфейса использует
//ссылку на объект этого класса (смотри в классе Main).

public class IntGenerator {

    public int next() {
        return 0;
    }
}
//на первый взгляд аргументы в next() и в абстрактном getNextElement(IntGenerator gen)  -  различаются
//но на самом деле не забываем что не явно в аргументах любого не статического метода
// присутствует  public int next(IntGenerator this){...} - ссылка на текущий объект класса