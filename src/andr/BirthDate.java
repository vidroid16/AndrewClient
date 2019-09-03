package andr;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

class BirthDate implements Serializable {
    private int day;
    private String month;
    private int year;

    public BirthDate(){
        String[] arr  = new Date().toString().split(" ");
        this.day = Integer.parseInt(arr[2]);
        this.month = arr[1];
        this.year = Integer.parseInt(arr[5]);
    }
    public BirthDate(JSONObject obj){
        day = obj.getInt("day");
        year = obj.getInt("year");
        month = obj.getString("month");
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }
    public int hashCode() {
        int kaef = 52;
        int result = 1;
        result = (int)(kaef * result + Objects.hash(day,year) + month.hashCode());
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
        BirthDate other = (BirthDate) obj;
        if (getDay() != other.getDay())
            return false;
        if (!getMonth().equals(other.getMonth()))
            return false;
        if (getYear() != other.getYear())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return day  + " " + month + " " + year;
    }

    public JSONObject getJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("day", day);
        jsonObject.put("month", month);
        jsonObject.put("year", year);
        return jsonObject;
    }

}

