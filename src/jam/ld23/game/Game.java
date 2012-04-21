package jam.ld23.game;
 
import jam.ld23.events.InputEvent;
import jam.ld23.managers.EntityManager;
import jam.ld23.managers.EventManager;
import jam.ld23.managers.PhysicsManager;
import org.newdawn.slick.*;
import sun.font.PhysicalFont;
 
public class Game extends BasicGame {
 
    private Image land = null;
    private Image plane = null;
    private float x = 400;
    private float y = 300;
    private float scale = 1.0f;
    private EntityManager em;
    private PhysicsManager pm;
    private EventManager evm;
    
    public Game()
    {
        super("Slick2DPath2Glory - SimpleGame");
        //TODO: esto pueden ser singletons todos si lo crees necesario
        //yo creo que es lo mejor también, así en las clases de Entity
        //podemos llamar a EventManager por ejemplo pa las teclas.
        em = EntityManager.getInstance();
        pm = PhysicsManager.getInstance();        
        evm = EventManager.getInstance();
        evm.addEvent("close_window", new InputEvent(InputEvent.KEYBOARD, Input.KEY_ESCAPE));
        evm.addEvent("move_left", new InputEvent(InputEvent.KEYBOARD, Input.KEY_A));
        evm.addEvent("move_right", new InputEvent(InputEvent.KEYBOARD, Input.KEY_D));
        evm.addEvent("move_forward", new InputEvent(InputEvent.KEYBOARD, Input.KEY_W));
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
        land = new Image("resources/land.jpg");
        plane = new Image("resources/player.png");
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
        if(evm.isHappening(("move_left"), gc))
        {
            plane.rotate(-0.2f * delta);
        }
 
        if(evm.isHappening(("move_right"), gc))
        {
            plane.rotate(0.2f * delta);
        }
 
        if(evm.isHappening(("move_forward"), gc))
        {
            float hip = 0.4f * delta;
 
            float rotation = plane.getRotation();
 
            x+= hip * Math.sin(Math.toRadians(rotation));
            y-= hip * Math.cos(Math.toRadians(rotation));
        }
    
        if(evm.isHappening(("close_window"), gc)) {
            gc.exit();
        }
    }
 
    @Override
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
        // Window origin is in top left
        land.draw(0, 0);
        plane.draw(x, y, scale);
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         AppGameContainer app = 
			new AppGameContainer(new Game());
 
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}