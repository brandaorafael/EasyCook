package Model;

/**
 * Created by lauralassance on 16/5/15.
 */
public class PreparationTimerStep extends PreparationStep {

    private long time;

    public PreparationTimerStep(String description, long time) {
        super(description);
        this.time = time;
    }
}
