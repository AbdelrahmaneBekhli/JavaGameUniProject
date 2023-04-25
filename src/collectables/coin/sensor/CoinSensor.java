package collectables.coin.sensor;

import city.cs.engine.Shape;
import city.cs.engine.Sensor;
import character.Character;
import collectables.coin.Coin;

public class CoinSensor extends Sensor {
    public CoinSensor(Coin coin, Shape shape, Character character) {
        super(coin, shape);
        CoinSensorListener sensorListener = new CoinSensorListener(character, coin);
        this.addSensorListener(sensorListener);
    }
}
