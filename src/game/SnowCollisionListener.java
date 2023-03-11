package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class SnowCollisionListener implements CollisionListener {
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof Platform){
            collisionEvent.getReportingBody().destroy();
        }
        if(collisionEvent.getOtherBody() instanceof Slime){
            ((Slime) collisionEvent.getOtherBody()).die();
            collisionEvent.getReportingBody().destroy();
        }
    }
}
