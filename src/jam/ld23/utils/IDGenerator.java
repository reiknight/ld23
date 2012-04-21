package jam.ld23.utils;

public class IDGenerator {
    private int id;
    
     //Static instance from IDGenerator
    private static IDGenerator instance;
    
    //Private constructor
    private IDGenerator() {
        id = 0;
    }
    
    //Getter
    public static IDGenerator getInstance() {
        if(instance == null) {
            instance = new IDGenerator();
        }
        return instance;
    }
    
    public int nextID() {
        return id++;
    }
}
