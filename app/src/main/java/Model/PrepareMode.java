package Model;

import com.parse.ParseObject;
import com.parse.ParseClassName;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 5/16/2015.
 */
@ParseClassName("PrepareMode")
public class PrepareMode extends ParseObject {
    private ArrayList<PreparationStep> steps;
    private int currentStep;

    public ArrayList<PreparationStep> getSteps() {
        return (ArrayList) getList("steps");
    }

    public void setSteps(ArrayList<PreparationStep> steps) {
        put("steps", steps);
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

    public PrepareMode() {
        this(null);
    }

    public PrepareMode(ArrayList<PreparationStep> steps) {
        this.steps = steps;
        this.currentStep = 0;
    }

    public PreparationStep nextStep() {
        if (currentStep < steps.size()-1)
            return steps.get(++currentStep);
        return null;
    }

    public PreparationStep previousStep() {
        if (!steps.isEmpty() && currentStep > 0)
            return steps.get(--currentStep);
        return null;
    }

    public int howManySteps() {
        return steps.size();
    }



}
