package javaCore34.lambdas.sample6;

//Лямбда функции и исключения:
//В случае если из лябда функции «выбрасывается» проверяемое исключение, то оно
//должно быть описано в абстрактном методе функционального интерфейса.

import java.io.IOException;

@FunctionalInterface
public interface Saver {

    public void save(Object obj) throws IOException; //Описание исключения обязательно
}
