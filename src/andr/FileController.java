package andr;

import org.json.*;

import java.io.*;
import java.util.*;

/**
 * @author Andrey Shalya
 * Класс для организации чтения и записи коллекции в файл
 */
public class FileController {
    /**
     * Путь к файлу
     */
    private String filePath;
    /**
     * Исходная коллекция
     */
    private Hashtable<Integer, Passport> collection;
    /**
     * Возвращяет ответ на филосовский вопрос: "Cуществует ли файл?"
     */
    private boolean isFileExists;

    /**
     * Конструктор
     * @param filePath путь к файлу
     */
    public FileController(String filePath){
        File file = new File(filePath);
        if (!file.exists()){
            System.out.println("Файл не найден!");
        }
        isFileExists = file.exists();
        this.filePath = filePath;
        collection = new Hashtable<>();
        try {
            collection.putAll(readCollection());
        }
        catch (JSONException e){
            System.out.println("Файл с коллекцией пуст!");
        }
    }

    /**
     * Чтение коллекции из файла
     * @return Коллекцию прочитанную из файла
     * @throws JSONException
     */
    public Hashtable<Integer, Passport> readCollection() throws JSONException{
        Hashtable<Integer, Passport> collection = new Hashtable<>();

        try(FileReader reader = new FileReader(filePath)) {

            Scanner scanner = new Scanner(reader);

            String str = "";

            while(scanner.hasNextLine())
                str += scanner.nextLine();

            JSONObject jsonObject = XML.toJSONObject(str);
            JSONArray valuesArray = jsonObject.getJSONArray("values");
            JSONArray keysArray = jsonObject.getJSONArray("keys");
            Iterator<Object> valIter = valuesArray.iterator();
            Iterator<Object> keyIter = keysArray.iterator();
            while(keyIter.hasNext() && valIter.hasNext()){
                collection.put((Integer)keyIter.next(), new Passport((JSONObject) valIter.next()));
            }
        } catch (IOException e) {
            System.out.println("Файл с коллекцией не найден!");
            System.exit(0);
        }

        return collection;
    }

    /**
     * Геттер isFileExists
     * @return isFileExists
     */
    public boolean isFileExists() {
        return isFileExists;
    }

    /**
     * Запись коллекции в файл
     * @param table сама коллекция
     * @return boolean произошла ли успешная запись
     */
    public boolean writeCollection(Hashtable<Integer, Passport> table){
        try(FileWriter writer = new FileWriter(filePath)) {
            ArrayList<Passport> valList = new ArrayList<>();
            ArrayList<Integer> keysList =  new ArrayList<>();

            Set<Map.Entry<Integer, Passport>> set = table.entrySet();

            set.forEach(p -> {
                valList.add(p.getValue());
                keysList.add(p.getKey());});

            JSONArray keys = new JSONArray();
            keysList.forEach(keys::put);

            JSONArray array = new JSONArray();
            ArrayList<JSONObject> jsonList = new ArrayList<>();
            for (int i = 0; i < valList.size(); i++)
                jsonList.add(valList.get(i).getJson());
            jsonList.forEach(array::put);
            writer.write(XML.toString(array, "values"));
            writer.write(XML.toString(keys, "keys"));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Обновить файл
     */
    public void updateFile(){
        writeCollection(collection);
    }

    /**
     * Геттер collection
     * @return collection
     */
    public Hashtable<Integer, Passport> getCollection() {
        return collection;
    }
}
