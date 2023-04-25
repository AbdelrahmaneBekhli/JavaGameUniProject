package game.enemies;

import city.cs.engine.*;
import game.enemies.sensor.EnemySensor;
import game.enemies.sensor.SpikeSensor;
import game.levels.GameLevel;
import org.jbox2d.common.Vec2;

public class Spike extends StaticBody {
    public Spike(GameLevel world, Vec2 position) {
        super(world);
        BodyImage spikesImage = new BodyImage("data/enemy/Golem/armorSpikes.gif", 2);
        this.addImage(spikesImage);
        this.setPosition(position);

        Shape shape = new BoxShape(1.1f,1);

        Fixture GhostlyFixture = new GhostlyFixture(this, shape);
        Sensor sensor = new SpikeSensor(this, shape);
    }
}
