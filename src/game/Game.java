package game;

import city.cs.engine.*;

import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicTreeUI;

public class Game{
    public Game(){
        GameWorld world = new GameWorld();

        GameView view = new GameView(world, 1000, 562);

        CharacterController controller = new CharacterController(world.getCharacter());
        view.addKeyListener(controller);

        final JFrame frame = new JFrame("2D platformer");
        frame.add(view);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);

        frame.setResizable((false));

        frame.pack();

        frame.setVisible(true);

        world.start();

        view.requestFocus();
    }

    public static void main(String[] args){
        new Game();
    }
}