package game;

import city.cs.engine.SoundClip;
import game.character.CharacterController;
import game.levels.*;
import javax.swing.JFrame;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Game{
    private GameLevel level;
    private GameView view;
    private CharacterController controller;

    private SoundClip gameMusic;

    public Game(){
        level = new Level1(this);

        //creating the world view
        view = new GameView(level, level.getCharacter(), 1000, 562);

        //adding the music background
        try{
            gameMusic = new SoundClip("data/audio/MusicTrack.wav");
            gameMusic.setVolume(0.2);
            gameMusic.loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }

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
        if (level instanceof Level1 && level.isComplete()){
            level.stop();
            level = new Level2(this);

            //level now refer to the new level
            view.setWorld(level);
            controller.updateCharacter(level.getCharacter());
            level.start();
        }
        else if (level instanceof Level2 && level.isComplete()){
            System.out.println("level 3 loading");
            System.exit(0);
        }
    }

    public static void main(String[] args){
        new Game();
    }
}