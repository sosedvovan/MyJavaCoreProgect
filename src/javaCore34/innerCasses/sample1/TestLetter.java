package javaCore34.innerCasses.sample1;

public class TestLetter {

    public static void main(String[] args) {
        //создание объекта внутреннего класса:
        //сначала создаем объект внешнего класса:
        Letter l = new Letter("Владимир");
        Letter.Address adrr = l.new Address(355000);

        //получаем доступ к полю внешнего класса из внутреннего:
        System.out.println(adrr.getSenderLetter());
    }
}


