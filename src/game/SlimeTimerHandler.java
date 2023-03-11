package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlimeTimerHandler implements ActionListener{
    private final Slime slime;
    public SlimeTimerHandler(Slime slime) {
        this.slime = slime;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        slime.destroy();
    }
}

