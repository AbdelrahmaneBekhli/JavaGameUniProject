package collectables.coin;

import city.cs.engine.*;
import city.cs.engine.Sensor;
import game.levels.GameLevel;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class Coin extends DynamicBody implements StepListener{
    private static final Shape CoinShape = new BoxShape(0.5f,0.5f);
    private static final BodyImage CoinImage = new BodyImage("data/collectables/coin/coinAnimation.gif", 1);
    private GameLevel level;
    private float maxHeight;
    private float minHeight;
    private float speed = 0.03f;
    private static SoundClip coinSound;

    static {
        try {
            coinSound = new SoundClip("data/audio/coin.wav");
            coinSound.setVolume(0.1);
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Coin(GameLevel world, String direction, float posX, float posY){
        super(world);
        this.addImage(CoinImage);
        this.level = world;
        this.setGravityScale(0);
        Fixture fixture = new GhostlyFixture(this, CoinShape);
        Sensor sensor = new CoinSensor(this, CoinShape, world.getCharacter());
        world.addStepListener(this);
        this.setPosition(new Vec2(posX, posY));
        this.setRange();
        if (direction.equals("down")){
            this.speed = this.speed * -1;
        }
    }
    /**
     * sets the range for the coin
     */
    private void setRange(){
        this.maxHeight = this.getPosition().y + 0.5f;
        this.minHeight = this.getPosition().y - 0.5f;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        this.setPosition(new Vec2(this.getPosition().x, this.getPosition().y + this.speed));
        //down
        if (this.getPosition().y > this.maxHeight) {
            this.speed = this.speed * -1;
        } else if(this.getPosition().y < this.minHeight) { //up
            this.speed = this.speed * -1;
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }

    @Override
    public void destroy(){
        if(level.getGame().getfxButton().isSound()) {
            coinSound.play();
        }
        super.destroy();
    }
}
