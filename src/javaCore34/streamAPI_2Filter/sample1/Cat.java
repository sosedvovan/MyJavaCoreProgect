package javaCore34.streamAPI_2Filter.sample1;

//Класс который мы будем использовать для демонстрации
public class Cat {

    private String name;
    private int weight;
    private String color;

    public Cat(String name, int weight, String color) {
        this.name = name;
        this.weight = weight;
        this.color = color;
    }

    public Cat() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        return "Cat [name=" + name + ", weight=" + weight + ", color=" + color + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cat cat = (Cat) o;

        if (weight != cat.weight) return false;
        if (name != null ? !name.equals(cat.name) : cat.name != null) return false;
        return color != null ? color.equals(cat.color) : cat.color == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + weight;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
