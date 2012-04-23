package jam.ld23.logic;

import jam.ld23.game.C;

public class LogicManager {
    
    private static LogicManager lm;
    
    private int score = 0;
    private C.GameModes gameMode;
    private int foodSpawnTime;
    
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

    public C.GameModes getGameMode() {
        return gameMode;
    }

    public void setGameMode(C.GameModes gameMode) {
        this.gameMode = gameMode;
        this.foodSpawnTime = gameMode.getBaseFoodSpawnTime();
    }

    public int getFoodSpawnTime() {
        return foodSpawnTime;
    }

    public void setFoodSpawnTime(int foodSpawnTime) {
        this.foodSpawnTime = foodSpawnTime;
    }
}
