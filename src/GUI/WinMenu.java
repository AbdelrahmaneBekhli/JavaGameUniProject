package GUI;

import GUI.tools.LeaderboardTextField;
import GUI.tools.RectangularButton;
import character.Character;
import city.cs.engine.SoundClip;
import city.cs.engine.UserView;
import city.cs.engine.World;
import game.Game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class WinMenu extends UserView {

    private Font bubblegum;

    private final int points;
    private static SoundClip backgroundMusic;

    static {
        try {
            backgroundMusic = new SoundClip("data/audio/gameWonMusic.wav");
            backgroundMusic.setVolume(0.1);
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    public WinMenu(Game game, Character character) {
        super(new World(), 1000, 562);
        this.setLayout(null);
        this.setBackground(new Color(22,36,37));
        this.setVisible(true);
        this.points = character.getCredits();
        if(game.getMusicButton().isSound()) {
            backgroundMusic.loop();
        }
        game.getMusicButton().updateMusic(backgroundMusic);
        this.add(game.getMusicButton());

        try {
            bubblegum = Font.createFont(Font.TRUETYPE_FONT, new File("data/fonts/Bubblegum.ttf")).deriveFont(100f);
        }catch (FontFormatException | IOException e){
            e.printStackTrace();
        }

        //main menu button
        RectangularButton mainMenuButton = new RectangularButton(395, 430, 200, 65, "data/GUI/mainMenuButton.png", "data/GUI/mainMenuHoverButton.png");
        this.add(mainMenuButton);

        // Create and add the text field to the view
        LeaderboardTextField textField = new LeaderboardTextField(350, 330, points, bubblegum);
        this.add(textField);


        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.mainMenu(true);
                WinMenu.super.setVisible(false);
            }
        });

    }

    @Override
    protected void paintForeground(Graphics2D g){
        g.setColor(Color.white);
        g.setFont(bubblegum);
        g.drawString("Congratulations!", 60, 200);
        g.setFont(bubblegum.deriveFont(25f));
        g.drawString("you won! your gained: " + points + " points", 330, 300);
        g.setFont(bubblegum.deriveFont(15f));
        g.drawString("Enter your name then press enter to add your score to the leaderboard", 230, 400);
    }
}
