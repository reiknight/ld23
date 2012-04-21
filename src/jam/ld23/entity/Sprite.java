package jam.ld23.entity;

import jam.ld23.managers.TextureManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprite extends Entity {
    private Image image;
    
    public Sprite(String name) {
        image = TextureManager.getInstance().getTexture(name);
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
