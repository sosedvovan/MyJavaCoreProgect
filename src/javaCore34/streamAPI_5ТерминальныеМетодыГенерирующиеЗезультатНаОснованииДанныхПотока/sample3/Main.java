/**
 * Методы для поиска минимума и максимума
 * <p>
 * Существует два метода возвращающие минимальный и максимальный элемент
 * потока (при условии что в потоке есть элементы).
 * <p>
 * Optional<T> max (Comparator<? super T> javaCore34.comparator) - вернет Optional с
 * максимальным элементом потока. Отношения порядка определяется реализацией
 * Comparator.
 * <p>
 * Optional<T> min (Comparator<? super T> javaCore34.comparator) - вернет Optional с минимальным
 * элементом потока. Отношения порядка определяется реализацией Comparator.
 */

package javaCore34.streamAPI_5ТерминальныеМетодыГенерирующиеЗезультатНаОснованииДанныхПотока.sample3;

import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        //имеются следующие объекты
        ProgrammingLanguage lang1 = new ProgrammingLanguage("Haskell", DifficultyLevel.HARD);
        ProgrammingLanguage lang2 = new ProgrammingLanguage("Python", DifficultyLevel.EASY);
        ProgrammingLanguage lang3 = new ProgrammingLanguage("Java", DifficultyLevel.MEDIUM);
        ProgrammingLanguage lang4 = new ProgrammingLanguage("C++", DifficultyLevel.HARD);
        ProgrammingLanguage lang5 = new ProgrammingLanguage("JS", DifficultyLevel.EASY);

        //создадим лист с этими объектами
        List<ProgrammingLanguage> languages = List.of(lang1, lang2, lang3, lang4, lang5);

        //Терминальный метод max
        //породили поток на languages и сразу затерминалили с пом max с Компоратором в аргументах. max возвращает Optional
        Optional<ProgrammingLanguage> result = languages.stream().max(Main::compareHard);
        System.out.println(result.get());
    }

    //Метод для сравнения двух языков, реализует абстракт корпоратора
    public static int compareHard(ProgrammingLanguage a, ProgrammingLanguage b) {
        if (a.getComplexity().ordinal() > b.getComplexity().ordinal()) {//ordinal() возвращает порядковый номер enum
            return 1;
        }
        if (a.getComplexity().ordinal() < b.getComplexity().ordinal()) {
            return -1;
        }
        return Integer.compare(a.getName().length(), b.getName().length());
    }
}

enum DifficultyLevel {
    EASY, MEDIUM, HARD;
}

//Вспомогательный класс который описывает язык программирования и сложность его изучения.
class ProgrammingLanguage {

    //поля
    private String name;
    private DifficultyLevel complexity;//это enum

    //конструктор
    public ProgrammingLanguage(String name, DifficultyLevel complexity) {
        super();
        this.name = name;
        this.complexity = complexity;
    }

    public String getName() {
        return name;
    }

    public DifficultyLevel getComplexity() {
        return complexity;
    }

    @Override
    public String toString() {
        return "ProgrammingLanguage [name=" + name + ", complexity=" + complexity + "]";
    }
}
