package monopoly17;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Paths;
import java.util.Map;


/**
 * parses the international versions of the game
 * @author Ibrahim Almalki
 */
public class JsonParse {

    /**
     * @param position the key, position of the square
     * @param version the language wanted
     * @return String, the name of the property
     */
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



}

