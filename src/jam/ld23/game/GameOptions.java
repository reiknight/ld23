package jam.ld23.game;

import java.io.Serializable;

public class GameOptions implements Serializable {
    
    //Private Static Instance
    private static GameOptions gameOptions;
    
    //Game options:
    private GameMode gameMode;
    private float musicVolume;
    private float effectVolume;

    private GameOptions() {
        gameMode = GameMode.NORMAL_MODE;
    }
    
    public static GameOptions getInstance() {
        if(gameOptions == null) {
            gameOptions = new GameOptions();
        } 
        return gameOptions;
    }

    public float getEffectVolume() {
        return effectVolume;
    }

    public void setEffectVolume(float effectVolume) {
        this.effectVolume = effectVolume;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }
            
}
