package jam.ld23.gui;

import jam.ld23.entities.Entity;
import jam.ld23.entities.Sprite;
import jam.ld23.game.C;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Button extends Sprite {
    private static int id = 0;  
    private String label;
    
    public Button(String textureName, String label) {
        this.setTexture(textureName);
        this.label = label;
        this.name = C.Entities.BULLET.name + id++;
        this.group = C.Groups.BUTTONS.name;
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
        g.drawString(label, getX(), getY());
    }
}
