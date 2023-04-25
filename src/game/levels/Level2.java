package game.levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.Game;
import collectables.coin.Coin;
import collectables.speed.SpeedBoost;
import enemies.Wolf;
import platform.Platform;
import platform.Wall;
import weapon.stone.Stone;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Level2 extends GameLevel implements StepListener{
    private SoundClip gameMusic;
    private final int platform_number = (int) Math.floor(Math.random() *(4 - 1 + 1) + 1);
    public Level2(Game game){
        //base class will create the student, professor
        super(game);
        this.addStepListener(this);
        getCharacter().setSpeed(11);
        getCharacter().setPosition(new Vec2(-10, 0));


        //adding the music background
        try{
            gameMusic = new SoundClip("data/audio/level2MusicTrack.wav");
            gameMusic.setVolume(0.1);
            gameMusic.loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }

        //platforms
        Platform p1 = new Platform(this, getShortPlatformShape(), -10, 0, "short");
        Platform p2 = new Platform(this, getShortPlatformShape(), -17, 5, "short");
        Platform p3 = new Platform(this, getShortPlatformShape(), -5, -5, "short");
        Platform p4 = new Platform(this, getShortPlatformShape(), 0, 7, "short");
        Platform p5 = new Platform(this, getShortPlatformShape(), 3, 0, "short");
        Platform p6 = new Platform(this, getShortPlatformShape(), 10, 10, "short");
        Platform p7 = new Platform(this, getShortPlatformShape(), 13, -4, "short");
        Platform p8 = new Platform(this, getShortPlatformShape(), -10, 15, "short");
        Platform p9 = new Platform(this, getShortPlatformShape(), -4, 22, "short");
        Platform p10 = new Platform(this, getShortPlatformShape(), 15, 23, "short");
        Platform p11 = new Platform(this, getShortPlatformShape(), 7, 28, "short");
        Platform p12 = new Platform(this, getShortPlatformShape(), -16, 24, "short");
        Platform p13 = new Platform(this, getMediumPlatformShape(), -20, -7, "medium");
        Platform p14 = new Platform(this, getMediumPlatformShape(), -26, 12, "medium");
        Platform p15 = new Platform(this, getMediumPlatformShape(), 16, 6, "medium");
        Platform p16 = new Platform(this, getMediumPlatformShape(), 6, 17, "medium");

        for(int i = 35; i <= 105; i = i + 10){
            Platform stair1 = new Platform(this, getShortPlatformShape(), 0, i, "short");
            Platform stair2 = new Platform(this, getShortPlatformShape(), -10, i -5, "short");
        }

        for(int i = 40; i <= 100; i = i + 20){
            Platform longPlatforms = new Platform(this, getLongPlatformShape(), 35, i, "long");
        }

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
        Wolf wolf6 = new Wolf(this,"black" ,13, "left", 35, 61.35f, game);
        Wolf wolf7 = new Wolf(this,"white" ,13, "left", 35, 81.35f, game);
        Wolf wolf8 = new Wolf(this,"grey" ,13, "left", 35, 101.35f, game);


    }
    @Override
    public boolean isComplete() {
        boolean complete = getCharacter().getKills() == 8;
        if(complete) {
            for (float i = 42.5f; i < 110; i = i + 20) {
                SpeedBoost speed = new SpeedBoost(this, 13, i);
            }
        }
        return complete;
    }

    @Override
    public float getPortal_x() {
        return 55;
    }

    @Override
    public float getPortal_y() {
        return switch (platform_number) {
            case 1 -> 42.5f;
            case 2 -> 62.5f;
            case 3 -> 82.5f;
            default -> 102.5f;
        };
    }

    @Override
    public void stopMusic() {
        gameMusic.stop();
    }

    @Override
    public String getExtraLongTiles() {
        return null;
    }
    @Override
    public String getLongTiles() {
        return "data/tiles/level2/LongPlatform.png";
    }

    @Override
    public String getMediumTiles() {
        return "data/tiles/level2/MediumPlatform.png";
    }

    @Override
    public String getShortTiles() {
        return "data/tiles/level2/ShortPlatform.png";
    }

    @Override
    public String getBackground() {
        return "data/tiles/level2/background.jpg";
    }

    @Override
    public Image getEnemyPic(){
        return new ImageIcon("data/enemy/wolf/wolf.png").getImage();
    }
    @Override
    public int getEnemyPicX(){
        return 9;
    }
    @Override
    public DynamicBody getWeapon() {
        Shape weaponShape = new CircleShape(0.3f);
        return new Stone(this, weaponShape);
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if(this.getCharacter().getPosition().y < -20){
            this.getCharacter().setPosition(new Vec2(-10, 0));
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
