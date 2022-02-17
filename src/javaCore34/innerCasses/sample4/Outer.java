package javaCore34.innerCasses.sample4;

//Пример наследования от внутреннего класса
public class Outer {

    private int id;

    public Outer(int id) {
        this.id = id;
    }

    public Outer() {
    }

    //Объявление внутреннего класса
    public class Inner {
        private String text;

        public Inner(String text) {
            this.text = text;
        }

        public String getTotalInfo() {
            return text + " " + id;
        }
    }
}
