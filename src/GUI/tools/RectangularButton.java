package GUI.tools;

import javax.swing.*;
import java.awt.*;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class RectangularButton extends JButton {
    public RectangularButton(int xPos, int yPos, int width, int height, String image, String hoverImage) {
        Image originalImage = new ImageIcon(image).getImage();
        Image hoverOverImage = new ImageIcon(hoverImage).getImage();
        //button properties
        this.setBounds(xPos, yPos, width, height);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        //adding the images on the button
        this.setIcon(new ImageIcon(originalImage));
        this.setRolloverIcon(new ImageIcon(hoverOverImage));
    }
}
