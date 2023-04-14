package game.levels;

import city.cs.engine.*;
import game.Game;
import game.character.Character;

public abstract class GameLevel extends World {
    private Character character;
    public GameLevel(Game game){
        character = new Character(this);
    }

    public Character getCharacter(){
        return character;
    }
    public abstract boolean isComplete();
}