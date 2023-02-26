package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
public class GameWorld  extends World{
    private Character character;

    public GameWorld(){
        super();

        //ground platform
        Shape shape = new BoxShape(10, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -11.5f));

        //platform
        Shape platformShap = new BoxShape(3, 0.5f);
        StaticBody platform1 = new StaticBody(this, platformShap);
        platform1.setAngle(40);

        StaticBody platform2 = new StaticBody(this, platformShap);
        platform2.setPosition(new Vec2(- 4, -5f));

        //character

        character = new Character(this);
        int height = 0;
        character.setPosition(new Vec2(4, height));
        character.setCredits(15);
    }

    public Character getCharacter(){
        return character;
    }
}
