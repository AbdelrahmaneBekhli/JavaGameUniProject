package game;

import city.cs.engine.*;

public class Coin extends StaticBody{
    private static final Shape CoinShape = new BoxShape(0.5f,0.5f);
    private static final BodyImage CoinImage = new BodyImage("data/coin.gif", 1);

    public Coin(GameWorld world){
        super(world, CoinShape);
        GhostlyFixture fixture = new GhostlyFixture(this, CoinShape);
        this.addImage(CoinImage);

    }


}
