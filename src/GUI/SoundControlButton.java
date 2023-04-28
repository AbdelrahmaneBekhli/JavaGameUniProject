package GUI;

import city.cs.engine.SoundClip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

public class SoundControlButton extends JCheckBox {
    private SoundClip music;
    private final Image[] images;

    private boolean sound = true;
    public SoundControlButton(){ //fx button
        this.images = new Image[]{new ImageIcon("data/GUI/fxMuteButton.png").getImage(), new ImageIcon("data/GUI/fxUnmuteButton.png").getImage()};
        this.setIcon(new ImageIcon(images[1]));
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SoundControlButton.super.isSelected()){
                    SoundControlButton.super.setIcon(new ImageIcon(images[0]));
                    sound = false;
                } else{
                    SoundControlButton.super.setIcon(new ImageIcon(images[1]));
                    sound = true;
                }
            }
        });

    }

    public SoundControlButton(SoundClip levelMusic){ //music button
        this.music = levelMusic;
        this.music.loop();
        this.images = new Image[]{new ImageIcon("data/GUI/musicMuteButton.png").getImage(), new ImageIcon("data/GUI/musicUnmuteButton.png").getImage()};        this.setIcon(new ImageIcon(images[1]));
        this.setIcon(new ImageIcon(images[1]));
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SoundControlButton.super.isSelected()){
                    SoundControlButton.super.setIcon(new ImageIcon(images[0]));
                    music.stop();
                    sound = false;
                } else{
                    SoundControlButton.super.setIcon(new ImageIcon(images[1]));
                    music.loop();
                    sound = true;
                }
            }
        });

    }

    public void updateMusic(SoundClip newLevelMusic){
        this.music.stop();
        this.music = newLevelMusic;
        if(sound){
            music.loop();
        }
    }

    public void setPosition(int xPos, int yPos){
        this.setBounds(xPos, yPos, 55, 50);
    }

    public boolean isSound(){
        return sound;
    }

    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        //g2.drawOval(5, 0, radius, radius); //draw border
        g2.dispose();
    }

    public boolean contains(int x, int y) {
        int radius = Math.min(getWidth(), getHeight()) / 2;
        return Point2D.distance(x, y, (double) getWidth() /2, (double) getHeight() /2) < radius;
    }
}
