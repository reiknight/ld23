package jam.ld23.entities;

public class Item extends Sprite {
    
    private ItemType type;
    
    public Item(ItemType type) {
        super(type.getName());
        this.type = type;
    }
    
}
