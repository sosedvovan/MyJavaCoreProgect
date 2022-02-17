package javaCore34.functionalInterfaceAndContainingMethod.sample10;

@FunctionalInterface
public interface StringModificationFunction {

    public String modification(String base);
}
//те для реализации этого абстрактного метода подойдет любой метод, ко принимает строку и возвращает строку.