package javaCore34.innerCasses.sample1;

public class Letter {
    private String sender;

    public Letter(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Letter [sender= " + sender + ']';
    }

    //создание объекта внутреннего класса во внешнем-обычным способом:
    Address adr = new Address(355000);


    //объявляем внутренний класс:
    public class Address {
        private int postCode;

        public Address(int postCode) {
            this.postCode = postCode;
        }

        //Метод внутреннего класса, который вернет поле внешнего: те есть такой доступ, но не наоборот.
        public String getSenderLetter (){
            return sender;
        }

        @Override
        public String toString() {
            //Letter.this-- Ссылка на объект внешнего класса (если без Letter- обратимся к объекту внутреннего)
            return "Address [postCode= " + postCode + ']' + Letter.this.toString();
        }
    }
}

/**
 *   Для внутренних классов существует ряд ограничений:
 * ● Не могут иметь статические поля и методы
 * ● Во внутреннем классе нельзя объявлять перечисления (inner classes)enum))
 * ● Нельзя вернуть объект внутреннего класса из статического метода внешнего
 *   (inner classes)
 *
 *   -----всегда нужна ссылка на объект внешнего класса)------
 *
 *   Между объектом внутреннего и внешнего класса существует однозначная связь.
 * Также во внутреннем классе вам доступны все члены внешнего класса (inner classes)но не наоборот).
 *
 * ----Это означает: внутренний класс имеет полный доступ к членам внешнего класса.-----
 */
