package jam.ld23.entities;

import jam.ld23.game.C;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

public class Item extends Sprite {
    
    private float speed;
    
    public Item() {
        super(C.Textures.ITEM.name);
        speed = .3F;
        name = "item";
        setPosition(new Vector2f(20,0));
    }

    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        addPosition(new Vector2f(0,speed*delta));
    }
    
}
