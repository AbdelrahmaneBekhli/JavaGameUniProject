package game.character;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.enemies.Spike;

public class CharacterCollisionListener implements CollisionListener {
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof Spike){
            ((Character) collisionEvent.getReportingBody()).die();
        }
    }
}
