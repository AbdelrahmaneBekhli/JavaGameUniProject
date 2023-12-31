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

public class Golem extends Enemy {
    private final BodyImage armor_run_right = new BodyImage("data/enemy/Golem/armorRun_right.gif", 2);
    private final BodyImage armor_run_left = new BodyImage("data/enemy/Golem/armorRun_Left.gif", 2);
    private final BodyImage armor_break_right = new BodyImage("data/enemy/Golem/armorBreak_right.gif", 2);
    private final BodyImage armor_break_left = new BodyImage("data/enemy/Golem/armorBreak_left.gif", 2);
    private final BodyImage armor_Attack_right = new BodyImage("data/enemy/Golem/armorAttack_right.gif", 2.5f);
    private final BodyImage armor_Attack_left = new BodyImage("data/enemy/Golem/armorAttack_left.gif", 2.5f);
    private final BodyImage noArmorRun_right = new BodyImage("data/enemy/Golem/noArmorRun_right.gif", 2);
    private final BodyImage noArmorRun_left = new BodyImage("data/enemy/Golem/noArmorRun_left.gif", 2);
    private final BodyImage noArmorDeath_right = new BodyImage("data/enemy/Golem/noArmorDeath_right.gif", 2);
    private final BodyImage noArmorDeath_left = new BodyImage("data/enemy/Golem/noArmorDeath_left.gif", 2);
    private final float range;
    private float right;
    private float left;
    private Timer animationTimer;
    private Timer deathTimer;
    private String facing;
    private Boolean alive = true;
    private Boolean attacking = false;
    private Boolean armor = true;
    private Boolean animation = false;
    private final GameLevel world;
    private static SoundClip golemDeathSound;
    static {
        try {
            golemDeathSound = new SoundClip("data/audio/golemDeath.wav");
            golemDeathSound.setVolume(0.1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    private static SoundClip golemArmorBreakSound;
    static {
        try {
            golemArmorBreakSound = new SoundClip("data/audio/golemArmorBreak.wav");
            golemArmorBreakSound.setVolume(0.5);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }


    public Golem(GameLevel world, float range, String initial_facing, float posX, float posY) {
        super(world);
        this.range = range;
        this.world = world;
        this.setPosition(new Vec2(posX, posY));
        this.setGravityScale(0);
        world.addStepListener(this);

        Shape golemShape = new BoxShape(0.7f, 1);
        Fixture fixture = new GhostlyFixture(this, golemShape);
        Sensor sensor = new EnemySensor(this, golemShape, world);

        //makes the golem attack at random times
        GolemAttackTimeHandler time = new GolemAttackTimeHandler(this, world);

        if (initial_facing.equals("right")){
            this.addImage(this.armor_run_right);
            this.startWalking(4);
            this.facing = "right";
        } else {
            this.addImage(this.armor_run_left);
            this.startWalking(-4);
            this.facing = "left";
        }
        this.setRange();
    }

    /**
     * @return if the golem has armor.
     */
    public Boolean getArmor() {
        return armor;
    }

    /**
     * sets the golem state to attacking or not attacking.
     */
    public void setAttacking(Boolean attacking){
        this.attacking = attacking;
    }

    /**
     * destroys the armor.
     */
    public void destroyArmor(){
        if(this.world.getGame().getfxButton().isSound()) {
            golemArmorBreakSound.play();
        }
        this.armor = false;
        this.animation = true;
        this.removeAllImages();
        if(this.facing.equals("right")){
            this.addImage(this.armor_break_right);
        } else{
            this.addImage(this.armor_break_left);
        }
        //displays the animation of the armor breaking
        animationTimer = new Timer(800, this::animationTimer);
        animationTimer.start();
    }
    /**
     * timer for the armor breaking animation to take place.
     */
    private void animationTimer (ActionEvent e){
        this.animation = false;
        animationTimer.stop();
    }

    /**
     * @return if golem is attacking.
     */
    public Boolean getAttacking() {
        return attacking;
    }


    @Override
    public void setRange() {
        //setting up the range for each direction
        this.left = this.getPosition().x - range;
        this.right = this.getPosition().x + range;
    }

    @Override
    public void die() {
        this.alive = false;
        this.startWalking(0);
        this.removeAllImages();
        if (this.facing.equals("right")){
            this.addImage(this.noArmorDeath_right);
        } else{
            this.addImage(this.noArmorDeath_left);
        }
        //death animation time
        if(this.world.getGame().getfxButton().isSound()) {
            golemDeathSound.play();
        }
        deathTimer = new Timer(800, this);
        deathTimer.start();
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
        if(this.alive && !(this.animation)) {
            if (attacking) {
                this.startWalking(0);
                this.removeAllImages();
                if (this.facing.equals("right")) {
                    this.addImage(this.armor_Attack_right);
                } else {
                    this.addImage(this.armor_Attack_left);
                }
            } else {
                if(this.facing.equals("right")){
                    this.removeAllImages();
                    if(this.armor){
                        this.startWalking(4);
                        this.addImage(this.armor_run_right);
                    }
                    else{
                        this.startWalking(8);
                        this.addImage(this.noArmorRun_right);
                    }
                } else{
                    this.removeAllImages();
                    if(this.armor) {
                        this.startWalking(-4);
                        this.addImage(this.armor_run_left);
                    } else{
                        this.startWalking(-8);
                        this.addImage(this.noArmorRun_left);
                    }
                }
                //changing gif when the range is reached
                if (getPosition().x > right) {
                    this.facing = "left";
                } else if (getPosition().x < left) {
                    this.facing = "right";
                }
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
        deathTimer.stop();
        this.destroy();
    }
}
