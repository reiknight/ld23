package jam.ld23.entities;

import java.io.Serializable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entity implements Serializable {

    //Rectangle for collision
    protected Rectangle r;
    
    //Position
    protected float x;
    protected float y;
    
    //Size
    protected float w;
    protected float h;
    
    //Group
    protected String group;
    
    //Getter, return the rectangle for testing collisions
    public Rectangle getR() {
        return r;
    }

    public void render(GameContainer gc, Graphics g) {
        // Draw bounding box
        g.drawRect(x, y, w, h);
    }

    public void update(GameContainer gc, int delta) {
        //Updating the Rectangle
        r.setLocation(x,y);
    }
   
    //Getters and Setters for the Position Variables
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    //Getters and Setters for the Size Variables
    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }
    
    public String getGroup() {
        return group;
    }
    
    public void setGroup(String group) {
        this.group = group;
    }
}
