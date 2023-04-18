package game.character;

import city.cs.engine.*;
import game.levels.GameLevel;
import game.levels.Level1;
import game.levels.Level2;
import game.weapon.snowball.Snowball;
import game.weapon.stone.Stone;
import org.jbox2d.common.Vec2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Character extends Walker implements StepListener, ActionListener{
    private static final Shape CharaterShape = new BoxShape(1,1.2f);
    private static final BodyImage image = new BodyImage("data/player/idle_right.gif", 2.35f);
    private int credits;
    private final World world;
    private int counter;
    private boolean bounce;
    private int kills = 0;

    private String facing = "right";

    private boolean alive = true;

    public Character(GameLevel world){
        super(world, CharaterShape);
        this.addImage(image);
        credits = 0;
        world.addStepListener(this);
        this.world = world;
        this.setGravityScale(5);
    }

    public void shoot(){
        DynamicBody weapon;
        //setting up the shape of the weapon
        Shape weaponShape = new CircleShape(0.3f);
        if(world instanceof Level1){
            weapon = new Snowball(world, weaponShape);
        } else{
            weapon = new Stone(world, weaponShape);
        }


        if(this.facing.equals("right")) {
            weapon.setPosition(this.getPosition());
            weapon.setLinearVelocity(new Vec2(15, 6));
        } else if (this.facing.equals("left")) {
            weapon.setPosition(new Vec2(this.getPosition().x -1, this.getPosition().y));
            weapon.setLinearVelocity(new Vec2(-10, 4));
        }

    }
    public void die(){
        this.alive = false;
        if(facing.equals("right")) {
            BodyImage image = new BodyImage("data/player/die_right.gif", 2.55f);
            this.removeAllImages();
            this.addImage(image);
        } else{
            BodyImage image = new BodyImage("data/player/die_left.gif", 2.55f);
            this.removeAllImages();
            this.addImage(image);
        }
        this.startWalking(0);
        Timer timer = new Timer(1000, new CharacterTimerHandler(this));
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

    public int getKills(){
        return kills;
    }
    public void incrementKills(){
        this.kills = this.kills + 1;
    }

    public void setBounce(boolean bounce) {
        this.bounce = bounce;
    }

    public void setFacing(String facing) {
        this.facing = facing;
    }


    @Override
    public void preStep(StepEvent stepEvent) {
        if(bounce) {
            if (counter == 0) {
                counter ++;
                this.setGravityScale(-220);
            }
            else{
                this.setGravityScale(5);
                bounce = false;
                this.counter = 0;
            }
        }
        if(world instanceof Level2){
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
    }
}
