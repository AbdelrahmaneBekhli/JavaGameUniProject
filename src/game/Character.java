package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class Character extends Walker {
    private final static  Shape CharaterShape = new BoxShape(1,1);
    private static final BodyImage image = new BodyImage("data/Owlet_Monster.png", 2);
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
