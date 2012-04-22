package jam.ld23.entities;

import org.newdawn.slick.SlickException;

public class Food extends Sprite {
    
    private Size size;
    
    public Food() throws SlickException {
        super("food" + Size.NORMAL);
        size = Size.NORMAL;
    }
    
    public Food(Size size) throws SlickException {
        //Must be foodSmall.png, foodNormal.png or foodBig.png
        super("food" + size);
        this.size = size;
    }

}
