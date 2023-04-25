package game;

import city.cs.engine.*;
import character.Character;
import game.levels.GameLevel;
import game.levels.Level3;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView{

    private Image background;
    private Character character;

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
        if(world instanceof Level3){
            g.setColor(Color.WHITE);
        }else {
            g.setColor(Color.BLACK);
        }

        //images
        Image coinImage = new ImageIcon("data/collectables/coin/coin.png").getImage();
        Image healthImage = new ImageIcon("data/heart.png").getImage();

        //coin display
        g.drawImage(coinImage,10,35,null, this);
        String coins = ": " + character.getCredits();
        g.drawString(coins, 44,58);

        //kills display
        g.drawImage(world.getEnemyPic(), world.getEnemyPicX(), 66, null, this);
        String kills = ": " + character.getKills();
        g.drawString(kills, 44,85);

        //health display
        int startingHealthPosX = 12;
        for(int i = 0; i < character.getHealth(); i++){
            g.drawImage(healthImage, startingHealthPosX, 10, null, null);
            startingHealthPosX = startingHealthPosX + 25;
        }

        //Game update
        Font endFont = new Font("Arial", Font.BOLD, 100);
        g.setFont(endFont);
        String gameOver = "Game Over!";
        if (!(character.isAlive())) {
            g.drawString(gameOver, 200, 290);
        }
    }
    public void updateCharacter(Character character){
        this.character = character;
    }

    public void updateLevel(GameLevel level){
        this.world = level;
    }
}
