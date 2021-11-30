package monopoly17;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONLanguages {

    public static void main(String[] args) {
        try {
            //for testing purposes
            Object obj = new JSONParser().parse(new FileReader("src/versions/Default.json"));

            JSONObject jo = (JSONObject) obj;

            Map address = ((Map) jo.get("square1"));

            // iterating address Map
            for (Map.Entry pair : (Iterable<Map.Entry>) address.entrySet()) {
                System.out.println(pair.getKey() + " : " + pair.getValue());
            }

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}