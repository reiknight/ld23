package jam.ld23.game;
 
import jam.ld23.entities.CrossHair;
import jam.ld23.entities.EntityConstantSheet;
import jam.ld23.entities.EntityManager;
import jam.ld23.entities.Player;
import jam.ld23.events.EventConstantSheet;
import jam.ld23.events.EventManager;
import jam.ld23.events.InputEvent;
import jam.ld23.physics.PhysicsManager;
import jam.ld23.sounds.SoundManager;
import jam.ld23.textures.TextureManager;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
 
public class Game extends BasicGame implements EntityConstantSheet,EventConstantSheet {
    private EntityManager em;
    private PhysicsManager pm;
    private EventManager evm;
    private TextureManager tm;
    private SoundManager sm;
    private SaveManager svm;
    private GameOptions go;
    private Image mouth;
    
    public Game()
    {
        super("LD23");
        em = EntityManager.getInstance();
        pm = PhysicsManager.getInstance();        
        evm = EventManager.getInstance();
        tm = TextureManager.getInstance();
        sm = SoundManager.getInstance();
        svm = SaveManager.getInstance();
        go = GameOptions.getInstance();
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
        //Player actions
        evm.addEvent(FIRE, new InputEvent(InputEvent.MOUSE_CLICK, Input.MOUSE_LEFT_BUTTON));
        evm.addEvent(CROSSHAIR_MOVED, new InputEvent(InputEvent.MOUSE_MOVE, new Rectangle(0, 0, 800, 600)));
        evm.addEvent(SAVE_GAME, new InputEvent(InputEvent.KEYBOARD,Input.KEY_R));
        evm.addEvent(LOAD_GAME, new InputEvent(InputEvent.KEYBOARD,Input.KEY_T));
        
        //Add textures
        tm.addTexture(PLAYER, PLAYER_RESOURCE);
        tm.addTexture("mouth", "resources/mouth.jpg");
        tm.addTexture("crosshair", "resources/crosshair.png");
//        tm.addTexture(ENEMY, ENEMY_RESOURCE);
//        tm.addTexture(FOOD_SMALL, FOOD_SMALL_RESOURCE);
//        tm.addTexture(FOOD_NORMAL, FOOD_NORMAL_RESOURCE);
//        tm.addTexture(FOOD_BIG, FOOD_BIG_RESOURCE);
//        tm.addTexture(MOUTHWASH,MOUTHWASH_RESOURCE);
        
        //Add music and sounds
        sm.addMusic("main_theme", "resources/music.ogg");
        sm.addSound("fire", "resources/fire.wav");
        //Play main theme by default
        //sm.playMusic("main_theme");
        
        //Add background
        mouth = tm.getTexture("mouth");
        
        //Add entities
        em.addEntity("player", new Player());
        em.addEntity("crosshair", new CrossHair());
    }
 
    @Override
    public void update(GameContainer gc, int delta)
    {
        if(evm.isHappening(("close_window"), gc)) {
            gc.exit();
        }
        if(evm.isHappening(SAVE_GAME, gc)) {
            svm.saveGame(go,(Player)em.getEntity(PLAYER));
        }
        if(evm.isHappening(LOAD_GAME,gc)) {
            svm.loadGame(go,(Player)em.getEntity(PLAYER));
        }
        em.update(gc, delta);
    }
 
    @Override
    public void render(GameContainer gc, Graphics g) 
    {
        mouth.draw(0, 0);
        em.render(gc, g);
    }
 
    public static void main(String[] args) throws SlickException 
    {
         AppGameContainer app = new AppGameContainer(new Game());
         app.setDisplayMode(800, 600, false);
         app.setMouseGrabbed(true);
         app.start();
    }
}