package Model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.parse.ParseQuery;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 5/16/2015.
 */

@ParseClassName("Recipe")
public class Recipe extends ParseObject {

    public static ParseQuery<Recipe> getQuery(){

        return ParseQuery.getQuery(Recipe.class);
    }

    // default constructor
    public Recipe() {
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public ArrayList<Ingredient> getIngredientsList() {
        JSONArray jIngredientsNames = getJSONArray("ingredientsNamesList");
        JSONArray jIngredientsQtds = getJSONArray("ingredientsQtdsList");
        JSONArray jIngredientsUnits = getJSONArray("ingredientsUnitsList");
        ArrayList<Ingredient> result = new ArrayList<Ingredient>();
        for(int i=0;i<jIngredientsNames.length();i++) {
            Ingredient ingredient = new Ingredient();
            try {
                ingredient.setName(jIngredientsNames.getString(i));
                ingredient.setQuantity(jIngredientsQtds.getInt(i));
                ingredient.setUnit(jIngredientsUnits.getString(i));
                result.add(ingredient);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void setIngredientsList(ArrayList<Ingredient> ingredientsList) {
        JSONArray jIngredientsNames = new JSONArray();
        JSONArray jIngredientsQtds = new JSONArray();
        JSONArray jIngredientsUnits = new JSONArray();
        for(int i = 0; i < ingredientsList.size(); i++){
            jIngredientsNames.put(ingredientsList.get(i).getName());
            jIngredientsQtds.put(ingredientsList.get(i).getQuantity());
            jIngredientsUnits.put(ingredientsList.get(i).getUnit());
        }
        put("ingredientsNamesList", jIngredientsNames);
        put("ingredientsQtdsList", jIngredientsQtds);
        put("ingredientsUnitsList", jIngredientsUnits);
    }

    public int getTime() {
        return getInt("time");
    }

    public void setTime(int time) {
        put("time", time);
    }

    public int getPortions() {
        return getInt("portions");
    }

    public void setPortions(int portions) {
        put("portions", portions);
    }

    public ArrayList<String> getEquipments() {
        return (ArrayList) getList("equipments");
    }

    public void setEquipments(ArrayList<String> equipments) {
        put("equipments", equipments);
    }

    public PrepareMode getPrepareMode() {
        return (PrepareMode) get("prepareMode");
    }

    public void setPrepareMode(PrepareMode prepareMode) {
        put("prepareMode", prepareMode);
    }

    public float getDifficulty() {
        return ((float) getDouble("difficulty"));
    }

    public void setDifficulty(float difficulty) {
        put("difficulty", difficulty);
    }
}
