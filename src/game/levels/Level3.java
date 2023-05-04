package game.levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.Game;
import game.JumpPad;
import collectables.coin.Coin;
import enemies.Golem;
import platform.MovingPlatform;
import platform.Platform;
import weapon.laser.Laser;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class Level3 extends GameLevel implements StepListener{
    private SoundClip gameMusic;
    private final Game game;

    public Level3(Game game) {
        super();
        this.game = game;
        this.addStepListener(this);
        float blockX = 60;
        float blockY = -12.6f;
        float blockHeight = 0.5f;

        //adding the music background
        try{
            gameMusic = new SoundClip("data/audio/level3MusicTrack.wav");
            gameMusic.setVolume(0.1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }

        getCharacter().setSpeed(20);
        getCharacter().setPosition(new Vec2(-12, -13));

        //platforms
        Platform short1 = new Platform(this, getShortPlatformShape(), 13, 0, "short");
        Platform short2 = new Platform(this, getShortPlatformShape(), 25, 0, "short");
        Platform short3 = new Platform(this, getShortPlatformShape(), 120, 4, "short");

        Platform medium1 = new Platform(this, getMediumPlatformShape(), 0, -7, "medium");
        Platform medium2 = new Platform(this, getMediumPlatformShape(), 38, -7, "medium");
        Platform medium3 = new Platform(this, getMediumPlatformShape(), 105, 0, "medium");
        Platform medium4 = new Platform(this, getMediumPlatformShape(), 135, 4, "medium");
        Platform medium5 = new Platform(this, getMediumPlatformShape(), 200, 5, "medium");
        Platform medium6 = new Platform(this, getMediumPlatformShape(), 220, 0, "medium");
        Platform medium7 = new Platform(this, getMediumPlatformShape(), 440, -3, "medium");
        Platform medium8 = new Platform(this, getMediumPlatformShape(), 460, -9, "medium");
        Platform medium9 = new Platform(this, getMediumPlatformShape(), 480, -13.6f, "medium");

        Platform long1 = new Platform(this, getLongPlatformShape(), 220, -13.6f, "long");
        Platform long2 = new Platform(this, getLongPlatformShape(), 280, -13.6f, "long");
        Platform long3 = new Platform(this, getLongPlatformShape(), 340, 4, "long");
        Platform long4 = new Platform(this, getLongPlatformShape(), 400, 4, "long");

        Platform extralongplatform = new Platform(this, getExtraLongPlatformShape(), 70, -13.6f, "extra long");

        //moving platforms
        for(int i = 14; i >= -16; i = i -10){
            MovingPlatform movingMedium = new MovingPlatform(this, getMediumPlatformShape(), 180, i, "medium");

        }

        //block walls
        for(int i = 0; i < 7; i++){
            Shape blockShape = new BoxShape(0.5f, blockHeight);
            StaticBody block = new StaticBody(this, blockShape);
            block.setPosition(new Vec2(blockX, blockY));
            blockX = blockX + 4;
            blockY = blockY + 0.5f;
            blockHeight = blockHeight + 0.5f;
            block.addImage(new BodyImage("data/tiles/level3/" + (i + 1) + "block.png", blockHeight * 2));
            block.setClipped(true);
        }

        for(int i = 6; i >= 0; i--){
            blockHeight = blockHeight - 0.5f;
            blockY = blockY - 0.5f;
            Shape blockShape = new BoxShape(0.5f, blockHeight);
            StaticBody block = new StaticBody(this, blockShape);
            block.setPosition(new Vec2(blockX, blockY));
            block.addImage(new BodyImage("data/tiles/level3/" + (i + 1) + "block.png", blockHeight * 2));
            block.setClipped(true);
            blockX = blockX + 4;
        }

        //enemies
        Golem golem1 = new Golem(this, 5, "right", 0, -5.4f);
        Golem golem2 = new Golem(this, 5, "left", 38, -5.4f);
        Golem golem3 = new Golem(this, 5, "right", 105, 1.5f);
        Golem golem4 = new Golem(this, 5, "left", 135, 5.6f);
        Golem golem5 = new Golem(this, 5, "right", 200, 6.6f);
        Golem golem6 = new Golem(this, 5, "left", 220, 1.6f);
        Golem golem7 = new Golem(this, 15, "right", 340, 5.6f);
        Golem golem8 = new Golem(this, 15, "left", 400, 5.6f);

        //jump pad
        JumpPad jumpPad1 = new JumpPad(this, 302,-12.5f);

        //coins
        Coin coin1 = new Coin(this, "up", 0, 0);
        Coin coin2 = new Coin(this, "down", 19, 7);
        Coin coin3 = new Coin(this, "up", 38, 0);
        Coin coin4 = new Coin(this, "down", 86, -9);
        Coin coin5 = new Coin(this, "up", 120, 7);
        Coin coin6 = new Coin(this, "down", 210, -10);
        Coin coin7 = new Coin(this, "up", 250, -7);
        Coin coin8 = new Coin(this, "down", 302, 10);
        Coin coin9 = new Coin(this, "up", 370, 11);
        Coin coin10 = new Coin(this, "down", 430, 11);
        Coin coin11 = new Coin(this, "up", 450, 3);
        Coin coin12 = new Coin(this, "down", 470, -3);

    }

    @Override
    public boolean isComplete() {
        return getCharacter().getKills() == 8;
    }

    @Override
    public float getPortal_x() {
        return 483;
    }

    @Override
    public float getPortal_y() {
        return -10.5f;
    }

    @Override
    public void stopMusic() {
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
    public String getExtraLongTiles() {
        return "data/tiles/level3/ExtraLongPlatform.png";
    }

    @Override
    public String getLongTiles() {
        return "data/tiles/level3/LongPlatform.png";
    }

    @Override
    public String getMediumTiles() {
        return "data/tiles/level3/MediumPlatform.png";
    }

    @Override
    public String getShortTiles() {
        return "data/tiles/level3/ShortPlatform.png";
    }

    @Override
    public String getBackground() {
        return "data/tiles/level3/background.jpg";
    }

    @Override
    public Image getEnemyPic() {
        return new ImageIcon("data/enemy/golem/golem.png").getImage();
    }

    @Override
    public int getEnemyPicX() {
        return 12;
    }
    @Override
    public DynamicBody getWeapon() {
        return new Laser(this);
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if(this.getCharacter().getPosition().y < -20){
            this.getCharacter().decreaseHealth();
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
