package dopMaterial.interfaseTest;

public class InterfaceTest {
    static final int j = 4;
    static final int jо = 4;

    public static int getJ() {
        return j;
    }

    interface MyConsammer {
        void consam();
    }

    interface MyConsammerInt {
       int consamInt();
    }

    public static void topFunction(int i, MyConsammer myConsammer) {
        myConsammer.consam();
    }

    public static void topFunctionInt(int i, MyConsammerInt myConsammerInt) {
        System.out.println("topFunctionInt" + myConsammerInt.consamInt());
    }

    public static void main(String[] args) {

        topFunction(5, new MyConsammer() {
            @Override
            public void consam() {
                System.out.println("Привет из абстракта" + InterfaceTest.getJ());
            }
        });

        topFunction(5, () -> System.out.println("Привет из лямбды" + InterfaceTest.getJ()));

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                topFunction(5, () -> System.out.println("Привет из потока- лямбда " + InterfaceTest.getJ()));
                topFunction(5, InterfaceTest::linkMethod);
            }
        });
        thread.start();

        topFunctionInt(jо, new MyConsammerInt() {
            @Override
            public int consamInt() {
                return j + jо;
            }
        });

        topFunctionInt(jо, () -> j + jо);

        topFunctionInt(5, new Realizator());


    }//main


    public static void linkMethod() {
        System.out.println("Привет из linkMethod  из потока- ссылка на метод ");
    }


}//class

class Realizator implements InterfaceTest.MyConsammerInt{
    @Override
    public int consamInt() {
        return 5;
    }
}
