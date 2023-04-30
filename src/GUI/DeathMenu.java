package GUI;

import GUI.tools.LeaderboardTextField;
import character.Character;
import city.cs.engine.SoundClip;
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DeathMenu extends UserView {
    Font bubblegum;
    private final int points;

    private static SoundClip backgroundMusic;

    static {
        try {
            backgroundMusic = new SoundClip("data/audio/gameOverMusic.wav");
            backgroundMusic.setVolume(0.1);
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    public DeathMenu(Character character){
        super(new World(), 1000, 562);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.points = character.getCredits();
        backgroundMusic.loop();

        try {
            bubblegum = Font.createFont(Font.TRUETYPE_FONT, new File("data/fonts/Bubblegum.ttf")).deriveFont(100f);
        }catch (FontFormatException | IOException e){
            e.printStackTrace();
        }

        // Create and add the name field to the view
        LeaderboardTextField textField = new LeaderboardTextField(350, 330, points, bubblegum);
        this.add(textField);
    }

    @Override
    protected void paintForeground(Graphics2D g){
        g.setColor(Color.white);
        g.setFont(bubblegum);
        g.drawString("Game Over!", 230, 200);
        g.setFont(bubblegum.deriveFont(25f));
        g.drawString("Your score: " + points, 410, 300);
        g.setFont(bubblegum.deriveFont(15f));
        g.drawString("Enter your name then press enter to add your score to the leaderboard", 230, 400);
    }
}
