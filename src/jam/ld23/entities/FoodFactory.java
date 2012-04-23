package jam.ld23.entities;

import jam.ld23.game.C;
import jam.ld23.logic.LogicManager;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

public class FoodFactory extends Entity {
    private int timer = 0;
    private Random rand = new Random();
    
    public FoodFactory() {
        autoRender = false;
        rand = new Random();
    }
    
    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        timer += delta;
        if(timer > LogicManager.getInstance().getFoodSpawnTime()) {
            timer = 0;
            this.spawnFood();
        }
    }
    
    public void spawnFood() {
        EntityManager em = EntityManager.getInstance();
        Food food = new Food(rand.nextInt(3), Size.BIG);
        food.setRotatingSpeed((rand.nextInt(20) - 10) * .01F);
        food.setPosition(new Vector2f(C.SCREEN_WIDTH,
                C.Positions.PLAYER_LIMIT_TOP.position.y + rand.nextFloat() * (C.Positions.PLAYER_LIMIT_BOTTOM.position.y - C.Positions.PLAYER_LIMIT_TOP.position.y + this.getWidth())));
        em.addFutureEntity(food.name, food);
    }    
}
