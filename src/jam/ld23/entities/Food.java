package jam.ld23.entities;

import jam.ld23.events.EventManager;
import jam.ld23.game.C;
import jam.ld23.logic.LogicManager;
import jam.ld23.physics.PhysicsManager;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Food extends Sprite {

    private Size size;
    private float speed = 0.1F;
    private static int id = 0;
    private static Random rand;
    private float rotatingSpeed;
    private int score;
    private int type;
    private Vector2f direction;
    
    //Handle reload time
    private int reload_time = (Integer) C.Logic.FOOD_RELOAD_TIME.data;
    private int reload_timer = 0;
    
    //Handle plop fade out
    private boolean dying = false;
    private int die_time = (Integer) C.Logic.FOOD_DIE_TIME.data;
    private int die_timer = 0;

    public Food(int type, Size size) {
        this.rand = new Random();
        this.type = type;
        this.size = size;
        switch (size) {
            case SMALL:
                name = C.Entities.FOOD_SMALL.name + id++;
                score = C.Scores.FOOD_SMALL.score;
                break;
            case NORMAL:
                name = C.Entities.FOOD_NORMAL.name + id++;
                score = C.Scores.FOOD_NORMAL.score;
                break;
            case BIG:
                name = C.Entities.FOOD_BIG.name + id++;
                score = C.Scores.FOOD_BIG.score;
                break;
        }
        this.group = C.Groups.FOOD.name;
        this.direction = new Vector2f(-1, 0);
        this.setTexture(C.Textures.valueOf("FOOD_" + size + "_" + type).name);
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

        //Food movement
        float x = this.getX();
        float y = this.getY();
        addPosition(direction.normalise().scale(speed * delta));

        if(!dying) {
            image.rotate(rotatingSpeed);
            //Testing Collisions with Bullets
            ArrayList<Entity> bullets = em.getEntityGroup(C.Groups.BULLETS.name);
            for (int i = 0; i < bullets.size(); i++) {
                Bullet bullet = (Bullet) bullets.get(i);
                if (pm.testCollisionsEntity(this, bullet)) {
                    lm.addScore(score);
                    em.removeEntity(bullet.getName());
                    if(size == Size.SMALL) {
                        setTexture(C.Textures.PLOP.name);
                        dying = true;
                        die_timer = 0;
                    }
                    else {
                        em.removeEntity(this.getName());
                        spawnEntities();
                    }
                }
            }
            
            //Collision with the player
            Player player = (Player) em.getEntity(C.Entities.PLAYER.name);
            if (pm.testCollisionsEntity(this, player)) {
                float xPlayer = player.getX();
                float yPlayer = player.getY();
                if (player.getX() + player.getWidth() >= x) {
                    if (player.getX()+player.getWidth()*(1-1/4F) < x) {
                        xPlayer = x - player.getWidth();
                    } else {
                        if (player.getY() < y) {
                            yPlayer = y-player.getHeight();
                        } else if (player.getY() <= y + getHeight()) {
                            yPlayer = y + getHeight();
                        }
                    }
                }
                player.setPosition(new Vector2f(xPlayer,yPlayer));
            }            

            //Testing Collisions with Teeth
            ArrayList<Entity> teeth = em.getEntityGroup(C.Groups.TEETH.name);
            for (int i = 0; i < teeth.size(); i++) {
                Entity tooth = teeth.get(i);
                if (pm.testCollisionsEntity(this, tooth)) {
                    direction = new Vector2f(direction.x, -direction.y);
                }
            }
            
            //Spawning enemies
            reload_timer += delta;
            if (reload_timer > reload_time) {
                Enemy enemy = new Enemy();
                enemy.setPosition(new Vector2f(x, y));
                em.addFutureEntity(enemy.name, enemy);
                reload_timer = 0;
            }
        }
        else {
            die_timer += delta;
            if(die_timer > die_time) {
                em.removeEntity(this.getName());
            }
        }
        
        //Remove if enemy is out of the screen
        if(outOfBounds(new Rectangle(0, 0, gc.getWidth(), gc.getHeight()))) {
            EntityManager.getInstance().removeEntity(name);
        }
    }

    public void setRotatingSpeed(float rotatingSpeed) {
        this.rotatingSpeed = rotatingSpeed;
    }

    private void spawnEntities() {
        EntityManager em = EntityManager.getInstance();
        Food f1, f2;
        switch (size) {
            case BIG:
                f1 = new Food(type, Size.NORMAL);
                f1.setPosition(new Vector2f(getX() + 25, getY() - 25));
                f1.direction = new Vector2f(-(float) Math.random(), (float) Math.random());
                f1.direction.normalise();
                em.addFutureEntity(f1.getName(), f1);
                f2 = new Food(type, Size.NORMAL);
                f2.setPosition(new Vector2f(getX() + 25, getY() + 25));
                f2.direction = new Vector2f(-(float) Math.random(), (float) Math.random());
                f2.direction.normalise();
                em.addFutureEntity(f2.getName(), f2);
                break;
            case NORMAL:
                f1 = new Food(type, Size.SMALL);
                f1.setPosition(new Vector2f(getX() + 25, getY() - 25));
                f1.direction = new Vector2f(-(float) Math.random(), (float) Math.random());
                f1.direction.normalise();
                em.addFutureEntity(f1.getName(), f1);
                f2 = new Food(type, Size.SMALL);
                f2.setPosition(new Vector2f(getX() + 25, getY() + 25));
                f2.direction = new Vector2f(-(float) Math.random(), (float) Math.random());
                f2.direction.normalise();
                em.addFutureEntity(f2.getName(), f2);
                break;
        }

    }
}
