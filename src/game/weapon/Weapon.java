package game.weapon;

import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.World;

public abstract class Weapon extends DynamicBody {
    public Weapon(World w, Shape s) {
        super(w, s);
    }
}
