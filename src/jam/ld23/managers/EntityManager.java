/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld23.managers;

import jam.ld23.entity.Entity;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 *
 * @author David
 */
public class EntityManager {

    private HashMap<String,Entity> entities;
    
    public EntityManager() {
        entities = new HashMap<String,Entity>() {};
    }
    
    public Map<String,Entity> getEntities() {
        return entities;
    }

    public void addEntity(String name, Entity entity) {
        entities.put(name, entity);
    }

    public Entity getEntity(String name) {
        return entities.get(name);
    }

    public boolean removeEntity(String name) {
        if(entities.containsKey(name)) {
            entities.remove(name);
            return true;
        }
        return false;
    }
    
    public void render(GameContainer gc, Graphics g) {
        Collection c = entities.values();
        Iterator itr = c.iterator();
        while(itr.hasNext()) {
            ((Entity) itr.next()).render(gc, g);
        }
    }

    public void update(GameContainer gc, int delta) {
        Collection c = entities.values();
        Iterator itr = c.iterator();
        while(itr.hasNext()) {
            ((Entity) itr.next()).update(gc, delta);
        }
    }
}
