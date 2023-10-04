package proyecto1;

import java.util.concurrent.Semaphore;

public class Drive {
    Specifications specs;
    
    static Semaphore semDrive = new Semaphore(1);
    
    static Semaphore semCurrentNarratives = new Semaphore(0);
    static Semaphore semCurrentLevels = new Semaphore(0);
    static Semaphore semCurrentDLCs = new Semaphore(0);
    
    static Semaphore semMaxNarratives = new Semaphore(25);
    static Semaphore semMaxLevels = new Semaphore(20);
    static Semaphore semMaxDLCs = new Semaphore(10);
     
    int narratives, levels, sprites, sistems, dlcs;
    int narrativesMax, levelsMax, spritesMax, sistemsDrive, dlcsMax;
    
    int games, gamesWithDLC, gamesBeforeDLC;

    public Drive(Specifications specs) {
        this.specs = specs;
        this.narratives = 0;
        this.levels = 0;
        this.sprites = 0;
        this.sistems = 0;
        this.dlcs = 0;
        this.narrativesMax = 25;
        this.levelsMax= 20;
        this.spritesMax = 55;
        this.dlcsMax = 35;
        
        this.games = 0; 
        this.gamesWithDLC = 0;
        this.gamesBeforeDLC = 0;
    }
    
    public void writeNarrative(){
        try {
            semMaxNarratives.acquire();
            semDrive.acquire();
            narratives++;
            System.out.println("Producer produced narrative : " + narratives + " " + levels);
            semDrive.release();
            semCurrentNarratives.release();
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }
    
    public void writeLevel(){
        try {
            semMaxLevels.acquire();
            semDrive.acquire();
            levels++;
            System.out.println("Producer produced level : " + narratives + " " + levels);
            semDrive.release();
            semCurrentLevels.release();
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }
    
    public void writeDLC(){
        try {
            semMaxDLCs.acquire();
            semDrive.acquire();
            dlcs++;
            System.out.println("Producer produced DLC : " + dlcs);
            semDrive.release();
            semCurrentDLCs.release();
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }
    
    public void getResources(){
        try {
            for(int i=0; i < specs.narratives; i++)
                semCurrentNarratives.acquire();
            for(int i=0; i < specs.levels; i++)
                semCurrentLevels.acquire();
            System.out.println("Recursos listos");
            semDrive.acquire();
            narratives -= specs.narratives;
            levels -= specs.levels;
            System.out.println("Integrator is ready to make game: " + narratives + " " + levels);
            semDrive.release();
            
            for(int i=0; i < specs.narratives; i++)
                semMaxNarratives.release();
            for(int i=0; i < specs.levels; i++)
                semMaxLevels.release();         
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }
    
    public void addGame(){
        try {
           semDrive.acquire();
           if(gamesBeforeDLC != 0 && gamesBeforeDLC % specs.gamesBeforeDlcs == 0){
               // Temporaly release the drive semaphore to wait for the dlcs
               semDrive.release();
               for(int i=0; i < specs.dlcs; i++)
                    semCurrentDLCs.acquire();
               
               // Adquire the drive lock again before modifying the values
               semDrive.acquire();
               gamesWithDLC++;
               dlcs -= specs.dlcs;
               gamesBeforeDLC = 0;
               
               for(int i=0; i < specs.dlcs; i++)
                semMaxDLCs.release();
               
               System.out.println("Game with DLC ready! " + games + " " + gamesWithDLC);
           } else {
               games++;
               gamesBeforeDLC++;
               System.out.println("Game ready! " + games + " " + gamesWithDLC);
           }

           semDrive.release();
           }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }     
    }
}
