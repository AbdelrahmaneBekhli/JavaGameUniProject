package game;

import city.cs.engine.SensorEvent;

public class CoinSensorListener implements city.cs.engine.SensorListener {

    private final Coin coin;
    public CoinSensorListener(Coin coin) {
        this.coin = coin;
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if(sensorEvent.getContactBody() instanceof Character){
            coin.destroy();
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
