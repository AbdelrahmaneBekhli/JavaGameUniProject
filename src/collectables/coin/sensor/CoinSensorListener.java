package collectables.coin.sensor;

import city.cs.engine.SensorEvent;
import character.Character;
import collectables.coin.Coin;
import city.cs.engine.SensorListener;

public class CoinSensorListener implements SensorListener {

    private final Coin coin;
    private final Character character;
    public CoinSensorListener(Character character, Coin coin) {
        this.coin = coin;
        this.character = character;
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if(sensorEvent.getContactBody() instanceof Character){
            coin.destroy();
            character.incrementcredits();
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
