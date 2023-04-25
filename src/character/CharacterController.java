package character;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CharacterController implements KeyListener {

    private Character character;
    private boolean shoot = true;

    public CharacterController(Character sprite1){
        this.character = sprite1;
    }

    public void updateCharacter(Character c){
        character = c;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(character.isAlive()) {
            if (code == KeyEvent.VK_A) {
                character.removeAllImages();
                character.addImage(character.getRun_left());
                character.startWalking(character.getSpeed() * -1);
                character.setFacing("left");
            }
            if (code == KeyEvent.VK_D) {
                if (character.isAlive()) {
                    character.removeAllImages();
                    character.addImage(character.getRun_right());
                    character.startWalking(character.getSpeed());
                    character.setFacing("right");
                }
            }
            if (code == KeyEvent.VK_W) {
                character.jump(30);
            }

            if (code == KeyEvent.VK_SPACE) {
                if(shoot) {
                    character.shoot();
                    shoot = false;
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(character.isAlive()) {
            if (code == KeyEvent.VK_A) {
                character.removeAllImages();
                character.addImage(character.getIdle_left());
                character.startWalking(0);
                character.setFacing("left");
            }
            if (code == KeyEvent.VK_D) {
                character.removeAllImages();
                character.addImage(character.getIdle_right());
                character.startWalking(0);
                character.setFacing("right");
            }
            if (code == KeyEvent.VK_SPACE){
                shoot = true;
            }
        }
    }
}
