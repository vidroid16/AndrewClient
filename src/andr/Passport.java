package andr;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Objects;

public class Passport implements Comparable<Passport>, Serializable {
    private String name;
    private String sername;
    private String patronymic;
    private Photo photo;
    private BirthDate birthDate;
    private int ID;

    public Passport(String name, String sername, String patronymic, Photo photo){
        this.name = name;
        this.sername = sername;
        this.patronymic = patronymic;
        this.photo = photo;
        this.birthDate = new BirthDate();
        ID = (int)(Math.random() * 321);
    }
    public Passport(JSONObject obj){
        name = obj.getString("name");
        sername = obj.getString("sername");
        patronymic = obj.getString("patronymic");
        photo = new Photo((JSONObject) obj.get("photo"));
        birthDate = new BirthDate((JSONObject) obj.get("birthDate"));
        ID = obj.getInt("ID");
    }


    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public BirthDate getBirthDate() {
        return birthDate;
    }

    public Photo getPhoto() {
        return photo;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getSername() {
        return sername;
    }

    @Override
    public int hashCode() {
        int kaef = 53;
        int result = 1;
        result = (int)(kaef * result + Objects.hash(name, sername, patronymic) + photo.hashCode() + birthDate.hashCode());
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
        Passport other = (Passport) obj;
        if (!getName().equals(other.getName()))
            return false;
        if (!getBirthDate().equals(other.getBirthDate()))
            return false;
        if (!getSername().equals(other.getSername()))
            return false;
        if (!getPatronymic().equals(other.getPatronymic()))
            return false;
        if (!getPhoto().equals(other.getPhoto()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return " Паспорт персонажа " + sername + name + patronymic;
    }

    public JSONObject getJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("sername", sername);
        jsonObject.put("patronymic", patronymic);
        jsonObject.put("photo", photo.getJson());
        jsonObject.put("birthDate", birthDate.getJson());
        jsonObject.put("ID", ID);
        return jsonObject;
    }

    @Override
    public int compareTo(Passport o) {
        if (this.sername.compareTo(o.getSername())!=0) {
            System.out.println("Hello");
            return sername.compareTo(o.getSername());
        }else if(this.name.compareTo(o.name)!=0) {
            return name.compareTo(o.name);
        }else{
            return patronymic.compareTo(o.patronymic);
        }
    }
}
