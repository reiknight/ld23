package jam.ld23.entities;

import jam.ld23.events.EventManager;
import jam.ld23.game.C;
import jam.ld23.game.GameMode;
import jam.ld23.sounds.SoundManager;
import java.io.Serializable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Player extends Sprite implements Serializable {

    //Characteristics
    private int life;
    private int bombs;
    private int continues;
    
    //Player speed
    private float speed = (float) 0.5;
    
    //Constructor with a Game Mode
    public Player(GameMode g) throws SlickException {
        super(C.Textures.PLAYER.name);
        
        //Characteristics taken from the game mode
        this.life = g.getLife();
        this.bombs = g.getBombs();
        this.continues = g.getContinues();
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
    public void update(GameContainer gc, int delta) {
        EntityManager em = EntityManager.getInstance();
        EventManager evm = EventManager.getInstance();
        SoundManager sm = SoundManager.getInstance();
        float x = getX();
        float y = getY();
        
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
        
        setPosition(new Vector2f(x, y));
        
        //Player actions
        if(evm.isHappening(C.Events.FIRE.name, gc)) {
            //TODO: Play sound
            //sm.playSound(C.Sounds.FIRE.name);
            CrossHair crosshair = (CrossHair)em.getEntity(C.Entities.CROSSHAIR.name);
            //Shot a bullet from player center to croosshair direction
            Bullet bullet = new Bullet(getCenter(), crosshair.getCenter().sub(getCenter()).normalise());
            em.addFutureEntity(bullet.name, bullet);
        }
            
        super.update(gc, delta);
    }    
}
