package monopoly17;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class json {



    public void whenUsingJsonNode_thenOk() throws IOException {
        JsonNode node = new ObjectMapper().readTree(new File("src/main/java/versions/Lang.json"));

        newSquareInfo square = new newSquareInfo();

        System.out.println(node.get("1").textValue());
    }

    public static void main(String[] args) throws IOException {
/*        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(new JavaTimeModule());
        objectReader = objectMapper.reader();
        Map<String, Object> parsedJson = (Map<String, Object>)objectReader.forType(Map.class).readValue(jsonString)
        newSquareInfo square = mapper.readValue(new File("src/main/java/versions/Lang.json"), newSquareInfo.class);
        System.out.println("squares" + square);

 */

        json j = new json();
        j.whenUsingJsonNode_thenOk();


    }
}
