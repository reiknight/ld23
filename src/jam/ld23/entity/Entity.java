package jam.ld23.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entity {

    //Rectangle for collision
    protected Rectangle r;
    
    //Position
    protected float x;
    protected float y;
    
    //Getter, return the rectangle for testing collisions
    public Rectangle getR() {
        return r;
    }

    public abstract void render(GameContainer gc, Graphics g);

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
    
}
