package jam.ld23.entities;

import jam.ld23.events.EventManager;
import jam.ld23.game.C;
import jam.ld23.game.GameMode;
import jam.ld23.physics.PhysicsManager;
import jam.ld23.sounds.SoundManager;
import java.io.Serializable;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Player extends Sprite implements Serializable {

    //Characteristics
    private GameMode gm;
    private int life;
    private int bombs;
    private int continues;
    
    //Player speed
    private float speed = (Float) C.Logic.PLAYER_SPEED.data;
    
    private int rotation = 0;
    private transient Image originalImage;
    private boolean dead = false;
    
    //Constructor with a Game Mode
    public Player(GameMode gm) throws SlickException {
        super(C.Textures.PLAYER.name);
        
        originalImage = image.copy();
        
        //Characteristics taken from the game mode
        this.gm = gm;
    }
    
    //Default constructor with Normal Mode
    public Player() throws SlickException {
        this(GameMode.NORMAL_MODE);
    }
    
    public void setState(Player pc) {
        this.bombs = pc.bombs;
        this.continues = pc.continues;
        this.life = pc.life;
        setX(pc.getX());
        setY(pc.getY());
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
                
        EntityManager em = EntityManager.getInstance();
        EventManager evm = EventManager.getInstance();
        SoundManager sm = SoundManager.getInstance();
        PhysicsManager pm = PhysicsManager.getInstance();
        float x = getX();
        float y = getY();
        float oldX = x;
        float oldY = y;
        
        //Check if we received a bullet
        ArrayList<Entity> entitiesToCollide = em.getEntityGroup(C.Groups.ENEMY_BULLETS.name);
        entitiesToCollide.addAll(em.getEntityGroup(C.Groups.ENEMIES.name));
        for(int i = 0; i < entitiesToCollide.size(); i++) {
            Entity entityToCollide = entitiesToCollide.get(i);
            if(pm.testCollisionsEntity(this, entityToCollide)) {
                if(entityToCollide instanceof Bullet) {
                    life -= (Integer) C.Logic.ENEMY_BULLET_DAMAGE.data;
                } else if(entityToCollide instanceof Enemy) {
                    life -= (Integer) C.Logic.ENEMY_DAMAGE.data;
                }
                if(life == 0){
                    dead = true;
                }
                float scale = (float)life / (float)gm.getLife();
                image = originalImage.getScaledCopy(scale);
                setWidth(image.getWidth());
                setHeight(image.getHeight());
                em.removeEntity(entityToCollide.getName());
            }
        }
        
        //Player movement
        if(evm.isHappening(C.Events.MOVE_LEFT.name, gc)) {
            x -= speed * delta;
        }
        if(evm.isHappening(C.Events.MOVE_RIGHT.name, gc)) {
            x += speed * delta;
        }
        if(evm.isHappening(C.Events.MOVE_UP.name, gc)) {
            y -= speed * delta;
        }
        if(evm.isHappening(C.Events.MOVE_DOWN.name, gc)) {
            y += speed * delta;
        }
        
        //Check if player is swallowed
        if(x < -getWidth()){
            dead = true;
        }
        
        //Check if player is inside bounds
        if(y < C.Positions.PLAYER_LIMIT_TOP.position.y || y > C.Positions.PLAYER_LIMIT_BOTTOM.position.y) {
            //Restore y position
            y = oldY;
        }
        if(x + getWidth() > C.Positions.PLAYER_LIMIT_RIGHT.position.x) {
            //Restore y position
            x = oldX;
        }

        setPosition(new Vector2f(x, y));
        
        //Player actions
        CrossHair crosshair = (CrossHair)em.getEntity(C.Entities.CROSSHAIR.name);
        if(evm.isHappening(C.Events.FIRE.name, gc)) {
            //TODO: Play sound
            //sm.playSound(C.Sounds.FIRE.name);
            //Shot a bullet from player center to croosshair direction
            Bullet bullet = new Bullet(getCenter(), crosshair.getCenter().sub(getCenter()).normalise());
            em.addFutureEntity(bullet.name, bullet);
        }
            
        Vector2f direction = crosshair.getCenter().sub(getCenter()).normalise();
        rotation = (int) Math.toDegrees(Math.acos(new Vector2f(1, 0).dot(direction)));
        if(direction.y < 0)  {
            rotation = -rotation;
        }
    }
    
    public boolean isDead() {
        return dead;
    }
    
    public void revive() {
        this.life = gm.getLife();
        this.bombs = gm.getBombs();
        this.continues = gm.getContinues();
        this.dead = false;
        image = originalImage;
        setWidth(image.getWidth());
        setHeight(image.getHeight());
    }
}
