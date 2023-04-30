package game;

import GUI.DeathMenu;
import GUI.MainMenu;
import GUI.tools.SoundControlButton;
import character.CharacterController;
import character.Tracker;
import city.cs.engine.SoundClip;
import game.levels.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;


public class Game{
    private SoundClip gameMusic;
    private final SoundControlButton musicButton;

    private final SoundControlButton fxButton = new SoundControlButton();
    private GameLevel level;
    private GameView view;
    private CharacterController controller;

    private final JFrame frame;

    public Game(){
        try{
            gameMusic = new SoundClip("data/audio/menuMusic.wav");
            gameMusic.setVolume(0.3);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }

        musicButton = new SoundControlButton(gameMusic);
        //naming the game
        frame = new JFrame("2D platformer");

        //set the X button to terminate the program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable((false));


        MainMenu mainMenu = new MainMenu(this, musicButton);
        frame.add(mainMenu);

        frame.pack();

        //display the game
        frame.setVisible(true);

    }

    public void startLevel(){
        level = new Level1(this);
        musicButton.updateMusic(level.getMusic());
        //creating the world view
        view = new GameView(level, 1000, 562, this);
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
            level.stop();
            int credits = level.getCharacter().getCredits();
            level = new Level2(this);
            musicButton.updateMusic(level.getMusic());
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
            int credits = level.getCharacter().getCredits();
            level = new Level3(this);
            musicButton.updateMusic(level.getMusic());
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

    public void deathMenu(){
        DeathMenu deathMenu = new DeathMenu(level.getCharacter());
        frame.add(deathMenu);
    }

    public SoundControlButton getfxButton(){
        return fxButton;
    }

    public SoundControlButton getMusicButton(){
        return musicButton;
    }

    public static void main(String[] args){
        new Game();
    }
}