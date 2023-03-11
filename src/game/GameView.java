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
    protected void paintForeground(Graphics2D g){
        Font coinsFont = new Font("Arial", Font.BOLD, 25);
        g.setFont(coinsFont);
        g.setColor(Color.BLACK);

        //Coins information
        Image coinImage = new ImageIcon("data/coin.png").getImage();
        String coins = ": " + character.getCredits();
        g.drawImage(coinImage,10,10,null, this);
        g.drawString(coins, 40,33);
        //Game update
        Font endFont = new Font("Arial", Font.BOLD, 100);
        g.setFont(endFont);
        String gameOver = "Game Over!";
        if (!(character.isAlive())) {
            g.drawString(gameOver, 200, 290);
        }
    }
}
