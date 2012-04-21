package jam.ld23.entity.interfaces;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface Updatable {
    public void update(GameContainer gc, int delta);
    public void render(GameContainer gc, Graphics g);
}
