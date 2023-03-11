package game;

import city.cs.engine.*;
import city.cs.engine.Sensor;
import org.jbox2d.common.Vec2;

public class Coin extends DynamicBody implements StepListener{
    private static final Shape CoinShape = new BoxShape(0.5f,0.5f);
    private static final BodyImage CoinImage = new BodyImage("data/coin.gif", 1);

    private float maxHeight;
    private float minHeight;
    private float speed = 0.03f;
    public Coin(GameWorld world, String direction, Character character){
        super(world);
        this.addImage(CoinImage);
        this.setGravityScale(0);
        Fixture fixture = new GhostlyFixture(this, CoinShape);
        Sensor sensor = new CoinSensor(this, CoinShape, this, character);
        world.addStepListener(this);
        if (direction.equals("down")){
            this.speed = this.speed * -1;
        }
    }

    public void setRange(){
        this.maxHeight = this.getPosition().y + 0.5f;
        this.minHeight = this.getPosition().y - 0.5f;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        this.setPosition(new Vec2(this.getPosition().x, this.getPosition().y + this.speed));
        if (this.getPosition().y > this.maxHeight) {
            this.speed = this.speed * -1;
        } else if(this.getPosition().y < this.minHeight) {
            this.speed = this.speed * -1;
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
