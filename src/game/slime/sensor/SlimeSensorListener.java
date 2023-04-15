package game.slime.sensor;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import game.Game;
import game.portal.Portal;
import game.character.Character;
import game.levels.GameLevel;
import game.slime.Slime;
import game.snowball.Snowball;

public class SlimeSensorListener implements SensorListener {
    private final Character character;
    private final Slime slime;
    private final GameLevel world;
    private final Game game;

    private boolean portalCreated = false;

    public SlimeSensorListener(Character character, Slime slime, GameLevel world, Game game) {
        this.character = character;
        this.slime = slime;
        this.world = world;
        this.game = game;
    }


    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if (sensorEvent.getContactBody() instanceof Character) {
            //check if the character collides with the slime from the top
            if (character.getPosition().y > slime.getPosition().y + 0.75f) {
                if(slime.isBounce()){
                    character.setBounce(true);
                    character.incrementKills();
                }
                slime.die();
                if(world.isComplete() && !(portalCreated)){
                    Portal portal = new Portal(world, world.getPortal_x(), world.getPortal_y(), character, game);
                    portalCreated = true;
                }

            } else {
                if (slime.isAlive()) {
                    character.die();
                }
            }
        }
        if (sensorEvent.getContactBody() instanceof Snowball){
            sensorEvent.getContactBody().destroy();
            if (slime.isAlive()) {
                character.incrementKills();
            }
            slime.die();
            if(world.isComplete() && !(portalCreated)){
                Portal portal = new Portal(world, world.getPortal_x(), world.getPortal_y(), character, game);
                portalCreated = true;
            }
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
