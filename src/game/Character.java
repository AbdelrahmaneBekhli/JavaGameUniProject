package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class Character extends Walker {
    private static final Shape CharaterShape = new BoxShape(1,1.2f);
    private static final BodyImage image = new BodyImage("data/player/idle_right.gif", 2.35f);
    private int credits;

    public Character(World world){
        super(world, CharaterShape);
        this.addImage(image);
        credits = 0;
    }

    public int getCredits(){
        return credits;
    }
    public void setCredits(int credits){
        this.credits = credits;
    }


}
