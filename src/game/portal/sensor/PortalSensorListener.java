package game.portal.sensor;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import game.Game;
import game.character.Character;
import game.levels.GameLevel;
import game.portal.Portal;

public class PortalSensorListener implements SensorListener {
    private final Character character;
    private Game game;

    private Portal portal;

    private boolean contact = false;

    public PortalSensorListener(Character character, Portal portal, Game game) {
        this.character = character;
        this.game = game;
        this.portal = portal;
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if(sensorEvent.getContactBody() instanceof Character){
            portal.destroy();
            game.goToNextLevel();
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}

