package collectables.coin;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import city.cs.engine.Shape;
import city.cs.engine.Sensor;
import character.Character;
import collectables.coin.Coin;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class CoinSensor extends Sensor implements SensorListener {
    private final Character character;
    private final Coin coin;
    public CoinSensor(Coin coin, Shape shape, Character character) {
        super(coin, shape);
        this.character = character;
        this.coin = coin;
        this.addSensorListener(this);
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if(sensorEvent.getContactBody() instanceof Character){
            coin.destroy();
            character.incrementCredits();
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
