package monopoly17;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONLanguages {
    private static Map square1values;

    public JSONLanguages(String file) {
        try {
            Object obj = new JSONParser().parse(new FileReader("src/versions/" + file));

            JSONObject jo = (JSONObject) obj;

            square1values = ((Map) jo.get("square1"));

            System.out.println(square1values.get("name"));

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}