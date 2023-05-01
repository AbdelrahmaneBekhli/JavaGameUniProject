package GUI;

import GUI.tools.CircularButton;
import GUI.tools.RectangularButton;
import GUI.tools.SoundControlButton;
import city.cs.engine.UserView;
import city.cs.engine.World;
import com.sun.tools.javac.Main;
import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenu extends UserView {
    public MainMenu(Game game, SoundControlButton musicButton) {
        super(new World(), 1000, 562);
        this.setLayout(null);

        RectangularButton start = new RectangularButton(350, 250, 262, 95, "data/GUI/Main Menu/StartButton.png", "data/GUI/Main Menu/StartHoverButton.png");
        RectangularButton exit = new RectangularButton(380, 350, 205, 68, "data/GUI/Main Menu/ExitButton.png", "data/GUI/Main Menu/ExitHoverButton.png");

        CircularButton leaderboard = new CircularButton(10, 20, 50, "data/GUI/Main Menu/LeaderBoardButton.png", "data/GUI/Main Menu/LeaderBoardHoverButton.png");
        CircularButton help = new CircularButton(70, 20, 50, "data/GUI/Main Menu/HelpButton.png", "data/GUI/Main Menu/HelpHoverButton.png");

        musicButton.setPosition(940, 10);


        this.add(start);
        this.add(leaderboard);
        this.add(help);
        this.add(exit);
        this.add(musicButton);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.startLevel();
                MainMenu.super.setVisible(false);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        leaderboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu.super.setVisible(false);
                try {
                    game.leaderboardMenu();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    @Override
    protected void paintBackground(Graphics2D image){
        image.drawImage(new ImageIcon("data/GUI/Main Menu/Main Menu.jpg").getImage(), 0, -50, this);
    }
}
