package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class GameWorld  extends World{
    private final Character character;
    public GameWorld(){
        super();

        //ground platform
        Shape Groundshape = new BoxShape(25, 0.5f);
        Platform ground = new Platform(this, Groundshape, 0, -13.6f, "ground");


        //platform
        Shape ShortplatformShape = new BoxShape(3, 0.5f);
        Shape MediumPlatformShape = new BoxShape(6, 0.5f);


        Platform p1 = new Platform(this, ShortplatformShape, -7, -7, "short");
        Platform p2 = new Platform(this, ShortplatformShape, 4, -7, "short");
        Platform p3 = new Platform(this, MediumPlatformShape, 19, -2, "medium");
        Platform p4 = new Platform(this, MediumPlatformShape, 0, 0, "medium");
        Platform p5 = new Platform(this, MediumPlatformShape, -20, -3, "medium");
        Platform p6 = new Platform(this, ShortplatformShape, 10, 5, "short");
        Platform p7 = new Platform(this, MediumPlatformShape, -20, 5, "medium");


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
        Coin coin1 = new Coin(this, "down", character);
        coin1.setPosition(new Vec2(-20, 9));
        coin1.setRange();

        Coin coin2 = new Coin(this, "up", character);
        coin2.setPosition(new Vec2(10, 7));
        coin2.setRange();

        Coin coin3 = new Coin(this, "up", character);
        coin3.setPosition(new Vec2(-20, 1));
        coin3.setRange();

        Coin coin4 = new Coin(this, "down", character);
        coin4.setPosition(new Vec2(0, 5));
        coin4.setRange();

        Coin coin5 = new Coin(this, "down", character);
        coin5.setPosition(new Vec2(16, 3));
        coin5.setRange();

        Coin coin6 = new Coin(this, "up", character);
        coin6.setPosition(new Vec2(20, -10));
        coin6.setRange();

        Coin coin7 = new Coin(this, "down", character);
        coin7.setPosition(new Vec2(-1.4f, -3));
        coin7.setRange();
    }

    public Character getCharacter(){
        return character;
    }

}
