package game.levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.Game;
import character.Character;

import java.awt.*;
/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public abstract class GameLevel extends World {
    private final Character character;
    private final Shape ShortplatformShape = new BoxShape(3, 0.5f);
    private final Shape MediumPlatformShape = new BoxShape(6, 0.5f);
    private final Shape LongPlatformShape = new BoxShape(25, 0.5f);
    private final Shape ExtraLongPlatformshape = new BoxShape(100, 0.5f);


    public GameLevel(){
        super(60);
        character = new Character(this);
    }
    /**
     * @return the character existing in the world.
     */
    public Character getCharacter(){
        return character;
    }
    /**
     * @return shape of short platform.
     */
    public Shape getShortPlatformShape() { return ShortplatformShape;}
    /**
     * @return shape of medium platform.
     */
    public Shape getMediumPlatformShape() { return MediumPlatformShape;}
    /**
     * @return shape of long platform.
     */
    public Shape getLongPlatformShape() {return LongPlatformShape;}
    /**
     * @return shape of extra long platform.
     */
    public Shape getExtraLongPlatformShape() {return ExtraLongPlatformshape;}

    /**
     * @return is the level completed.
     */
    public abstract boolean isComplete();
    /**
     * @return X position of the portal.
     */
    public abstract float getPortal_x();
    /**
     * @return Y position of the portal.
     */
    public abstract float getPortal_y();
    /**
     * stops the level music.
     */
    public abstract void stopMusic();
    /**
     * @return level music.
     */
    public abstract SoundClip getMusic();
    /**
     * @return game class.
     */
    public abstract Game getGame();
    /**
     * @return filename of extra long tiles.
     */
    public abstract String getExtraLongTiles()
    /**
     * @return filename of long tiles.
     */;
    public abstract String getLongTiles();
    /**
     * @return filename of medium tiles.
     */
    public abstract String getMediumTiles();
    /**
     * @return filename of short tiles.
     */
    public abstract String getShortTiles();
    /**
     * @return filename of level background.
     */
    public abstract String getBackground();
    /**
     * @return enemy picture.
     */
    public abstract Image getEnemyPic();
    /**
     * @return enemy picture X coordinate.
     */
    public abstract int getEnemyPicX();
    /**
     * @return level weapon type.
     */
    public abstract DynamicBody getWeapon();
}