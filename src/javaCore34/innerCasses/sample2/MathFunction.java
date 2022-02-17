package javaCore34.innerCasses.sample2;

//здесь мы имеем несколько реализаций одного и того же абстрактного класса или интерфейса в
// пределах одного внешнего класса

public class MathFunction {

    //1-и внутренний клас наледует и реализует абстракт метод
    private class Square extends Function {
        @Override
        public int function(int n) {
            return n * n;
        }
    }
    //2-и внутренний клас наледует и реализует абстракт метод
    private class Cube extends Function {
        @Override
        public int function(int n) {
            return n * n * n;
        }
    }

    //метод возвращает объект Square чз полиморфизм
    public Function getSquare() {
        return new Square();
    }
    //метод возвращает объект Cube чз полиморфизм
    public Function getCube() {
        return new Cube();
    }
}

