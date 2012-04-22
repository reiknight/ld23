package jam.ld23.entities;

import jam.ld23.textures.TextureManager;
import java.io.Serializable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Sprite extends Entity implements Serializable {
    protected transient Image image;
    
    public Sprite() { 
    }
    
    public Sprite(String name) {
        this.setTexture(name);
    }
    
    public void setTexture(String name) {
        this.image = TextureManager.getInstance().getTexture(name);
        
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
        if(image != null) {
            image.draw(x, y);
        }
    }
    
}
