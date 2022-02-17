package javaCore34.innerCasses.sample4;

//Наследник внутреннего класса
public class InnerSubclass extends Outer.Inner {

    public InnerSubclass(Outer outer) {//Ссылка на объект внешнего класса
        outer.super("Hello");//Вызов конструктора суперкласса. Т.е. внутреннего класса, выступающего в роли суперкласса.
    }
}

/**
 *При наследовании от внутренних классов необходимо в конструктор подкласса
 * передать ссылку на объект внешнего класса (inner classes)в примере Outer outer). И используя эту
 * ссылку, вызвать конструктор внутреннего класса (inner classes)в примере outer.super("Hello") это
 * вызов конструктора:
 *
 * public Inner(String text) {
 * super();
 * this.text = text;
 * }
 *
 */