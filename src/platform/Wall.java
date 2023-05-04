package platform;

import city.cs.engine.BodyImage;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import game.levels.GameLevel;
import org.jbox2d.common.Vec2;
/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class Wall extends StaticBody {
    public Wall(GameLevel level, Shape s, float positionX, float positionY) {
        super(level, s);
        BodyImage transparent = new BodyImage("data/tiles/transparent.png");
        this.setPosition(new Vec2(positionX, positionY));
        this.addImage(transparent);
    }
}
