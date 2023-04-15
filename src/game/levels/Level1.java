package game.levels;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import game.Game;
import game.character.Character;
import game.coin.Coin;
import game.platform.Platform;
import game.slime.Slime;
import org.jbox2d.common.Vec2;

public class Level1  extends GameLevel {
    public Level1(Game game) {
        super(game);

        //ground platform
        Shape Groundshape = new BoxShape(25, 0.5f);
        Platform ground = new Platform(this, Groundshape, 0, -13.6f, "ground");


        //platform
        Shape ShortplatformShape = new BoxShape(3, 0.5f);
        Shape MediumPlatformShape = new BoxShape(6, 0.5f);


        Platform p1 = new Platform(this, ShortplatformShape, -7, -7, "short");
        Platform p2 = new Platform(this, ShortplatformShape, 4, -7, "short");
        Platform p3 = new Platform(this, MediumPlatformShape, 19, -2, "medium");
        Platform p4 = new Platform(this, MediumPlatformShape, 0, 0, "medium");
        Platform p5 = new Platform(this, MediumPlatformShape, -20, -3, "medium");
        Platform p6 = new Platform(this, ShortplatformShape, 10, 5, "short");
        Platform p7 = new Platform(this, MediumPlatformShape, -20, 5, "medium");


        //character
        getCharacter().setPosition(new Vec2(-14,-12));

        //Slime
        Slime slime1 = new Slime(this, 4, "left", getCharacter(), this, game);
        slime1.setPosition(new Vec2(-19, 6.3f));
        slime1.setRange();

        Slime slime2 = new Slime(this, 5, "left", getCharacter(), this, game);
        slime2.setPosition(new Vec2(19, -0.7f));
        slime2.setRange();

        Slime slime3 = new Slime(this, 5, "right", getCharacter(), this, game);
        slime3.setPosition(new Vec2(0, 1.3f));
        slime3.setRange();

        Slime slime4 = new Slime(this, 12, "left", getCharacter(), this, game);
        slime4.setPosition(new Vec2(10, -12.3f));
        slime4.setRange();

        //coins
        Coin coin1 = new Coin(this, "down", getCharacter());
        coin1.setPosition(new Vec2(-20, 9));
        coin1.setRange();

        Coin coin2 = new Coin(this, "up", getCharacter());
        coin2.setPosition(new Vec2(10, 7));
        coin2.setRange();

        Coin coin3 = new Coin(this, "up", getCharacter());
        coin3.setPosition(new Vec2(-20, 1));
        coin3.setRange();

        Coin coin4 = new Coin(this, "down", getCharacter());
        coin4.setPosition(new Vec2(0, 5));
        coin4.setRange();

        Coin coin5 = new Coin(this, "down", getCharacter());
        coin5.setPosition(new Vec2(16, 3));
        coin5.setRange();

        Coin coin6 = new Coin(this, "up", getCharacter());
        coin6.setPosition(new Vec2(20, -10));
        coin6.setRange();

        Coin coin7 = new Coin(this, "down", getCharacter());
        coin7.setPosition(new Vec2(-1.4f, -3));
        coin7.setRange();
    }

    @Override
    public boolean isComplete() {
        return getCharacter().getKills() == 4;
    }

    public float getPortal_x() {
        return 23;
    }

    public float getPortal_y() {
        return 1;
    }
}
