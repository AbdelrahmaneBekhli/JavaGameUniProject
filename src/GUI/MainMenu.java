package GUI;

import GUI.tools.CircularButton;
import GUI.tools.RectangularButton;
import GUI.tools.SoundControlButton;
import city.cs.engine.UserView;
import city.cs.engine.World;
import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class MainMenu extends UserView {
    public MainMenu(Game game, SoundControlButton musicButton) {
        super(new World(), 1000, 562);
        this.setLayout(null);

        RectangularButton start = new RectangularButton(365, 250, 262, 95, "data/GUI/Main Menu/StartButton.png", "data/GUI/Main Menu/StartHoverButton.png");
        RectangularButton exit = new RectangularButton(395, 350, 205, 68, "data/GUI/Main Menu/ExitButton.png", "data/GUI/Main Menu/ExitHoverButton.png");

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
                if(game.getPlayer() == null){
                    game.startLevel(0);
                } else{
                    game.startLevel(game.getPlayer().getCredits());
                }
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

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu.super.setVisible(false);
                game.HowToPlayMenu();
            }
        });
    }

    @Override
    protected void paintBackground(Graphics2D image){
        image.drawImage(new ImageIcon("data/GUI/Main Menu/Main Menu.jpg").getImage(), 0, -50, this);
    }
    @Override
    protected void paintForeground(Graphics2D g){
        Image logo = new ImageIcon("data/GUI/Main Menu/logo.gif").getImage();
        g.drawImage(logo,303,40,null, this);
    }
}
