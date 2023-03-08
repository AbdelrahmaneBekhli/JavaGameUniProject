package game;

import city.cs.engine.*;

public class Slime extends Walker implements StepListener{
    private static final Shape SlimeShape = new BoxShape(1,0.75f);
    private static final BodyImage rightImage = new BodyImage("data/enemy/slime_right.gif", 1.5f);
    private static final BodyImage leftImage = new BodyImage("data/enemy/slime_left.gif", 1.5f);

    private float left;
    private float right;

    private final float range;


    public Slime(World world, float range, String facing) {
        super(world, SlimeShape);
        world.addStepListener(this);

        this.range = range;

        if (facing.equals("right")){
            this.addImage(rightImage);
            this.startWalking(3);
        } else {
            this.addImage(leftImage);
            this.startWalking(-3);
        }
    }

    public void setRange(){
        //setting up the range for each direction
        this.left = this.getPosition().x - range;
        this.right = this.getPosition().x + range;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        //changing speed and gif when the range is reached
        if(getPosition().x > right){
            this.removeAllImages();
            this.addImage(leftImage);
            this.startWalking(-3);
        }
        if(getPosition().x < left){
            this.removeAllImages();
            this.addImage(rightImage);
            this.startWalking(3);
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
