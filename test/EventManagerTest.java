import jam.ld23.events.Event;
import jam.ld23.managers.EventManager;
import junit.framework.TestCase;
import org.junit.*;
import static org.junit.Assert.*;
import org.newdawn.slick.GameContainer;

public class EventManagerTest extends TestCase {
    public class DummyEvent extends Event {

        @Override
        public boolean isHappening(GameContainer gc) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    
    }
    
    private static EventManager evm;
    private static DummyEvent dummyEvent;
    
    public EventManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        evm = EventManager.getInstance();
        evm.clear();
        dummyEvent = new DummyEvent();
    }
    
    @After
    public void tearDown() {
    }

    public void testGetEvents() {
        assertEquals(evm.getEvents().size(), 0);
    }
   
    public void testAddEvent() { 
        evm.addEvent("dummy", dummyEvent);
        assertEquals(evm.getEvents().size(), 1);
    }
    
    public void testRemoveEventWithExistingKey() {
        evm.addEvent("dummy", dummyEvent);
        evm.removeEvent("dummy");
        assertEquals(evm.getEvents().size(), 0);
    }
    
    public void testRemoveEventWithNonExistingKey() {
        assertFalse(evm.removeEvent("dummy"));
    }
}
