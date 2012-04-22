package jam.ld23.textures;

import java.util.HashMap;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TextureManager {
    private HashMap<String,Image> textures;
    
    //Static instance from PhsysicsManager
    private static TextureManager instance;
    
    //Private constructor
    private TextureManager() {
        textures = new HashMap<>();
    }
    
    //Getter
    public static TextureManager getInstance() {
        if(instance == null) {
            instance = new TextureManager();
        }
        return instance;
    }
    
    public void addTexture(String name, String textureFileName) throws SlickException {
        textures.put(name, new Image(textureFileName));
    }
    
    public void addSubTexture(String parentName, String name, int x, int y, int w, int h) throws SlickException {
        if(textures.containsKey(parentName)) {
            textures.put(name, textures.get(parentName).getSubImage(x, y, h, h));
        }
    }
    
    public Image getTexture(String name) {
        return textures.get(name);
    }
    
    public boolean removeEntity(String name) {
        if(textures.containsKey(name)) {
            textures.remove(name);
            return true;
        }
        return false;
    }
}
