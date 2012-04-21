package jam.ld23.entity;

import jam.ld23.entity.interfaces.EntityConstantSheet;
import org.newdawn.slick.SlickException;

public class Enemy extends Sprite {
    public Enemy() throws SlickException {
        super(EntityConstantSheet.ENEMY);
    }
}
