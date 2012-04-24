package jam.ld23.game;
 
import jam.ld23.entities.*;
import jam.ld23.events.InputEvent;
import jam.ld23.logic.LogicManager;
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
 
public class MainState extends ManagedGameState {
    private boolean paused = false;
    
    public MainState(int stateID)
    {
        super(stateID);
        em.setGameState(C.States.MAIN_STATE.name);
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        super.init(gc, game);
        em.setGameState(C.States.MAIN_STATE.name);
        //Register input events
        //Player movement
        evm.addEvent(C.Events.MOVE_LEFT.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_A));
        evm.addEvent(C.Events.MOVE_RIGHT.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_D));
        evm.addEvent(C.Events.MOVE_UP.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_W));
        evm.addEvent(C.Events.MOVE_DOWN.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_S));
        //Player actions
        evm.addEvent(C.Events.FIRE.name, new InputEvent(InputEvent.MOUSE_CLICK, 
                Input.MOUSE_LEFT_BUTTON, (Integer) C.Logic.PLAYER_RELOAD_TIME.data));
        evm.addEvent(C.Events.SAVE_GAME.name, new InputEvent(InputEvent.KEYBOARD,Input.KEY_R));
        evm.addEvent(C.Events.LOAD_GAME.name, new InputEvent(InputEvent.KEYBOARD,Input.KEY_T));
        evm.addEvent(C.Events.PAUSE_GAME.name, 
                new InputEvent(InputEvent.KEYBOARD, Input.KEY_P, (Integer) C.Logic.SELECT_OPTION_DELAY.data));
        evm.addEvent(C.Events.BOMB.name, 
                new InputEvent(InputEvent.KEYBOARD, Input.KEY_SPACE, (Integer) C.Logic.BOMB_DELAY.data));
        //Add textures
        tm.addTexture(C.Textures.PLAYER.name, C.Textures.PLAYER.path);
        tm.addTexture(C.Textures.MOUTH.name, C.Textures.MOUTH.path);
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
        tm.addTexture(C.Textures.PLOP.name, C.Textures.PLOP.path);
        tm.addTexture(C.Textures.TEETH.name, C.Textures.TEETH.path);
        tm.addTexture(C.Textures.TOOTH_DECAY.name, C.Textures.TOOTH_DECAY.path);
        tm.addTexture(C.Textures.TOOTH_DECAY_DEAD.name, C.Textures.TOOTH_DECAY_DEAD.path);
        tm.addTexture(C.Textures.SCORE.name, C.Textures.SCORE.path);
        tm.addTexture(C.Textures.HEART.name, C.Textures.HEART.path);
        tm.addTexture(C.Textures.ITEM.name, C.Textures.ITEM.path);
        
        //Add music and sounds
        sm.addMusic(C.Sounds.MUSIC.name, C.Sounds.MUSIC.path);
        sm.addSound(C.Sounds.FIRE.name, C.Sounds.FIRE.path);
        
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
        
        //Add food factory entity
        em.addEntity(C.Entities.FOOD_FACTORY.name, new FoodFactory());
    }
    
    @Override
    public void restart() {
        em.setGameState(C.States.MAIN_STATE.name);
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
        
       //Play main theme by default
        sm.playMusic(C.Sounds.MUSIC.name);
        
        //Remove tooth decays
        ArrayList<Entity> teeth = em.getEntityGroup(C.Groups.TEETH.name);
        for(int i = 0; i < teeth.size(); i++) {
            Tooth tooth = (Tooth)teeth.get(i);
            tooth.setDecayed(false);
        }
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        super.render(gc, game, g);
        LogicManager lm = LogicManager.getInstance();
        em.setGameState(C.States.MAIN_STATE.name);
        tm.getTexture(C.Textures.MOUTH.name).draw(0, 0);
        em.render(gc, g);
        tm.getTexture(C.Textures.TEETH.name).draw(0, 0);
        //Draw tooth decays after teeth
        ArrayList<Entity> teeth = em.getEntityGroup(C.Groups.TEETH.name);
        for(int i = 0; i < teeth.size(); i++) {
            Tooth tooth = (Tooth)teeth.get(i);
            tooth.render(gc, g);
        }
        //Draw crosshair over all entities
        Entity crosshair = em.getEntity(C.Entities.CROSSHAIR.name);
        crosshair.render(gc, g);
        if(paused) {
            g.setColor(Color.black);
            g.drawString("- PAUSED - ", 350, 300);
        }
        
        tm.getTexture(C.Textures.SCORE.name).draw(320,0);
        g.drawString("Score: " + lm.getScore(),gc.getWidth()/2 - 50,20);
        
        Player player = (Player) em.getEntity(C.Entities.PLAYER.name);
        for(int i = 0; i < player.getContinues(); i++) {
            tm.getTexture(C.Textures.HEART.name).draw(750 - i * 40, 5);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        super.update(gc, game, delta);
        em.setGameState(C.States.MAIN_STATE.name);
        Player player = (Player) em.getEntity(C.Entities.PLAYER.name);
        //Updates all events
        evm.update(gc, delta);
        
        if(evm.isHappening(C.Events.PAUSE_GAME.name, gc)) {
            paused = !paused;
        }
        
        //Check if game is paused
        if(!paused) {
            if(evm.isHappening(C.Events.SAVE_GAME.name, gc)) {
                svm.saveGame(go,(Player)em.getEntity(C.Entities.PLAYER.name));
            }
            if(evm.isHappening(C.Events.LOAD_GAME.name,gc)) {
                svm.loadGame(go,(Player)em.getEntity(C.Entities.PLAYER.name));
            }
            
            if(evm.isHappening(C.Events.BOMB.name, gc) && player.getBombs() > 0) {
                player.addBomb(-1);
                ArrayList<Entity> entitiesDestroyed;
                entitiesDestroyed = em.getEntityGroup(C.Groups.ENEMY_BULLETS.name);
                for(int i = 0; i < entitiesDestroyed.size(); i++) {
                    ((Bullet)entitiesDestroyed.get(i)).die();
                }
                entitiesDestroyed = em.getEntityGroup(C.Groups.ENEMIES.name);
                for(int i = 0; i < entitiesDestroyed.size(); i++) {
                    ((Enemy)entitiesDestroyed.get(i)).die();
                }
                entitiesDestroyed = em.getEntityGroup(C.Groups.FOOD.name);
                for(int i = 0; i < entitiesDestroyed.size(); i++) {
                    ((Food)entitiesDestroyed.get(i)).die();
                }
                ArrayList<Entity> teeth = em.getEntityGroup(C.Groups.TEETH.name);
                for(int i = 0; i < teeth.size(); i++) {
                    Tooth tooth = (Tooth)teeth.get(i);
                    if(tooth.isDecayed()) {
                        tooth.die();
                    }
                }
            }

            //Updates all entities
            em.update(gc, delta);

            //If player dies change state to game over
            if(player.isDead()) {
                ((ManagedGameState)game.getState(C.States.GAME_OVER_STATE.value)).restart();
                player.addContinue(-1);
                if(player.getContinues() < 0) {
                    lm.setGameOver(true);
                }
                game.enterState(C.States.GAME_OVER_STATE.value, new FadeOutTransition(), new FadeInTransition());
            }
        } 
    }
}