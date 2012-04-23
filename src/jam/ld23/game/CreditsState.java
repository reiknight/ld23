package jam.ld23.game;

import jam.ld23.events.InputEvent;
import jam.ld23.game.C.GameModes;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class CreditsState extends ManagedGameState {
    private Image background;

    public CreditsState(int stateID) {
        super(stateID);
        em.setGameState(C.States.CREDITS_STATE.name);
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        super.init(gc, game);
        em.setGameState(C.States.CREDITS_STATE.name);
        tm.addTexture(C.Textures.CREDITS_BACKGROUND.name, C.Textures.CREDITS_BACKGROUND.path);
        background = tm.getTexture(C.Textures.CREDITS_BACKGROUND.name);
    }

    @Override
    public void restart() {
        super.restart();
        em.setGameState(C.States.CREDITS_STATE.name);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        super.render(gc, game, g);
        em.setGameState(C.States.CREDITS_STATE.name);
        background.draw(0, 0);
        g.setColor(Color.black);
        g.drawString("press <ENTER> to back", 530, 50);
        g.drawString("programmed by", 600, 350);
        g.drawString("@ReikVal", 600, 370);
        g.drawString("@ultrayoshi", 600, 390);
        g.drawString("art by", 600, 430);
        g.drawString("@_Dreisa_", 600, 450);
        g.drawString("special thanks", 600, 490);
        g.drawString("@hmudarra_", 600, 510);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        super.update(gc, game, delta);
        em.setGameState(C.States.CREDITS_STATE.name);
        if(evm.isHappening(C.Events.NEXT_STATE.name, gc)) {
            ((ManagedGameState)game.getState(C.States.START_STATE.value)).restart();
            game.enterState(C.States.START_STATE.value, new FadeOutTransition(), new FadeInTransition());
        }
    }   
}
