package jam.ld23.entities;

import jam.ld23.game.C;
import java.io.Serializable;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity implements Serializable {

    //Rectangle for collision
    protected Rectangle r;    
    //Name
    protected String name;
    //Group
    protected String group;
    
    //Entity manager will not update this entity if false
    protected boolean autoUpdate = true;
    //Entity manager will not render this entity if false
    protected boolean autoRender = true;
    
    public Entity() {
        r = new Rectangle(0,0,0,0);
    }
    
    //Getter, return the rectangle for testing collisions
    public Rectangle getR() {
        return r;
    }

    public void render(GameContainer gc, Graphics g) {
        // Draw bounding box
        if(C.DEBUG_MODE) {
            g.setColor(Color.black);
            g.drawRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        }
    }

    public void update(GameContainer gc, int delta) {
        
    }
   
    //Getters and Setters for the Position Variables
    protected float getX() {
        return r.getX();
    }

    protected void setX(float x) {
        r.setX(x);
    }

    protected float getY() {
        return r.getY();
    }

    protected void setY(float y) {
        r.setX(y);
    }
    
    //Getters and Setters for the Size Variables
    protected float getWidth() {
        return r.getWidth();
    }

    protected void setWidth(float w) {
        r.setWidth(w);
    }

    protected float getHeight() {
        return r.getHeight();
    }

    protected void setHeight(float h) {
        r.setHeight(h);
    }
    
    public String getGroup() {
        return group;
    }
    
    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Vector2f position) {
        r.setX(position.x);
        r.setY(position.y);
    }
    
    public void addPosition(Vector2f position) {
        r.setX(r.getX() + position.x);
        r.setY(r.getY() + position.y);
    }
    
    protected void setSize(Vector2f size) {
        r.setWidth(size.x);
        r.setHeight(size.y);
    }
    
    protected Vector2f getCenter() {
        return new Vector2f(r.getX() + r.getWidth() / 2, r.getY() + r.getHeight() / 2);
    }
    
    //return true if entity is out of bounds
    protected boolean outOfBounds(Rectangle r) {
        return !getR().intersects(r);
    }
}
