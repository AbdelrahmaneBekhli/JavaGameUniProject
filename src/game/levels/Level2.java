package game.levels;

import city.cs.engine.*;
import game.Game;
import game.coin.Coin;
import game.enemies.Wolf;
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
    private final int platform_number = (int) Math.floor(Math.random() *(8 - 1 + 1) + 1);
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


        //platforms
        Platform p1 = new Platform(this, getShortplatformShape(), -10, 0, "short");
        Platform p2 = new Platform(this, getShortplatformShape(), -17, 5, "short");
        Platform p3 = new Platform(this, getShortplatformShape(), -5, -5, "short");
        Platform p4 = new Platform(this, getShortplatformShape(), 0, 7, "short");
        Platform p5 = new Platform(this, getShortplatformShape(), 3, 0, "short");
        Platform p6 = new Platform(this, getShortplatformShape(), 10, 10, "short");
        Platform p7 = new Platform(this, getShortplatformShape(), 13, -4, "short");
        Platform p8 = new Platform(this, getShortplatformShape(), -10, 15, "short");
        Platform p9 = new Platform(this, getShortplatformShape(), -4, 22, "short");
        Platform p10 = new Platform(this, getShortplatformShape(), 15, 23, "short");
        Platform p11 = new Platform(this, getShortplatformShape(), 7, 28, "short");
        Platform p12 = new Platform(this, getShortplatformShape(), -16, 24, "short");
        Platform p13 = new Platform(this, getShortplatformShape(), -10, 30, "short");
        Platform p14 = new Platform(this, getShortplatformShape(), 0, 35, "short");
        Platform p15 = new Platform(this, getShortplatformShape(), -10, 40, "short");
        Platform p16 = new Platform(this, getShortplatformShape(), 0, 45, "short");
        Platform p17 = new Platform(this, getShortplatformShape(), -10, 50, "short");
        Platform p18 = new Platform(this, getShortplatformShape(), 0, 55, "short");
        Platform p19 = new Platform(this, getShortplatformShape(), -10, 60, "short");
        Platform p20 = new Platform(this, getShortplatformShape(), 0, 65, "short");
        Platform p21 = new Platform(this, getShortplatformShape(), -10, 70, "short");
        Platform p22 = new Platform(this, getShortplatformShape(), 0, 75, "short");
        Platform p23 = new Platform(this, getShortplatformShape(), -10, 80, "short");
        Platform p24 = new Platform(this, getShortplatformShape(), 0, 85, "short");
        Platform p25 = new Platform(this, getShortplatformShape(), -10, 90, "short");
        Platform p26 = new Platform(this, getShortplatformShape(), 0, 95, "short");
        Platform p27 = new Platform(this, getShortplatformShape(), -10, 100, "short");
        Platform p28 = new Platform(this, getShortplatformShape(), 0, 105, "short");
        Platform p29 = new Platform(this, getMediumPlatformShape(), -20, -7, "medium");
        Platform p30 = new Platform(this, getMediumPlatformShape(), -26, 12, "medium");
        Platform p31 = new Platform(this, getMediumPlatformShape(), 16, 6, "medium");
        Platform p32 = new Platform(this, getMediumPlatformShape(), 6, 17, "medium");
        Platform p33 = new Platform(this, getLongPlatformShape(), 35, 40, "long");
        Platform p34 = new Platform(this, getLongPlatformShape(), 35, 50, "long");
        Platform p35 = new Platform(this, getLongPlatformShape(), 35, 60, "long");
        Platform p36 = new Platform(this, getLongPlatformShape(), 35, 70, "long");
        Platform p37 = new Platform(this, getLongPlatformShape(), 35, 80, "long");
        Platform p38 = new Platform(this, getLongPlatformShape(), 35, 90, "long");
        Platform p39 = new Platform(this, getLongPlatformShape(), 35, 100, "long");
        Platform p40 = new Platform(this, getLongPlatformShape(), 35, 110, "long");

        //wall
        Shape wallShape = new BoxShape(0.5f,40);
        Wall wall = new Wall(this, wallShape, 60.5f, 79.53f);

        //coins
        Coin coin1 = new Coin(this, "up", -17, 9);
        Coin coin2 = new Coin(this, "down", 3, 4);
        Coin coin3 = new Coin(this, "down",-20, -3);
        Coin coin4 = new Coin(this, "up", 13, 0);
        Coin coin5 = new Coin(this, "down",-10, 18);
        Coin coin6 = new Coin(this, "down", 15, 26);
        Coin coin7 = new Coin(this, "down", 6, 20);
        Coin coin8 = new Coin(this, "down", -10, 33);

        //enemies
        Wolf wolf1 = new Wolf(this,"black" ,5, "left", 16, 7.35f, game);
        Wolf wolf2 = new Wolf(this,"brown" ,5, "right", -20, -5.65f, game);
        Wolf wolf3 = new Wolf(this,"grey" ,4.5f, "left", -26, 13.35f, game);
        Wolf wolf4 = new Wolf(this,"white" ,4.57f, "right", 6, 18.35f, game);
        Wolf wolf5 = new Wolf(this,"brown" ,13, "left", 35, 41.35f, game);
        Wolf wolf6 = new Wolf(this,"white" ,13, "right", 35, 51.35f, game);
        Wolf wolf7 = new Wolf(this,"black" ,13, "left", 35, 61.35f, game);
        Wolf wolf8 = new Wolf(this,"grey" ,13, "right", 35, 71.35f, game);
        Wolf wolf9 = new Wolf(this,"black" ,13, "left", 35, 81.35f, game);
        Wolf wolf10 = new Wolf(this,"white" ,13, "right", 35, 91.35f, game);
        Wolf wolf11 = new Wolf(this,"grey" ,13, "left", 35, 101.35f, game);
        Wolf wolf12 = new Wolf(this,"brown" ,13, "right", 35, 111.35f, game);


    }
    @Override
    public boolean isComplete() {
        return getCharacter().getKills() == 12;
    }

    @Override
    public float getPortal_x() {
        return 55;
    }

    @Override
    public float getPortal_y() {
        return switch (platform_number) {
            case 1 -> 42.5f;
            case 2 -> 52.5f;
            case 3 -> 62.5f;
            case 4 -> 72.5f;
            case 5 -> 82.5f;
            case 6 -> 92.5f;
            case 7 -> 102.5f;
            default -> 112.5f;
        };
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
