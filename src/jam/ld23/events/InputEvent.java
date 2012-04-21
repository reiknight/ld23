package jam.ld23.events;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class InputEvent extends Event {

    public static final int KEYBOARD = 0;
    public static final int MOUSE = 1;
    
    private int type;
    private int code;
    
    public InputEvent(int type, int code) {
        this.type = type;
        this.code = code;
    }
    
    @Override
    public boolean isHappening(GameContainer gc) {
        Input input = gc.getInput();
        
        switch(type) {
            case KEYBOARD:
                return input.isKeyDown(code);
            case MOUSE:
                return input.isMouseButtonDown(code);
            default:
                throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
}
