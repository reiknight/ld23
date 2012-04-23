/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld23.game;

import jam.ld23.entities.EntityManager;
import jam.ld23.events.EventManager;
import jam.ld23.physics.PhysicsManager;
import jam.ld23.sounds.SoundManager;
import jam.ld23.textures.TextureManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author david
 */
public abstract class ManagedGameState extends BasicGameState {
    private int stateID = -1;
    protected EntityManager em = EntityManager.getInstance();
    protected PhysicsManager pm = PhysicsManager.getInstance();
    protected EventManager evm = EventManager.getInstance();
    protected TextureManager tm = TextureManager.getInstance();
    protected SoundManager sm = SoundManager.getInstance();
    protected SaveManager svm = SaveManager.getInstance();
    protected GameOptions go = GameOptions.getInstance();

    public ManagedGameState(int stateID) {
        this.stateID = stateID;
    }
    
    @Override
    public int getID() {
        return this.stateID;
    }
}
