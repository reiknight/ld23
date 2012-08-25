package jam.ld23.game;

import jam.ld23.entities.CrossHair;
import infinitedog.frisky.events.InputEvent;
import jam.ld23.game.C.GameModes;
import infinitedog.frisky.gui.Button;
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class StartState extends ManagedGameState {
    private Image background;
    private Button button_start, button_instructions, button_credits,
            button_easy, button_normal, button_hard;
    
    private boolean start_game = false;
    private C.GameModes game_mode = null;
    
    public StartState(int stateID) {
        super(stateID);
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        super.init(gc, game);
        em.setGameState(C.States.START_STATE.name);
        //Add events
        evm.addEvent(C.Events.CLICK_BUTTON.name, new InputEvent(InputEvent.MOUSE_CLICK, 
                Input.MOUSE_LEFT_BUTTON, (Integer) C.Logic.SELECT_OPTION_DELAY.data));
        //Load textures
        tm.addTexture(C.Textures.START_BACKGROUND.name, C.Textures.START_BACKGROUND.path);
        tm.addTexture(C.Textures.BUTTON_0.name, C.Textures.BUTTON_0.path);
        tm.addTexture(C.Textures.BUTTON_1.name, C.Textures.BUTTON_1.path);
        tm.addTexture(C.Textures.BUTTON_2.name, C.Textures.BUTTON_2.path);
        //Load entities
        button_start = new Button(C.Buttons.START_GAME.textureName,
                "button_start", C.Groups.BUTTONS.name,
                C.Buttons.START_GAME.label, C.Buttons.START_GAME.labelPosition);
        button_start.setPosition(C.Buttons.START_GAME.position);
        em.addEntity(button_start.getName(), button_start);
        
        button_instructions = new Button(C.Buttons.INSTRUCTIONS.textureName,
                "button_instructions", C.Groups.BUTTONS.name,
                C.Buttons.INSTRUCTIONS.label, C.Buttons.INSTRUCTIONS.labelPosition);
        button_instructions.setPosition(C.Buttons.INSTRUCTIONS.position);
        em.addEntity(button_instructions.getName(), button_instructions);
        
        button_credits = new Button(C.Buttons.CREDITS.textureName, 
                "button_credits", C.Groups.BUTTONS.name,
                C.Buttons.CREDITS.label, C.Buttons.CREDITS.labelPosition);
        button_credits.setPosition(C.Buttons.CREDITS.position);
        em.addEntity(button_credits.getName(), button_credits);
        
        button_easy = new Button(C.Buttons.EASY.textureName,
                "button_easy", C.Groups.BUTTONS.name,
                C.Buttons.EASY.label, C.Buttons.EASY.labelPosition);
        button_easy.setPosition(C.Buttons.EASY.position);
        button_easy.setAutoRendered(false);
        em.addEntity(button_easy.getName(), button_easy);
        
        button_normal = new Button(C.Buttons.NORMAL.textureName,
                "button_normal", C.Groups.BUTTONS.name,
                C.Buttons.NORMAL.label, C.Buttons.NORMAL.labelPosition);
        button_normal.setPosition(C.Buttons.NORMAL.position);
        button_normal.setAutoRendered(false);
        em.addEntity(button_normal.getName(), button_normal);
        
        button_hard = new Button(C.Buttons.HARD.textureName,
                "button_hard", C.Groups.BUTTONS.name,
                C.Buttons.HARD.label, C.Buttons.HARD.labelPosition);
        button_hard.setPosition(C.Buttons.HARD.position);
        button_hard.setAutoRendered(false);
        em.addEntity(button_hard.getName(), button_hard);
        
        //Add Crosshair
        em.addEntity(C.Entities.CROSSHAIR.name, new CrossHair());
        //Add background
        background = tm.getTexture(C.Textures.START_BACKGROUND.name);
        //Add sound
        sm.addSound(C.Sounds.OPENING.name, C.Sounds.OPENING.path);
        sm.playSound(C.Sounds.OPENING.name);
    }

    @Override
    public void restart() {
        super.restart();
        sm.playSound(C.Sounds.OPENING.name);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        super.render(gc, game, g);
        em.setGameState(C.States.START_STATE.name);
        background.draw(0, 0);
        em.render(gc, g);
        CrossHair crosshair = (CrossHair) em.getEntity(C.Entities.CROSSHAIR.name);
        crosshair.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        super.update(gc, game, delta);
        evm.update(gc, delta);
        em.setGameState(C.States.START_STATE.name);
        em.update(gc, delta);
        
        if(evm.isHappening(C.Events.CLICK_BUTTON.name, gc)) {
            CrossHair crosshair = (CrossHair) em.getEntity(C.Entities.CROSSHAIR.name);
            if(start_game) {
                if(pm.testCollisionsEntity(crosshair, button_easy)) {
                    game_mode = C.GameModes.EASY_MODE;
                }
                if(pm.testCollisionsEntity(crosshair, button_normal)) {
                    game_mode = C.GameModes.NORMAL_MODE;
                }
                if(pm.testCollisionsEntity(crosshair, button_hard)) {
                    game_mode = C.GameModes.HARD_MODE;
                }
                if(game_mode != null) {
                    lm.setGameMode(game_mode);
                    ((ManagedGameState)game.getState(C.States.MAIN_STATE.value)).restart();
                    game.enterState(C.States.MAIN_STATE.value, new FadeOutTransition(), new BlobbyTransition());
                }
            }
            else {
                if(pm.testCollisionsEntity(crosshair, button_start)) {
                    start_game = true;
                    button_start.setAutoRendered(false);
                    button_instructions.setAutoRendered(false);
                    button_credits.setAutoRendered(false);
                    button_easy.setAutoRendered(true);
                    button_normal.setAutoRendered(true);
                    button_hard.setAutoRendered(true);
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
}
