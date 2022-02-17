package javaCore34.functionalInterfaceAndContainingMethod.sample5;

@FunctionalInterface
public interface Generator {

    //Конструктор всех массивов принимает int значение:
    public Object createNewObject(int size);
}
