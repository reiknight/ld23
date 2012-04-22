package jam.ld23.game;

import jam.ld23.entities.Size;

public class C {
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
        MOUTH("mouth", "resources/mouth.jpg");

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
        BULLET("bullet");
        
        public String name;

        private Entities(String name) {
            this.name = name;
        }
    }
    
    public static enum Groups {
        BULLETS("bullets");
        
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