package jam.ld23.game;

import jam.ld23.events.InputEvent;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameOverState extends ManagedGameState {
    public GameOverState(int stateID) {
        super(stateID);
        em.setGameState(C.States.GAME_OVER_STATE.name);
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        super.init(gc, game);
        em.setGameState(C.States.GAME_OVER_STATE.name);
    }

    @Override
    public void restart() {
        super.restart();
        em.setGameState(C.States.GAME_OVER_STATE.name);
    }
        
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        super.render(gc, game, g);
        em.setGameState(C.States.GAME_OVER_STATE.name);
        g.setColor(Color.white);
        g.drawString("press <ENTER> to continue", 300, 300);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        super.update(gc, game, delta);
        em.setGameState(C.States.GAME_OVER_STATE.name);
        if(evm.isHappening(C.Events.NEXT_STATE.name, gc)) {
            ((ManagedGameState)game.getState(C.States.MAIN_STATE.value)).restart();
            game.enterState(C.States.MAIN_STATE.value, new FadeOutTransition(), new BlobbyTransition());
        }
    }
    
}
