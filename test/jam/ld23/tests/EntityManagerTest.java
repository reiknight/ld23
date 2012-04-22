package jam.ld23.tests;

import jam.ld23.entities.Entity;
import jam.ld23.entities.EntityManager;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class EntityManagerTest extends TestCase  {
    public static class DummyEntity extends Entity {
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
        em = EntityManager.getInstance();
        em.clear();
        dummyEntity = new DummyEntity();
    }
    
    @After
    public void tearDown() {
    }
    
    public void testGetEntities() {
        assertEquals(0, em.getEntities().size());
    }
   
    public void testAddEntity() { 
        em.addEntity("dummy", dummyEntity);
        assertEquals(1, em.getEntities().size());
    }
    
    public void testGetEntity() {
        em.addEntity("dummy", dummyEntity);
        assertSame(dummyEntity, em.getEntity("dummy"));
    }
    
    public void testGetEntityGroup() {
        DummyEntity e1 = new DummyEntity();
        e1.setGroup("items");
        DummyEntity e2 = new DummyEntity();
        e2.setGroup("items");
        DummyEntity e3 = new DummyEntity();
        e3.setGroup("enemies");
        
        em.addEntity("item_0", e1);
        em.addEntity("item_1", e2);
        em.addEntity("item_2", e3);
        
        ArrayList<Entity> items = em.getEntityGroup("items");
        assertEquals("items", items.get(0).getGroup());
        assertEquals("items", items.get(1).getGroup());
        ArrayList<Entity> enemies = em.getEntityGroup("enemies");
        assertEquals("enemies", enemies.get(0).getGroup());
    }
    
    public void testRemoveEntityWithExistingEntity() {
        em.addEntity("dummy", dummyEntity);
        em.removeEntity("dummy");
        assertEquals(0, em.getEntities().size());
    }
    
    public void testRemoveEntityWithNonExistingKey() {
        assertFalse(em.removeEntity("dummy"));
    }
}
