package game.levels;

import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.SoundClip;
import game.Game;
import collectables.coin.Coin;
import platform.Platform;
import enemies.Slime;
import weapon.snowball.Snowball;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class Level1  extends GameLevel {
    private SoundClip gameMusic;
    private final Game game;
    public Level1(Game game) {
        super();
        this.game = game;

        //adding the music background
        try{
            gameMusic = new SoundClip("data/audio/level1MusicTrack.wav");
            gameMusic.setVolume(0.2);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }

        //ground platform
        Platform ground = new Platform(this, getLongPlatformShape(), 0, -13.6f, "long");


        Platform p1 = new Platform(this, getShortPlatformShape(), -7, -7, "short");
        Platform p2 = new Platform(this, getShortPlatformShape(), 4, -7, "short");
        Platform p3 = new Platform(this, getShortPlatformShape(), 10, 5, "short");

        Platform p4 = new Platform(this, getMediumPlatformShape(), -20, 3, "medium");
        Platform p5 = new Platform(this, getMediumPlatformShape(), 19, -2, "medium");
        Platform p6 = new Platform(this, getMediumPlatformShape(), -1, 0, "medium");
        Platform p7 = new Platform(this, getShortPlatformShape(), -23, -5, "short");

        //character
        getCharacter().setSpeed(10);
        getCharacter().setPosition(new Vec2(-14,-12));

        //enemies
        Slime slime1 = new Slime(this, 4, "left",-19, 4.3f);
        Slime slime2 = new Slime(this, 5, "left", 19, -0.7f);
        Slime slime3 = new Slime(this, 5, "right",-1, 1.3f);
        Slime slime4 = new Slime(this, 12, "left", 10, -12.3f);

        //coins
        Coin coin1 = new Coin(this, "down", -20, 7);
        Coin coin2 = new Coin(this, "up", 10, 7);
        Coin coin3 = new Coin(this, "up", -20,1);
        Coin coin4 = new Coin(this, "down", -1, 5);
        Coin coin5 = new Coin(this, "down", 16, 3);
        Coin coin6 = new Coin(this, "up", 20, -10);
        Coin coin7 = new Coin(this, "down", -1.4f, -3);
    }

    @Override
    public void stopMusic(){
        gameMusic.stop();
    }

    @Override
    public SoundClip getMusic() {
        return gameMusic;
    }

    @Override
    public Game getGame() {
        return this.game;
    }

    @Override
    public boolean isComplete() {
        return getCharacter().getKills() == 4;
    }

    public float getPortal_x() {
        return 23;
    }

    public float getPortal_y() {
        return 1;
    }

    @Override
    public String getExtraLongTiles() {
        return null;
    }
    @Override
    public String getLongTiles() {
        return "data/tiles/level1/LongPlatform.png";
    }

    @Override
    public String getMediumTiles() {
        return "data/tiles/level1/MediumPlatform.png";
    }

    @Override
    public String getShortTiles() {
        return "data/tiles/level1/ShortPlatform.png";
    }

    @Override
    public String getBackground(){
        return "data/tiles/level1/background.jpg";
    }

    @Override
    public Image getEnemyPic(){
        return new ImageIcon("data/enemy/slime/slime.png").getImage();
    }

    public int getEnemyPicX(){
        return 11;
    }

    @Override
    public DynamicBody getWeapon() {
        Shape weaponShape = new CircleShape(0.3f);
        return new Snowball(this, weaponShape);
    }

}
