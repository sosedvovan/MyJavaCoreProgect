package javaCore34.functionalInterfaceAndContainingMethod.sample7;

//Автовыведение типа для функционального интерфейса
//обращаем внимание, что метод этого класса, кот будет использован функциональным интерфейсом для реализации своего
//абстрактного метода- параметризован(в отличие от интерфейса)

public class Gen {

    public <T> T nextElement(T current) {
        return current;
    }
}
