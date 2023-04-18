package game.weapon.snowball;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.platform.Platform;
import game.platform.Wall;

public class SnowCollisionListener implements CollisionListener {
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof Platform || collisionEvent.getOtherBody() instanceof Wall){
            collisionEvent.getReportingBody().destroy();
        }
    }
}
