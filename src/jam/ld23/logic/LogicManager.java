package jam.ld23.logic;

import jam.ld23.game.GameMode;

public class LogicManager {
    
    private static LogicManager lm;
    
    private int score;
    private GameMode gameMode;
    
    private LogicManager() {
        
    }
    
    public static LogicManager getInstance() {
        if(lm == null) {
            lm = new LogicManager();
        }
        return lm;
    }
    
    public void addScore(int i) {
        score += i;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
    
}
