package javaCore34.innerCasses.sample2;
//здесь мы будем иметь несколько реализаций одного и того же абстрактного класса или интерфейса в
// * пределах одного (inner classes)внешнего класса)

public abstract class Function {

    //метод который надо реализовать в наследниках
    public abstract int function(int n);

    //метод который наследуют наследники
    public int calculate(int begin, int end){
        return function(end) - function(begin);
    }


}

/**
 * Благодаря связи между объектами внутреннего и внешнего классов можно
 * реализовать несколько приемов программирования:
 * ● Иметь несколько реализаций одного и того же абстрактного класса или интерфейса в
 * пределах одного (inner classes)внешнего класса)
 * ● Иметь несколько объектов, каждый из которых обладает своим собственным
 * состоянием, и в тоже время обладает общим состоянием объекта внешнего класса
 * ● Можно создать объект внутреннего класса после создания объекта внешнего класса
 */