package game;

import game.character.CharacterController;
import game.character.Tracker;
import game.levels.*;
import javax.swing.JFrame;


public class Game{
    private GameLevel level;
    private final GameView view;
    private final CharacterController controller;

    public Game(){
        level = new Level3(this);

        //creating the world view
        view = new GameView(level, level.getCharacter(), 1000, 562);
        view.setBackground(level);
        Tracker tr2 = new Tracker(view, level);
        level.addStepListener(tr2);

        //controlling the character
        controller = new CharacterController(level.getCharacter());
        view.addKeyListener(controller);

        level.start();

        //naming the game
        final JFrame frame = new JFrame("2D platformer");
        frame.add(view);

        //set the X button to terminate the program
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
        if (level instanceof Level1 && level.isComplete()){
            level.stopMusic();
            level.stop();
            int credits = level.getCharacter().getCredits();
            level = new Level2(this);
            view.setBackground(level);
            Tracker tr = new Tracker(view, level);
            level.addStepListener(tr);
            //level now refer to the new level
            view.setWorld(level);
            view.updateLevel(level);
            controller.updateCharacter(level.getCharacter());
            view.updateCharacter(level.getCharacter());
            level.getCharacter().setCredits(credits);
            level.start();
        }
        if (level instanceof Level2 && level.isComplete()){
            level.stop();
            System.out.println("level 3 loading");
            int credits = level.getCharacter().getCredits();
            level = new Level3(this);
            Tracker tr2 = new Tracker(view, level);
            level.addStepListener(tr2);
            view.setBackground(level);
            view.setWorld(level);
            view.updateLevel(level);
            view.updateCharacter(level.getCharacter());
            controller.updateCharacter(level.getCharacter());
            level.getCharacter().setCredits(credits);
            level.start();
        }
    }

    public static void main(String[] args){
        new Game();
    }
}