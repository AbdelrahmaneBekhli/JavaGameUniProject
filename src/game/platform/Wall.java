package game.platform;

import city.cs.engine.BodyImage;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import game.levels.GameLevel;
import org.jbox2d.common.Vec2;

public class Wall extends StaticBody {

    public Wall(GameLevel level, Shape s, float positionX, float positionY) {
        super(level, s);
        BodyImage transparant = new BodyImage("data/tiles/transparant.png");
        this.setPosition(new Vec2(positionX, positionY));
        this.addImage(transparant);
    }
}
