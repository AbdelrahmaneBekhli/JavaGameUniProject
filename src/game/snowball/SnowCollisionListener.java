package game.snowball;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.platform.Platform;
import game.slime.Slime;

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
