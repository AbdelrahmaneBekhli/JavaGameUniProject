package game;

import city.cs.engine.*;

import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicTreeUI;

public class Game{
    public Game(){
        //creating the world
        GameWorld world = new GameWorld();
        //creating the world view
        GameView view = new GameView(world, 1000, 562);

        //controlling the character
        CharacterController controller = new CharacterController(world.getCharacter());
        view.addKeyListener(controller);

        //naming the game
        final JFrame frame = new JFrame("2D platformer");
        frame.add(view);

        //set the X button to terminante the program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable((false));

        frame.pack();

        //display the game
        frame.setVisible(true);

        world.start();

        //give focus to the game when clicked on the window
        GiveFocus focus = new GiveFocus(view);
        view.addMouseListener(focus);

        view.requestFocus();
    }

    public static void main(String[] args){
        new Game();
    }
}