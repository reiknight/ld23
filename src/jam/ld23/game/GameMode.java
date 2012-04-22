package jam.ld23.game;

public enum GameMode {
    
    EASY_MODE(10,3,2),NORMAL_MODE(8,2,1),HARD_MODE(5,2,0);
    
    private int life;
    private int bombs;
    private int continues;
    
    GameMode(int life, int bombs, int continues) {
        this.life = life;
        this.bombs = bombs;
        this.continues = continues;
    }

    public int getBombs() {
        return bombs;
    }

    public int getContinues() {
        return continues;
    }

    public int getLife() {
        return life;
    }
    
}
