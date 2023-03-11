package game;

import city.cs.engine.Body;
import city.cs.engine.Shape;

public class CoinSensor extends city.cs.engine.Sensor {
    public CoinSensor(Body body, Shape shape, Coin coin) {
        super(body, shape);
        CoinSensorListener sensorListener = new CoinSensorListener(coin);
        this.addSensorListener(sensorListener);
    }
}
