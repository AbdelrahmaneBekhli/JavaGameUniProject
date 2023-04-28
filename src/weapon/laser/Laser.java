package weapon.laser;

import city.cs.engine.BoxShape;
import game.levels.GameLevel;
import weapon.Weapon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Laser extends Weapon implements ActionListener {
    Timer timer;
    public Laser(GameLevel world) {
        super(world, new BoxShape(0.3f, 0.08f));
        this.setFillColor(Color.white);
        this.setLineColor(Color.WHITE);
        this.setGravityScale(0);
        LaserCollisionListener collisionListener = new LaserCollisionListener();
        this.addCollisionListener(collisionListener);
        timer = new Timer(2000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.stop();
        this.destroy();
    }
}
