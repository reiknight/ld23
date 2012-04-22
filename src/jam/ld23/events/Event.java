package jam.ld23.events;

import org.newdawn.slick.GameContainer;

public abstract class Event {
    protected int timer;
    protected int time;
    
    public abstract boolean isHappening(GameContainer gc);    
    
       
    public void update(GameContainer gc, int delta) {
        this.timer += delta;
    }
}
