package javaCore34.streamAPI_8КлассCollectors.sample2;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    //Todo Файловый классификатор на основе groupBy
    public static void main(String[] args) {

        //вспомогательная мапа, в кот расширения файлов ассоциированно со словом: document или document (или not classified далее)
        Map<String, List<String>> fileType = Map.of("document", List.of("txt", "docx", "odt", "pdf"), "image", List.of("jpg", "png"));

        //Функция для получения расширения файла:
        Function<File, String> getFileExc = a -> {
            int n = a.getName().lastIndexOf(".");
            if (n == -1) {//если это папка, а не файл
                return "";
            }
            return a.getName().substring(n + 1); //если это файл
        };


        //Функция для связи расширения файла с его типом
        Function<File, String> outerClassifier = file -> {
            for (String type : fileType.keySet()) {
                String ext = getFileExc.apply(file);
                if (fileType.get(type).contains(ext)) {
                    return type;
                }
            }
            return "not classified";
        };


        Function<File, String> innerClassifier = a -> getFileExc.apply(a);

        //Группировка файлов по типам
        Map<String, Map<String, List<File>>> groupByExt = Arrays.stream(new File("sample_folder").listFiles())
                .collect(Collectors.groupingBy(outerClassifier, Collectors.groupingBy(innerClassifier)));
        groupByExt.forEach((type, typeMap) -> {
            System.out.println(type);
            typeMap.forEach((ext, files) -> {
                System.out.println("\t" + ext);
                files.forEach(a -> System.out.println("\t\t" + a));
            });
        });

        //Вывод:
        //image
        //	jpg
        //		sample_folder\1.jpg
        //document
        //	txt
        //		sample_folder\1.txt
        //		sample_folder\2.txt
        //	pdf
        //		sample_folder\1.pdf


        //Как работает метод toMap с внутренним Collector
        //Этот метод возвращает реализацию Collector собирающий элементы потока в карту. С помощью
        //classifier создаются ключи на основании элементов потока, каждому созданному ключу соответствует
        //значение получаемое на основе внутреннего Collector.
        // схему см на 13-ой стр конспекта


    }
}
