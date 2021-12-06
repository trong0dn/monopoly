package monopoly17;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Parses the international versions of the game.
 * @author Ibrahim Almalki
 * Modified by Trong Nguyen
 */
public class JsonParse {
    /**
     * JSON parsing method.
     * @param position      key, position of the square
     * @param version       international version of the game
     * @return String       name of the property
     */
    public static String parseJSON(int  position, String version) {
        String pos = String.valueOf(position);
        ObjectMapper mapper = new ObjectMapper();
        Map<?, ?> map;
        String filepathJSON = "/versions/" + version + ".json";
        String outputJSON = null;

        try {
            InputStream inputStream = JsonParse.class.getResourceAsStream(filepathJSON);
            assert inputStream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); {
                String contents = reader.lines().collect(Collectors.joining(System.lineSeparator()));
                map = mapper.readValue(contents, Map.class);
                outputJSON = (String) map.get(pos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputJSON;
    }
}

