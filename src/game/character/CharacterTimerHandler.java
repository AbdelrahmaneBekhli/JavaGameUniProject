package game.character;

import game.character.Character;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class CharacterTimerHandler implements ActionListener {
    private final Character character;

    public CharacterTimerHandler(Character character) {
        this.character = character;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        character.destroy();
    }
}
