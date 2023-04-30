package GUI.tools;

import GUI.txtFileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class LeaderboardTextField extends JTextField {
    private final int points;
    public LeaderboardTextField(int xPos, int yPos, int points, Font font){
        this.points = points;
        this.setBounds(xPos, yPos, 300, 40);
        this.setFont(font.deriveFont(20f));
        this.setForeground(Color.WHITE);
        this.setOpaque(false); //paint only required pixels
        this.setCaretColor(Color.WHITE); //set color of text
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        this.setHorizontalAlignment(SwingConstants.CENTER); //make the text centered
        this.addKeyListener(new LeaderboardTextField.NameFieldKeyListener());
    }

    private class NameFieldKeyListener extends KeyAdapter {
        private boolean enter = true; //state to whether key enter was pressed

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String playerName = LeaderboardTextField.super.getText();
                // if invalid name
                if (playerName.length() < 3 || playerName.length() > 15) {
                    LeaderboardTextField.super.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(Color.RED, 2),
                            BorderFactory.createEmptyBorder(5, 10, 5, 10)
                    ));
                } else {
                    LeaderboardTextField.super.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(Color.GREEN, 2),
                            BorderFactory.createEmptyBorder(5, 10, 5, 10)
                    ));
                    LeaderboardTextField.super.setEnabled(false);
                    LeaderboardTextField.super.removeKeyListener(this);
                    if(enter) {
                        enter = false;
                        //write score in leaderboard.txt
                        txtFileHandler writer = new txtFileHandler("data/leaderboard.txt");
                        try {
                            writer.writeHighScore(playerName, points);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            } else{
                LeaderboardTextField.super.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.WHITE, 2),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                enter = true;
            }
        }
    }
}
