package jam.ld23.entities;

public enum Size {
    
    SMALL, NORMAL, BIG;
    
    @Override
    public String toString() {
        
        //Must return Small, Normal or Big
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
    
}