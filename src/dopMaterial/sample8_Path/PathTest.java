package dopMaterial.sample8_Path;

import org.w3c.dom.ls.LSOutput;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {
    public static void main(String[] args) {

        Path path1 = Paths.get("D:\\MyJavaCore\\MyJavaCoreProgect\\StringText.txt");
        Path path2 = Paths.get("StringText.txt");

        System.out.println(path1.toUri());//получ ссылку для браузера
        System.out.println(path1.toAbsolutePath());//получ абсолютн путь

        System.out.println(path1.equals(path2.toAbsolutePath()));//сравниваем пути
        System.out.println(path1.compareTo(path2));//сравниваем пути по кол-ву символов(0-если совпадает вернет)

        //http://www.quizful.net/post/java-nio-tutorial

    }
}
