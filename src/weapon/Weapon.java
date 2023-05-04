package weapon;

import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import game.levels.GameLevel;
/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public abstract class Weapon extends DynamicBody {
    public Weapon(GameLevel w, Shape s) {
        super(w, s);
    }
}
