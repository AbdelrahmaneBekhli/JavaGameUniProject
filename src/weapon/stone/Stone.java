package weapon.stone;

import city.cs.engine.BodyImage;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import game.levels.GameLevel;
import weapon.Weapon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class Stone extends Weapon implements ActionListener {

    private final Timer timer;
    public Stone(GameLevel world, Shape shape) {
        super(world, shape);

        SolidFixture fixture = new SolidFixture(this, shape);

        BodyImage stoneImage = new BodyImage("data/weapons/stone.png", 0.7f);
        this.addImage(stoneImage);
        this.setGravityScale(3);
        fixture.setRestitution(0.5f);
        timer = new Timer(3500, this);
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.stop();
        this.destroy();
    }
}
