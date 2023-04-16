package game.character;

import city.cs.engine.*;
import game.character.Character;

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
                BodyImage image = new BodyImage("data/player/run_left.gif", 2.35f);
                character.removeAllImages();
                character.addImage(image);
                character.startWalking(-8);
                character.setFacing("left");
            }
            if (code == KeyEvent.VK_D) {
                if (character.isAlive()) {
                    BodyImage image = new BodyImage("data/player/run_right.gif", 2.35f);
                    character.removeAllImages();
                    character.addImage(image);
                    character.startWalking(8);
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
                BodyImage image = new BodyImage("data/player/idle_left.gif", 2.35f);
                character.removeAllImages();
                character.addImage(image);
                character.startWalking(0);
                character.setFacing("left");
            }
            if (code == KeyEvent.VK_D) {
                BodyImage image = new BodyImage("data/player/idle_right.gif", 2.35f);
                character.removeAllImages();
                character.addImage(image);
                character.startWalking(0);
                character.setFacing("right");
            }
            if (code == KeyEvent.VK_SPACE){
                shoot = true;
            }
        }
    }
}
