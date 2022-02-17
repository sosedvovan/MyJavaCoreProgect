package javaCore34.innerCasses.sample3;

import java.util.Iterator;
import java.util.NoSuchElementException;

//Реализация итераторов строк/ здесь мы будем иметь несколько объектов, каждый из которых обладает своим собственным
//  состоянием, и в тоже время обладает общим состоянием объекта внешнего класса
//здесь и внешнии и внутренний имплементят интерфейсы

public class StringWrapper implements Iterable<Character> {

    private String text;

    public StringWrapper(String text) {
        this.text = text;
    }

    @Override
    public Iterator<Character> iterator() {
        return new StringIterator();
    }

    //Реализация Iterator внутренним классом
    private class StringIterator implements Iterator<Character> {
        private int start = 0;

        @Override
        public boolean hasNext() {
            return start < text.length();
        }

        @Override
        public Character next() {
            if (hasNext()) {
                return text.charAt(start++);
            }
            throw new NoSuchElementException();
        }
    }
}
