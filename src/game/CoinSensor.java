package game;

import city.cs.engine.Body;
import city.cs.engine.Shape;
import city.cs.engine.Sensor;

public class CoinSensor extends Sensor {
    public CoinSensor(Body body, Shape shape, Coin coin, Character character) {
        super(body, shape);
        CoinSensorListener sensorListener = new CoinSensorListener(character, coin);
        this.addSensorListener(sensorListener);
    }
}dwd
