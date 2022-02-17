package javaCore34.functionalInterfaceAndContainingMethod.sample7;

//Автовыведение типа для функционального интерфейса
//обращаем внимание, что этот функциональный интерфейс не параметризованн(ситуация обратная предидущему sample)
//а метод реализующий этот интерфейс будет параметризован

@FunctionalInterface
public interface IntElementGenerator {

    public Integer next(Integer current);
}
