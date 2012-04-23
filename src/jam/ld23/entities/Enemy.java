package jam.ld23.entities;

import jam.ld23.events.EventManager;
import jam.ld23.game.C;
import jam.ld23.logic.LogicManager;
import jam.ld23.physics.PhysicsManager;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Enemy extends Sprite {
    
    private static int id = 0;
    private Vector2f direction;
    
    public Enemy() {
        super(C.Textures.ENEMY.name);
        name =  C.Entities.ENEMY.name + id++;
        group = C.Groups.ENEMIES.name;
        direction = new Vector2f((float)Math.random()*-.5F,(float)Math.random()*-.5F);
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

        //Collision with teeth
        ArrayList<Entity> teeth = em.getEntityGroup(C.Groups.TEETH.name);
        for(int i = 0; i < teeth.size(); i++) {
            Tooth tooth = (Tooth) teeth.get(i);
            if(pm.testCollisionsEntity(this, tooth)) {
                if(Math.random() < lm.getGameMode().getBaseDecaySpawnProbability()) {
                    tooth.setDecayed(true);
                }
                em.removeEntity(this.getName());
            }
        }

        //Testing Collisions with Bullets
        ArrayList<Entity> bullets = em.getEntityGroup(C.Groups.BULLETS.name);
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = (Bullet) bullets.get(i);
            if (pm.testCollisionsEntity(this, bullet)) {
                em.removeEntity(bullet.getName());
                em.removeEntity(this.getName());
                lm.addScore(C.Scores.ENEMY.score);
            }
        }

        //Remove if enemy is out of the screen
        if (outOfBounds(new Rectangle(0, 0, gc.getWidth(), gc.getHeight()))) {
            EntityManager.getInstance().removeEntity(name);
        }
        
    }


}
