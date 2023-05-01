package GUI;

import GUI.tools.RectangularButton;
import city.cs.engine.UserView;
import city.cs.engine.World;
import game.Game;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Leaderboard extends UserView {

    private Font bubblegum;
    public Leaderboard(Game game) throws IOException {
        super(new World(), 1000, 562);
        this.setVisible(true);
        this.setLayout(null);

        try {
            bubblegum = Font.createFont(Font.TRUETYPE_FONT, new File("data/fonts/Bubblegum.ttf")).deriveFont(35f);
        }catch (FontFormatException | IOException e){
            e.printStackTrace();
        }

        //main menu button
        RectangularButton mainMenuButton = new RectangularButton(390, 480, 200, 65, "data/GUI/mainMenuButton.png", "data/GUI/mainMenuHoverButton.png");


        txtFileHandler writer = new txtFileHandler("data/leaderboard.txt");
        ArrayList<Object[]> leaderboardList = writer.readScores();
        //sort the leaderboard list based on scores
        leaderboardList.sort((o1, o2) -> ((Integer) o2[1]).compareTo((Integer) o1[1]));

        //content of the leaderboard
        JPanel leaderboardPanel = new JPanel(new GridLayout(0, 2, 10, 20));

        for (Object[] array : leaderboardList) {
            JLabel nameLabel = new JLabel((String) array[0], JLabel.LEFT);
            JLabel scoreLabel = new JLabel(Integer.toString((int) array[1]), JLabel.RIGHT);
            //add padding to the scores
            scoreLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
            nameLabel.setFont(bubblegum);
            scoreLabel.setFont(bubblegum);
            nameLabel.setForeground(Color.WHITE);
            scoreLabel.setForeground(Color.WHITE);
            leaderboardPanel.add(nameLabel);
            leaderboardPanel.add(scoreLabel);
        }

        JScrollPane scrollPane = new JScrollPane(leaderboardPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.setBounds(50, 200, 900, 280);

        //making the scroll pane transparent
        scrollPane.setBorder(null);
        leaderboardPanel.setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.mainMenu(false);
                Leaderboard.super.setVisible(false);
            }
        });

        //make the vertical scrollbar transparent but only have a thumb that's in image
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Image imageThumb;
                try {
                    imageThumb = ImageIO.read(new File("data/GUI/scrollThumb.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.translate(thumbBounds.x, thumbBounds.y);
                //scales the image
                AffineTransform transform = AffineTransform.getScaleInstance((double)thumbBounds.width/imageThumb.getWidth(null),(double)thumbBounds.height/imageThumb.getHeight(null));
                ((Graphics2D)g).drawImage(imageThumb, transform, null);
                g.translate( -thumbBounds.x, -thumbBounds.y );
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                Image imageTrack;
                try {
                    imageTrack = ImageIO.read(new File("data/tiles/transparent.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //removing the scrollbar
                g.translate(trackBounds.x, trackBounds.y);
                ((Graphics2D)g).drawImage(imageTrack,AffineTransform.getScaleInstance(1,(double)trackBounds.height/imageTrack.getHeight(null)),null);
                g.translate( -trackBounds.x, -trackBounds.y );
            }
            //removing the buttons
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }
        });

        this.add(scrollPane);
        this.add(mainMenuButton);
    }

    @Override
    protected void paintForeground(Graphics2D g){
        g.setColor(Color.WHITE);
        g.setFont(bubblegum.deriveFont(100f));
        g.drawString("leaderboard", 180, 130);
    }

    @Override
    protected void paintBackground(Graphics2D image){
        image.drawImage(new ImageIcon("data/GUI/leaderboard.jpg").getImage(), 0, 0, this);
    }
}
