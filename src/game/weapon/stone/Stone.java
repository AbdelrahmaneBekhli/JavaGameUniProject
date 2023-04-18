package game.weapon.stone;

import city.cs.engine.*;

public class Stone extends DynamicBody {

    private boolean collided;
    public Stone(World world, Shape shape) {
        super(world, shape);
        StoneCollisionListener stoneCollisions = new StoneCollisionListener();
        this.addCollisionListener(stoneCollisions);

        SolidFixture fixture = new SolidFixture(this, shape);

        BodyImage stoneImage = new BodyImage("data/weapons/stone.png", 0.7f);
        this.addImage(stoneImage);
        this.setGravityScale(3);
        fixture.setRestitution(0.5f);

    }

    public void setCollided(boolean b){
        collided = b;
    }

    public boolean isCollided() {
        return collided;
    }
}
