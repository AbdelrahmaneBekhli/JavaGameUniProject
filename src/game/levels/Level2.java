package game.levels;

import city.cs.engine.*;
import game.Game;
import game.platform.Platform;
import game.platform.Wall;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class Level2 extends GameLevel{
    private SoundClip gameMusic;

    private final String longTiles = "data/tiles/level2/GroundPlatform.png";
    private final String mediumTiles = "data/tiles/level2/MediumPlatform.png";
    private final String shortTiles = "data/tiles/level2/ShortPlatform.png";

    private final String background = "data/tiles/level2/background.jpg";
    public Level2(Game game){
        //base class will create the student, professor
        super(game);
        getCharacter().setPosition(new Vec2(-10, 0));

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

        //platform
        Shape ShortplatformShape = new BoxShape(3, 0.5f);
        Shape MediumPlatformShape = new BoxShape(6, 0.5f);


        Platform p1 = new Platform(this, ShortplatformShape, -20, -7, "short");
        Platform p2 = new Platform(this, ShortplatformShape, -10, 0, "short");
        Platform p3 = new Platform(this, ShortplatformShape, -17, 5, "short");
        Platform p4 = new Platform(this, ShortplatformShape, -5, -5, "short");
        Platform p5 = new Platform(this, ShortplatformShape, 0, 7, "short");
        Platform p6 = new Platform(this, ShortplatformShape, 3, 0, "short");
        Platform p7 = new Platform(this, ShortplatformShape, -22, 12, "short");
        Platform p8 = new Platform(this, ShortplatformShape, 10, 10, "short");
        Platform p9 = new Platform(this, ShortplatformShape, 16, 6, "short");
        Platform p10 = new Platform(this, ShortplatformShape, 13, -4, "short");
        Platform p11 = new Platform(this, ShortplatformShape, -10, 15, "short");
        Platform p12 = new Platform(this, ShortplatformShape, 6, 17, "short");
        Platform p13 = new Platform(this, ShortplatformShape, -4, 22, "short");
        Platform p14 = new Platform(this, ShortplatformShape, 15, 23, "short");
        Platform p15 = new Platform(this, ShortplatformShape, 7, 28, "short");
        Platform p16 = new Platform(this, ShortplatformShape, -16, 24, "short");
        Platform p17 = new Platform(this, ShortplatformShape, -10, 30, "short");
        Platform p18 = new Platform(this, ShortplatformShape, 0, 35, "short");
        Platform p19 = new Platform(this, ShortplatformShape, -10, 40, "short");
        Platform p20 = new Platform(this, ShortplatformShape, 0, 45, "short");
        Platform p21 = new Platform(this, ShortplatformShape, -10, 50, "short");
        Platform p22 = new Platform(this, ShortplatformShape, 0, 55, "short");
        Platform p23 = new Platform(this, ShortplatformShape, -10, 60, "short");
        Platform p24 = new Platform(this, ShortplatformShape, 0, 65, "short");
        Platform p25 = new Platform(this, ShortplatformShape, -10, 70, "short");
        Platform p26 = new Platform(this, ShortplatformShape, 0, 75, "short");
        Platform p27 = new Platform(this, ShortplatformShape, -10, 80, "short");
        Platform p28 = new Platform(this, ShortplatformShape, 0, 85, "short");
        Platform p29 = new Platform(this, ShortplatformShape, -10, 90, "short");
        Platform p30 = new Platform(this, ShortplatformShape, 0, 95, "short");
        Platform p31 = new Platform(this, ShortplatformShape, -10, 100, "short");
        Platform p32 = new Platform(this, ShortplatformShape, 0, 105, "short");
        Platform p33 = new Platform(this, Groundshape, 35, 40, "ground");
        Platform p34 = new Platform(this, Groundshape, 35, 50, "ground");
        Platform p35 = new Platform(this, Groundshape, 35, 60, "ground");
        Platform p36 = new Platform(this, Groundshape, 35, 70, "ground");
        Platform p37 = new Platform(this, Groundshape, 35, 80, "ground");
        Platform p38 = new Platform(this, Groundshape, 35, 90, "ground");
        Platform p39 = new Platform(this, Groundshape, 35, 100, "ground");
        Platform p40 = new Platform(this, Groundshape, 35, 110, "ground");
        Shape wallShape = new BoxShape(0.5f,40);
        Wall wall = new Wall(this, wallShape, 60.5f, 79.53f);


    }
    @Override
    public boolean isComplete() {
        return getCharacter().getKills() == 5;
    }

    @Override
    public float getPortal_x() {
        return 0;
    }

    @Override
    public float getPortal_y() {
        return 0;
    }

    @Override
    public void stopMusic() {
        gameMusic.stop();
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
    public String getBackground(){ return background; }

}
