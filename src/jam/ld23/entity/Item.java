package jam.ld23.entity;

import jam.ld23.entity.interfaces.ItemType;

public class Item extends Sprite {
    
    private ItemType type;
    
    public Item(ItemType type) {
        super(type.getName());
        this.type = type;
    }
    
}
