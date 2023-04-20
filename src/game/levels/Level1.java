package game.levels;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.SoundClip;
import game.Game;
import game.coin.Coin;
import game.platform.Platform;
import game.enemies.Slime;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Level1  extends GameLevel {
    private SoundClip gameMusic;

    private final String longTiles = "data/tiles/level1/GroundPlatform.png";
    private final String mediumTiles = "data/tiles/level1/MediumPlatform.png";
    private final String shortTiles = "data/tiles/level1/ShortPlatform.png";

    private final String background = "data/tiles/level1/background.jpg";
    public Level1(Game game) {
        super(game);

        //adding the music background
        try{
            gameMusic = new SoundClip("data/audio/MusicTrack.wav");
            gameMusic.setVolume(0.2);
            gameMusic.loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }

        //ground platform
        Platform ground = new Platform(this, getLongPlatformShape(), 0, -13.6f, "ground");


        Platform p1 = new Platform(this, getShortplatformShape(), -7, -7, "short");
        Platform p2 = new Platform(this, getShortplatformShape(), 4, -7, "short");
        Platform p3 = new Platform(this, getMediumPlatformShape(), 19, -2, "medium");
        Platform p4 = new Platform(this, getMediumPlatformShape(), 0, 0, "medium");
        Platform p5 = new Platform(this, getMediumPlatformShape(), -20, -3, "medium");
        Platform p6 = new Platform(this, getShortplatformShape(), 10, 5, "short");
        Platform p7 = new Platform(this, getMediumPlatformShape(), -20, 5, "medium");

        //character
        getCharacter().setPosition(new Vec2(-14,-12));

        //enemies
        Slime slime1 = new Slime(this, 4, "left",-19, 6.3f, game);
        Slime slime2 = new Slime(this, 5, "left", 19, -0.7f, game);
        Slime slime3 = new Slime(this, 5, "right",0, 1.3f, game);
        Slime slime4 = new Slime(this, 12, "left", 10, -12.3f, game);

        //coins
        Coin coin1 = new Coin(this, "down", -20, 9);
        Coin coin2 = new Coin(this, "up", 10, 7);
        Coin coin3 = new Coin(this, "up", -20,1);
        Coin coin4 = new Coin(this, "down", 0, 5);
        Coin coin5 = new Coin(this, "down", 16, 3);
        Coin coin6 = new Coin(this, "up", 20, -10);
        Coin coin7 = new Coin(this, "down", -1.4f, -3);
    }

    @Override
    public void stopMusic(){
        gameMusic.stop();
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
    public String getLongTiles() {
        return longTiles;
    }

    @Override
    public String getMediumTiles() {
        return mediumTiles;
    }

    @Override
    public String getShortTiles() {
        return shortTiles;
    }

    @Override
    public String getBackground(){
        return background;
    }

}
