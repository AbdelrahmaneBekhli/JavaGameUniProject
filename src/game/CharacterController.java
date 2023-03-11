package game;

import city.cs.engine.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CharacterController implements KeyListener {

    private final Character character;
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
            BodyImage image = new BodyImage("data/player/run_left.gif", 2.35f);
            character.removeAllImages();
            character.addImage(image);
            character.startWalking(-5);
            character.setFacing("left");
        }

        if(code == KeyEvent.VK_D){
            BodyImage image = new BodyImage("data/player/run_right.gif", 2.35f);
            character.removeAllImages();
            character.addImage(image);
            character.startWalking(5);
            character.setFacing("right");
        }

        if(code == KeyEvent.VK_W){
            character.jump(22);
        }

        if(code == KeyEvent.VK_SPACE){
            character.shoot();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_A){
            BodyImage image = new BodyImage("data/player/idle_left.gif", 2.35f);
            character.removeAllImages();
            character.addImage(image);
            character.startWalking(0);
            character.setFacing("left");
        }
        if (code == KeyEvent.VK_D){
            BodyImage image = new BodyImage("data/player/idle_right.gif", 2.35f);
            character.removeAllImages();
            character.addImage(image);
            character.startWalking(0);
            character.setFacing("right");
        }

    }
}
