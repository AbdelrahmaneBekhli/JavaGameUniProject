package weapon.stone;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import platform.Platform;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoneCollisionListener implements CollisionListener, ActionListener {
    private Stone stone;
    private Timer timer;

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof Platform){
            stone = (Stone) collisionEvent.getReportingBody();
            if(!(stone.isCollided())){
                timer = new Timer(2500  , this);
                timer.start();
                stone.setCollided(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.stop();
        stone.destroy();
    }
}
