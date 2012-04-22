package jam.ld23.entities;

import jam.ld23.events.EventManager;
import jam.ld23.game.C;
import jam.ld23.sounds.SoundManager;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

public class Food extends Sprite {
    
    private Size size;
    private float speed = (float) 0.1;
    private static int idBig = 0;
    private static int idNormal = 0;
    private static int idSmall = 0;
    private static Random rand;
    private float rotatingSpeed;

    //Initialize block for the two constructors
    {
        rand = new Random();
        rotatingSpeed = (rand.nextInt(20)-10) * .01F;
        setPosition(new Vector2f(C.SCREEN_WIDTH, rand.nextFloat()*C.SCREEN_HEIGHT));
        group = C.Groups.FOOD.name;
    }
    
    public Food() {
        super(C.Textures.FOOD_NORMAL.name);
        size = Size.NORMAL;
        name = C.Entities.FOOD_SMALL.name + idNormal++;
    }
    
    public Food(Size size) {
        String texture = null;
        switch(size) {
            case SMALL:
                texture = C.Textures.FOOD_SMALL.name;
                name = C.Entities.FOOD_SMALL.name + idSmall++;
                break;
            case NORMAL:
                texture = C.Textures.FOOD_NORMAL.name;
                name = C.Entities.FOOD_NORMAL.name + idNormal++;
                break;
            case BIG:
                texture = C.Textures.FOOD_BIG.name;
                name = C.Entities.FOOD_BIG.name + idBig++;
                break;
        }
        this.size = size;
        this.setTexture(texture);
    }
    
    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        EntityManager em = EntityManager.getInstance();
        EventManager evm = EventManager.getInstance();
        SoundManager sm = SoundManager.getInstance();
        
        //Food movement
        float x = getX();
        float y = getY();
        x -= speed * delta;
        setPosition(new Vector2f(x, y));
        
        image.rotate(rotatingSpeed);
    }
    
    

}
