package jam.ld23.entities;

import jam.ld23.events.EventManager;
import jam.ld23.game.C;
import jam.ld23.logic.LogicManager;
import jam.ld23.physics.PhysicsManager;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Enemy extends Sprite {
    
    private static int id = 0;
    private Vector2f direction;
    
    //Handle plop fade out
    private boolean dying = false;
    private int die_time = (Integer) C.Logic.ENEMY_DIE_TIME.data;
    private int die_timer = 0;
    
    public Enemy() {
        super(C.Textures.ENEMY.name);
        name =  C.Entities.ENEMY.name + id++;
        group = C.Groups.ENEMIES.name;
        direction = new Vector2f((float)Math.random()*-.5F,(float)Math.random()*-.5F);
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) {
        if(dying) {
            float factor = (float) (die_time - die_timer) / (float) die_time;
            image.setAlpha(factor);
        }
        else {
            image.setAlpha(1);
        }
        super.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        EntityManager em = EntityManager.getInstance();
        EventManager evm = EventManager.getInstance();
        PhysicsManager pm = PhysicsManager.getInstance();
        LogicManager lm = LogicManager.getInstance();

        float x = this.getX();
        float y = this.getY();
        addPosition(direction);

        if(!dying) {
            //Collision with teeth
            ArrayList<Entity> teeth = em.getEntityGroup(C.Groups.TEETH.name);
            for(int i = 0; i < teeth.size(); i++) {
                Tooth tooth = (Tooth) teeth.get(i);
                if(pm.testCollisionsEntity(this, tooth)) {
                    tooth.setDecayed(true);
                    this.explode();
                }
            }

            //Testing Collisions with Bullets
            ArrayList<Entity> bullets = em.getEntityGroup(C.Groups.BULLETS.name);
            for (int i = 0; i < bullets.size(); i++) {
                Bullet bullet = (Bullet) bullets.get(i);
                if (pm.testCollisionsEntity(this, bullet)) {
                    em.removeEntity(bullet.getName());
                    this.die();
                }
            }
        }
        else {
            die_timer += delta;
            if(die_timer > die_time) {
                em.removeEntity(this.getName());
            }
        }

        //Remove if enemy is out of the screen
        if (outOfBounds(new Rectangle(0, 0, gc.getWidth(), gc.getHeight()))) {
            EntityManager.getInstance().removeEntity(name);
        }
        
    }
    
    public void explode() {
        setTexture(C.Textures.PLOP.name);
        dying = true;
        die_timer = 0;        
    }
    
    public void die() {
        LogicManager.getInstance().addScore(C.Scores.ENEMY.score);
        this.explode();
    }


}
