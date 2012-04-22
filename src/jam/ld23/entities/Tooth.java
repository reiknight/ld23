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
    
    //Handle reload time
    private int reload_time = (int) C.Logic.TOOTH_RELOAD_TIME.data;
    private int reload_timer = 0;
    
    //Tooth decay image
    private Image toothDecay;
    
    public Tooth(Vector2f position, Vector2f size) {
        super();
        setPosition(position);
        setSize(size);
        toothDecay = TextureManager.getInstance().getTexture(C.Textures.TOOTH_DECAY.name);
        
        group = C.Groups.TEETH.name;
        
        //EntityManager will not render automatically
        autoRender = false;
    }

    public boolean isDecayed() {
        return decayed;
    }

    public void setDecayed(boolean decayed) {
        this.decayed = decayed;
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
        if(decayed) {
            toothDecay.draw(getCenter().x - toothDecay.getWidth() / 2, 
                    getCenter().y - toothDecay.getHeight() / 2);
        }
    }

    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        EntityManager em = EntityManager.getInstance();
        PhysicsManager pm = PhysicsManager.getInstance();

        ArrayList<Entity> bullets = em.getEntityGroup(C.Groups.BULLETS.name);
        for(int i = 0; i < bullets.size(); i++) {
            Bullet bullet = (Bullet) bullets.get(i);
            if(pm.testCollisionsEntity(this, bullet)) {
                //TODO: esto solo es para hacer pruebas
                decayed = !decayed;
                em.removeEntity(bullet.getName());
            }
        }
        
        Player player = (Player) em.getEntity(C.Entities.PLAYER.name);
        
        //If tooth is decayed it should shoot bullets to player
        reload_timer += delta;
        if(decayed && reload_timer > reload_time) {
            Bullet bullet = new Bullet(getCenter(), player.getCenter().sub(getCenter()).normalise());
            bullet.setGroup(C.Groups.ENEMY_BULLETS.name);
            bullet.setTexture(C.Textures.ENEMY_BULLET.name);
            em.addFutureEntity(bullet.name, bullet);
            reload_timer = 0;
        }
    }
}
