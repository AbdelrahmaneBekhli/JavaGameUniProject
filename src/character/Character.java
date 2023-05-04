package character;

import city.cs.engine.*;
import game.levels.GameLevel;
import weapon.laser.Laser;
import org.jbox2d.common.Vec2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */

public class Character extends Walker implements ActionListener{
    private static final Shape CharaterShape = new BoxShape(0.8f,1.2f);
    private final BodyImage idle_right = new BodyImage("data/player/idle_right.gif", 2.35f);
    private final BodyImage idle_left = new BodyImage("data/player/idle_left.gif", 2.35f);
    private final BodyImage die_right = new BodyImage("data/player/die_right.gif", 2.55f);
    private final BodyImage die_left = new BodyImage("data/player/die_left.gif", 2.55f);
    private final BodyImage run_right = new BodyImage("data/player/run_right.gif", 2.35f);
    private final BodyImage run_left = new BodyImage("data/player/run_left.gif", 2.35f);
    private int speed;
    private final GameLevel world;
    private int speedBoostCollected;
    private int credits;
    private int health = 4;
    private int kills = 0;
    private String facing = "right";
    private boolean alive = true;

    private static SoundClip damageSound;

    static {
        try {
            damageSound = new SoundClip("data/audio/characterDamage.wav");
            damageSound.setVolume(0.1);
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Character(GameLevel world){
        super(world, CharaterShape);
        this.addImage(idle_right);
        credits = 0;
        this.world = world;
        this.setGravityScale(5);
        this.addCollisionListener(new CharacterCollisionListener());
        SolidFixture fixture = new SolidFixture(this, CharaterShape);
        fixture.setFriction(0); //disable climbing
    }

    /**
     * make the player shoot towards the direction he is facing
     */
    public void shoot(){
        //getting the weapon
        DynamicBody weapon = this.world.getWeapon();
        //setting the physics of the weapon
        if(weapon instanceof Laser){
            if(this.facing.equals("right")){
                weapon.setPosition(new Vec2(this.getPosition().x + 1, this.getPosition().y + 0.5f));
                weapon.setLinearVelocity(new Vec2(25, 0));
            } else{
                weapon.setPosition(new Vec2(this.getPosition().x - 1, this.getPosition().y + 0.5f));
                weapon.setLinearVelocity(new Vec2(-25, 0));
            }
        } else {
            if (this.facing.equals("right")) {
                weapon.setPosition(this.getPosition());
                weapon.setLinearVelocity(new Vec2(15, 6));
            } else if (this.facing.equals("left")) {
                weapon.setPosition(new Vec2(this.getPosition().x - 1, this.getPosition().y));
                weapon.setLinearVelocity(new Vec2(-10, 4));
            }
        }

    }
    /**
     * decrease the player's health by 1.
     */
    public void decreaseHealth(){
        if(this.world.getGame().getfxButton().isSound()) {
            damageSound.play();
        }
        this.health = health - 1;
        if (this.health == 0){
            this.die();
        }
    }

    /**
     * destroy the player with displaying the death animation.
     */
    private void die(){
        this.alive = false;
        if(facing.equals("right")) {
            this.removeAllImages();
            this.addImage(die_right);
        } else{
            this.removeAllImages();
            this.addImage(die_left);
        }
        this.startWalking(0);
        //play the death animation
        Timer timer = new Timer(1000, this);
        timer.start();
    }
    /**
     * @return if player is still alive.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @return player's credits.
     */
    public int getCredits(){
        return credits;
    }

    /**
     * increases the credits by 1.
     */
    public void incrementCredits(){
        this.credits = this.credits + 1;
    }

    /**
     * sets the credits of the player to a certain integer.
     */
    public void setCredits(int credits){
        this.credits = credits;
    }


    /**
     * @return player's kills.
     */
    public int getKills(){
        return kills;
    }

    /**
     * increments the kills of the player by 1.
     */
    public void incrementKills(){
        this.kills = this.kills + 1;
    }

    /**
     * sets the direction of where the player is facing.
     * @param facing String indicating the direction (right or left)
     */
    public void setFacing(String facing) {
        this.facing = facing;
    }

    /**
     * @return speed of the player.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * sets the speed of the player.
     * @param speed integer speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return number of speed boosts collected by the player.
     */
    public int getSpeedBoostCollected() {
        return speedBoostCollected;
    }

    /**
     * sets the number of speed collected by the player.
     * @param speedBoostCollected integer of how many speed boosts are collected
     */
    public void setSpeedBoostCollected(int speedBoostCollected) {
        this.speedBoostCollected = speedBoostCollected;
    }
    /**
     * @return player's Idle gif facing right.
     */
    public BodyImage getIdle_right() {
        return idle_right;
    }
    /**
     * @return player's Idle gif facing left.
     */
    public BodyImage getIdle_left() {
        return idle_left;
    }
    /**
     * @return player's running gif facing left.
     */
    public BodyImage getRun_left() {
        return run_left;
    }
    /**
     * @return player's running gif facing right.
     */
    public BodyImage getRun_right() {
        return run_right;
    }
    /**
     * @return player's health.
     */
    public int getHealth(){
        return health;
    }

    @Override
    public GameLevel getWorld(){
        return world;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.destroy();
    }
}
