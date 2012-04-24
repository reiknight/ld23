package jam.ld23.game;

import jam.ld23.entities.Size;
import org.newdawn.slick.geom.Vector2f;

public class C {
    public static final boolean DEBUG_MODE = false;
    public static final boolean GOD_MODE = false;
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
        PAUSE_GAME("pause_game"),
        CLICK_BUTTON("click_button"),
        BOMB("bomb");
        
        public String name;

        private Events(String name) {
            this.name = name;
        }
    }
    
    public static enum Scores {
        ENEMY(8),
        ENEMY_TOOTH(15),
        FOOD_SMALL(3),
        FOOD_NORMAL(2),
        FOOD_BIG(1);
        
        public int score;
        
        private Scores(int score) {
            this.score = score;
        }
    }
    
    public static enum Textures {
        START_BACKGROUND("start_background", "resources/inicio.jpg"),
        CREDITS_BACKGROUND("credits_background", "resources/credits.jpg"),
        GAME_OVER_BACKGROUND("game_over_background", "resources/gameover.jpg"),
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
        PLOP("plop", "resources/plop.png"),
        MOUTHWASH("mouthwash", "resources/mouthwash.png"),
        CROSSHAIR("crosshair", "resources/crosshair.png"),
        BULLET("bullet", "resources/bullet.png"),
        ENEMY_BULLET("enemy_bullet", "resources/enemy_bullet.png"),
        MOUTH("mouth", "resources/mouth.jpg"),
        TEETH("teeth", "resources/teeth.png"),
        TOOTH_DECAY("tooth_decay", "resources/tooth_decay.png"),
        TOOTH_DECAY_DEAD("tooth_decay_dead", "resources/bacteriadiente2.png"),
        BUTTON_0("button_0", "resources/menu1.png"),
        BUTTON_1("button_1", "resources/menu2.png"),
        BUTTON_2("button_2", "resources/menu3.png"),
        SCORE("score", "resources/score.png"),
        HEART("heart", "resources/heart.png"),
        ITEM("item", "resources/item.png");

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
        TOOTH_TOP_4("tooth_top_4"),
        FOOD_FACTORY("food_factory"),
        BUTTON("button"),
        ITEM("item");

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
        ENEMIES("enemies"),
        BUTTONS("buttons");
        
        public String name;

        private Groups(String name) {
            this.name = name;
        }
    }
    
    public static enum Sounds {
        MUSIC("music", "resources/music.ogg"),
        FIRE("fire", "resources/fire.wav"),
        OPENING("opening","resources/moothlyOpening.wav"),
        GAME_OVER("gameover","resources/gameOver.wav");
        
        public String name;
        public String path;

        private Sounds(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }

    public static enum Positions {
        PLAYER(new Vector2f(0, 300)),
        PLAYER_LIMIT_TOP(new Vector2f(0, 150)),
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
        TOOTH_RELOAD_TIME(2500),
        FOOD_RELOAD_TIME(2000),
        SELECT_OPTION_DELAY(500),
        BOMB_DELAY(1000),
        FOOD_DIE_TIME(1000),
        ENEMY_DIE_TIME(1000),
        TOOTH_DECAY_DIE_TIME(1000);
        
        public Object data;
        
        private Logic(Object data) {
            this.data = data;
        }
    }
    
    public static enum States {
        BASE_STATE("Base", -1),
        START_STATE("Start", 0),
        MAIN_STATE("Main", 1),
        GAME_OVER_STATE("GameOver", 2),
        CREDITS_STATE("Credits", 3),
        INSTRUCTIONS_STATE("Instructions", 4);
        
        public String name;
        public int value;
        
        private States(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }
    
    public static enum GameModes {
        EASY_MODE(10,3,2,1500,.33F),
        NORMAL_MODE(8,2,1,1000,.66F),
        HARD_MODE(5,2,0,500,1);

        private int life;
        private int bombs;
        private int continues;
        private int baseFoodSpawnTime; //in ms
        private float baseDecaySpawnProbability;

        GameModes(int life, int bombs, int continues, int baseFoodSpawnTime, float baseDecaySpawnProbability) {
            this.life = life;
            this.bombs = bombs;
            this.continues = continues;
            this.baseFoodSpawnTime = baseFoodSpawnTime;
            this.baseDecaySpawnProbability = baseDecaySpawnProbability;
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
        
        public int getBaseFoodSpawnTime() {
            return baseFoodSpawnTime;
        }

        public float getBaseDecaySpawnProbability() {
            return baseDecaySpawnProbability;
        }
        
    }
    
    public static enum Buttons {
        START_GAME(Textures.BUTTON_0.name, "Start", new Vector2f(600, 175), new Vector2f(45, 25)),
        INSTRUCTIONS(Textures.BUTTON_1.name, "Instructions", new Vector2f(600, 275), new Vector2f(15, 25)),
        CREDITS(Textures.BUTTON_2.name, "Credits", new Vector2f(600, 375), new Vector2f(40, 25)),
        EASY(Textures.BUTTON_0.name, "Easy", new Vector2f(600, 175), new Vector2f(45, 25)),
        NORMAL(Textures.BUTTON_1.name, "Normal", new Vector2f(600, 275), new Vector2f(40, 25)),
        HARD(Textures.BUTTON_2.name, "Hard", new Vector2f(600, 375), new Vector2f(45, 25));
        
        public String textureName;
        public String label;
        public Vector2f position;
        public Vector2f labelPosition;
        
        Buttons(String textureName, String label, Vector2f position, Vector2f labelPosition) {
            this.textureName = textureName;
            this.label = label;
            this.position = position;
            this.labelPosition = labelPosition;
        }
    }
}