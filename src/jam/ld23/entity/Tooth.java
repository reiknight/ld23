package jam.ld23.entity;

public class Tooth extends Sprite {
    
    //A boolean which said if the tooth is decayed or not (has enemy tourret)
    private boolean decayed;

    public boolean isDecayed() {
        return decayed;
    }

    public void setDecayed(boolean decayed) {
        this.decayed = decayed;
    }

}
