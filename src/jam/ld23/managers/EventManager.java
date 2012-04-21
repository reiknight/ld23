package jam.ld23.managers;

import jam.ld23.entity.Entity;
import jam.ld23.events.Event;
import java.util.HashMap;
import org.newdawn.slick.GameContainer;

public class EventManager {
    private HashMap<String,Event> events;
    
    public EventManager() {
      events = new HashMap<String,Event>() {};
    }
      
    public HashMap<String,Event> getEvents() {
        return events;
    }

    public void addEvent(String name, Event event) {
        events.put(name, event);
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
