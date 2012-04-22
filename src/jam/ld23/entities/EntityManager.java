package jam.ld23.entities;

import java.util.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class EntityManager {

    //Static instance from EntityManager
    private static EntityManager em;
    
    private HashMap<String,Entity> entities;
    private HashMap<String,Entity> entitiesToAdd;
    
    private EntityManager() {
        entities = new HashMap<String,Entity>();
        entitiesToAdd = new HashMap<String,Entity>();
    }
    
    //Getter of the instance
    public static EntityManager getInstance() {
        if(em == null) {
            em = new EntityManager();
        }
        return em;
    }
    
    public Map<String,Entity> getEntities() {
        return entities;
    }

    public void clear() {
        entities.clear();
    }
    
    public void addEntity(String name, Entity entity) {
        entity.setName(name);
        entities.put(name, entity);
    }
    
    public void addFutureEntity(String name, Entity entity) {
        entity.setName(name);
        entitiesToAdd.put(name, entity);
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
        // Call update for each method
        Collection c = entities.values();
        Iterator itr = c.iterator();
        while(itr.hasNext()) {
            ((Entity) itr.next()).update(gc, delta);
        }
        
        // Add new entities
        entities.putAll(entitiesToAdd);
        entitiesToAdd.clear();
    }

    public ArrayList<Entity> getEntityGroup(String name) {
        ArrayList<Entity> result = new ArrayList<>();
        
        Collection c = entities.values();
        Iterator itr = c.iterator();
        while(itr.hasNext()) {
            Entity entity = (Entity) itr.next();
            if(entity.getGroup() == name) {
                result.add(entity);
            }
        }
        
        return result;
    }
}
