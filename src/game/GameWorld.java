package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class GameWorld  extends World{
    private Character character;

    public GameWorld(){
        super();

        //ground platform
        Shape shape = new BoxShape(25, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(-0f, -13.5f));

        //platform
        Shape ShortplatformShape = new BoxShape(3, 0.5f);
        Shape MediumPlatformShape = new BoxShape(6, 0.5f);

        StaticBody platform1 = new StaticBody(this, ShortplatformShape);
        platform1.setPosition(new Vec2(-7, -7));

        StaticBody platform2 = new StaticBody(this, ShortplatformShape);
        platform2.setPosition(new Vec2(4, -7f));

        StaticBody platform3 = new StaticBody(this, MediumPlatformShape);
        platform3.setPosition(new Vec2(19, -2f));

        StaticBody platform4 = new StaticBody(this, MediumPlatformShape);
        platform4.setPosition(new Vec2(0, 0f));

        StaticBody platform5 = new StaticBody(this, MediumPlatformShape);
        platform5.setPosition(new Vec2(-20, -3f));

        StaticBody platform6 = new StaticBody(this, ShortplatformShape);
        platform6.setPosition(new Vec2(10, 5f));

        StaticBody platform7 = new StaticBody(this, MediumPlatformShape);
        platform7.setPosition(new Vec2(-20, 5f));

        //character

        character = new Character(this);
        character.setPosition(new Vec2(-14,-10 ));
        character.setCredits(15);

        GenericCollisionListener gcl = new GenericCollisionListener();
        character.addCollisionListener(gcl);
    }

    public Character getCharacter(){
        return character;
    }
}
