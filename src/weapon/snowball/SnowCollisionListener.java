package weapon.snowball;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import platform.Platform;
import platform.Wall;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class SnowCollisionListener implements CollisionListener {
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof Platform || collisionEvent.getOtherBody() instanceof Wall){
            collisionEvent.getReportingBody().destroy();
        }
    }
}
