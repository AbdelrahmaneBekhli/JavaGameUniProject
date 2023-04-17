package game.character;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;
import game.GameView;

public class Tracker implements StepListener {
    private final GameView view;
    private final Character character;
    public Tracker(GameView view, Character character) {
        this.view = view;
        this.character = character;
    }
    public void preStep(StepEvent e) {}
    public void postStep(StepEvent e) {
        view.setCentre(new Vec2(character.getPosition()));
    }
}