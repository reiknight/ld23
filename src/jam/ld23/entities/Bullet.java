package jam.ld23.entities;

import infinitedog.infinity.entities.EntityManager;
import infinitedog.infinity.entities.Sprite;
import jam.ld23.game.C;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Bullet extends Sprite {
    private static int id = 0;
    private Vector2f direction;
    private float speed = (Float) C.Logic.BULLET_SPEED.data;
    private int rotation;
    
    public Bullet(Vector2f position, Vector2f direction) {
        super(C.Textures.BULLET.name);
        name = C.Entities.BULLET.name + id++;
        group = C.Groups.BULLETS.name;
        
        setPosition(position);
        this.direction = direction;
        
        rotation = (int) Math.toDegrees(Math.acos(new Vector2f(1, 0).dot(direction)));
        if(direction.y < 0)  {
            rotation = -rotation;
        }
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) {
        g.pushTransform();
        image.setRotation(rotation);
        super.render(gc, g);
        g.popTransform();
    }
    
    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        addPosition(new Vector2f(direction.x * speed * delta, direction.y * speed * delta));

        if(outOfBounds(new Rectangle(0, 0, gc.getWidth(), gc.getHeight()))) {
          EntityManager.getInstance().removeEntity(name);        
        }
    }
    
    public void die() {
        EntityManager.getInstance().removeEntity(name);
    }
}
