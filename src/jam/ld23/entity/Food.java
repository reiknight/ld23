package jam.ld23.entity;

import jam.ld23.entity.interfaces.Size;
import org.newdawn.slick.SlickException;

public class Food extends Sprite {
    
    private Size size;
    
    public Food() throws SlickException {
        super("resources/food" + Size.NORMAL + ".png");
        size = Size.NORMAL;
    }
    
    public Food(Size size) throws SlickException {
        //Must be foodSmall.png, foodNormal.png or foodBig.png
        super("resources/food" + size + ".png");
        this.size = size;
    }

}
