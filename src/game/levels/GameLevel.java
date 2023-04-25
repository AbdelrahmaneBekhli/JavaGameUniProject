package game.levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.Game;
import character.Character;

import java.awt.*;

public abstract class GameLevel extends World {
    private Character character;
    private final Shape ShortplatformShape = new BoxShape(3, 0.5f);
    private final Shape MediumPlatformShape = new BoxShape(6, 0.5f);
    private final Shape LongPlatformShape = new BoxShape(25, 0.5f);

    private final Shape ExtraLongPlatformshape = new BoxShape(100, 0.5f);


    public GameLevel(Game game){
        super(60);
        character = new Character(this);
    }

    public Character getCharacter(){
        return character;
    }
    public Shape getShortPlatformShape() { return ShortplatformShape;}
    public Shape getMediumPlatformShape() { return MediumPlatformShape;}
    public Shape getLongPlatformShape() {return LongPlatformShape;}
    public Shape getExtraLongPlatformShape() {return ExtraLongPlatformshape;}

    public abstract boolean isComplete();

    public abstract float getPortal_x();

    public abstract float getPortal_y();

    public abstract void stopMusic();

    public abstract String getExtraLongTiles();
    public abstract String getLongTiles();
    public abstract String getMediumTiles();
    public abstract String getShortTiles();

    public abstract String getBackground();

    public abstract Image getEnemyPic();

    public abstract int getEnemyPicX();

    public abstract DynamicBody getWeapon();
}