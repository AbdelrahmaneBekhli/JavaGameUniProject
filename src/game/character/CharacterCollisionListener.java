package game.character;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.JumpPad;
import game.enemies.Spike;
import org.jbox2d.common.Vec2;

public class CharacterCollisionListener implements CollisionListener {
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof Spike){
            ((Character) collisionEvent.getReportingBody()).die();
        }
        if(collisionEvent.getOtherBody() instanceof JumpPad jumpPad){
            Character character = (Character) collisionEvent.getReportingBody();
            if(((int) character.getPosition().y >= ((int) jumpPad.getPosition().y + 2))){
                character.applyImpulse(new Vec2(0,700));
                jumpPad.playSound();
            }
        }
    }
}
