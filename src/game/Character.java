package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Character extends Walker implements StepListener{
    private static final Shape CharaterShape = new BoxShape(1,1.2f);
    private static final BodyImage image = new BodyImage("data/player/idle_right.gif", 2.35f);
    private int credits;

    private int counter;
    private boolean bounce;

    public Character(World world){
        super(world, CharaterShape);
        this.addImage(image);
        credits = 0;
        world.addStepListener(this);
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
}
