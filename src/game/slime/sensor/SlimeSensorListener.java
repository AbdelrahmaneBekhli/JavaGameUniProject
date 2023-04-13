package game.slime.sensor;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import game.character.Character;
import game.slime.Slime;
import game.snowball.Snowball;

public class SlimeSensorListener implements SensorListener {
    private final Character character;
    private final Slime slime;

    public SlimeSensorListener(Character character, Slime slime) {
        this.character = character;
        this.slime = slime;
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
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
