package jam.ld23.entities;

import jam.ld23.game.C;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

public class Bullet extends Sprite {
    private static int id = 0;
    private Vector2f direction;
    private float speed = (float) 0.1;
    
    public Bullet(Vector2f position, Vector2f direction) {
        super(C.Textures.BULLET.name);
        name = C.Entities.BULLET.name + id++;
        group = C.Groups.BULLETS.name;
        
        x = position.x;
        y = position.y;
        this.direction = direction;
    }
    
    public void update(GameContainer gc, int delta) {
        x += direction.x * delta;
        y += direction.y * delta;
    }
}
