package jam.ld23.entities;

import jam.ld23.game.C;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class Bullet extends Sprite {
    private static int id = 0;
    private Vector2f direction;
    private float speed = (float) 0.5;
    
    public Bullet(Vector2f position, Vector2f direction) {
        super(C.Textures.BULLET.name);
        name = C.Entities.BULLET.name + id++;
        group = C.Groups.BULLETS.name;
        
        setPosition(position);
        this.direction = direction;
    }
    
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
    }
    
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        addPosition(new Vector2f(direction.x * speed * delta, direction.y * speed * delta));

        if(outOfBounds(new Rectangle(0, 0, gc.getWidth(), gc.getHeight()))) {
          EntityManager.getInstance().removeEntity(name);        
        }
    }
}
