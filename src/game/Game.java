package game;

import game.character.CharacterController;
import game.levels.*;
import javax.swing.JFrame;

public class Game{
    private GameLevel level;
    private GameView view;
    private CharacterController controller;

    public Game(){
        level = new Level1(this);

        //creating the world view
        view = new GameView(level, level.getCharacter(), 1000, 562);

        //controlling the character
        controller = new CharacterController(level.getCharacter());
        view.addKeyListener(controller);

        level.start();

        //naming the game
        final JFrame frame = new JFrame("2D platformer");
        frame.add(view);

        //set the X button to terminante the program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable((false));

        frame.pack();

        //display the game
        frame.setVisible(true);

        //give focus to the game when clicked on the window
        GiveFocus focus = new GiveFocus(view);
        view.addMouseListener(focus);

        view.requestFocus();

    }

    public void goToNextLevel(){
        if (level instanceof Level1){
            level.stop();
            level = new Level2(this);
            //level now refer to the new level
            view.setWorld(level);
            controller.updateCharacter(level.getCharacter());
            level.start();
        }
        else if (level instanceof Level2){
            System.out.println("level complete");
            System.exit(0);
        }
    }

    public static void main(String[] args){
        new Game();
    }
}