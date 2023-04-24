package game.platform;

import city.cs.engine.*;
import game.levels.GameLevel;
import org.jbox2d.common.Vec2;

public class Platform extends StaticBody {
    public Platform(GameLevel level, Shape shape, float x, float y, String type) {
        super(level, shape);
        this.setPosition(new Vec2(x, y));
        this.setClipped(true);
        switch (type) {
            case "short" -> {
                BodyImage shortPlatformImage = new BodyImage(level.getShortTiles(), 1f);
                this.addImage(shortPlatformImage);
            }
            case "medium" -> {
                BodyImage mediumPlatformImage = new BodyImage(level.getMediumTiles(), 1f);
                this.addImage(mediumPlatformImage);
            }
            case "long" -> {
                BodyImage longPlatformImage = new BodyImage(level.getLongTiles(), 1f);
                this.addImage(longPlatformImage);
            }
            default -> {
                BodyImage extraLongPlatformImage = new BodyImage(level.getExtraLongTiles(), 1f);
                this.addImage(extraLongPlatformImage);
            }
        }
    }
}
