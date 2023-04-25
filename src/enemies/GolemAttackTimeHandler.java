package enemies;

import game.levels.GameLevel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GolemAttackTimeHandler implements ActionListener {
    Golem golem;
    GameLevel world;
    Timer animationTimer;
    Timer spikeTimer;
    Timer cooldownTimer;
    Spike spike;

    GolemAttackTimeHandler(Golem golem, GameLevel world){
        this.golem = golem;
        this.world = world;
        this.startAttack();
    }

    public void startAttack(){
        if(golem.getArmor()) {
            //random time for the golem to attack
            int delay = (int) Math.floor(Math.random() * (6000 - 1000 + 1) + 1000);
            animationTimer = new Timer(delay, this);
            animationTimer.start();
        }
}
    public void stopAttack(ActionEvent e){
        golem.setAttacking(false);
        spikeTimer.stop();
        spike.destroy();
        //cooldown time for the next random attack
        int delay = (int) Math.floor(Math.random() * (8000 - 5000 + 1) + 5000);
        cooldownTimer = new Timer(delay, this::attackCooldown);
        cooldownTimer.start();
    }

    private void attackCooldown(ActionEvent e){
        cooldownTimer.stop();
        startAttack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(golem.getArmor()) {
            golem.setAttacking(true);
            animationTimer.stop();
            spike = new Spike(world, golem.getPosition());
            //animation time for the spike attack
            spikeTimer = new Timer(1550, this::stopAttack);
            spikeTimer.start();
        }
    }
}
