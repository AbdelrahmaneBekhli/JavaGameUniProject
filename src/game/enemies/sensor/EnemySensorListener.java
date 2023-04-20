package game.enemies.sensor;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import game.Game;
import game.enemies.Enemy;
import game.enemies.Slime;
import game.enemies.Wolf;
import game.portal.Portal;
import game.character.Character;
import game.levels.GameLevel;
import game.weapon.snowball.Snowball;
import game.weapon.stone.Stone;

public class EnemySensorListener implements SensorListener {
    private final Character character;
    private final Enemy enemy;
    private final GameLevel world;
    private final Game game;

    private boolean portalCreated = false;

    public EnemySensorListener(Character character, Enemy enemy, GameLevel world, Game game) {
        this.character = character;
        this.enemy = enemy;
        this.world = world;
        this.game = game;
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if (sensorEvent.getContactBody() instanceof Character) {
            if (enemy instanceof Slime) {
                //check if the character collides with the slime from the top
                if (character.getPosition().y > enemy.getPosition().y + 0.75f) {
                    if (enemy.isBounce()) {
                        character.setBounce(true);
                        character.incrementKills();
                    }
                    enemy.die();
                } else {
                    if (enemy.isAlive()) {
                        character.die();
                    }
                }
            }

            if (enemy instanceof Wolf){
                if (enemy.isAlive()) {
                    character.die();
                }
            }
        }
        if (sensorEvent.getContactBody() instanceof Snowball || sensorEvent.getContactBody() instanceof Stone) {
            if (enemy.isAlive()) {
                sensorEvent.getContactBody().destroy();
                character.incrementKills();
                enemy.die();
            }
        }

        if (world.isComplete() && !(portalCreated)) {
            this.portalCreated = true;
            Portal portal = new Portal(world, world.getPortal_x(), world.getPortal_y(), character, game);
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
