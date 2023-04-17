package game;

import city.cs.engine.*;
import game.character.Character;
import game.levels.GameLevel;
import game.levels.Level1;
import game.levels.Level2;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView{

    private Image background;
    private final Character character;

    private GameLevel world;
    public GameView(GameLevel world, Character character, int width, int height) {

        super(world, width, height);
        this.character = character;
        this.world = world;

    }
    public void setBackground(GameLevel level){
        background = new ImageIcon(level.getBackground()).getImage();
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
        Image slimeImage = new ImageIcon("data/enemy/slime.png").getImage();

        String coins = ": " + character.getCredits();
        String kills = ": " + character.getKills();
        g.drawImage(coinImage,10,10,null, this);
        g.drawImage(slimeImage, 12, 42, null, this);
        g.drawString(coins, 40,33);
        g.drawString(kills, 40,64);
        //Game update
        Font endFont = new Font("Arial", Font.BOLD, 100);
        g.setFont(endFont);
        String gameOver = "Game Over!";
        if (!(character.isAlive())) {
            g.drawString(gameOver, 200, 290);
        }
    }
}
