package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 5/16/2015.
 */
public class Recipe {


    private String name;
    private ArrayList<Ingredient> ingredientsList;
    private int time;
    private int portions;
    private ArrayList<String> equipments;
    private PrepareMode prepareMode;


    public Recipe(){
        prepareMode = new PrepareMode();
        equipments = new ArrayList<String>();
        ingredientsList = new ArrayList<Ingredient>();
        time = 0;
        portions = 0;
        name = "";
    }

    public String getName() {
        return name;
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

}
