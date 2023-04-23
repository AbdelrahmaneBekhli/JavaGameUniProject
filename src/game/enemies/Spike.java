package game.enemies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Spike extends StaticBody {
    public Spike(World w, Vec2 position) {
        super(w, new BoxShape(1.1f,1));
        BodyImage spikesImage = new BodyImage("data/enemy/Golem/armorSpikes.gif", 2);
        this.addImage(spikesImage);
        this.setPosition(position);
    }
}
