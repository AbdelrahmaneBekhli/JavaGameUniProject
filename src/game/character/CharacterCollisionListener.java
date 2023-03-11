package game.character;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.slime.Slime;

public class CharacterCollisionListener implements CollisionListener {
    private final Character character;
    public CharacterCollisionListener(Character character) {
        this.character = character;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        //check if the collision is with a slime
        if(collisionEvent.getOtherBody() instanceof Slime){
            //check if the character collides with the slime from the top
            if (collisionEvent.getReportingBody().getPosition().y > collisionEvent.getOtherBody().getPosition().y + 1.5f){
                //collisionEvent.getOtherBody().destroy();
                ((Slime) collisionEvent.getOtherBody()).die();
                character.setBounce(true);

            } else {
            if (((Slime) collisionEvent.getOtherBody()).isAlive()) {
                character.die();
                }
            }
        }
    }

}
