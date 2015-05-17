package Model;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseClassName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 5/16/2015.
 */
@ParseClassName("PreparationStep")
public class PreparationStep extends ParseObject {

    private String name;
    private String description;
    private int time;
    private ParseFile photo;

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public int getTime() {
        return getInt("time");
    }

    public void setTime(int time) {
        put("time", time);
    }

    public ParseFile getPhoto() {
        return getParseFile("photo");
    }

    public void setPhoto(ParseFile photo) {
        put("photo", photo);
    }


    public PreparationStep() {
        name = "";
        description = "";
        time = 0;
        photo = null;
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }

}
