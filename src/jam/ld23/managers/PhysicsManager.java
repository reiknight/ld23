package jam.ld23.managers;

import jam.ld23.entity.Entity;

//Singleton
public class PhysicsManager {
    
    //Instance from PhsysicsManager
    static PhysicsManager pm;
    
    //Private constructor
    private PhysicsManager() {
        
    }
    
    //Getter
    public final static PhysicsManager getInstance() {
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
