package jam.ld23.game;

import jam.ld23.entities.EntityManager;
import jam.ld23.events.EventManager;
import jam.ld23.events.InputEvent;
import jam.ld23.logic.LogicManager;
import jam.ld23.physics.PhysicsManager;
import jam.ld23.sounds.SoundManager;
import jam.ld23.textures.TextureManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
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
        //Close window
        evm.addEvent(C.Events.CLOSE_WINDOW.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_ESCAPE));
    }
    
    @Override
    public int getID() {
        return this.stateID;
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
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
