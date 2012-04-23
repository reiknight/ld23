package jam.ld23.game;

import jam.ld23.events.InputEvent;
import jam.ld23.game.C.GameModes;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class InstructionsState extends ManagedGameState {

    public InstructionsState(int stateID) {
        super(stateID);
        em.setGameState(C.States.INSTRUCTIONS_STATE.name);
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        super.init(gc, game);
        em.setGameState(C.States.INSTRUCTIONS_STATE.name);
    }

    @Override
    public void restart() {
        super.restart();
        em.setGameState(C.States.INSTRUCTIONS_STATE.name);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        super.render(gc, game, g);
        em.setGameState(C.States.INSTRUCTIONS_STATE.name);
        g.setColor(Color.white);
        g.drawString("You are the last defense for your host!", 100, 100);
        g.drawString("Use your fluorine skills to destroy all bacteries but", 100, 120);
        g.drawString("be careful! You can be swallowed if you don't dodge food.", 100, 140);
        g.drawString("Also, bacteries will infect your host teeth. Repair them or", 100, 160);
        g.drawString("get killed by tooth decays.", 100, 180);
        g.drawString("press <ENTER> to back", 300, 300);
        g.drawString("WASD: Move player", 550, 350);
        g.drawString("Mouse move: Move crosshair", 550, 370);
        g.drawString("Mouse left button: Fire", 550, 390);
        g.drawString("Use bomb: Space", 550, 410);
        g.drawString("Exit game: Esc", 550, 430);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        super.update(gc, game, delta);
        em.setGameState(C.States.INSTRUCTIONS_STATE.name);
        if(evm.isHappening(C.Events.NEXT_STATE.name, gc)) {
            ((ManagedGameState)game.getState(C.States.START_STATE.value)).restart();
            game.enterState(C.States.START_STATE.value, new FadeOutTransition(), new FadeInTransition());
        }
    }   
}