package game.levels;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.SoundClip;
import game.Game;
import game.character.Character;
import game.coin.Coin;
import game.platform.Platform;
import game.slime.Slime;
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
        getCharacter().setPosition(new Vec2(-14,-12));

        //Slime
        Slime slime1 = new Slime(this, 4, "left", getCharacter(), this, game);
        slime1.setPosition(new Vec2(-19, 6.3f));
        slime1.setRange();

        Slime slime2 = new Slime(this, 5, "left", getCharacter(), this, game);
        slime2.setPosition(new Vec2(19, -0.7f));
        slime2.setRange();

        Slime slime3 = new Slime(this, 5, "right", getCharacter(), this, game);
        slime3.setPosition(new Vec2(0, 1.3f));
        slime3.setRange();

        Slime slime4 = new Slime(this, 12, "left", getCharacter(), this, game);
        slime4.setPosition(new Vec2(10, -12.3f));
        slime4.setRange();

        //coins
        Coin coin1 = new Coin(this, "down", getCharacter());
        coin1.setPosition(new Vec2(-20, 9));
        coin1.setRange();

        Coin coin2 = new Coin(this, "up", getCharacter());
        coin2.setPosition(new Vec2(10, 7));
        coin2.setRange();

        Coin coin3 = new Coin(this, "up", getCharacter());
        coin3.setPosition(new Vec2(-20, 1));
        coin3.setRange();

        Coin coin4 = new Coin(this, "down", getCharacter());
        coin4.setPosition(new Vec2(0, 5));
        coin4.setRange();

        Coin coin5 = new Coin(this, "down", getCharacter());
        coin5.setPosition(new Vec2(16, 3));
        coin5.setRange();

        Coin coin6 = new Coin(this, "up", getCharacter());
        coin6.setPosition(new Vec2(20, -10));
        coin6.setRange();

        Coin coin7 = new Coin(this, "down", getCharacter());
        coin7.setPosition(new Vec2(-1.4f, -3));
        coin7.setRange();
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
