package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class GenericCollisionListener implements CollisionListener {

    @Override
    public void collide(CollisionEvent collisionEvent) {
        //check if the collision is with a slime
        if(collisionEvent.getOtherBody() instanceof Slime){
            //check if the character collides with the slime from the top
            if (collisionEvent.getReportingBody().getPosition().y > collisionEvent.getOtherBody().getPosition().y + 1.5f){
                collisionEvent.getOtherBody().destroy();
            } else {
                collisionEvent.getReportingBody().destroy();
            }
        }
    }

}
