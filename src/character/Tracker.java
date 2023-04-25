package character;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import game.levels.GameLevel;
import game.levels.Level2;
import game.levels.Level3;
import org.jbox2d.common.Vec2;
import game.GameView;

public class Tracker implements StepListener {
    private final GameView view;
    private final Character character;
    private float centre;

    private final float xForwardRange = 10;
    private final float xBackwardRange = 17;
    private final GameLevel level;
    public Tracker(GameView view, GameLevel level) {
        this.view = view;
        this.level = level;
        this.character = level.getCharacter();
        this.centre = character.getPosition().x + xForwardRange;
    }
    @Override
    public void preStep(StepEvent e) {}

    @Override
    public void postStep(StepEvent e) {
        if(level instanceof Level2) {
            view.setCentre(new Vec2(character.getPosition()));
        }
        if(level instanceof Level3){
            //detects if the character is at the left of the camera border
            if(character.getPosition().x <= centre - xBackwardRange){
                centre = character.getPosition().x + xBackwardRange;

                //detects if the character is at the right of the camera border
            } else if(character.getPosition().x >= centre + xForwardRange){
                centre = character.getPosition().x - xForwardRange;
            }
            view.setCentre(new Vec2(centre, 0));
        }
    }
}