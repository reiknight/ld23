package jam.ld23.entity.interfaces;

public enum ItemType {
    
    TYPE_1("powerup1"), TYPE_2("powerup2");
    
    private String name;

    private ItemType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public String getResource() {
        return "resource/" + name + ".png";
    }
    
}
