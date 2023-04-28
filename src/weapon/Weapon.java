package weapon;

import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import game.levels.GameLevel;

public abstract class Weapon extends DynamicBody {
    public Weapon(GameLevel w, Shape s) {
        super(w, s);
    }
}
