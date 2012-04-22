package jam.ld23.game;
 
import jam.ld23.entities.*;
import jam.ld23.events.EventManager;
import jam.ld23.events.InputEvent;
import jam.ld23.physics.PhysicsManager;
import jam.ld23.sounds.SoundManager;
import jam.ld23.textures.TextureManager;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
 
public class Game extends BasicGame {
    private EntityManager em;
    private PhysicsManager pm;
    private EventManager evm;
    private TextureManager tm;
    private SoundManager sm;
    private SaveManager svm;
    private GameOptions go;
    private Image mouth;
    private Image teeth;
    
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
        evm.addEvent(C.Events.CLOSE_WINDOW.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_ESCAPE));
        //Player movement
        evm.addEvent(C.Events.MOVE_LEFT.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_A));
        evm.addEvent(C.Events.MOVE_RIGHT.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_D));
        evm.addEvent(C.Events.MOVE_UP.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_W));
        evm.addEvent(C.Events.MOVE_DOWN.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_S));
        //Player actions
        evm.addEvent(C.Events.FIRE.name, new InputEvent(InputEvent.MOUSE_CLICK, Input.MOUSE_LEFT_BUTTON, 500));
        evm.addEvent(C.Events.CROSSHAIR_MOVED.name, new InputEvent(InputEvent.MOUSE_MOVE, new Rectangle(0, 0, 800, 600)));
        evm.addEvent(C.Events.SAVE_GAME.name, new InputEvent(InputEvent.KEYBOARD,Input.KEY_R));
        evm.addEvent(C.Events.LOAD_GAME.name, new InputEvent(InputEvent.KEYBOARD,Input.KEY_T));
        
        //Add textures
        tm.addTexture(C.Textures.PLAYER.name, C.Textures.PLAYER.path);
        tm.addTexture(C.Textures.MOUTH.name, C.Textures.MOUTH.path);
        tm.addTexture(C.Textures.CROSSHAIR.name, C.Textures.CROSSHAIR.path);
        tm.addTexture(C.Textures.BULLET.name, C.Textures.BULLET.path);
        tm.addTexture(C.Textures.ENEMY.name, C.Textures.ENEMY.path);
        tm.addTexture(C.Textures.FOOD_SMALL.name, C.Textures.FOOD_SMALL.path);
        tm.addTexture(C.Textures.FOOD_NORMAL.name, C.Textures.FOOD_NORMAL.path);
        tm.addTexture(C.Textures.FOOD_BIG.name, C.Textures.FOOD_BIG.path);
        tm.addTexture(C.Textures.MOUTHWASH.name, C.Textures.MOUTHWASH.path);
        tm.addTexture(C.Textures.TEETH.name, C.Textures.TEETH.path);
        
        //Add music and sounds
        sm.addMusic(C.Sounds.MUSIC.name, C.Sounds.MUSIC.path);
        sm.addMusic(C.Sounds.FIRE.name, C.Sounds.FIRE.path);
        //Play main theme by default
        //sm.playMusic("main_theme");
        
        //Add background
        mouth = tm.getTexture(C.Textures.MOUTH.name);
        
        //Add deep effect
        teeth = tm.getTexture(C.Textures.TEETH.name);
        
        //Add entities
        em.addEntity(C.Entities.PLAYER.name, new Player());
        em.addEntity(C.Entities.CROSSHAIR.name, new CrossHair());
        Food f1,f2,f3,f4;
        em.addEntity((f1 = new Food(Size.BIG)).getName(), f1);
        em.addEntity((f2 = new Food(Size.SMALL)).getName(), f2);
        em.addEntity((f3 = new Food(Size.NORMAL)).getName(), f3);
        em.addEntity((f4 = new Food(Size.SMALL)).getName(), f4);
        
    }
 
    @Override
    public void update(GameContainer gc, int delta)
    {
        if(evm.isHappening(C.Events.CLOSE_WINDOW.name, gc)) {
            gc.exit();
        }
        if(evm.isHappening(C.Events.SAVE_GAME.name, gc)) {
            svm.saveGame(go,(Player)em.getEntity(C.Entities.PLAYER.name));
        }
        if(evm.isHappening(C.Events.LOAD_GAME.name,gc)) {
            svm.loadGame(go,(Player)em.getEntity(C.Entities.PLAYER.name));
        }
        
        //Updates all entities
        em.update(gc, delta);
        //Updates all events
        evm.update(gc, delta);
    }
 
    @Override
    public void render(GameContainer gc, Graphics g) 
    {
        mouth.draw(0, 0);
        em.render(gc, g);
        teeth.draw(0, 0);
    }
 
    public static void main(String[] args) throws SlickException 
    {
         AppGameContainer app = new AppGameContainer(new Game());
         app.setDisplayMode(C.SCREEN_WIDTH, C.SCREEN_HEIGHT, false);
         app.setMouseGrabbed(true);
         app.start();
    }
}