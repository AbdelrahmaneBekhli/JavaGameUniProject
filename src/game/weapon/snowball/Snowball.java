package game.weapon.snowball;

import city.cs.engine.BodyImage;
import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.World;

public class Snowball extends DynamicBody {
    public Snowball(World world, Shape shape) {
        super(world, shape);
        SnowCollisionListener SnowCollisions = new SnowCollisionListener();
        this.addCollisionListener(SnowCollisions);

        BodyImage snowballImage = new BodyImage("data/weapons/snowball.png", 0.7f);
        this.addImage(snowballImage);
        this.setGravityScale(3);
    }
}
