package jam.ld23.game;

import jam.ld23.entities.Size;

public class C {
    
    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 800;
    
    public static enum Events {
        CLOSE_WINDOW("close_window"),
        MOVE_LEFT("move_left"),
        MOVE_RIGHT("move_right"),
        MOVE_UP("move_up"),
        MOVE_DOWN("move_down"),
        FIRE("fire"),
        CROSSHAIR_MOVED("crosshair_moved"),
        SAVE_GAME("save_game"),
        LOAD_GAME("load_game");
        
        public String name;

        private Events(String name) {
            this.name = name;
        }
    }
    
    public static enum Textures {
        ENEMY("enemy", "resources/enemy.png"),    
        PLAYER("player", "resources/player.png"),
        FOOD_SMALL("food_small", "resources/food" + Size.SMALL + ".png"),
        FOOD_NORMAL("food_normal", "resources/food" + Size.NORMAL + ".png"),
        FOOD_BIG ("food_big", "resources/food" + Size.BIG + ".png"),
        MOUTHWASH("mouthwash", "resources/mouthwash.png"),
        CROSSHAIR("crosshair", "resources/crosshair.png"),
        BULLET("bullet", "resources/bullet.png"),
        MOUTH("mouth", "resources/mouth.jpg"),
        TEETH("teeth", "resources/teeth.png"),
        TOOTH_DECAY("tooth_decay", "resources/tooth_decay.png");

        public String name;
        public String path;

        private Textures(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }
    
    public static enum Entities {
        PLAYER("player"),
        ENEMY("enemy"),
        FOOD_SMALL("food" + Size.SMALL),
        FOOD_NORMAL("food" + Size.NORMAL),
        FOOD_BIG("food" + Size.BIG),
        MOUTHWASH("mouthwash"),              
        CROSSHAIR("crosshair"),
        BULLET("bullet"),
        TEETH("teeth"),
        TOOTH_BOTTOM_0("tooth_bottom_0"),
        TOOTH_BOTTOM_1("tooth_bottom_1"),
        TOOTH_BOTTOM_2("tooth_bottom_2"),
        TOOTH_BOTTOM_3("tooth_bottom_3"),
        TOOTH_BOTTOM_4("tooth_bottom_4"),
        TOOTH_TOP_0("tooth_top_0"),
        TOOTH_TOP_1("tooth_top_1"),
        TOOTH_TOP_2("tooth_top_2"),
        TOOTH_TOP_3("tooth_top_3"),
        TOOTH_TOP_4("tooth_top_4");

        public String name;

        private Entities(String name) {
            this.name = name;
        }
    }
    
    public static enum Groups {
        BULLETS("bullets"),
        ENEMY_BULLETS("enemy_bullets"),
        FOOD("food"),
        TEETH("teeth");
        
        public String name;

        private Groups(String name) {
            this.name = name;
        }
    }
    
    public static enum Sounds {
        MUSIC("music", "resources/music.ogg"),
        FIRE("fire", "resources/fire.wav");
        
        public String name;
        public String path;

        private Sounds(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }

}