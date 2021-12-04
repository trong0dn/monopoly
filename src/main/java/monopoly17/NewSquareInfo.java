package monopoly17;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class NewSquareInfo {
    @JsonProperty("position")
    private  String  position;

    @JsonProperty("name")
    private  String name;


    public NewSquareInfo(){

    }

    public NewSquareInfo(String position, String name) {
        this.position = position;
        this.name = name;
    }

    /**
     * Get position
     * @return  int
     */
    public String  getPosition() {
        return position;
    }

    /**
     * Get name
     * @return  String
     */
    public String getName() {
        return name;
    }


    public void setPosition(String position) {
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    @Override
    public String toString(){
        return position+name;
    }


 */
}
