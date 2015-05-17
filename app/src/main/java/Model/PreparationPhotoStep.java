package Model;

import com.parse.ParseFile;

import java.util.ArrayList;

/**
 * Created by lauralassance on 16/5/15.
 */
public class PreparationPhotoStep extends PreparationStep {

    private ParseFile photo;
    private ArrayList<Ingredient> ingredientsList;

    public PreparationPhotoStep() {
        super("");
        this.photo = null;
    }

    public PreparationPhotoStep(String description) {
        super(description);
    }
}
