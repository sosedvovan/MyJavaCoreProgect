package javaCore34.privateMethodsInterfase.sample2;

import java.util.Comparator;

//Небезопасная относительно наличия null
//реализация Comparator
public class CatWeightComparator implements Comparator<Cat> {

    //реализуем сравнение котов по их весу:
    @Override
    public int compare(Cat o1, Cat o2) {
        if (o1.getWeight() - o2.getWeight() > 0) {
            return 1;
        }
        if (o1.getWeight() - o2.getWeight() < 0) {
            return -1;
        }
        return 0;
    }
}
