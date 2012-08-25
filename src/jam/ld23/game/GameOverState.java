package jam.ld23.game;

import infinitedog.frisky.game.ManagedGameState;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameOverState extends MothlyState {
    public GameOverState(int stateID) {
        super(stateID);
        em.setGameState(C.States.GAME_OVER_STATE.name);
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        super.init(gc, game);
        em.setGameState(C.States.GAME_OVER_STATE.name);
        //Add music and sounds
        sm.addSound(C.Sounds.GAME_OVER.name, C.Sounds.GAME_OVER.path);
        //Add background
        tm.addTexture(C.Textures.GAME_OVER_BACKGROUND.name, C.Textures.GAME_OVER_BACKGROUND.path);
        tm.addTexture(C.Textures.CONTINUE_BACKGROUND.name, C.Textures.CONTINUE_BACKGROUND.path);
    }

    @Override
    public void restart() {
        super.restart();
        em.setGameState(C.States.GAME_OVER_STATE.name);
        if(lm.isGameOver()) {
            sm.playSound(C.Sounds.GAME_OVER.name);
        }
    }
        
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        super.render(gc, game, g);
        em.setGameState(C.States.GAME_OVER_STATE.name);
        
        if(lm.isGameOver()) {
            tm.getTexture(C.Textures.GAME_OVER_BACKGROUND.name).draw(0, 0);
        }
        else {
            tm.getTexture(C.Textures.CONTINUE_BACKGROUND.name).draw(0, 0);
        }
        g.setColor(Color.white);
        g.drawString("Highscore: " + lm.getHighScore(), 600, 100);
        g.drawString("Score: " + lm.getScore(), 600, 120);
        g.drawString("press <ENTER> to continue", 280, 550);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        super.update(gc, game, delta);
        em.setGameState(C.States.GAME_OVER_STATE.name);
        if(evm.isHappening(C.Events.NEXT_STATE.name, gc)) {
            ((MothlyState)game.getState(C.States.MAIN_STATE.value)).restart();
            game.enterState(C.States.MAIN_STATE.value, new FadeOutTransition(), new BlobbyTransition());
        }
    }
    
}
