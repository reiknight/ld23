package jam.ld23.physics;

import jam.ld23.entities.Entity;

//Singleton
public class PhysicsManager {
    
    //Static instance from PhysicsManager
    private static PhysicsManager pm;
    
    //Private constructor
    private PhysicsManager() {
        
    }
    
    //Getter
    public static PhysicsManager getInstance() {
        if(pm == null) {
            pm = new PhysicsManager();
        }
        return pm;
    }
    
    //TestCollisions
    public boolean testCollisionsEntity(Entity x, Entity y) {
        return x.getR().intersects(y.getR());
    }
    
}
