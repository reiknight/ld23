package jam.ld23.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprite extends Entity {
    private Image image;
    
    public Sprite(String name) throws SlickException {
        image = new Image(name);
    }
    
    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) {
        image.draw(x, y);
    }
    
}
