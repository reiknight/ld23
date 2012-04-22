package jam.ld23.game;

public class C {
    public static enum Events {
        CLOSE_WINDOW,
        MOVE_LEFT,
        MOVE_RIGHT,
        MOVE_UP,
        MOVE_DOWN,
        FIRE,
        CROSSHAIR_MOVED;
    }
    
    public static enum Textures {
        ENEMY("resources/enemy.png");
        
        private String path;

        private Textures(String path) {
            this.path = path;
        }
        
        @Override
        public String toString() {
            return path;
        }
    }
}
