package enemies;

import city.cs.engine.*;
import enemies.sensor.EnemySensor;
import game.levels.GameLevel;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */

public class Wolf  extends Enemy {
    private static final Shape wolfShape = new BoxShape(1.5f,0.9f);
    private final BodyImage run_left;
    private final BodyImage run_right;
    private final BodyImage die_left;
    private final BodyImage die_right;
    private final float range;
    private float right;
    private float left;
    private boolean alive = true;
    private String facing;

    private final GameLevel level;
    private static SoundClip wolfDeathSound;

    static {
        try {
            wolfDeathSound = new SoundClip("data/audio/wolf.wav");
            wolfDeathSound.setVolume(0.5);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Wolf(GameLevel world, String color, float range, String initial_facing, float posX, float posY) {
        super(world);
        this.range = range;
        this.facing = initial_facing;
        this.level = world;
        this.run_left = new BodyImage("data/enemy/wolf/" + color + "/run_left.gif", 1.7f);
        this.run_right = new BodyImage("data/enemy/wolf/" + color + "/run_right.gif", 1.7f);
        this.die_left = new BodyImage("data/enemy/wolf/"+ color + "/die_left.gif", 1.7f);
        this.die_right =  new BodyImage("data/enemy/wolf/"+ color + "/die_right.gif", 1.7f);

        Fixture fixture = new GhostlyFixture(this, wolfShape);
        Sensor sensor = new EnemySensor(this, wolfShape, world);

        if (initial_facing.equals("right")){
            this.addImage(run_right);
            this.startWalking(7);
            this.facing = "right";
        } else {
            this.addImage(run_left);
            this.startWalking(-7);
            this.facing = "left";
        }

        this.setGravityScale(0);
        this.setPosition(new Vec2(posX, posY));
        this.setRange();
        world.addStepListener(this);
    }

    @Override
    public void setRange() {
        //setting up the range for each direction
        this.left = this.getPosition().x - range;
        this.right = this.getPosition().x + range;
    }

    @Override
    public void die() {
        if(this.level.getGame().getfxButton().isSound()) {
            wolfDeathSound.play();
        }
        this.startWalking(0);
        if (this.facing.equals("right")) {
            this.removeAllImages();
            this.addImage(this.die_right);
        } else {
            this.removeAllImages();
            this.addImage(this.die_left);
        }
        this.alive = false;
        //death animation timer
        Timer timer = new Timer(600, this);
        timer.start();
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

    @Override
    public boolean isBounce() {
        return false;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if(this.alive) {
            //changing speed and gif when the range is reached
            if (getPosition().x > right) {
                this.removeAllImages();
                this.addImage(run_left);
                this.startWalking(-7);
                this.facing = "left";
            }
            if (getPosition().x < left) {
                this.removeAllImages();
                this.addImage(run_right);
                this.startWalking(7);
                this.facing = "right";
            }
        }
        else{
            this.startWalking(0);
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.destroy();
    }
}
