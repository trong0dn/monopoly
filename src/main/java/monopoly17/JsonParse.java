package monopoly17;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;


/**
 * Parses the international versions of the game
 * @author Ibrahim Almalki
 */
public class JsonParse {
    /**
     * JSON parsing method.
     * @param position the key, position of the square
     * @param version the language wanted
     * @return String, the name of the property
     */
    public static String parseJSON(int  position, String version) {
        String pos = String.valueOf(position);
        String pathJSON = "src/main/java/versions/"+version+".json";
        File fileJSON = new File(pathJSON);
        ObjectMapper mapper = new ObjectMapper();
        String outputJSON = null;
        Map<?, ?> map;

        try {
            map = mapper.readValue(fileJSON, Map.class);
            outputJSON = (String) map.get(pos);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputJSON;
    }
}

