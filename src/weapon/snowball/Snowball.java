package weapon.snowball;

import city.cs.engine.BodyImage;
import city.cs.engine.Shape;
import game.levels.GameLevel;
import weapon.Weapon;

public class Snowball extends Weapon {
    public Snowball(GameLevel world, Shape shape) {
        super(world, shape);
        SnowCollisionListener SnowCollisions = new SnowCollisionListener();
        this.addCollisionListener(SnowCollisions);

        BodyImage snowballImage = new BodyImage("data/weapons/snowball.png", 0.7f);
        this.addImage(snowballImage);
        this.setGravityScale(3);
    }
}
