package andr;

import andr.DataBaseWorks.DBConst;
import andr.DataBaseWorks.iQuery;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Objects;

public class Photo implements Serializable, iQuery {
    private boolean isColored;
    private int height;
    private int width;
    private String link;

    public Photo(boolean isColored, int height, int width, String link ){
        this.isColored = isColored;
        this.height = height;
        this.width = width;
        this.link = link;
    }
    public Photo(JSONObject obj){
        isColored = obj.getBoolean("isColored");
        height = obj.getInt("height");
        width = obj.getInt("width");
        link = obj.getString("link");
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getLink() {
        return link;
    }

    public boolean isColored() {
        return isColored;
    }

    @Override
    public int hashCode() {
        int kaef = 51;
        int result = 1;
        int iscolored = isColored ? 1 : 0;
        result = (int)(kaef * result + kaef*iscolored + Objects.hash(height, width) +
                link.hashCode());
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
        Photo other = (Photo) obj;
        if (getHeight() != other.getHeight())
            return false;
        if (getWidth() != other.getWidth())
            return false;
        if (!getLink().equals(other.getLink()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Res: " + width + "X" + height;
    }

    public JSONObject getJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isColored", isColored);
        jsonObject.put("height", height);
        jsonObject.put("width", width);
        jsonObject.put("link", link);
        return jsonObject;
    }

    @Override
    public String getInsertSqlQuery() {
        return String.format("INSERT INTO %s VALUES(%s, %s, %s, %s, '%s')",
                DBConst.PHOTO_TABLE, "DEFAULT", isColored(), getWidth(), getHeight(), getLink());
    }

    @Override
    public String getDelSqlQuery() {
        return String.format("DELETE FROM %s WHERE id=%s", DBConst.PHOTO_TABLE, "DEFAULT");
    }
}