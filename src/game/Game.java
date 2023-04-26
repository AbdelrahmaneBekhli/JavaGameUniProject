package game;

import GUI.MainMenu;
import character.CharacterController;
import character.Tracker;
import com.sun.tools.javac.Main;
import game.levels.*;

import javax.swing.*;
import java.awt.*;


public class Game{
    private GameLevel level;
    private GameView view;
    private CharacterController controller;

    private final JFrame frame;

    public Game(){
        //naming the game
        frame = new JFrame("2D platformer");

        //set the X button to terminate the program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable((false));


        MainMenu mainMenu = new MainMenu(this, 1000, 562);
        frame.add(mainMenu);

        frame.pack();

        //display the game
        frame.setVisible(true);

    }

    public void startLevel(){
        level = new Level1(this);

        //creating the world view
        view = new GameView(level, level.getCharacter(), 1000, 562);
        view.setBackground(level);
        //controlling the character
        controller = new CharacterController(level.getCharacter());
        view.addKeyListener(controller);

        level.start();

        //give focus to the game when clicked on the window
        GiveFocus focus = new GiveFocus(view);
        view.addMouseListener(focus);

        view.requestFocus();
        frame.add(view);
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
            level.stopMusic();
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