package jam.ld23.entities;

import jam.ld23.events.EventConstantSheet;
import jam.ld23.events.EventManager;
import jam.ld23.events.InputEvent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class CrossHair extends Sprite implements EventConstantSheet {
    public CrossHair() {
        super("crosshair");
    }
    
    public void update(GameContainer gc, int delta) {
        EventManager em = EventManager.getInstance();
        if(em.isHappening(CROSSHAIR_MOVED, gc)) {
            Input input = ((InputEvent)em.getEvent(CROSSHAIR_MOVED)).getInput();
            x = input.getMouseX() - w / 2;
            y = input.getMouseY() - h / 2;
        }
    }
}
