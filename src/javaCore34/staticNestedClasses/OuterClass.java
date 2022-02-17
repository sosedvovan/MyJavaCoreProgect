package javaCore34.staticNestedClasses;

public class OuterClass {

    private String text;
    private static int number = 10;

    public OuterClass(String text) {
        super();
        this.text = text;
    }

    //Объявление статического вложенного класса
    public static class NestedClass {
        private String word;

        public NestedClass(String word) {
            super();
            this.word = word;
        }

        public void printText() {
            //Доступ к private статическому полю внешнего класса - number
            System.out.println(word + " " + number);
        }

        public void printText(OuterClass oc) {
            System.out.println(word + " " + number + " " + oc.text);//oc.text - Доступ к private полю внешнего класса
            // через ссылку на объект внешнего класса
        }
    }
}

/**
 * Статические вложенные классы создаются путем определения класса с
 * модификатором static в теле другого класса.
 * Статические вложенные классы имеют доступ только к статическим членам
 * внешнего класса. Для создания объекта статического вложенного класса не требуется
 * объект внешнего класса.
 * <p>
 * Из тела статического вложенного класса имеется доступ только к статическим
 * членам внешнего класса. Однако если в теле статического вложенного класса получить
 * ссылку на объект внешнего класса, то можно обращаться к его private членам.
 *
 * Статические вложенные классы часто используют для:
 * ● Подчеркивание, что одна сущность (Static nested classes)внешний класс) состоит из более мелких
 * частей (Static nested classes)внутренний класс).
 * ● Скрытие части логики внутри класса. Например, узел в реализации дерева или
 * map.
 */
