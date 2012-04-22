package jam.ld23.entities;

import jam.ld23.game.C;

public class Food extends Sprite {
    
    private Size size;
    
    public Food() {
        super(C.Textures.FOOD_NORMAL.name);
        size = Size.NORMAL;
    }
    
    public Food(Size size) {
        //TODO: fuck, esto no lo podemos hacer XD
        /*switch(size) {
            case SMALL:
                super(C.Textures.FOOD_SMALL.name);
                break;
            case MEDIUM:
                super(C.Textures.FOOD_NORMAL.name);
                break;
            case BIG:
                super(C.Textures.FOOD_BIG.name);
                break;
        }*/
        this.size = size;
    }

}
