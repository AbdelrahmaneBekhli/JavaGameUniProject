package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CharacterController implements KeyListener {

    private Character character;

    public CharacterController(Character sprite1){
        this.character = sprite1;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_A){
            character.startWalking(-5);
        }
        if(code == KeyEvent.VK_D){
            character.startWalking(5);
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_A){
            character.startWalking(0);
        }
        if (code == KeyEvent.VK_D){
            character.startWalking(0);
        }

    }
}
