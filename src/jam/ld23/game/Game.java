package jam.ld23.game;
 
import jam.ld23.entity.Player;
import jam.ld23.entity.interfaces.EntityConstantSheet;
import jam.ld23.events.EventConstantSheet;
import jam.ld23.events.InputEvent;
import jam.ld23.managers.EntityManager;
import jam.ld23.managers.EventManager;
import jam.ld23.managers.PhysicsManager;
import jam.ld23.managers.TextureManager;
import org.newdawn.slick.*;
 
public class Game extends BasicGame implements EntityConstantSheet,EventConstantSheet {
    
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
        evm.addEvent(CLOSE_WINDOW, new InputEvent(InputEvent.KEYBOARD, Input.KEY_ESCAPE));
        //Player movement
        evm.addEvent(MOVE_LEFT, new InputEvent(InputEvent.KEYBOARD, Input.KEY_A));
        evm.addEvent(MOVE_RIGHT, new InputEvent(InputEvent.KEYBOARD, Input.KEY_D));
        evm.addEvent(MOVE_UP, new InputEvent(InputEvent.KEYBOARD, Input.KEY_W));
        evm.addEvent(MOVE_DOWN, new InputEvent(InputEvent.KEYBOARD, Input.KEY_S));
        
        //Add textures
        tm.addTexture(PLAYER, PLAYER_RESOURCE);
//        tm.addTexture(ENEMY, ENEMY_RESOURCE);
//        tm.addTexture(FOOD_SMALL, FOOD_SMALL_RESOURCE);
//        tm.addTexture(FOOD_NORMAL, FOOD_NORMAL_RESOURCE);
//        tm.addTexture(FOOD_BIG, FOOD_BIG_RESOURCE);
        
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