package dopMaterial.concarencyTest.threadLocal;
//ThreadLocal<String> это еще один поток(как бы утильный).
//каждый наш рабочий поток может положить(set) в ThreadLocal<String> свою срингу, а потом взять ее же (get).
//те на стрингу, принадлежащую конкретному потоку, другой поток повлиять не может
public class Main {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        class MyRunnable implements Runnable {

            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println(name + " first threadLocal: "
                        + threadLocal.get());
                threadLocal.set(name + " thread value");
                System.out.println(name + " end threadLocal: "
                        + threadLocal.get());
            }
        };

        threadLocal.set("From main thread");
        Thread thread1 = new Thread(new MyRunnable(), "first_thread");
        thread1.start();
        Thread thread2 = new Thread(new MyRunnable(), "second_thread");
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("fromMainThread: " + threadLocal.get());
    }
}
//first_thread first threadLocal: null
//second_thread first threadLocal: null
//first_thread end threadLocal: first_thread thread value
//second_thread end threadLocal: second_thread thread value
//fromMainThread: From main thread

//run  еще раз
//second_thread first threadLocal: null
//first_thread first threadLocal: null
//second_thread end threadLocal: second_thread thread value
//first_thread end threadLocal: first_thread thread value
//fromMainThread: From main thread
/**
 *
 ThreadLocal в Java

 Класс java.lang.ThreadLocal<T> используется для хранения переменных, которые должны быть доступны для всего потока.
 Фактически это нечто вроде ещё одной области видимости переменных. Класс ThreadLocal  имеет методы get  и set,
 которые позволяют получить текущее значение и установить новое значение.

 Обычно экземпляры ThreadLocal  объявляются как приватные статические переменные в классе. Каждый поток получает
 из метода get своё значение и устанавливает через set тоже своё значение, изолированное от других потоков.

 Про ThreadLocal  любят спрашивать на собеседованиях в дополнение к вопросам многопоточности и её проблем.

 Для чего можно использовать ThreadLocal  в Java:

 Хранение информации о пользователе и его правах доступа, что позволит не передавать её в качестве аргумента в каждый
 класс или метод.
 Можно использовать ThreadLocal  для кеширования непотокобезопасных ресурсов вроде SimpleDateFormat.
 Различные счётчики обращений для потоков.

 Имейте в виду, что ThreadLocal гарантирует лишь то, что каждый поток получит ссылку на свой объект,
 но он не изолирует сами объекты. Если два разных потока положат в ThreadLocal один и тот же объект,
 то при доступе к нему будут возникать все проблемы многопоточности.
 */