package monopoly17;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Json {


    public static String parseJSON(int  position, String version) {
        String pos = String.valueOf(position);
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert JSON file to map
            Map<?, ?> map = mapper.readValue(Paths.get("src/main/java/versions/"+version+".json").toFile(), Map.class);

            return (String) map.get(pos);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }



    public static void main(String[] args) throws IOException {
        Json j = new Json();
        System.out.print(j.parseJSON(2, "UK"));

    }

}

