/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld23.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author david
 */
public class MothlysWorld extends StateBasedGame {
    
    public MothlysWorld() {
        super("Mothly's World - v0.1");
        this.addState(new StartState(C.States.START_STATE.value));
        this.addState(new MainState(C.States.MAIN_STATE.value));
        this.addState(new GameOverState(C.States.GAME_OVER_STATE.value));
        this.enterState(C.States.START_STATE.value);
    }
    
    public static void main(String[] args) throws SlickException 
    {
         AppGameContainer app = new AppGameContainer(new MothlysWorld());
         app.setDisplayMode(C.SCREEN_WIDTH, C.SCREEN_HEIGHT, false);
         app.setShowFPS(C.DEBUG_MODE);
         app.setMouseGrabbed(true);
         app.start();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(C.States.START_STATE.value).init(gc, this);
        this.getState(C.States.MAIN_STATE.value).init(gc, this);
        this.getState(C.States.GAME_OVER_STATE.value).init(gc, this);
    }
}
