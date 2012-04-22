package jam.ld23.entity;

import jam.ld23.managers.TextureManager;
import java.io.Serializable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Sprite extends Entity implements Serializable {
    protected transient Image image;
    
    public Sprite() { 
    }
    
    public Sprite(String name) {
        image = TextureManager.getInstance().getTexture(name);
        
        // Grab image size
        w = image.getWidth();
        h = image.getHeight();
    }
    
    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
        image.draw(x, y);
    }
    
}
