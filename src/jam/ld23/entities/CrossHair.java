package jam.ld23.entities;

import infinitedog.frisky.entities.Sprite;
import jam.ld23.events.EventManager;
import jam.ld23.events.InputEvent;
import jam.ld23.game.C;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class CrossHair extends Sprite {
    public CrossHair() {
        super(C.Textures.CROSSHAIR.name);
        autoRender = false;
    }
    
    @Override
    public void update(GameContainer gc, int delta) {
        EventManager em = EventManager.getInstance();
        if(em.isHappening(C.Events.CROSSHAIR_MOVED.name, gc)) {
            Input input = ((InputEvent)em.getEvent(C.Events.CROSSHAIR_MOVED.name)).getInput();
            setPosition(new Vector2f(input.getMouseX() - getWidth() / 2, input.getMouseY() - getHeight() / 2));
        }
    }
}
