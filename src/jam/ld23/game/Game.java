package jam.ld23.game;
 
import org.newdawn.slick.*;
 
public class Game extends BasicGame {
 
    private Image land = null;
    private Image plane = null;
    private float x = 400;
    private float y = 300;
    private float scale = 1.0f;
    private EntityManager em;
    
    public Game()
    {
        super("Slick2DPath2Glory - SimpleGame");
        em = new EntityManager();
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
        land = new Image("resources/land.jpg");
        plane = new Image("resources/player.png");
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
        Input input = gc.getInput();
 
        if(input.isKeyDown(Input.KEY_A))
        {
            plane.rotate(-0.2f * delta);
        }
 
        if(input.isKeyDown(Input.KEY_D))
        {
            plane.rotate(0.2f * delta);
        }
 
        if(input.isKeyDown(Input.KEY_W))
        {
            float hip = 0.4f * delta;
 
            float rotation = plane.getRotation();
 
            x+= hip * Math.sin(Math.toRadians(rotation));
            y-= hip * Math.cos(Math.toRadians(rotation));
        }
 
        if(input.isKeyDown(Input.KEY_2))
        {
            scale += (scale >= 5.0f) ? 0 : 0.1f;
            plane.setCenterOfRotation(plane.getWidth()/2.0f*scale, plane.getHeight()/2.0f*scale);
        }
        if(input.isKeyDown(Input.KEY_1))
        {
            scale -= (scale <= 1.0f) ? 0 : 0.1f;
            plane.setCenterOfRotation(plane.getWidth()/2.0f*scale, plane.getHeight()/2.0f*scale);
        }
        
        // close window with esc
        if(input.isKeyDown(Input.KEY_ESCAPE)) {
            gc.exit();
        }
    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
        // Window origin is in top left
        land.draw(0, 0);
        plane.draw(x, y, scale);
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         AppGameContainer app = 
			new AppGameContainer(new Game());
 
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}