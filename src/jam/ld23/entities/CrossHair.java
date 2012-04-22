package jam.ld23.entities;

import jam.ld23.events.EventManager;
import jam.ld23.events.InputEvent;
import jam.ld23.game.C;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class CrossHair extends Sprite {
    public CrossHair() {
        super(C.Textures.CROSSHAIR.name);
    }
    
    public void update(GameContainer gc, int delta) {
        EventManager em = EventManager.getInstance();
        if(em.isHappening(C.Events.CROSSHAIR_MOVED.name, gc)) {
            Input input = ((InputEvent)em.getEvent(C.Events.CROSSHAIR_MOVED.name)).getInput();
            x = input.getMouseX() - w / 2;
            y = input.getMouseY() - h / 2;
        }
    }
}
