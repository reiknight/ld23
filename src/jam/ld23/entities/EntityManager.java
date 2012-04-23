package jam.ld23.entities;

import java.util.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class EntityManager {

    //Static instance from EntityManager
    private static EntityManager em;
    
    private HashMap<String,HashMap<String,Entity>> entities;
    private HashMap<String,HashMap<String,Entity>> entitiesToAdd;
    private HashMap<String,HashMap<String,Entity>> entitiesToRemove;
    
    private String gameState;
    
    private EntityManager() {
        entities = new HashMap();
        entitiesToAdd = new HashMap();
        entitiesToRemove = new HashMap();
    }
    
    public void setGameState(String gameState) {
        if(!entities.containsKey(gameState)) {
            this.entities.put(gameState, new HashMap());
            this.entitiesToAdd.put(gameState, new HashMap());
            this.entitiesToRemove.put(gameState, new HashMap());
        }
        this.gameState = gameState;
    }
    
    //Getter of the instance
    public static EntityManager getInstance() {
        if(em == null) {
            em = new EntityManager();
        }
        return em;
    }
    
    public Map<String,Entity> getEntities() {
        return entities.get(this.gameState);
    }

    public void clear() {
        entities.get(this.gameState).clear();
        entitiesToAdd.get(this.gameState).clear();
        entitiesToRemove.get(this.gameState).clear();
    }
    
    public void addEntity(String name, Entity entity) {
        entity.setName(name);
        entities.get(this.gameState).put(name, entity);
    }
    
    public void addFutureEntity(String name, Entity entity) {
        entity.setName(name);
        entitiesToAdd.get(this.gameState).put(name, entity);
    }

    public Entity getEntity(String name) {
        return entities.get(this.gameState).get(name);
    }

    public boolean removeEntity(String name) {
        if(entities.get(this.gameState).containsKey(name)) {
            Entity entity = entities.get(this.gameState).get(name);
            entitiesToRemove.get(this.gameState).put(name, entity);
            return true;
        }
        return false;
    }
    
    public void render(GameContainer gc, Graphics g) {
        Collection c = entities.get(this.gameState).values();
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
        c = entities.get(this.gameState).values();
        itr = c.iterator();
        while(itr.hasNext()) {
            Entity entity = (Entity) itr.next();
            if(entity.autoUpdate) {
                entity.update(gc, delta);
            }
        }
        
        // Add new entities
        entities.get(this.gameState).putAll(entitiesToAdd.get(this.gameState));
        entitiesToAdd.get(this.gameState).clear();
        // Remove entitites marked as removed
        this.forceRemoval();
    }

    public ArrayList<Entity> getEntityGroup(String name) {
        ArrayList<Entity> result = new ArrayList<Entity>();
        
        Collection c = entities.get(this.gameState).values();
        Iterator itr = c.iterator();
        while(itr.hasNext()) {
            Entity entity = (Entity) itr.next();
            if(entity.getGroup() == name) {
                result.add(entity);
            }
        }
        
        return result;
    }

    public void removeEntityGroup(String name) {
        Collection c = entities.get(this.gameState).values();
        Iterator itr = c.iterator();
        while(itr.hasNext()) {
            Entity entity = (Entity) itr.next();
            if(entity.getGroup() == name) {
                this.removeEntity(entity.name);
            }
        }
    }

    public void forceRemoval() {
        Collection c = entitiesToRemove.get(this.gameState).values();
        Iterator itr = c.iterator();
        while(itr.hasNext()) {
            entities.get(this.gameState).remove(((Entity)itr.next()).getName());
        }
        entitiesToRemove.get(this.gameState).clear();    
    }
}
