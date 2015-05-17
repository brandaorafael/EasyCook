package Model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 5/16/2015.
 */
@ParseClassName("Recipe")
public class Recipe extends ParseObject {


    private String name;
    private ArrayList<Ingredient> ingredientsList;
    private int time;
    private int portions;
    private float difficulty; // from 0 to 5 stars
    private ArrayList<String> equipments;
    private PrepareMode prepareMode;

    // full constructor
    public Recipe(String name, ArrayList<Ingredient> ingredientsList, int time, int portions, float difficulty, ArrayList<String> equipments, PrepareMode prepareMode) {
        this.name = name;
        this.ingredientsList = ingredientsList;
        this.time = time;
        this.portions = portions;
        this.difficulty = difficulty;
        this.equipments = equipments;
        this.prepareMode = prepareMode;
    }

    public Recipe(String name, ArrayList<Ingredient> ingredientsList, int time, PrepareMode prepareMode, int portions) {
        this.name = name;
        this.ingredientsList = ingredientsList;
        this.time = time;
        this.prepareMode = prepareMode;
        this.portions = portions;
        this.prepareMode = null;
        this.difficulty = 0;
    }

    // default constructor
    public Recipe(){
        this.prepareMode = null;
        this.equipments = null;
        this.ingredientsList = null;
        this.time = 0;
        this.portions = 0;
        this.name = "";
        this.difficulty = 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(ArrayList<Ingredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public List<String> getEquipments() {
        return equipments;
    }

    public void setEquipments(ArrayList<String> equipments) {
        this.equipments = equipments;
    }

    public PrepareMode getPrepareMode() {
        return prepareMode;
    }

    public void setPrepareMode(PrepareMode prepareMode) {
        this.prepareMode = prepareMode;
    }

    public float getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(float difficulty) {
        this.difficulty = difficulty;
    }

    public JSONObject getJSONObject() {
        JSONObject result = new JSONObject();
        try {
            result.put("name",this.name);
            result.put("time",this.time);
            result.put("portions",this.portions);
            result.put("difficulty",this.difficulty);
            result.put("ingredients",new JSONArray(this.ingredientsList));
            result.put("equipments",new JSONArray(this.equipments));
            // verify
            result.put("preparationMode", prepareMode);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}
