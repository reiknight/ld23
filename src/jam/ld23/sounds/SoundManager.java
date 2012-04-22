package jam.ld23.sounds;

import java.util.HashMap;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundManager {
    //Static instance from SoundManager
    private static SoundManager instance;
    
    private HashMap<String,Sound> sounds;
    private HashMap<String,Music> musics;
    
    //Private constructor
    private SoundManager() {
        sounds = new HashMap<>();
        musics = new HashMap<>();
    }
    
    //Getter
    public static SoundManager getInstance() {
        if(instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }
    
    public void clear() {
        sounds.clear();
        musics.clear();
    }
    
    public void addSound(String name, String soundFileName) throws SlickException {
        sounds.put(name, new Sound(soundFileName));
    }

    public Sound getSound(String name) {
        return sounds.get(name);
    }

    public boolean removeSound(String name) {
        if(sounds.containsKey(name)) {
            sounds.remove(name);
            return true;
        }
        return false;
    }
    
    public void playSound(String name) {
        if(sounds.containsKey(name)) {
            ((Sound) sounds.get(name)).play();
        }
    }
    
    public void addMusic(String name, String musicFileName) throws SlickException {
        musics.put(name, new Music(musicFileName));
    }

    public Music getMusic(String name) {
        return musics.get(name);
    }

    public boolean removeMusic(String name) {
        if(musics.containsKey(name)) {
            musics.remove(name);
            return true;
        }
        return false;
    }
    
    public void playMusic(String name) {
        if(musics.containsKey(name)) {
            ((Music) musics.get(name)).play();
        }
    }
}
