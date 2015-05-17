package Model;

import java.util.ArrayList;

/**
 * Created by User on 5/16/2015.
 */
public class PrepareMode {
    private ArrayList<PreparationStep> steps;
    private int currentStep;

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
