package enemies.sensor;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import game.Game;
import enemies.Enemy;
import enemies.Golem;
import enemies.Slime;
import game.portal.Portal;
import character.Character;
import game.levels.GameLevel;
import weapon.Weapon;
import org.jbox2d.common.Vec2;

public class EnemySensorListener implements SensorListener {
    private final Character character;
    private final Enemy enemy;
    private final GameLevel world;
    private final Game game;

    boolean damagePlayer = true;
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
                    if (enemy.isAlive() && damagePlayer) {
                        character.decreaseHealth();
                        this.damagePlayer = false;
                    }
                }
            } else {
                if (enemy.isAlive() && damagePlayer) {
                    if(!(enemy instanceof Golem golem)) {
                        character.decreaseHealth();
                        this.damagePlayer = false;
                    } else{
                        if(!(golem.getAttacking())){
                            character.decreaseHealth();
                            this.damagePlayer = false;
                        }
                    }
                }
            }
        }
        if (sensorEvent.getContactBody() instanceof Weapon) {
            sensorEvent.getContactBody().destroy();
            if (enemy.isAlive()) {
                if (enemy instanceof Golem golem) {
                    if(!(golem.getAttacking())) {
                        if (golem.getArmor()) {
                            golem.destroyArmor();
                        } else {
                            character.incrementKills();
                            golem.die();
                        }
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
        if(sensorEvent.getContactBody() instanceof Character){
            this.damagePlayer = true;
        }
    }
}
