package jam.ld23.entities;

import jam.ld23.game.C;
import jam.ld23.logic.LogicManager;
import jam.ld23.physics.PhysicsManager;
import infinitedog.infinite.textures.TextureManager;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class Tooth extends Sprite {
    
    //A boolean which said if the tooth is decayed or not (has enemy tourret)
    private boolean decayed;
    
    //Handle reload time
    private int reload_time = (Integer) C.Logic.TOOTH_RELOAD_TIME.data+(int)(Math.random()-.5);
    private int reload_timer = 0;
    
    //Tooth decay image
    private Image toothDecay;
    
    //Handle plop fade out
    private boolean dying = false;
    private int die_time = (Integer) C.Logic.TOOTH_DECAY_DIE_TIME.data;
    private int die_timer = 0;
    
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
        if(dying) {
            float factor = (float) (die_time - die_timer) / (float) die_time;
            toothDecay.setAlpha(factor);
        }
        else {
            toothDecay.setAlpha(1);
        }
        if(decayed) {
            if(getY() < C.SCREEN_HEIGHT/2) {
                toothDecay.draw(getCenter().x - toothDecay.getWidth() / 2,
                        getY() + getHeight() - toothDecay.getHeight());
            }
            else {
                toothDecay.draw(getCenter().x - toothDecay.getWidth() / 2,
                        getY());
            }
        }
    }

    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        EntityManager em = EntityManager.getInstance();
        PhysicsManager pm = PhysicsManager.getInstance();
        LogicManager lm = LogicManager.getInstance();

        if(dying) {
            toothDecay = TextureManager.getInstance().getTexture(C.Textures.TOOTH_DECAY_DEAD.name);
            die_timer += delta;
            if(die_timer > die_time) {
                decayed = false;
                dying = false;
            }
        }
        else {
            toothDecay = TextureManager.getInstance().getTexture(C.Textures.TOOTH_DECAY.name);
            ArrayList<Entity> bullets = em.getEntityGroup(C.Groups.BULLETS.name);
            for(int i = 0; i < bullets.size(); i++) {
                Bullet bullet = (Bullet) bullets.get(i);
                if(pm.testCollisionsEntity(this, bullet)) {
                    //TODO: esto solo es para hacer pruebas
                    if(decayed) {
                        this.die();
                    }
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
    
    public void die() {
        LogicManager.getInstance().addScore(C.Scores.ENEMY_TOOTH.score);
        dying = true;
        die_timer = 0;        
    }
}
