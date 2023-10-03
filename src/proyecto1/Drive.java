package proyecto1;

import java.util.concurrent.Semaphore;

public class Drive {
    static Semaphore semCon = new Semaphore(0);
    static Semaphore semDrive = new Semaphore(1);
     
    int narratives, levels, sprites, sistems, dlcs;
    int narrativesMax, levelsMax, spritesMax, sistemsDrive, dlcsMax;

    public Drive() {
        this.narratives = 0;
        this.levels = 0;
        this.sprites = 0;
        this.sistems = 0;
        this.dlcs = 0;
        this.narrativesMax = 25;
        this.levelsMax= 20;
        this.spritesMax = 55;
        this.dlcsMax = 35;
    }
    
    public void writeNarrative(){
        try {
            // Before producer can produce an item, it must acquire a permit from semProd
            semDrive.acquire();
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
  
        // producer producing an item
        narratives++;
  
        System.out.println("Producer produced narrative : " + narratives + levels);
        semDrive.release();
        // After producer produces the item,
        // it releases semCon to notify consumer
        //semCon.release();
    }
    
    public void writeLevel(){
        try {
            // Before producer can produce an item, it must acquire a permit from semProd
            semDrive.acquire();
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
  
        // producer producing an item
        levels++;
  
        System.out.println("Producer produced level : " + narratives + levels);
        semDrive.release();
  
        // After producer produces the item,
        // it releases semCon to notify consumer
        //semCon.release();
    }
}
