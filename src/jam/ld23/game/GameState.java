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

/**
 *
 * @author david
 */
public interface GameState {
    EntityManager em = EntityManager.getInstance();
    PhysicsManager pm = PhysicsManager.getInstance();
    EventManager evm = EventManager.getInstance();
    TextureManager tm = TextureManager.getInstance();
    SoundManager sm = SoundManager.getInstance();
    SaveManager svm = SaveManager.getInstance();
    GameOptions go = GameOptions.getInstance();
}
