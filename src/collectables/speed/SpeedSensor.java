package collectables.speed;

import city.cs.engine.Sensor;
import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import city.cs.engine.Shape;
import character.Character;
import collectables.speed.SpeedBoost;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class SpeedSensor extends Sensor implements SensorListener, ActionListener {
    private final SpeedBoost speedBoost;
    private final Character character;
    private Timer timer;
    public SpeedSensor(SpeedBoost speedBoost, Shape shape, Character character) {
        super(speedBoost, shape);
        this.character = character;
        this.speedBoost = speedBoost;
        this.addSensorListener(this);
    }
    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if(sensorEvent.getContactBody() instanceof Character){
            speedBoost.destroy();
            character.setSpeedBoostCollected(character.getSpeedBoostCollected() + 1);
            character.setSpeed(16);
            timer = new Timer(7000  , this);
            timer.start();
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.stop();
        if(character.getSpeedBoostCollected() == 1){
            character.setSpeed(11);
        }
        character.setSpeedBoostCollected(character.getSpeedBoostCollected() - 1);

    }
}
