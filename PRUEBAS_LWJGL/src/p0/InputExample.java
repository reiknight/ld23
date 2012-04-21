package p0;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class InputExample {

    public void start() {
        try {
	    Display.setDisplayMode(new DisplayMode(800, 600));
	    Display.create();
	} catch (LWJGLException e) {
	    e.printStackTrace();
	    System.exit(0);
	}

	// init OpenGL here

        while (!Display.isCloseRequested()) {

	    // render OpenGL here
			
	    pollInput();
	    Display.update();
	}

	Display.destroy();
    }

    public void pollInput() {
		
        if (Mouse.isButtonDown(0)) {
	    int x = Mouse.getX();
	    int y = Mouse.getY();
			
	    System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
	}
		
	if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
	    System.out.println("SPACE KEY IS DOWN");
	}
		
	while (Keyboard.next()) {
	    if (Keyboard.getEventKeyState()) {
	        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
		    System.out.println("A Key Pressed");
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_S) {
		    System.out.println("S Key Pressed");
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_D) {
		    System.out.println("D Key Pressed");
		}
	    } else {
	        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
		    System.out.println("A Key Released");
	        }
	    	if (Keyboard.getEventKey() == Keyboard.KEY_S) {
		    System.out.println("S Key Released");
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_D) {
		    System.out.println("D Key Released");
		}
	    }
	}
    }

    public static void main(String[] argv) {
        InputExample inputExample = new InputExample();
	inputExample.start();
    }
}