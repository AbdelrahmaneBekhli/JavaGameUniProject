package game.enemies.sensor;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import game.Game;
import game.enemies.Enemy;
import game.enemies.Golem;
import game.enemies.Slime;
import game.enemies.Wolf;
import game.portal.Portal;
import game.character.Character;
import game.levels.GameLevel;
import game.weapon.snowball.Snowball;
import game.weapon.stone.Stone;
import org.jbox2d.common.Vec2;

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

    private void checkComplete(){
        if (world.isComplete() && !(portalCreated)) {
            this.portalCreated = true;
            Portal portal = new Portal(world, world.getPortal_x(), world.getPortal_y(), character, game);
        }
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if (sensorEvent.getContactBody() instanceof Character) {
            if (enemy instanceof Slime) {
                //check if the character collides with the slime from the top
                if (character.getPosition().y > enemy.getPosition().y + 0.75f) {
                    if (enemy.isBounce()) {
                        //make the character bounce
                        character.applyImpulse(new Vec2(0, 1200));
                        character.incrementKills();
                    }
                    enemy.die();
                    this.checkComplete();
                } else {
                    if (enemy.isAlive()) {
                        character.die();
                    }
                }
            } else {
                if (enemy.isAlive()) {
                    character.die();
                }
            }
        }
        if (sensorEvent.getContactBody() instanceof Snowball || sensorEvent.getContactBody() instanceof Stone) {
            sensorEvent.getContactBody().destroy();
            if (enemy.isAlive()) {
                if (enemy instanceof Golem) {
                    if (((Golem) enemy).getArmor()) {
                        ((Golem) enemy).destroyArmor();
                    } else {
                        character.incrementKills();
                        enemy.die();
                    }
                } else {
                    character.incrementKills();
                    enemy.die();
                }
            }
            this.checkComplete();
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
