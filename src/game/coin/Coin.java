package game.coin;

import city.cs.engine.*;
import city.cs.engine.Sensor;
import game.character.Character;
import game.coin.sensor.CoinSensor;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Coin extends DynamicBody implements StepListener{
    private static final Shape CoinShape = new BoxShape(0.5f,0.5f);
    private static final BodyImage CoinImage = new BodyImage("data/coinAnimation.gif", 1);

    private float maxHeight;
    private float minHeight;
    private float speed = 0.03f;

    private static SoundClip coinSound;

    static {
        try {
            coinSound = new SoundClip("data/audio/coin.wav");
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Coin(World world, String direction, Character character){
        super(world);
        this.addImage(CoinImage);
        this.setGravityScale(0);
        Fixture fixture = new GhostlyFixture(this, CoinShape);
        Sensor sensor = new CoinSensor(this, CoinShape, character);
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

    @Override
    public void destroy(){
        coinSound.play();
        super.destroy();
    }
}
