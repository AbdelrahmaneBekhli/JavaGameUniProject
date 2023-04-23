package game.character;

import city.cs.engine.*;
import game.levels.GameLevel;
import game.levels.Level1;
import game.levels.Level2;
import game.weapon.laser.Laser;
import game.weapon.snowball.Snowball;
import game.weapon.stone.Stone;
import org.jbox2d.common.Vec2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Character extends Walker implements StepListener, ActionListener{
    private static final Shape CharaterShape = new BoxShape(1,1.2f);

    private final BodyImage idle_right = new BodyImage("data/player/idle_right.gif", 2.35f);
    private final BodyImage idle_left = new BodyImage("data/player/idle_left.gif", 2.35f);
    private final BodyImage die_right = new BodyImage("data/player/die_right.gif", 2.55f);
    private final BodyImage die_left = new BodyImage("data/player/die_left.gif", 2.55f);
    private final BodyImage run_right = new BodyImage("data/player/run_right.gif", 2.35f);
    private final BodyImage run_left = new BodyImage("data/player/run_left.gif", 2.35f);
    private int speed;
    private GameLevel world;
    private int speedBoostCollected;
    private int credits;
    private int counter;
    private int kills = 0;
    private String facing = "right";
    private boolean alive = true;

    public Character(GameLevel world){
        super(world, CharaterShape);
        this.addImage(idle_right);
        credits = 0;
        this.world = world;
        world.addStepListener(this);
        this.setGravityScale(5);
        this.addCollisionListener(new CharacterCollisionListener());
        SolidFixture fixture = new SolidFixture(this, CharaterShape);
        fixture.setFriction(0);
    }

    public void shoot(){
        DynamicBody weapon = this.world.getWeapon();
        if(weapon instanceof Laser){
            if(this.facing.equals("right")){
                weapon.setPosition(new Vec2(this.getPosition().x + 1, this.getPosition().y + 0.5f));
                weapon.setLinearVelocity(new Vec2(25, 0));
            } else{
                weapon.setPosition(new Vec2(this.getPosition().x - 1, this.getPosition().y + 0.5f));
                weapon.setLinearVelocity(new Vec2(-25, 0));
            }
        } else {
            //setting up the shape of the weapon
            if (this.facing.equals("right")) {
                weapon.setPosition(this.getPosition());
                weapon.setLinearVelocity(new Vec2(15, 6));
            } else if (this.facing.equals("left")) {
                weapon.setPosition(new Vec2(this.getPosition().x - 1, this.getPosition().y));
                weapon.setLinearVelocity(new Vec2(-10, 4));
            }
        }

    }
    public void die(){
        this.alive = false;
        if(facing.equals("right")) {
            this.removeAllImages();
            this.addImage(die_right);
        } else{
            this.removeAllImages();
            this.addImage(die_left);
        }
        this.startWalking(0);
        Timer timer = new Timer(1000, this);
        timer.start();
    }
    public boolean isAlive() {
        return alive;
    }
    public int getCredits(){
        return credits;
    }
    public void incrementcredits(){
        this.credits = this.credits + 1;
    }

    public void setCredits(int credits){
        this.credits = credits;
    }

    public int getKills(){
        return kills;
    }
    public void incrementKills(){
        this.kills = this.kills + 1;
    }

    public void setFacing(String facing) {
        this.facing = facing;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeedBoostCollected() {
        return speedBoostCollected;
    }

    public void setSpeedBoostCollected(int speedBoostCollected) {
        this.speedBoostCollected = speedBoostCollected;
    }

    public BodyImage getIdle_right() {
        return idle_right;
    }

    public BodyImage getIdle_left() {
        return idle_left;
    }

    public BodyImage getRun_left() {
        return run_left;
    }

    public BodyImage getRun_right() {
        return run_right;
    }


    @Override
    public void preStep(StepEvent stepEvent) {
        if(this.getWorld() instanceof Level2){
            if(this.getPosition().y < -20){
                this.setPosition(new Vec2(-10, 0));
            }
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
