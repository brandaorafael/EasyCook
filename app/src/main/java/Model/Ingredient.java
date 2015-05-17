package Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by User on 5/16/2015.
 */
public class Ingredient {
    private String name;
    private int quantity;



    private String unit;

    public Ingredient(String name, int qtd, String unit) {
        this.name = name;
        this.quantity = qtd;
        this.unit = unit;
    }

    // default constructor
    public Ingredient() {
        this("",0, "");
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("quantity", quantity);
            jsonObject.put("unit", unit);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public void fromJSONObject(JSONObject jsonObject){
        try {
            name = jsonObject.getString("name");
            quantity = jsonObject.getInt("quantity");
            unit = jsonObject.getString("unit");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
