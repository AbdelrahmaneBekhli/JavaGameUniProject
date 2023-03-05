package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class GenericCollisionListener implements CollisionListener {

    private int counter;

    @Override
    public void collide(CollisionEvent collisionEvent) {
        counter++;
        System.out.print("Collision " + counter + ":\n" +
                "  rep. body:" + collisionEvent.getReportingBody() + "\n" +
                "  oth. body:" + collisionEvent.getOtherBody() + "\n");
    }
}
