/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import jam.ld23.events.Event;
import jam.ld23.managers.EventManager;
import junit.framework.TestCase;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Héctor
 */
public class EventManagerTest extends TestCase {
    public class DummyEvent extends Event {
    
    }
    
    private static EventManager vm;
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
        vm = new EventManager();
        dummyEvent = new DummyEvent();
    }
    
    @After
    public void tearDown() {
    }

    public void testGetEvents() {
        assertEquals(vm.getEvents().size(), 0);
    }
   
    public void testAddEvent() { 
        vm.addEvent("dummy", dummyEvent);
        assertEquals(vm.getEvents().size(), 1);
    }
    
    public void testRemoveEventWithExistingKey() {
        vm.addEvent("dummy", dummyEvent);
        vm.removeEvent("dummy");
        assertEquals(vm.getEvents().size(), 0);
    }
    
    public void testRemoveEventWithNonExistingKey() {
        assertFalse(vm.removeEvent("dummy"));
    }
}