package jam.ld23.game;

import jam.ld23.entities.Player;
import java.io.*;

public class SaveManager {
    
    //Private static Instance of the SaveManager
    private static SaveManager saveManager;
    
    //Private Constructor
    private SaveManager() {
        
    }
    
    //Getter of the instance
    public static SaveManager getInstance() {
        if(saveManager == null) {
            saveManager = new SaveManager();
        }
        return saveManager;
    }
    
    public void saveGame(GameOptions gm, Player pc) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            File f = new File("save.save");
            
            //Deleting if exists
            if(f.exists()) {
                f.delete();
            }
            
            fos = new FileOutputStream(f, true);
            oos = new ObjectOutputStream(fos);
            
            oos.writeObject(gm);
            oos.writeObject(pc);            
        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch(IOException ioe) {
            System.out.println("I/O error: " + ioe.getMessage());
        } finally {
            //Closing OutputStream
            try {
                if (fos != null) {
                    if (oos != null) {
                        oos.close();
                    }
                    fos.close();
                }
            } catch (IOException ioe) {
                System.out.println("I/O error: " + ioe.getMessage());
            }
        }
        
    }
    
    public void loadGame(GameOptions gm, Player pc) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            File f = new File("save.save");
            
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            
            
            gm.setGameMode(((GameOptions)ois.readObject()).getGameMode());
            Player playerLoaded = (Player) ois.readObject();
            pc.setState(playerLoaded);
            
            
        } catch(ClassNotFoundException cnfe) {
            System.out.println("Class Error: " + cnfe.getMessage());
        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch(IOException ioe) {
            System.out.println("I/O error: " + ioe.getMessage());
        } finally {
            //Closing OutputStream
            try {
                if (fis != null) {
                    if (ois != null) {
                        ois.close();
                    }
                    fis.close();
                }
            } catch (IOException ioe) {
                System.out.println("I/O error: " + ioe.getMessage());
            }
        }
    }
    
}
