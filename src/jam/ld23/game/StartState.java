package jam.ld23.game;

import jam.ld23.entities.CrossHair;
import jam.ld23.entities.Entity;
import jam.ld23.events.InputEvent;
import jam.ld23.game.C.GameModes;
import jam.ld23.gui.Button;
import jam.ld23.physics.PhysicsManager;
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class StartState extends ManagedGameState {
    private Image background;
    private Button button_start, button_instructions, button_credits;
    
    public StartState(int stateID) {
        super(stateID);
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        super.init(gc, game);
        em.setGameState(C.States.START_STATE.name);
        //Add events
        evm.addEvent(C.Events.CLICK_BUTTON.name, new InputEvent(InputEvent.MOUSE_CLICK, 
                Input.MOUSE_LEFT_BUTTON));
        //Load textures
        tm.addTexture(C.Textures.START_BACKGROUND.name, C.Textures.START_BACKGROUND.path);
        tm.addTexture(C.Textures.BUTTON_0.name, C.Textures.BUTTON_0.path);
        tm.addTexture(C.Textures.BUTTON_1.name, C.Textures.BUTTON_1.path);
        tm.addTexture(C.Textures.BUTTON_2.name, C.Textures.BUTTON_2.path);
        //Load entities
        button_start = new Button(C.Buttons.START_GAME.textureName, 
                C.Buttons.START_GAME.label, C.Buttons.START_GAME.labelPosition);
        button_start.setPosition(C.Buttons.START_GAME.position);
        em.addEntity(button_start.getName(), button_start);
        
        button_instructions = new Button(C.Buttons.INSTRUCTIONS.textureName, 
                C.Buttons.INSTRUCTIONS.label, C.Buttons.INSTRUCTIONS.labelPosition);
        button_instructions.setPosition(C.Buttons.INSTRUCTIONS.position);
        em.addEntity(button_instructions.getName(), button_instructions);
        
        button_credits = new Button(C.Buttons.CREDITS.textureName, 
                C.Buttons.CREDITS.label, C.Buttons.CREDITS.labelPosition);
        button_credits.setPosition(C.Buttons.CREDITS.position);
        em.addEntity(button_credits.getName(), button_credits);
        //Add Crosshair
        em.addEntity(C.Entities.CROSSHAIR.name, new CrossHair());
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
        CrossHair crosshair = (CrossHair) em.getEntity(C.Entities.CROSSHAIR.name);
        crosshair.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        super.update(gc, game, delta);
        em.setGameState(C.States.START_STATE.name);
        em.update(gc, delta);
        
        if(evm.isHappening(C.Events.CLICK_BUTTON.name, gc)) {
            CrossHair crosshair = (CrossHair) em.getEntity(C.Entities.CROSSHAIR.name);
            if(pm.testCollisionsEntity(crosshair, button_start)) {
                lm.setGameMode(GameModes.EASY_MODE);
                ((ManagedGameState)game.getState(C.States.MAIN_STATE.value)).restart();
                game.enterState(C.States.MAIN_STATE.value, new FadeOutTransition(), new BlobbyTransition());
            }

            if(pm.testCollisionsEntity(crosshair, button_instructions)) {
                ((ManagedGameState)game.getState(C.States.INSTRUCTIONS_STATE.value)).restart();
                game.enterState(C.States.INSTRUCTIONS_STATE.value, new FadeOutTransition(), new FadeInTransition());
            }

            if(pm.testCollisionsEntity(crosshair, button_credits)) {
                ((ManagedGameState)game.getState(C.States.CREDITS_STATE.value)).restart();
                game.enterState(C.States.CREDITS_STATE.value, new FadeOutTransition(), new FadeInTransition());
            }
        }
    }   
}
