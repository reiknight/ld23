package jam.ld23.entity;

import jam.ld23.entity.interfaces.EntityConstantSheet;
import jam.ld23.game.GameMode;
import jam.ld23.managers.EventManager;
import jam.ld23.managers.SoundManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Sprite {

    //Characteristics
    private int life;
    private int bombs;
    private int continues;
    
    //Player speed
    private float speed = 1;
    
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
    
    @Override
    public void update(GameContainer gc, int delta) {
        EventManager em = EventManager.getInstance();
        SoundManager sm = SoundManager.getInstance();
        
        //Player movement
        if(em.isHappening("move_left", gc)) {
            x -= speed * delta;
        }
        if(em.isHappening("move_right", gc)) {
            x += speed * delta;
        }
        if(em.isHappening("move_up", gc)) {
            y -= speed * delta;
        }
        if(em.isHappening("move_down", gc)) {
            y += speed * delta;
        }
        
        //Player actions
        if(em.isHappening("fire", gc)) {
            sm.playSound("fire");
        }
            
        super.update(gc, delta);
    }    
}
