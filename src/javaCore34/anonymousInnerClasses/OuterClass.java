package javaCore34.anonymousInnerClasses;

//Анонимный класс в качестве внутреннего класса
public class OuterClass {

    //первое поле класса
    private String text;

    //второе поле класса
    //Объявление анонимного класса как внутреннего.
    //(можно еще в методе объявить- будет типа локального анонимного класса)
    //здесь переменная cmp объявляется как ПОЛЕ класса- перед конструктором
    public Comparable<String> cmp = new Comparable<String>() {
        @Override
        public int compareTo(String o) {
            //у анонимного класса имеется Доступ в полю внешнего класса text
            //доступ осуществляется через OuterClass.this....(все как во внутренних классах)
            //compareTo() метод interface Comparable, реализован в классе: String implements Comparable<String>
            //он таким образом реализован в String, что принимает строку, сравнивает ее с текущей строкой, а возвращает число
            //те можно любой класс (напр Person) сделать implements Comparable<Person>,
            //реализовать в нем compareTo() нужным нам образом и можно будет сравнивать инстансы Person и исп двоичн поиск
            return OuterClass.this.text.compareTo(o);
        }
    };

    //конструктор
    public OuterClass(String text) {
        super();
        this.text = text;
    }

    //метод будет вызываться из тестового класса через объект этого класса (внутри которого есть своё поле String text)
    //и compareTo() будет сравнивать это своё поле объекта (текущее) с пришедшим в аргументы метода.
    //метод вернет число
    public int compareLength(String text) {
        return cmp.compareTo(text);
    }
}
