package game.collectables.speed.sensor;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import game.character.Character;
import game.collectables.speed.SpeedBoost;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeedBoostSensorListener implements SensorListener, ActionListener {

    Character character;
    SpeedBoost speedBoost;
    Timer timer;

    SpeedBoostSensorListener(Character character, SpeedBoost speedBoost){
        this.character = character;
        this.speedBoost = speedBoost;
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
