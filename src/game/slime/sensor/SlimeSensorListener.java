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
    private GameLevel world;
    private Game game;

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
                if(world.isComplete()){
                    Portal portal = new Portal(world, 10,10, character, game);
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
            if(world.isComplete()){
                Portal portal = new Portal(world, 10,10, character, game);
            }
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
