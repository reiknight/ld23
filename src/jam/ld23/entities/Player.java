package jam.ld23.entities;

import jam.ld23.events.EventConstantSheet;
import jam.ld23.events.EventManager;
import jam.ld23.game.GameMode;
import jam.ld23.sounds.SoundManager;
import java.io.Serializable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Player extends Sprite implements Serializable, EntityConstantSheet,EventConstantSheet {

    //Characteristics
    private int life;
    private int bombs;
    private int continues;
    
    //Player speed
    private float speed = (float) 0.5;
    
    //Constructor with a Game Mode
    public Player(GameMode g) throws SlickException {
        super(EntityConstantSheet.PLAYER);
        
        //Characteristics taken from the game mode
        this.life = g.getLife();
        this.bombs = g.getBombs();
        this.continues = g.getContinues();
        
        //Rectangle for the 
        this.r = new Rectangle(x-10,y-10,20,20);
    }
    
    //Default constructor with Normal Mode
    public Player() throws SlickException {
        this(GameMode.NORMAL_MODE);
    }
    
    public void setState(Player pc) {
        this.bombs = pc.bombs;
        this.continues = pc.continues;
        this.life = pc.life;
        this.x = pc.x;
        this.y = pc.y;
    }
    
    @Override
    public void update(GameContainer gc, int delta) {
        EntityManager em = EntityManager.getInstance();
        EventManager evm = EventManager.getInstance();
        SoundManager sm = SoundManager.getInstance();
        
        //Player movement
        if(evm.isHappening(MOVE_LEFT, gc)) {
            x -= speed * delta;
        }
        if(evm.isHappening(MOVE_RIGHT, gc)) {
            x += speed * delta;
        }
        if(evm.isHappening(MOVE_UP, gc)) {
            y -= speed * delta;
        }
        if(evm.isHappening(MOVE_DOWN, gc)) {
            y += speed * delta;
        }
        
        //Player actions
        if(evm.isHappening(FIRE, gc)) {
            //sm.playSound("fire");
            CrossHair crosshair = (CrossHair)em.getEntity("crosshair");
            //Shot a bullet from player center to croosshair direction
            Bullet bullet = new Bullet(new Vector2f(x + w/2, y + h/2),
                    new Vector2f((crosshair.x  + (crosshair.w / 2)) - (x + (w / 2)), 
                    (crosshair.y + (crosshair.h / 2)) - (y + (h / 2))).normalise());
            em.addEntity(bullet.name, bullet);
        }
            
        super.update(gc, delta);
    }    
}
