package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class GameWorld  extends World{
    private Character character;
    private Slime slime;

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
        character.setPosition(new Vec2(-14,-12));
        character.setGravityScale(3);
        character.setCredits(15);

        GenericCollisionListener gcl = new GenericCollisionListener();
        character.addCollisionListener(gcl);

        //Slime
        Slime slime1 = new Slime(this, 4, "left");
        slime1.setPosition(new Vec2(-19, 5.5f));
        slime1.setRange();

        Slime slime2 = new Slime(this, 5, "left");
        slime2.setPosition(new Vec2(19, -1));
        slime2.setRange();

        Slime slime3 = new Slime(this, 5, "right");
        slime3.setPosition(new Vec2(0, 1f));
        slime3.setRange();

        Slime slime4 = new Slime(this, 12, "left");
        slime4.setPosition(new Vec2(10, -12f));
        slime4.setRange();



    }

    public Character getCharacter(){
        return character;
    }

    public Slime getSlime(){return slime;}
}
