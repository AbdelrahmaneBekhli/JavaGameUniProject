package GUI;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class CircularButton extends JButton {

    public CircularButton(int xPos, int yPos, int radius, String image, String hoverImage) {
        Image originalImage = new ImageIcon(image).getImage();
        Image hoverOverImage = new ImageIcon(hoverImage).getImage();
        this.setBounds(xPos, yPos, radius, radius);
        //button properties
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setIcon(new ImageIcon(originalImage));
        this.setRolloverIcon(new ImageIcon(hoverOverImage));
    }

    //change border shape to circle
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        //g2.drawOval(0, 0, radius, radius);
        g2.dispose();
    }

    public boolean contains(int x, int y) {
        int radius = Math.min(getWidth(), getHeight()) / 2;
        return Point2D.distance(x, y, (double) getWidth() /2, (double) getHeight() /2) < radius;
    }
}