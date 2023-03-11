package game;

import city.cs.engine.UserView;
import game.character.Character;

import javax.swing.*;
import java.awt.*;
public class GameView extends UserView{

    private final Image background;
    private final Character character;
    public GameView(GameWorld world,Character character, int width, int height) {

        super(world, width, height);
        background = new ImageIcon("data/background.jpg").getImage();
        this.character = character;

    }

    @Override
    protected void paintBackground(Graphics2D image){
        image.drawImage(background, 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D image){
        Font font = new Font("Arial", Font.BOLD, 30);
        image.setFont(font);
        image.setColor(Color.BLACK);

        //Coins information
        String coins = "Coins: " + character.getCredits();
        image.drawString(coins, 0,30);
    }
}
