package game;

import GUI.tools.SoundControlButton;
import city.cs.engine.*;
import character.Character;
import game.levels.GameLevel;
import game.levels.Level3;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameView extends UserView implements ActionListener {

    private Image background;
    private Character character;

    private final SoundControlButton musicButton;
    private Timer deathScreenTimer;

    boolean startTimer = false;
    private SoundClip playerDeath;

    private final SoundControlButton fxButton;
    private GameLevel world;
    private final Game game;
    public GameView(GameLevel world, int width, int height, Game game) {
        super(world, width, height);
        this.character = world.getCharacter();
        this.world = world;
        this.game = game;
        this.musicButton = game.getMusicButton();
        this.musicButton.setPosition(940, 10);
        this.fxButton = game.getfxButton();
        this.fxButton.setPosition(895, 10);

    }
    public void setBackground(GameLevel level){
        background = new ImageIcon(level.getBackground()).getImage();
    }

    @Override
    protected void paintBackground(Graphics2D image){
        image.drawImage(background, 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g){
        this.add(musicButton);
        this.add(fxButton);
        Font coinsFont = new Font("Arial", Font.BOLD, 25);
        g.setFont(coinsFont);
        if(world instanceof Level3){
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.BLACK);
        }

        try{
            playerDeath = new SoundClip("data/audio/playerDeath.wav");
            playerDeath.setVolume(0.05);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }

        //images
        Image coinImage = new ImageIcon("data/collectables/coin/coin.png").getImage();
        Image healthImage = new ImageIcon("data/heart.png").getImage();

        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("data/fonts/Bubblegum.ttf")).deriveFont(25f);
            g.setFont(customFont);
        }catch (FontFormatException | IOException e){
            e.printStackTrace();
        }

        //coin display
        g.drawImage(coinImage,10,35,null, this);
        String coins = ": " + character.getCredits();
        g.drawString(coins, 44,58);

        //kills display
        g.drawImage(world.getEnemyPic(), world.getEnemyPicX(), 66, null, this);
        String kills = ": " + character.getKills();
        g.drawString(kills, 44,85);

        //health display
        int startingHealthPosX = 12;
        for(int i = 0; i < character.getHealth(); i++){
            g.drawImage(healthImage, startingHealthPosX, 10, null, null);
            startingHealthPosX = startingHealthPosX + 25;
        }

        //Game update
        Font endFont = new Font("Arial", Font.BOLD, 100);
        g.setFont(endFont);
        String gameOver = "Game Over!";
        if (!(character.isAlive())) {
            g.drawString(gameOver, 200, 290);
            if(!startTimer) {
                deathScreenTimer = new Timer(2200, this);
                deathScreenTimer.start();
                world.stopMusic();
                playerDeath.play();
                startTimer = true;
            }
        }
    }
    public void updateCharacter(Character character){
        this.character = character;
    }

    public void updateLevel(GameLevel level){
        this.world = level;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        deathScreenTimer.stop();
        this.setVisible(false);
        game.deathMenu();
    }
}
