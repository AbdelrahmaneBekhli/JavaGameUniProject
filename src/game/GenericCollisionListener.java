package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

public class GenericCollisionListener implements CollisionListener {
    private final Character character;
    public GenericCollisionListener(Character character) {
        this.character = character;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        //check if the collision is with a slime
        if(collisionEvent.getOtherBody() instanceof Slime){
            //check if the character collides with the slime from the top
            if (collisionEvent.getReportingBody().getPosition().y > collisionEvent.getOtherBody().getPosition().y + 1.5f){
                collisionEvent.getOtherBody().destroy();
                character.setBounce(true);
            } else {
                collisionEvent.getReportingBody().destroy();
            }
        }
        //check if the collision is with a coin
        if(collisionEvent.getOtherBody() instanceof Coin){
            collisionEvent.getOtherBody().destroy();
            character.incrementcredits();
            System.out.println(character.getCredits());
        }
    }

}
