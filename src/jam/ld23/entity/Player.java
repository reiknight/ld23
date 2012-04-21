package jam.ld23.entity;

import jam.ld23.entity.interfaces.GameMode;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Sprite {

    //Characteristics
    private int life;
    private int bombs;
    private int continues;
    
    //Constructor with a Game Mode
    public Player(GameMode g) throws SlickException {
        super("resources/player.png");
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
    
    
    
}
