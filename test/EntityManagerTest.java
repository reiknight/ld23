import junit.framework.TestCase;
import org.junit.*;
import static org.junit.Assert.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import project1.Entity;
import project1.EntityManager;

/**
 *
 * @author David
 */



public class EntityManagerTest extends TestCase  {
    public static class DummyEntity implements Entity {

        @Override
        public void update(GameContainer gc, int delta) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void render(GameContainer gc, Graphics g) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    
    }
    
    private static EntityManager em;
    private static DummyEntity dummyEntity;
    
    public EntityManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        em = new EntityManager();
        dummyEntity = new DummyEntity();
    }
    
    @After
    public void tearDown() {
    }
    
    public void testGetEntities() {
        assertEquals(em.getEntities().size(), 0);
    }
   
    public void testAddEntity() { 
        em.addEntity("dummy", dummyEntity);
        assertEquals(em.getEntities().size(), 1);
    }
    
    public void testGetEntity() {
        em.addEntity("dummy", dummyEntity);
        assertSame(em.getEntity("dummy"), dummyEntity);
    }
    
    public void testRemoveEntityWithExistingEntity() {
        em.addEntity("dummy", dummyEntity);
        em.removeEntity("dummy");
        assertEquals(em.getEntities().size(), 0);
    }
    
    public void testRemoveEntityWithNonExistingKey() {
        assertFalse(em.removeEntity("dummy"));
    }
}
