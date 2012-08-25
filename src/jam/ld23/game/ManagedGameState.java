package jam.ld23.game;

import infinitedog.frisky.entities.EntityManager;
import infinitedog.frisky.events.EventManager;
import infinitedog.frisky.events.InputEvent;
import infinitedog.frisky.physics.PhysicsManager;
import infinitedog.frisky.sounds.SoundManager;
import infinitedog.frisky.textures.TextureManager;
import jam.ld23.logic.LogicManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class ManagedGameState extends BasicGameState {
    private int stateID = -1;
    protected EntityManager em = EntityManager.getInstance();
    protected PhysicsManager pm = PhysicsManager.getInstance();
    protected EventManager evm = EventManager.getInstance();
    protected TextureManager tm = TextureManager.getInstance();
    protected SoundManager sm = SoundManager.getInstance();
    protected SaveManager svm = SaveManager.getInstance();
    protected GameOptions go = GameOptions.getInstance();
    protected LogicManager lm = LogicManager.getInstance();

    public ManagedGameState(int stateID) {
        this.stateID = stateID;
    }
    
    @Override
    public int getID() {
        return this.stateID;
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        tm.addTexture(C.Textures.CROSSHAIR.name, C.Textures.CROSSHAIR.path);
        //Close window
        evm.addEvent(C.Events.CLOSE_WINDOW.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_ESCAPE));
        //Next game state
        evm.addEvent(C.Events.NEXT_STATE.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_ENTER));
        //Crosshair movement
        evm.addEvent(C.Events.CROSSHAIR_MOVED.name, new InputEvent(InputEvent.MOUSE_MOVE, 
                new Rectangle(0, 0, C.SCREEN_WIDTH, C.SCREEN_HEIGHT)));
    }
    
    public void restart() {
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        if(evm.isHappening(C.Events.CLOSE_WINDOW.name, gc)) {
            gc.exit();
        }
    }   
}
