package character;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.JumpPad;
import org.jbox2d.common.Vec2;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */

public class CharacterCollisionListener implements CollisionListener {
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof JumpPad jumpPad){
            Character character = (Character) collisionEvent.getReportingBody();
            //if character was on top of jump pad
            if(((int) character.getPosition().y >= ((int) jumpPad.getPosition().y + 2))){
                character.applyImpulse(new Vec2(0,550));
                jumpPad.playSound();
            }
        }
    }
}
