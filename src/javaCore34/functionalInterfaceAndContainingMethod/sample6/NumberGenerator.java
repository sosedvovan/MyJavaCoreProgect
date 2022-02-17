package javaCore34.functionalInterfaceAndContainingMethod.sample6;

//класс для демонстрации использования обобщенного функционального интерфейса
//его методы имееют возвращ значения и список аргументов как и у функционального интерфейса
//и чтобы в классе Main указать, какой именно из этих трех методов использовать в качестве реализации
//абстрактного метода- надо параметризовать ссылку типа интерфейс.
public class NumberGenerator {

    //три перегруженных метода:
    public Integer add(Integer n) {
        return n + 10;
    }

    public Double add(Double n) {
        return n + 10.0;
    }

    public String add(String n) {
        return n + " " + n;
    }

}
