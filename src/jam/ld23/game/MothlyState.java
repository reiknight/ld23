/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld23.game;

import infinitedog.frisky.events.InputEvent;
import infinitedog.frisky.game.ManagedGameState;
import jam.ld23.logic.LogicManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Administrador
 */
public class MothlyState extends ManagedGameState {    
    protected SaveManager svm = SaveManager.getInstance();
    protected GameOptions go = GameOptions.getInstance();
    protected LogicManager lm = LogicManager.getInstance();
    
    public MothlyState(int stateID) {
        super(stateID);
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
     
    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        if(evm.isHappening(C.Events.CLOSE_WINDOW.name, gc)) {
            gc.exit();
        }
    }   

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        
    }

    public void restart() {
        
    }
}
