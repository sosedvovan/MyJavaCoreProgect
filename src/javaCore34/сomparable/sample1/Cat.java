package javaCore34.сomparable.sample1;

import java.util.Objects;

//Пример реализации Comparable<Cat> в виде класса Cat:
public class Cat implements Comparable<Cat>{

    //поля
    private String name;
    private int age;
    //конструктор
    public Cat(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
    //пустой конструктор
    public Cat() {
        super();
    }
    //далее геттеры и сеттеры
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    //переопределили toString
    @Override
    public String toString() {
        return "Cat [name=" + name + ", age=" + age + "]";
    }

    //переопределили equals класса Object:
    //Желательно что бы отношение естественного порядка было согласованно с
    //отношением эквивалентности. те когда объекты равны по equals(возвр. true) и compareTo возвращает 0 (то же с false и 1 и -1)
    //это надо для правильной работы множества Set
    //Реализация метода equals для Cat производит сравнение не только по возрасту но и
    //по имени. Следовательно в реализации абстракта compareTo интерфейса Comparable это нужно учесть.
    @Override
    public boolean equals(Object o) {
        //если подали тот же самый объект, как и текущий(их ссылки равны), то возвращаем сразу true:
        if (this == o) return true;
        //если подали null или объект другого класса - возвращаем false:
        if (o == null || getClass() != o.getClass()) return false;
        //кастуем o - изменяем тип Object из аргументов к нашему классу и пришедший объект называем cat:
        Cat cat = (Cat) o;
        //сравниваем первые поля двух объектов с пом. ==, тк в поле age - int (если != возвращаем false):
        if (age != cat.age) return false;

        //сравниваем вторые поля двух объектов с пом. equals класса String(в закоментированном варианте):
        // если name != null тогда сравниваем так: name.equals(cat.name) , иначе сравниваем так: cat.name == null(будет false???)
        //return name != null ? name.equals(cat.name) : cat.name == null;
        //return Objects.equals(name, cat.name);//исправили желтое подчеркивание получили как у автора

        ////сравниваем вторые поля двух объектов с пом. equals класса Objects
        return age == cat.age && Objects.equals(name, cat.name);
    }

    //автоматом вместе с equals переопределили hashCode, но hashCode для Comparable не нужен (а нужен для добавления в Map и сортировки)
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    //тк желательно, чтобы equals и compareTo были согласованны, так и сделаем:
    @Override
    public int compareTo(Cat cat) {
        //правильно при использовании интерфейса Comparable при переопределении compareTo -
        // делать проверку на null и выбрасывать NullPointerException, даже если equals при этом возвращает false
        if (cat == null) {
            throw new NullPointerException();
        }

        //задаем логику сравнения котов:
        //сначала сравниваем их возраста:
        if (this.age > cat.age) {
            return 1;
        } if (
                this.age < cat.age) {
            return -1;
            //а если возраста одинаковые тогда сравниваем имена:(с пом. метода из класса String: compareTo(cat.name)
            // который также, как и compareTo возвращает любое положительное или отрицательное число, или 0)
        } return this.name.compareTo(cat.name);

        //то инты мы сравнили в рукописных блоках if, а сранения строк делигировали методу из класса String: compareTo
        //Отношение естественного порядка основывается на возрастах. И если возраст кошек
        //разный то будет получен результат. Но если он одинаков, то будет возвращено сравнение
        //имен. Это выполнено для согласования с equals.
    }
}




