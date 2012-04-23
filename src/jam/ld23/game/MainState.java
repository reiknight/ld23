package jam.ld23.game;
 
import jam.ld23.entities.*;
import jam.ld23.events.InputEvent;
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.VerticalSplitTransition;
 
public class MainState extends ManagedGameState {
    private Image mouth;
    private Image teeth;
    
    public MainState(int stateID)
    {
        super(stateID);
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        super.init(gc, game);
        //Register input events
        //Player movement
        evm.addEvent(C.Events.MOVE_LEFT.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_A));
        evm.addEvent(C.Events.MOVE_RIGHT.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_D));
        evm.addEvent(C.Events.MOVE_UP.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_W));
        evm.addEvent(C.Events.MOVE_DOWN.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_S));
        //Player actions
        evm.addEvent(C.Events.FIRE.name, new InputEvent(InputEvent.MOUSE_CLICK, 
                Input.MOUSE_LEFT_BUTTON, (Integer) C.Logic.PLAYER_RELOAD_TIME.data));
        evm.addEvent(C.Events.CROSSHAIR_MOVED.name, new InputEvent(InputEvent.MOUSE_MOVE, 
                new Rectangle(0, 0, C.SCREEN_WIDTH, C.SCREEN_HEIGHT)));
        evm.addEvent(C.Events.SAVE_GAME.name, new InputEvent(InputEvent.KEYBOARD,Input.KEY_R));
        evm.addEvent(C.Events.LOAD_GAME.name, new InputEvent(InputEvent.KEYBOARD,Input.KEY_T));
        
        //Add textures
        tm.addTexture(C.Textures.PLAYER.name, C.Textures.PLAYER.path);
        tm.addTexture(C.Textures.MOUTH.name, C.Textures.MOUTH.path);
        tm.addTexture(C.Textures.CROSSHAIR.name, C.Textures.CROSSHAIR.path);
        tm.addTexture(C.Textures.BULLET.name, C.Textures.BULLET.path);
        tm.addTexture(C.Textures.ENEMY_BULLET.name, C.Textures.ENEMY_BULLET.path);
        tm.addTexture(C.Textures.ENEMY.name, C.Textures.ENEMY.path);
        tm.addTexture(C.Textures.FOOD_SMALL_0.name, C.Textures.FOOD_SMALL_0.path);
        tm.addTexture(C.Textures.FOOD_NORMAL_0.name, C.Textures.FOOD_NORMAL_0.path);
        tm.addTexture(C.Textures.FOOD_BIG_0.name, C.Textures.FOOD_BIG_0.path);
        tm.addTexture(C.Textures.FOOD_SMALL_1.name, C.Textures.FOOD_SMALL_1.path);
        tm.addTexture(C.Textures.FOOD_NORMAL_1.name, C.Textures.FOOD_NORMAL_1.path);
        tm.addTexture(C.Textures.FOOD_BIG_1.name, C.Textures.FOOD_BIG_1.path);
        tm.addTexture(C.Textures.FOOD_SMALL_2.name, C.Textures.FOOD_SMALL_2.path);
        tm.addTexture(C.Textures.FOOD_NORMAL_2.name, C.Textures.FOOD_NORMAL_2.path);
        tm.addTexture(C.Textures.FOOD_BIG_2.name, C.Textures.FOOD_BIG_2.path);
        tm.addTexture(C.Textures.MOUTHWASH.name, C.Textures.MOUTHWASH.path);
        tm.addTexture(C.Textures.TEETH.name, C.Textures.TEETH.path);
        tm.addTexture(C.Textures.TOOTH_DECAY.name, C.Textures.TOOTH_DECAY.path);
        
        //Add music and sounds
        sm.addMusic(C.Sounds.MUSIC.name, C.Sounds.MUSIC.path);
        sm.addMusic(C.Sounds.FIRE.name, C.Sounds.FIRE.path);
        //Play main theme by default
        //sm.playMusic("main_theme");
        
        //Add background
        mouth = tm.getTexture(C.Textures.MOUTH.name);
        
        //Add deep effect
        teeth = tm.getTexture(C.Textures.TEETH.name);
        
        //Add entities
        //Add player
        em.addEntity(C.Entities.PLAYER.name, new Player());
        //Add Crosshair
        em.addEntity(C.Entities.CROSSHAIR.name, new CrossHair());
        //Add bottom teeth
        em.addEntity(C.Entities.TOOTH_BOTTOM_0.name, 
                new Tooth(C.Positions.TOOTH_BOTTOM_0.position, C.Dimensions.TOOTH_BOTTOM_0.dimension));
        em.addEntity(C.Entities.TOOTH_BOTTOM_1.name, 
                new Tooth(C.Positions.TOOTH_BOTTOM_1.position, C.Dimensions.TOOTH_BOTTOM_1.dimension));
        em.addEntity(C.Entities.TOOTH_BOTTOM_2.name, 
                new Tooth(C.Positions.TOOTH_BOTTOM_2.position, C.Dimensions.TOOTH_BOTTOM_2.dimension));    
        em.addEntity(C.Entities.TOOTH_BOTTOM_3.name, 
                new Tooth(C.Positions.TOOTH_BOTTOM_3.position, C.Dimensions.TOOTH_BOTTOM_3.dimension));    
        em.addEntity(C.Entities.TOOTH_BOTTOM_4.name, 
                new Tooth(C.Positions.TOOTH_BOTTOM_4.position, C.Dimensions.TOOTH_BOTTOM_4.dimension));    
        //Add top teeth
        em.addEntity(C.Entities.TOOTH_TOP_0.name, 
                new Tooth(C.Positions.TOOTH_TOP_0.position, C.Dimensions.TOOTH_TOP_0.dimension));
        em.addEntity(C.Entities.TOOTH_TOP_1.name, 
                new Tooth(C.Positions.TOOTH_TOP_1.position, C.Dimensions.TOOTH_TOP_1.dimension));
        em.addEntity(C.Entities.TOOTH_TOP_2.name, 
                new Tooth(C.Positions.TOOTH_TOP_2.position, C.Dimensions.TOOTH_TOP_2.dimension));    
        em.addEntity(C.Entities.TOOTH_TOP_3.name, 
                new Tooth(C.Positions.TOOTH_TOP_3.position, C.Dimensions.TOOTH_TOP_3.dimension));    
        em.addEntity(C.Entities.TOOTH_TOP_4.name, 
                new Tooth(C.Positions.TOOTH_TOP_4.position, C.Dimensions.TOOTH_TOP_4.dimension));   
        
        this.restart();
    }
    
    @Override
    public void restart() {
        //Set player's position and life
        Player player = (Player) em.getEntity(C.Entities.PLAYER.name);
        player.revive();
        player.setPosition(C.Positions.PLAYER.position);
        
        //Remove enemies and bullets
        em.removeEntityGroup(C.Groups.ENEMIES.name);
        em.removeEntityGroup(C.Groups.BULLETS.name);
        em.removeEntityGroup(C.Groups.ENEMY_BULLETS.name);
        em.removeEntityGroup(C.Groups.FOOD.name);
        //Force entity removal
        em.forceRemoval();
        
        //Remove tooth decays
        ArrayList<Entity> teeth = em.getEntityGroup(C.Groups.TEETH.name);
        for(int i = 0; i < teeth.size(); i++) {
            Tooth tooth = (Tooth)teeth.get(i);
            tooth.setDecayed(false);
        }
        
        //Add demo food
        Food f1,f2,f3,f4;
        em.addEntity((f1 = new Food(Size.BIG)).getName(), f1);
        em.addEntity((f2 = new Food(Size.SMALL)).getName(), f2);
        em.addEntity((f3 = new Food(Size.NORMAL)).getName(), f3);
        em.addEntity((f4 = new Food(Size.SMALL)).getName(), f4);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        super.render(gc, game, g);
        mouth.draw(0, 0);
        em.render(gc, g);
        teeth.draw(0, 0);
        //Draw tooth decays after teeth
        ArrayList<Entity> teeth = em.getEntityGroup(C.Groups.TEETH.name);
        for(int i = 0; i < teeth.size(); i++) {
            Tooth tooth = (Tooth)teeth.get(i);
            tooth.render(gc, g);
        }
        //Draw crosshair over all entities
        Entity crosshair = em.getEntity(C.Entities.CROSSHAIR.name);
        crosshair.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        super.update(gc, game, delta);
        if(evm.isHappening(C.Events.SAVE_GAME.name, gc)) {
            svm.saveGame(go,(Player)em.getEntity(C.Entities.PLAYER.name));
        }
        if(evm.isHappening(C.Events.LOAD_GAME.name,gc)) {
            svm.loadGame(go,(Player)em.getEntity(C.Entities.PLAYER.name));
        }
        
        //Updates all entities
        em.update(gc, delta);
        //Updates all events
        evm.update(gc, delta);
        
        //If player dies change state to game over
        Player player = (Player) em.getEntity(C.Entities.PLAYER.name);
        if(player.isDead()) {
            game.enterState(C.States.GAME_OVER_STATE.value, new FadeOutTransition(), new FadeInTransition());
        }
    }
}