package platform;

import city.cs.engine.Shape;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import game.levels.GameLevel;
import org.jbox2d.common.Vec2;

public class MovingPlatform extends Platform implements StepListener {
    private float minX;
    private float maxX;
    float speed = 0.3f;
    public MovingPlatform(GameLevel level, Shape shape, float x, float y, String type) {
        super(level, shape, x, y, type);
        level.addStepListener(this);
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        //if platform is out of the screen
        if(this.getPosition().y > 20){
            this.setPosition(new Vec2(this.getPosition().x, -20));
        }
        //otherwise move up
        else {
            this.setPosition(new Vec2(this.getPosition().x, this.getPosition().y + 0.1f));
        }

    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
