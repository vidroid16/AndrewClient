package andr;

public class Food {
    private int price;
    private String name;

    public Food(String name, int price){
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int kaef = 4;
        int result = 1;
        result = kaef * result + price + name.length();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Food other = (Food) obj;
        if (price != other.price)
            return false;
        if (name != other.name)
            return false;
        return true;

    }
}
