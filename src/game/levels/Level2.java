package game.levels;

import game.Game;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import game.platform.Platform;
import org.jbox2d.common.Vec2;

public class Level2 extends GameLevel{
    public Level2(Game game){
        //base class will create the student, professor
        super(game);
        getCharacter().setPosition(new Vec2(8, -10));

        //ground platform
        Shape Groundshape = new BoxShape(25, 0.5f);
        Platform ground = new Platform(this, Groundshape, 0, -13.6f, "ground");
    }
    @Override
    public boolean isComplete() {
        return getCharacter().getKills() == 5;
    }

    @Override
    public float getPortal_x() {
        return 0;
    }

    @Override
    public float getPortal_y() {
        return 0;
    }
}
