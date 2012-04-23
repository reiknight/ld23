package jam.ld23.game;

import jam.ld23.events.InputEvent;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class StartState extends ManagedGameState {
    private Image background;
    
    public StartState(int stateID) {
        super(stateID);
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        super.init(gc, game);
        evm.addEvent(C.Events.NEXT_STATE.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_ENTER));
        tm.addTexture(C.Textures.START_BACKGROUND.name, C.Textures.START_BACKGROUND.path);
        background = tm.getTexture(C.Textures.START_BACKGROUND.name);
    }

    @Override
    public void restart() {
        super.restart();
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        super.render(gc, game, g);
        background.draw(0, 0);
        g.setColor(Color.black);
        g.drawString("press <ENTER> to continue", 300, 300);
        g.drawString("programmed by", 600, 350);
        g.drawString("@ReikVal", 600, 370);
        g.drawString("@ultrayoshi", 600, 390);
        g.drawString("designed by", 600, 430);
        g.drawString("@_Dreisa_", 600, 450);
        g.drawString("special thanks", 600, 490);
        g.drawString("@hmudarra_", 600, 510);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        super.update(gc, game, delta);
        if(evm.isHappening(C.Events.NEXT_STATE.name, gc)) {
            game.enterState(C.States.MAIN_STATE.value, new FadeOutTransition(), new BlobbyTransition());
        }
    }   
}
