/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Interfaz base para todos los objetos del juego
 *
 * @author David
 */
public interface Entity {
    public void update(GameContainer gc, int delta);
    public void render(GameContainer gc, Graphics g);
}
