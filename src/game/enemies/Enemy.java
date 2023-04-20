package game.enemies;

import city.cs.engine.Shape;
import city.cs.engine.StepListener;
import city.cs.engine.Walker;
import city.cs.engine.World;
import game.levels.GameLevel;

import java.awt.event.ActionListener;

public abstract class Enemy extends Walker implements StepListener, ActionListener {
    public Enemy(GameLevel world) {
        super(world);
    }
    public abstract void setRange();

    public abstract void die();

    public abstract boolean isAlive();

    public abstract boolean isBounce();
}
