package game;

import city.cs.engine.*;
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

    private String facing = "right";

    private boolean alive = true;

    public Character(World world){
        super(world, CharaterShape);
        this.addImage(image);
        credits = 0;
        world.addStepListener(this);
        this.world = world;
    }

    public void shoot(){
        //setting up the shape of the snowball
        Shape snowballshape = new CircleShape(0.3f);
        DynamicBody snowball = new DynamicBody(world, snowballshape);
        BodyImage snowballImage = new BodyImage("data/snowball.png", 0.7f);
        //adding a collision listener to snowball
        SnowCollisionListener SnowCollisions = new SnowCollisionListener();
        snowball.addCollisionListener(SnowCollisions);
        //setting the physics of the snowball
        snowball.addImage(snowballImage);


        if(this.facing.equals("right")) {
            snowball.setPosition(this.getPosition());
            snowball.setLinearVelocity(new Vec2(10, 4));
        } else if (this.facing.equals("left")) {
            snowball.setPosition(new Vec2(this.getPosition().x -1, this.getPosition().y));
            snowball.setLinearVelocity(new Vec2(-10, 4));
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
        Timer timer = new Timer(1000, new TimerHandler(this));
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
                this.setGravityScale(-50);
            }
            else{
                this.setGravityScale(3);
                bounce = false;
                this.counter = 0;
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
