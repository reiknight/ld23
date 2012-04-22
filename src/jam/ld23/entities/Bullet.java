package jam.ld23.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

public class Bullet extends Sprite {
    private static int id = 0;
    private Vector2f direction;
    private float speed = (float) 0.1;
    
    public Bullet(Vector2f position, Vector2f direction) {
        super("bullet");
        name = "bullet_" + id++;
        group = "bullets";
        
        x = position.x;
        y = position.y;
        this.direction = direction;
    }
    
    public void update(GameContainer gc, int delta) {
        x += direction.x * delta;
        y += direction.y * delta;
    }
}
