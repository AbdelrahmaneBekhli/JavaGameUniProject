package GUI;

import GUI.tools.RectangularButton;
import game.Game;
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class HowToPlay extends UserView {
    private Font bubblegum;

    /**
     * array of gif gameplay of how to kill a certain enemies.
     */
    String[] helpGif = new String[]{"data/GUI/How to play/slimeHelp.gif", "data/GUI/How to play/wolfHelp.gif", "data/GUI/How to play/golemHelp.gif"};

    private int helpIndex = 0;
    public HowToPlay(Game game) {
        super(new World(), 1000, 562);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.LIGHT_GRAY);

        try {
            bubblegum = Font.createFont(Font.TRUETYPE_FONT, new File("data/fonts/Bubblegum.ttf")).deriveFont(100f);
        }catch (FontFormatException | IOException e){
            e.printStackTrace();
        }
        RectangularButton next = new RectangularButton(860, 200, 120, 192, "data/GUI/How to play/next.png", "data/GUI/How to play/nextHover.png");
        RectangularButton previous = new RectangularButton(370, 200, 120, 192, "data/GUI/How to play/previous.png", "data/GUI/How to play/previousHover.png");
        RectangularButton mainMenuButton = new RectangularButton(80, 470, 200, 65, "data/GUI/mainMenuButton.png", "data/GUI/mainMenuHoverButton.png");

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(helpIndex < helpGif.length - 1){
                    helpIndex = helpIndex + 1;
                }
            }
        });

        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(helpIndex > 0){
                    helpIndex = helpIndex - 1;
                }
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.mainMenu(false);
                HowToPlay.super.setVisible(false);
            }
        });

        this.add(next);
        this.add(previous);
        this.add(mainMenuButton);
    }

    @Override
    protected void paintForeground(Graphics2D g){
        //title
        g.setFont(bubblegum);
        g.drawString("How to play", 180, 130);

        //controls
        Image W_key = new ImageIcon("data/GUI/How to play/W_key.png").getImage();
        Image A_key = new ImageIcon("data/GUI/How to play/A_key.png").getImage();
        Image D_key = new ImageIcon("data/GUI/How to play/D_key.png").getImage();
        Image SPACE_key = new ImageIcon("data/GUI/How to play/SPACE_key.png").getImage();

        g.setFont(bubblegum.deriveFont(25f));
        g.drawString("movement", 110, 310);
        g.drawString("shoot", 135, 430);


        g.drawImage(W_key,150,200,null, this);
        g.drawImage(A_key,120,240,null, this);
        g.drawImage(D_key,180,240,null, this);
        g.drawImage(SPACE_key,130,360,null, this);

        //game logic
        Image helpGif = new ImageIcon(this.helpGif[helpIndex]).getImage();

        g.drawImage(helpGif,500,200,null, this);

        if(helpIndex == 0){
            g.drawString("kill the slime by either", 525, 440);
            g.drawString("shooting at it or jumping on it", 470, 470);
        } else if(helpIndex == 1){
            g.drawString("kill the wolf by shooting at it", 480, 440);
        } else if(helpIndex == 2){
            g.drawString("kill the golem by shooting at it", 480, 440);
            g.setFont(bubblegum.deriveFont(17f));
            g.drawString("first hit: breaks shield and increase its speed", 480, 470);
            g.drawString("second hit: kills the golem", 480, 500);
            g.drawString("note: it can't be damaged while its attacking", 480, 530);
        }



    }
}
