package jam.ld23.entities;

import java.util.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class EntityManager {

    //Static instance from EntityManager
    private static EntityManager em;
    
    private HashMap<String,Entity> entities;
    private HashMap<String,Entity> entitiesToAdd;
    private HashMap<String,Entity> entitiesToRemove;
    
    private EntityManager() {
        entities = new HashMap<String,Entity>();
        entitiesToAdd = new HashMap<String,Entity>();
        entitiesToRemove = new HashMap<String,Entity>();
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
        entitiesToAdd.clear();
        entitiesToRemove.clear();
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
            Entity entity = entities.get(name);
            entitiesToRemove.put(name, entity);
            return true;
        }
        return false;
    }
    
    public void render(GameContainer gc, Graphics g) {
        Collection c = entities.values();
        Iterator itr = c.iterator();
        while(itr.hasNext()) {
            Entity entity = (Entity) itr.next();
            if(entity.autoRender) {
                entity.render(gc, g);
            }
        }
    }

    public void update(GameContainer gc, int delta) {
        Collection c;
        Iterator itr;
        
        // Call update for each method
        c = entities.values();
        itr = c.iterator();
        while(itr.hasNext()) {
            Entity entity = (Entity) itr.next();
            if(entity.autoUpdate) {
                entity.update(gc, delta);
            }
        }
        
        // Add new entities
        entities.putAll(entitiesToAdd);
        entitiesToAdd.clear();
        
         // Remove entitites marked as removed
        c = entitiesToRemove.values();
        itr = c.iterator();
        while(itr.hasNext()) {
            entities.remove(((Entity)itr.next()).getName());
        }
        entitiesToRemove.clear();
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
