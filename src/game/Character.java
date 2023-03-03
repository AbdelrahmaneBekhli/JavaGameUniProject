package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class Character extends Walker {
    private static final Shape CharaterShape = new BoxShape(1,1.2f);
    //-0.968f,-1.096f, 0.707f,-1.086f, 0.692f,1.03f, -1.003f,1.02f, -1.028f,-0.254f
    private static final BodyImage image = new BodyImage("data/player/idle_right.gif", 2.35f);
    private int credits;

    public Character(World world){
        super(world, CharaterShape);
        this.addImage(image);
        this.setAlwaysOutline(true);
        credits = 0;
    }

    public int getCredits(){
        return credits;
    }
    public void setCredits(int credits){
        this.credits = credits;
    }


}
