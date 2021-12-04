package monopoly17;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;



public class JsonParse {

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
        JsonParse j = new JsonParse();
        System.out.print(j.parseJSON(30, "UK"));

    }

}

