package enemies;

import city.cs.engine.StepListener;
import city.cs.engine.Walker;
import game.levels.GameLevel;

import java.awt.event.ActionListener;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public abstract class Enemy extends Walker implements StepListener, ActionListener {
    public Enemy(GameLevel world) {
        super(world);
    }

    /**
     * sets the range of where the enemy can move.
     */
    public abstract void setRange();

    public abstract void die();
    /**
     * @return if enemy is alive.
     */
    public abstract boolean isAlive();
    /**
     * @return can player can bounce on enemy.
     */
    public abstract boolean isBounce();
}
