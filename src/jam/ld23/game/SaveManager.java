package jam.ld23.game;

import jam.ld23.entities.EntityManager;
import java.io.*;
import java.util.HashMap;

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
    
    public void saveGame() {
        EntityManager em = EntityManager.getInstance();
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            File f = new File("save.save");
            
            //Deleting if exists
            if(f.exists()) {
                f.delete();
            }
            
            fos = new FileOutputStream(f,true);
            oos = new ObjectOutputStream(fos);
            
            oos.writeObject(em.getEntities());           
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
    
    public void loadGame() {
        EntityManager em = EntityManager.getInstance();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            File f = new File("save.save");
            
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            em.removeEntities();
            em.forceRemoval();
            em.setEntities((HashMap<String,HashMap<String,Entity>>)ois.readObject());
            
            
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
