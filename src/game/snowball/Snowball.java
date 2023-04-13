package game.snowball;

import city.cs.engine.BodyImage;
import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.World;
import game.character.Character;

public class Snowball extends DynamicBody {
    public Snowball(World world, Shape shape) {
        super(world, shape);
        SnowCollisionListener SnowCollisions = new SnowCollisionListener();
        this.addCollisionListener(SnowCollisions);

        BodyImage snowballImage = new BodyImage("data/snowball.png", 0.7f);
        this.addImage(snowballImage);
    }
}
