package jam.ld23.entity;

import jam.ld23.entity.interfaces.GameMode;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tests.xml.Entity;

public class Player extends Entity {

    //Characteristics
    private int life;
    private int bombs;
    private int continues;
    
    //Position
    private float x;
    private float y;
    
    public Player(GameMode g) {
        this.life = g.getLife();
        this.bombs = g.getBombs();
        this.continues = g.getContinues();
    }
    
    public Player() {
        this(GameMode.NORMAL_MODE);
    }
    
    public Rectangle getRectangle() {
        return new Rectangle(x-10, y-10, 20, 20);
    }
    
}
