package game.platform;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Platform extends StaticBody {
    public Platform(World world, Shape shape, float x, float y, String type) {
        super(world, shape);
        this.setPosition(new Vec2(x, y));
        this.setClipped(true);
        if(type.equals("short")) {
            BodyImage shortPlatformImage = new BodyImage("data/ShortPlatform.png", 1f);
            this.addImage(shortPlatformImage);
        }
        else if(type.equals("medium")){
            BodyImage mediumPlatformImage = new BodyImage("data/MediumPlatform.png", 1f);
            this.addImage(mediumPlatformImage);
        }
        else{
            BodyImage GroundImage = new BodyImage("data/GroundPlatform.png", 1f);
            this.addImage(GroundImage);
        }
    }
}
