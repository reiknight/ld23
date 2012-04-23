package jam.ld23.game;

import jam.ld23.events.InputEvent;
import jam.ld23.game.C.GameModes;
import jam.ld23.gui.Button;
import org.newdawn.slick.*;
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
        em.setGameState(C.States.START_STATE.name);
        //Load textures
        tm.addTexture(C.Textures.START_BACKGROUND.name, C.Textures.START_BACKGROUND.path);
        tm.addTexture(C.Textures.BUTTON_0.name, C.Textures.BUTTON_0.path);
        tm.addTexture(C.Textures.BUTTON_1.name, C.Textures.BUTTON_1.path);
        tm.addTexture(C.Textures.BUTTON_2.name, C.Textures.BUTTON_2.path);
        //Load entities
        Button button_start = new Button(C.Buttons.START_GAME.textureName, C.Buttons.START_GAME.label);
        button_start.setPosition(C.Buttons.START_GAME.position);
        em.addEntity(button_start.getName(), button_start);
        //Add background
        background = tm.getTexture(C.Textures.START_BACKGROUND.name);
    }

    @Override
    public void restart() {
        super.restart();
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        super.render(gc, game, g);
        em.setGameState(C.States.START_STATE.name);
        background.draw(0, 0);
        g.setColor(Color.black);
        g.drawString("press <ENTER> to continue", 300, 300);
        em.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        super.update(gc, game, delta);
        em.setGameState(C.States.START_STATE.name);
        if(evm.isHappening(C.Events.NEXT_STATE.name, gc)) {
            //TODO: select game mode using GUI
            lm.setGameMode(GameModes.EASY_MODE);
            ((ManagedGameState)game.getState(C.States.MAIN_STATE.value)).restart();
            game.enterState(C.States.MAIN_STATE.value, new FadeOutTransition(), new BlobbyTransition());
        }
        em.update(gc, delta);
    }   
}
