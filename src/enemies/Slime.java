package enemies;

import city.cs.engine.*;
import game.levels.GameLevel;
import enemies.sensor.EnemySensor;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Slime extends Enemy {
    private static final Shape SlimeShape = new BoxShape(0.7f,0.75f);
    private static final BodyImage rightImage = new BodyImage("data/enemy/slime/slime_right.gif", 1.5f);
    private static final BodyImage leftImage = new BodyImage("data/enemy/slime/slime_left.gif", 1.5f);
    private float leftRange;
    private float rightRange;
    private final float range;
    private boolean alive = true;
    private String facing;
    private boolean bounce = true;

    private final GameLevel level;

    private static SoundClip slimeDeathSound;
    static {
        try {
            slimeDeathSound = new SoundClip("data/audio/slime.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    /**
     * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
     */
    public Slime(GameLevel world, float range, String initial_facing, float posX, float posY) {
        super(world);
        world.addStepListener(this);
        this.range = range;
        this.level = world;
        this.setGravityScale(0);
        Fixture fixture = new GhostlyFixture(this, SlimeShape);
        Sensor sensor = new EnemySensor(this, SlimeShape, world);

        if (initial_facing.equals("right")){
            this.addImage(rightImage);
            this.startWalking(4);
            this.facing = "right";
        } else {
            this.addImage(leftImage);
            this.startWalking(-4);
            this.facing = "left";
        }
        this.setPosition(new Vec2(posX, posY));
        this.setRange();
    }

    @Override
    public void setRange(){
        //setting up the range for each direction
        this.leftRange = this.getPosition().x - range;
        this.rightRange = this.getPosition().x + range;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if(this.alive) {
            //changing speed and gif when the range is reached
            if (getPosition().x > rightRange) {
                this.removeAllImages();
                this.addImage(leftImage);
                this.startWalking(-3);
                this.facing = "left";
            }
            if (getPosition().x < leftRange) {
                this.removeAllImages();
                this.addImage(rightImage);
                this.startWalking(3);
                this.facing = "right";
            }
        }
        else{
            this.startWalking(0);
        }
    }
    @Override
    public void die(){
        if(this.level.getGame().getfxButton().isSound()){
            slimeDeathSound.play();
        }
        this.bounce = false;
        this.alive = false;
        if (this.facing.equals("right")) {
            BodyImage image = new BodyImage("data/enemy/slime/die_right.gif", 1.5f);
            this.removeAllImages();
            this.addImage(image);
        } else{
            BodyImage image = new BodyImage("data/enemy/slime/die_left.gif", 1.5f);
            this.removeAllImages();
            this.addImage(image);
        }
        //death animation timer
        Timer timer = new Timer(1000, this);
        timer.start();

    }
    @Override
    public boolean isAlive() {
        return alive;
    }
    @Override
    public boolean isBounce() {
        return bounce;
    }
    @Override
    public void postStep(StepEvent stepEvent) {

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.destroy();
    }
}
