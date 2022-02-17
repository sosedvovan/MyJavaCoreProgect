package dopMaterial.IO_File.sample2;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Java FilenameFilter. Получаем список файлов с определенным расширением
 *
 * Интерфейс java.io.FilenameFilter может быть реализован для фильтрации имен файлов в определенной папке.
 * Интерфейс FilenameFilter содержит метод boolean accept(File dir, String name). Класс должен реализовывать
 * этот метод, а каждый тестируемый файл должен быть включен в общий список файлов.
 */
// Реализация интерфейса FileNameFilter для подачи  в аргументы метода listFiles()
public final class FileFilter implements FilenameFilter {

    //в поле принимет массив с искомыми строками для фильтрации
    private final String[] ends;

    //конструктор
    public FileFilter(final String[] ends) {
        this.ends = ends;
    }

    //абстракт FilenameFilter принимает папку в которой будем искать и искомую строку
    @Override
    public boolean accept(final File dir, final String name) {
        boolean result = false;//предполагаем что искомое значение отсутствует
        for (String end : this.ends) {//итерируемся по массиву поля
            if (name.endsWith(end)) {//endsWith() возвращает true, если строка заканчивается на значение в аргументе
                result = true;
                break;
            }
        }
        return result;//возвращаем true если есть хотя бы одно совпадение, и false если нет совпадений
    }
}

