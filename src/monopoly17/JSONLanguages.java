package monopoly17;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JSONLanguages {

    public void languageDict(){
        JSONObject uk = new JSONObject();
        uk.put(0, "GO");
        uk.put(1, "Old Kent");
        uk.put(2, "Community Chest");
        uk.put(3, "White Chapel");
        uk.put(4, "incomeTax");
        uk.put(5, "Kings Cross");
        uk.put(6, "Angel Islington");
        uk.put(7, "Chance");
        uk.put(8, "Euston");
        uk.put(9, "Pentonville");
        uk.put(10, "Just Visiting");
        uk.put(11, "Pentonville");
        uk.put(12, "Pentonville");

    }
    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        obj.put("name", "sonoo");
        obj.put("age", 27);
        obj.put("salary", 600000);
        System.out.print(obj);
    }
}
