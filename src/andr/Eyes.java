package andr;

import org.json.JSONObject;

public class Eyes{
    private Color color;
    private boolean isDamaged;
    private int health = 100;
    public Eyes(Color color){
        this.color = color;
    }

    public void restoreHealth(){
        health = 100;
    }

    public void doDamage(int dmg){
        health -= dmg;
    }

    public void setColor(Color color){
        this.color = color;
    }

    @Override
    public String toString() {
        return color+" глаза";
    }

    @Override
    public int hashCode() {
        int kaef = 3;
        int result = 1;
        int isdamaged =isDamaged ? 1 : 0;
        result = kaef * result +  color.hashCode()+health+isdamaged;
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
        Eyes other = (Eyes) obj;
        if (color != other.color)
            return false;
        if (isDamaged != other.isDamaged)
            return false;
        if (health != other.health)
            return false;
        return true;

    }

    public JSONObject getJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("color", color.toString());
        jsonObject.put("isDamaged", isDamaged);
        jsonObject.put("health", health);

        return jsonObject;
    }
}

