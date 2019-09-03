package andr;

public class Soap implements EyeDamager {
    private boolean isLiquid ;
    private Color color;
    private int damage;


    public Soap(Color color, int dmg){
        this.color = color;
        damage = dmg;
    }

    public boolean isLiquid() {
        return isLiquid;
    }

    @Override
    public int getDmg() {
        return damage;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color.toString() + "Мыло";
    }

    @Override
    public int hashCode() {
        int kaef = 12;
        int result = 1;
        int isliquid = isLiquid()? 1 : 0;
        result = kaef * result + color.hashCode() + damage + isliquid;
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
        Soap other = (Soap) obj;
        if ( color!= other.color)
            return false;
        if (isLiquid!= other.isLiquid)
            return false;
        if (damage!= other.damage)
            return false;

        return true;

    }
}
