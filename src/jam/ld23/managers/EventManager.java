package jam.ld23.managers;

import jam.ld23.events.Event;
import java.util.HashMap;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class EventManager {
    
    //Static instance from EventManager
    private static EventManager evm;
    
    private HashMap<String,Event> events;
    
    private EventManager() {
      events = new HashMap<String,Event>() {};
    }
    
    //Getter of the instance
    public static EventManager getInstance() {
        if(evm == null) {
            evm = new EventManager();
        }
        return evm;
    }
            
    public HashMap<String,Event> getEvents() {
        return events;
    }

    public void clear() {
        events.clear();
    }
    
    public void addEvent(String name, Event event) {
        events.put(name, event);
    }
    
    public Event getEvent(String name) {
        return events.get(name);
    }

    public boolean removeEvent(String name) {
        if(events.containsKey(name)) {
            events.remove(name);
            return true;
        }
        return false;
    }
    
    public boolean isHappening(String name, GameContainer gc) {
        return events.get(name).isHappening(gc);
    }
}
