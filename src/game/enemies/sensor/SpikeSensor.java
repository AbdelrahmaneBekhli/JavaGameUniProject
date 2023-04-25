package game.enemies.sensor;

import city.cs.engine.*;
import game.character.Character;

public class SpikeSensor extends Sensor implements SensorListener {
    private boolean damagePlayer = true;
    public SpikeSensor(Body body, Shape shape) {
        super(body, shape);
        this.addSensorListener(this);
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if(sensorEvent.getContactBody() instanceof Character character){
            if(damagePlayer) {
                character.decreaseHealth();
                this.damagePlayer = false;
            }
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {
        if(sensorEvent.getContactBody() instanceof Character character){
            this.damagePlayer = true;
        }
    }
}
