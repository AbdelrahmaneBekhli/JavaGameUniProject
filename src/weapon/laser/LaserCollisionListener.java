package weapon.laser;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import character.Character;

public class LaserCollisionListener implements CollisionListener {
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(!(collisionEvent.getOtherBody() instanceof Character)) {
            collisionEvent.getReportingBody().destroy();
        }
    }
}
