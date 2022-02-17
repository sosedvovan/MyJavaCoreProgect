package javaCore34.anonymousInnerClasses;

//использование анонимного класса в контексте обычного наследования:
public class Cat {
    //поле класса
    String name;

    //конструктор
    public Cat(String name) {
        this.name = name;
    }
    //конструктор
    public Cat() {
    }


    public void getVoise(){
        System.out.println("May");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }

    //анонимный класс внутри метода- как локальный возвращает ссылку на класс наследника этого класса Cat и
    //переопределяет метод этого класса getVoise(). в тестовом методе вызываем этот метод через объект класса Cat,
    //и на возвращенном объекте вызываем переопределенный метод-----vasy.getInstanseCat().getVoise();-------
    public Cat getInstanseCat() {
        Cat anonimCat = new Cat() {
            @Override
            public void getVoise() {
                System.out.println("anon2 podal golos");
            }
        };
        return anonimCat;
    }




    //внутренний класс, кот еще и является наследником внешнего- можно создать много его инстансов в тестовом классе
    //и ему не обязательно быть внутренним
    //вместо него в тестовом классе можно создать один объект наследника с пом анонимного класса
    class SonCat extends Cat{
        public void getVoise(){
            System.out.println("iAm small cat");
        }
    }
}





class ProstoClassTest {
    public static void main(String[] args) {
        Cat vasy = new Cat("Vasy");//создали объект класса Cat

        System.out.println(vasy);
        vasy.getVoise();//объект класса Cat вызвал свой метод

       //создадим ссылку anon на объект класса без имени в этом потоковом методе main,
        // который явл наследником класса Cat и переопределяет его метод
        Cat anon = new Cat("Vasy"){
            @Override
            public void getVoise() {
                System.out.println("gov");
            }
        };

        //объект анонимного класса вызвал свой переопределенный метод
        anon.getVoise();

        //создание объекта внутреннего класса, используя объект внешнего
        Cat.SonCat sonCat = vasy.new SonCat();//объект внутреннего класса SonCat
        sonCat.getVoise();

        //то же, что и выше в main, только обратились к пустому конструктору
        Cat anon2 = new Cat(){
            @Override
            public void getVoise() {
                System.out.println("anon2 podal golos esho raz");
            }
        };

        anon2.getVoise();

        //тест метода getInstanseCat() класса Cat, кот возвращает ссылку на наследника Cat,
        //и переопределяет метод getVoise()
        vasy.getInstanseCat().getVoise();
    }
}
