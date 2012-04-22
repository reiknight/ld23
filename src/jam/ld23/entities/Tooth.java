package jam.ld23.entities;

import jam.ld23.game.C;
import jam.ld23.physics.PhysicsManager;
import jam.ld23.textures.TextureManager;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class Tooth extends Sprite {
    
    //A boolean which said if the tooth is decayed or not (has enemy tourret)
    private boolean decayed;
    
    //Tooth decay image
    private Image toothDecay;
    
    public Tooth(Vector2f position, Vector2f size) {
        super();
        x = position.x;
        y = position.y;
        w = size.x;
        h = size.y;
        toothDecay = TextureManager.getInstance().getTexture(C.Textures.TOOTH_DECAY.name);
        
        group = C.Groups.TEETH.name;
    }

    public boolean isDecayed() {
        return decayed;
    }

    public void setDecayed(boolean decayed) {
        this.decayed = decayed;
    }
    
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
        if(decayed) {
            toothDecay.draw(x, y);
        }
        g.drawString("Tooth position: " + x + "," + y, 100, 180);
    }

    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        EntityManager em = EntityManager.getInstance();
        PhysicsManager pm = PhysicsManager.getInstance();

        ArrayList<Entity> bullets = em.getEntityGroup(C.Groups.BULLETS.name);
        for(int i = 0; i < bullets.size(); i++) {
            Bullet bullet = (Bullet) bullets.get(i);
            if(pm.testCollisionsEntity(this, bullet)) {
                System.out.println("COLISION!!!!");
                decayed = true;
            }
        }
    }
}
