package jam.ld23.game;

import jam.ld23.entities.Size;
import org.newdawn.slick.geom.Vector2f;

public class C {
    public static final boolean DEBUG_MODE = true;
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
        LOAD_GAME("load_game"),
        NEXT_STATE("next_state"),
        PAUSE_GAME("pause_game");
        
        public String name;

        private Events(String name) {
            this.name = name;
        }
    }
    
    public static enum Textures {
        ENEMY("enemy", "resources/enemy.png"),    
        PLAYER("player", "resources/player.png"),
        FOOD_SMALL_0("foodSmall0", "resources/food" + Size.SMALL + "0.png"),
        FOOD_NORMAL_0("foodNormal0", "resources/food" + Size.NORMAL + "0.png"),
        FOOD_BIG_0("foodBig0", "resources/food" + Size.BIG + "0.png"),
        FOOD_SMALL_1("foodSmall1", "resources/food" + Size.SMALL + "1.png"),
        FOOD_NORMAL_1("foodNorma1", "resources/food" + Size.NORMAL + "1.png"),
        FOOD_BIG_1("foodBig1", "resources/food" + Size.BIG + "1.png"),
        FOOD_SMALL_2("foodSmall2", "resources/food" + Size.SMALL + "2.png"),
        FOOD_NORMAL_2("foodNormal2", "resources/food" + Size.NORMAL + "2.png"),
        FOOD_BIG_2("foodBig2", "resources/food" + Size.BIG + "2.png"),
        MOUTHWASH("mouthwash", "resources/mouthwash.png"),
        CROSSHAIR("crosshair", "resources/crosshair.png"),
        BULLET("bullet", "resources/bullet.png"),
        ENEMY_BULLET("enemy_bullet", "resources/enemy_bullet.png"),
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
        TEETH("teeth"),
        ENEMIES("enemies");
        
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

    public static enum Positions {
        PLAYER(new Vector2f(0, 300)),
        PLAYER_LIMIT_TOP(new Vector2f(0, 100)),
        PLAYER_LIMIT_BOTTOM(new Vector2f(0, 480)),
        PLAYER_LIMIT_RIGHT(new Vector2f(700, 0)),
        TOOTH_BOTTOM_0(new Vector2f(2, 490)),
        TOOTH_BOTTOM_1(new Vector2f(140, 490)),
        TOOTH_BOTTOM_2(new Vector2f(318, 493)),
        TOOTH_BOTTOM_3(new Vector2f(448, 493)),
        TOOTH_BOTTOM_4(new Vector2f(560, 470)),
        TOOTH_TOP_0(new Vector2f(2, 30)),
        TOOTH_TOP_1(new Vector2f(100, 50)),
        TOOTH_TOP_2(new Vector2f(265, 55)),
        TOOTH_TOP_3(new Vector2f(400, 60)),
        TOOTH_TOP_4(new Vector2f(530, 50));
        
        public Vector2f position;
        
        private Positions(Vector2f position) {
            this.position = position;
        }
    }
    
    public static enum Dimensions {
        TOOTH_BOTTOM_0(new Vector2f(108, 88)),
        TOOTH_BOTTOM_1(new Vector2f(158, 100)),
        TOOTH_BOTTOM_2(new Vector2f(123, 100)),
        TOOTH_BOTTOM_3(new Vector2f(110, 100)),
        TOOTH_BOTTOM_4(new Vector2f(110, 110)),
        TOOTH_TOP_0(new Vector2f(90, 110)),
        TOOTH_TOP_1(new Vector2f(158, 100)),
        TOOTH_TOP_2(new Vector2f(123, 100)),
        TOOTH_TOP_3(new Vector2f(110, 100)),
        TOOTH_TOP_4(new Vector2f(110, 110));
        
        public Vector2f dimension;
        
        private Dimensions(Vector2f dimension) {
            this.dimension = dimension;
        }
    }
    
    public static enum Logic {
        PLAYER_SPEED((float)0.5),
        PLAYER_RELOAD_TIME(200),
        BULLET_SPEED((float)0.5),
        ENEMY_BULLET_DAMAGE(1),
        ENEMY_DAMAGE(2),
        TOOTH_RELOAD_TIME(1000),
        FOOD_RELOAD_TIME(2000),
        SELECT_OPTION_DELAY(500);
        
        public Object data;
        
        private Logic(Object data) {
            this.data = data;
        }
    }
    
    public static enum States {
        START_STATE(0),
        MAIN_STATE(1),
        GAME_OVER_STATE(2);
        
        public int value;
        
        private States(int value) {
            this.value = value;
        }
    }
}