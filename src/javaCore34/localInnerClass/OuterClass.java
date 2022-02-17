package javaCore34.localInnerClass;


import java.util.Comparator;

//Пример локальные внутреннего класса в методе
public class OuterClass {

    private int i = 1;

    private boolean reverse;

    public OuterClass() {
    }

    public OuterClass(boolean reverse) {
        this.reverse = reverse;
    }

    public Comparator<String> getLengtComparator() {
        //Локальная переменная метода: должна быть Effective-final
        int n = 1;
        //Объявление локального вложенного класса
        class StringLengComparator implements Comparator<String> {
            @Override
            public int compare(String o1, String o2) {
                //Использование локальной переменной метода n и Поля внешнего класса i:
                return o1.length() - o2.length() * n * OuterClass.this.i;
            }
        }
        //Возврат ссылки на объект локального вложенного класса
        return new StringLengComparator();
    }

    //Локальный внутренний класс в теле цикла:
    public void toPrint() {
        for (int i = 0; i < 10; i++) {

            class Printer {//Объявление локального вложенного класса
                private String text;

                public Printer(String text) {
                    this.text = text;
                }

                public void print() {
                    System.out.println("***" + text + "***");
                }
            }
            //каждую итерацию будет создаваться инстанс new Printer (внутри него "Hello") и выполняться print()
            Printer prt = new Printer("Hello");
            prt.print();
        }
    }
}

/**
 * Локальные внутренние классы (local inner classes)local inner classes) — не статические классы,
 * объявленные внутри метода или локального блока кода. Локальные внутренние классы
 * не являются членами внешнего класса, а выступают в качестве локальных переменных.
 * Локальные внутренние классы являются подвидом внутренних классов.
 * Внимание: Статические локальные вложенные классы в Java не поддерживаются.
 * <p>
 * Локальный внутренний класс довольно часто применяют при объявлении в теле
 * метода. Это позволяет скрыть реализацию от внешнего доступа в теле метода. При этом
 * обычно используется несколько подходов:
 * ● Реализация логики метода с использованием объекта локального внутреннего
 * класса в теле метода
 * ● Реализация локальным внутренним классом абстрактного класса или интерфейса и
 * возврат ссылки требуемого типа
 * <p>
 * Локальные внутренние классы являются подвидом внутренних. И как следствие
 * обладают рядом схожих особенностей:
 * ● Доступ к локальным переменным того блока кода, в котором они объявлены. Однако
 * это относится к effective-final переменным. К ним относят final переменные и обычные
 * локальные переменные которые, не изменяют свое значение после инициализации.
 * ● В случае, если локальный класс описан в не статическом методе, имеется доступ к
 * членам объемлющего класса.
 * ● Если локальный внутренний класс описан в статическом контексте, то и доступ есть
 * только к статическим членам внешнего класса.
 * ● Запрещено объявление статических членов в локальных внутренних классах.
 * Однако можно объявлять статические константы.
 * ● Запрещено использование статических инициализаторов
 * ● Во внутреннем классе нельзя объявлять перечисления
 * <p>
 * Локальные внутренние классы обладают такими особенностями:
 * ● Объект локального класса не может создаваться за пределами метода или блока, в
 * котором его объявили
 * ● Требование effective-final для локальных переменных
 * ● Локальные внутренние классы не могут быть объявлены с модификаторами доступа
 * ● Локальные внутренние классы не могут быть static
 * <p>
 * Локальные внутренние классы можно объявлять в более узких блоках кода, чем
 * методы. Это, например, тело условного оператора, циклы, блоки инициализации,
 * блоки try-catch. В общем случае поведение таких классов не отличается от объявления
 * в классах. Только область доступа ограничена этим блоком.
 */
