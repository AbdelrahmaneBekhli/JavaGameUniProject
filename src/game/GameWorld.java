package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class GameWorld  extends World{
    private final Character character;
    private Slime slime;

    public GameWorld(){
        super();
        BodyImage GroundImage = new BodyImage("data/GroundPlatform.png", 1f);
        BodyImage ShortPlatformImage = new BodyImage("data/ShortPlatform.png", 1f);
        BodyImage MediumPlatformImage = new BodyImage("data/MediumPlatform.png", 1f);

        //ground platform
        Shape shape = new BoxShape(25, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(-0f, -13.6f));
        ground.setClipped(true);
        ground.addImage(GroundImage);

        //platform
        Shape ShortplatformShape = new BoxShape(3, 0.5f);
        Shape MediumPlatformShape = new BoxShape(6, 0.5f);

        StaticBody platform1 = new StaticBody(this, ShortplatformShape);
        platform1.setPosition(new Vec2(-7, -7));
        platform1.setClipped(true);
        platform1.addImage(ShortPlatformImage);

        StaticBody platform2 = new StaticBody(this, ShortplatformShape);
        platform2.setPosition(new Vec2(4, -7f));
        platform2.setClipped(true);
        platform2.addImage(ShortPlatformImage);

        StaticBody platform3 = new StaticBody(this, MediumPlatformShape);
        platform3.setPosition(new Vec2(19, -2f));
        platform3.setClipped(true);
        platform3.addImage(MediumPlatformImage);

        StaticBody platform4 = new StaticBody(this, MediumPlatformShape);
        platform4.setPosition(new Vec2(0, 0f));
        platform4.setClipped(true);
        platform4.addImage(MediumPlatformImage);

        StaticBody platform5 = new StaticBody(this, MediumPlatformShape);
        platform5.setPosition(new Vec2(-20, -3f));
        platform5.setClipped(true);
        platform5.addImage(MediumPlatformImage);

        StaticBody platform6 = new StaticBody(this, ShortplatformShape);
        platform6.setClipped(true);
        platform6.addImage(ShortPlatformImage);
        platform6.setPosition(new Vec2(10, 5f));

        StaticBody platform7 = new StaticBody(this, MediumPlatformShape);
        platform7.setPosition(new Vec2(-20, 5f));
        platform7.setClipped(true);
        platform7.addImage(MediumPlatformImage);

        //character
        character = new Character(this);
        character.setPosition(new Vec2(-14,-12));
        character.setGravityScale(3);

        GenericCollisionListener gcl = new GenericCollisionListener(character);
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

        //coins
        Coin coin1 = new Coin(this);
        coin1.setPosition(new Vec2(-20, 9));

        Coin coin2 = new Coin(this);
        coin2.setPosition(new Vec2(10, 7));

        Coin coin3 = new Coin(this);
        coin3.setPosition(new Vec2(-20, 1));

        Coin coin4 = new Coin(this);
        coin4.setPosition(new Vec2(0, 5));

        Coin coin5 = new Coin(this);
        coin5.setPosition(new Vec2(16, 3));

        Coin coin6 = new Coin(this);
        coin6.setPosition(new Vec2(20, -10));

        Coin coin7 = new Coin(this);
        coin7.setPosition(new Vec2(-1.4f, -3));
    }

    public Character getCharacter(){
        return character;
    }

    public Slime getSlime(){return slime;}
}
