package jam.ld23.entities;

import jam.ld23.events.EventManager;
import jam.ld23.game.C;
import jam.ld23.physics.PhysicsManager;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

public class Food extends Sprite {

    private Size size;
    private float speed = 0.1F;
    private static int idBig = 0;
    private static int idNormal = 0;
    private static int idSmall = 0;
    private static Random rand;
    private float rotatingSpeed;
    private int type;
    private Vector2f direction;

    //Handle reload time
    private int reload_time = (Integer) C.Logic.FOOD_RELOAD_TIME.data;
    private int reload_timer = 0;

    //Initialize block for the two constructors
    static {
        rand = new Random();
    }
    
    {
        rotatingSpeed = (rand.nextInt(20) - 10) * .01F;
        setPosition(new Vector2f(C.SCREEN_WIDTH,C.Positions.PLAYER_LIMIT_TOP.position.y + rand.nextFloat() * (C.Positions.PLAYER_LIMIT_BOTTOM.position.y - C.Positions.PLAYER_LIMIT_TOP.position.y + this.getWidth())));
        group = C.Groups.FOOD.name;
        direction = new Vector2f(-1,0);
    }

    public Food() {
        type = rand.nextInt(3);
        String texture = C.Textures.valueOf(C.Entities.FOOD_NORMAL.name + type).name;
        size = Size.NORMAL;
        name = C.Entities.FOOD_NORMAL.name + idNormal++;
        this.setTexture(texture);
    }

    public Food(Size size) {
        this(size,rand.nextInt(3));
    }
    
    public Food(Size size, int type) {
        this.type = type;
        String texture = C.Textures.valueOf("FOOD_" + size + "_" + type).name;
        switch (size) {
            case SMALL:
                name = C.Entities.FOOD_SMALL.name + idSmall++;
                break;
            case NORMAL:
                name = C.Entities.FOOD_NORMAL.name + idNormal++;
                break;
            case BIG:
                name = C.Entities.FOOD_BIG.name + idBig++;
                break;
        }
        this.size = size;
        this.setTexture(texture);
    }
    
    

    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        EntityManager em = EntityManager.getInstance();
        EventManager evm = EventManager.getInstance();
        PhysicsManager pm = PhysicsManager.getInstance();

        //Food movement
        float x = this.getX();
        float y = this.getY();
        addPosition(direction.normalise().scale(speed*delta));

        image.rotate(rotatingSpeed);

        //Testing Collisions with Bullets
        ArrayList<Entity> bullets = em.getEntityGroup(C.Groups.BULLETS.name);
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = (Bullet) bullets.get(i);
            if (pm.testCollisionsEntity(this, bullet)) {
                em.removeEntity(bullet.getName());
                em.removeEntity(this.getName());
                spawnEntities();
            }
        }
        
        //Testing Collisions with Teeth
        ArrayList<Entity> teeth = em.getEntityGroup(C.Groups.TEETH.name);
        for (int i = 0; i < teeth.size(); i++) {
            Entity tooth = teeth.get(i);
            if (pm.testCollisionsEntity(this, tooth)) {
                direction = new Vector2f(direction.x,-direction.y);
            }
        }

        //Collision with the player
        Player player = (Player) em.getEntity(C.Entities.PLAYER.name);
        if (pm.testCollisionsEntity(this, player)) {
            player.setPosition(new Vector2f(x - player.getWidth(), player.getY()));
        }

        //Spawning enemies
        reload_timer += delta;
        if(reload_timer > reload_time) {
            Enemy enemy = new Enemy();
            enemy.setPosition(new Vector2f(x,y));
            em.addFutureEntity(enemy.name, enemy);
            reload_timer = 0;
        }

    }

    private void spawnEntities() {
        EntityManager em = EntityManager.getInstance();
        Food f1,f2;
        switch(size) {
            case BIG:
                f1 = new Food(Size.NORMAL,type);
                f1.setPosition(new Vector2f(getX()+25,getY()-25));
                f1.direction = new Vector2f(-(float)Math.random(),(float)Math.random());
                f1.direction.normalise();
                em.addFutureEntity(f1.getName(),f1);
                f2 = new Food(Size.NORMAL,type);
                f2.setPosition(new Vector2f(getX()+25,getY()+25));
                f2.direction = new Vector2f(-(float)Math.random(),(float)Math.random());
                f2.direction.normalise();
                em.addFutureEntity(f2.getName(),f2);
                break;
            case NORMAL:
                f1 = new Food(Size.SMALL,type);
                f1.setPosition(new Vector2f(getX()+25,getY()-25));
                f1.direction = new Vector2f(-(float)Math.random(),(float)Math.random());
                f1.direction.normalise();
                em.addFutureEntity(f1.getName(),f1);
                f2 = new Food(Size.SMALL,type);
                f2.setPosition(new Vector2f(getX()+25,getY()+25));
                f2.direction = new Vector2f(-(float)Math.random(),(float)Math.random());
                f2.direction.normalise();
                em.addFutureEntity(f2.getName(),f2);
                break;
        }
        
    }
}
