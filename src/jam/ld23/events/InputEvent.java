package jam.ld23.events;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public class InputEvent extends Event {

    public static final int KEYBOARD = 0;
    public static final int MOUSE_CLICK = 1;
    public static final int MOUSE_MOVE = 2;
    
    private int type;
    private Input input;
    private Object data;
    
    public InputEvent(int type, Object data) {
        this.type = type;
        this.data = data;
        this.input = null;
    }
    
    @Override
    public boolean isHappening(GameContainer gc) {
        this.input = gc.getInput();
        
        switch(type) {
            case KEYBOARD:
                return input.isKeyDown((int) data);
            case MOUSE_CLICK:
                return input.isMouseButtonDown((int) data);
            case MOUSE_MOVE:
                Rectangle r = new Rectangle(input.getMouseX(), input.getMouseY(), 1, 1);
                return r.intersects((Rectangle) data);
            default:
                throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    public Input getInput() {
        return input;
    }
}
