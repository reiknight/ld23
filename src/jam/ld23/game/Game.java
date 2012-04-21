package jam.ld23.game;
 
import jam.ld23.entity.Player;
import jam.ld23.entity.interfaces.Size;
import jam.ld23.events.InputEvent;
import jam.ld23.managers.EntityManager;
import jam.ld23.managers.EventManager;
import jam.ld23.managers.PhysicsManager;
import jam.ld23.managers.TextureManager;
import org.newdawn.slick.*;
 
public class Game extends BasicGame {
    
    private float x = 400;
    private float y = 300;
    private float scale = 1.0f;
    private EntityManager em;
    private PhysicsManager pm;
    private EventManager evm;
    private TextureManager tm;
    private Player player;
    
    public Game()
    {
        super("LD23");
        em = EntityManager.getInstance();
        pm = PhysicsManager.getInstance();        
        evm = EventManager.getInstance();
        tm = TextureManager.getInstance();
    }
 
    @Override
    public void init(GameContainer gc) throws SlickException {
        //Register input events
        //Close window
        evm.addEvent("close_window", new InputEvent(InputEvent.KEYBOARD, Input.KEY_ESCAPE));
        //Player movement
        evm.addEvent("move_left", new InputEvent(InputEvent.KEYBOARD, Input.KEY_A));
        evm.addEvent("move_right", new InputEvent(InputEvent.KEYBOARD, Input.KEY_D));
        evm.addEvent("move_up", new InputEvent(InputEvent.KEYBOARD, Input.KEY_W));
        evm.addEvent("move_down", new InputEvent(InputEvent.KEYBOARD, Input.KEY_S));
        
        //Add textures
        tm.addTexture("player", "resources/player.png");
        tm.addTexture("enemy", "resources/enemy.png");
        tm.addTexture("food" + Size.SMALL, "resources/food" + Size.SMALL + ".png");
        tm.addTexture("food" + Size.NORMAL, "resources/food" + Size.NORMAL + ".png");
        tm.addTexture("food" + Size.BIG, "resources/food" + Size.BIG + ".png");
        
        //Add entities
        player = new Player();
        em.addEntity("player", player);
    }
 
    @Override
    public void update(GameContainer gc, int delta)
    {
        if(evm.isHappening(("close_window"), gc)) {
            gc.exit();
        }
        em.update(gc, delta);
    }
 
    @Override
    public void render(GameContainer gc, Graphics g) 
    {
        em.render(gc, g);
    }
 
    public static void main(String[] args) throws SlickException 
    {
         AppGameContainer app = new AppGameContainer(new Game());
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}