package Model;

/**
 * Created by User on 5/16/2015.
 */
public abstract class PreparationStep {
    private String description;

    public PreparationStep(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
