/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld23.game;

import jam.ld23.events.InputEvent;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.Transition;

/**
 *
 * @author david
 */
public class StartState extends ManagedGameState {
    public StartState(int stateID) {
        super(stateID);
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        super.init(gc, game);
        evm.addEvent(C.Events.NEXT_STATE.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_ENTER));
    }

    @Override
    public void restart() {
        super.restart();
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        super.render(gc, game, g);
        g.setColor(Color.white);
        g.drawString("press <ENTER> to continue", 300, 300);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        super.update(gc, game, delta);
        if(evm.isHappening(C.Events.NEXT_STATE.name, gc)) {
            game.enterState(C.States.MAIN_STATE.value, new FadeOutTransition(), new BlobbyTransition());
        }
    }   
}