package game;

import city.cs.engine.*;
import game.character.Character;
import org.jbox2d.common.Vec2;

public class JumpPad extends StaticBody {
    public JumpPad(World w, float xPos, float yPos) {
        super(w, new BoxShape(0.7f, 0.5f));
        this.addImage(new BodyImage("data/jumpPad/jumpPad.png"));
        this.setPosition(new Vec2(xPos, yPos));
    }
}
