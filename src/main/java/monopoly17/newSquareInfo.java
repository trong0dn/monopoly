package monopoly17;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class newSquareInfo {
    private  String  position;
    private  String nameUS;
    private  String nameUK;


/*
    newSquareInfo(String position, String name) {
        this.position = position;
        this.name = name;
    }
    */


    /**
     * Get position
     * @return  int
     */
    public String  getPosition() {
        return this.position;
    }

    /**
     * Get name
     * @return  String
     */
    public String getUSName() {
        return this.nameUS;
    }

    /**
     * Get name
     * @return  String
     */
    public String getUKName() {
        return this.nameUK;
    }


    @SuppressWarnings("unchecked")
    @JsonProperty("position")
    private void unpackNested(Map<String,Object> position) {
        this.nameUK = (String)position.get("UK");
        this.nameUS = (String)position.get("US");

    }


    @Override
    public String toString(){
        return position+nameUS+nameUS;
    }

}
