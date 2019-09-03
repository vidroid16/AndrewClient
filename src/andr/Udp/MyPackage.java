package andr.Udp;

import java.io.*;

public class MyPackage implements Serializable {
    private int id;
    private byte[] data;
    private int userId;
    MyPackage(int id, byte[] data){
        this.data = data;
        this.id = id;
        this.userId = 0;
    }
    public MyPackage(int id, int userId, byte[] data){
        this(id, data);
        this.userId = userId;

    }
    public MyPackage(byte[] pack){
        try(ByteArrayInputStream bais = new ByteArrayInputStream(pack);
            ObjectInputStream ois = new ObjectInputStream(bais)){

            MyPackage newPack = (MyPackage) ois.readObject();

            this.userId = newPack.getUserId();
            this.id = newPack.getId();
            this.data = newPack.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public byte[] getSerialized(){
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)){
            oos.writeObject(this);
            return baos.toByteArray();
        }catch (IOException e){
            System.err.println("Serialization error");
        }
        return null;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setId(int id) {
        this.id = id;
    }
}
