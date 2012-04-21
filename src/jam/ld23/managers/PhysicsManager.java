package jam.ld23.managers;

import jam.ld23.entity.Entity;

//Singleton
public class PhysicsManager {
    
    //Instance from PhsysicsManager
    PhysicsManager pm;
    
    //Private constructor
    private PhysicsManager() {
        
    }
    
    //Getter
    public PhysicsManager getInstance() {
        if(pm == null) {
            pm = new PhysicsManager();
        }
        return pm;
    }
    
    //TestCollisions
    public boolean testCollisionsEntity(Entity x, Entity y) {
        if(x.getR().intersects(y.getR())) {
            return true;
        } else {
            return false;
        }
    }
    
}
