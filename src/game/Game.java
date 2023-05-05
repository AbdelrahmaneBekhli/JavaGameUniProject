package game;

import GUI.*;
import GUI.tools.SoundControlButton;
import character.Character;
import character.CharacterController;
import character.Tracker;
import city.cs.engine.SoundClip;
import game.levels.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class Game{
    private SoundClip gameMusic;
    private final SoundControlButton musicButton;
    private final SoundControlButton fxButton = new SoundControlButton();
    private GameLevel level;
    private GameView view;
    private final MainMenu mainMenu;
    private CharacterController controller;
    boolean completed = false;

    private int score;

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
        frame = new JFrame("Blitz Adventures");

        //set the X button to terminate the program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable((false));


        mainMenu = new MainMenu(this, musicButton);
        frame.add(mainMenu);

        frame.pack();

        //display the game
        frame.setVisible(true);

    }
    /**
     * start level 1.
     */
    public void startLevel(int coins){
        level = new Level1(this);
        musicButton.updateMusic(level.getMusic());
        //creating the world view
        view = new GameView(level, 1000, 562, this);
        view.setBackground(level);
        //controlling the character
        controller = new CharacterController(level.getCharacter());
        view.addKeyListener(controller);
        if(completed) {
            level.getCharacter().setCredits(coins);
        }

        level.start();

        //give focus to the game when clicked on the window
        GiveFocus focus = new GiveFocus(view);
        view.addMouseListener(focus);

        view.requestFocus();
        frame.add(view);
    }
    /**
     * go to next level.
     */
    public void goToNextLevel(){
        Tracker tracker;
        this.score = this.score + 200;
        if (level instanceof Level1 && level.isComplete()){
            level.stop();
            int credits = level.getCharacter().getCredits();
            level = new Level2(this);
            musicButton.updateMusic(level.getMusic());
            view.setBackground(level);
            tracker = new Tracker(view, level);
            level.addStepListener(tracker);
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
            tracker = new Tracker(view, level);
            level.addStepListener(tracker);
            view.setBackground(level);
            view.setWorld(level);
            view.updateLevel(level);
            view.updateCharacter(level.getCharacter());
            controller.updateCharacter(level.getCharacter());
            level.getCharacter().setCredits(credits);
            level.start();
        }
        if (level instanceof Level3 && level.isComplete()){
            //WinMenu winMenu = new WinMenu(this, level.getCharacter());
            view.setVisible(false);
            level.stop();
            this.winMenu();
            completed = true;
        }
    }
    /**
     * go to death menu.
     */
    public void deathMenu(){
        DeathMenu deathMenu = new DeathMenu(this, level.getCharacter());
        frame.add(deathMenu);
    }
    /**
     * go to win menu.
     */
    private void winMenu(){
        gameMusic.stop();
        WinMenu WinMenu = new WinMenu(this, level.getCharacter());
        frame.add(WinMenu);
    }
    /**
     * go to leaderboard menu.
     */
    public void leaderboardMenu() throws IOException {
        mainMenu.setVisible(false);
        Leaderboard leaderboardMenu = new Leaderboard(this);
        leaderboardMenu.add(musicButton);
        frame.add(leaderboardMenu);
    }
    /**
     * go to how to play menu.
     */
    public void HowToPlayMenu(){
        mainMenu.setVisible(false);
        HowToPlay howToPlayMenu = new HowToPlay(this);
        howToPlayMenu.add(musicButton);
        frame.add(howToPlayMenu);
    }
    /**
     * go to main menu.
     */
    public void mainMenu(boolean changemusic){
        mainMenu.setVisible(true);
        mainMenu.add(musicButton);
        if(changemusic) {
            musicButton.updateMusic(gameMusic);
        }
        frame.add(mainMenu);
    }
    /**
     * @return sound effect control button.
     */
    public SoundControlButton getfxButton(){
        return fxButton;
    }
    /**
     * @return music control button.
     */
    public SoundControlButton getMusicButton(){
        return musicButton;
    }
    /**
     * set score
     */
    public void setScore(int score){
        this.score = score;
    }
    /**
     * @return player score.
     */
    public int getScore(){
        return this.score;
    }
    /**
     * @return player
     */
    public Character getPlayer(){
        if(this.level == null){
            return null;
        } else {
            return level.getCharacter();
        }
    }
    /**
     * set game completion
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public static void main(String[] args){
        new Game();
    }
}